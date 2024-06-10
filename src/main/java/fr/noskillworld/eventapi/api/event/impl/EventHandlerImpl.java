package fr.noskillworld.eventapi.api.event.impl;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.event.EventHandler;
import fr.noskillworld.eventapi.api.event.EventState;
import fr.noskillworld.eventapi.api.event.exception.EventStartedException;
import fr.noskillworld.eventapi.api.team.Team;
import fr.noskillworld.eventapi.event.EventStateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerImpl implements EventHandler {

    private final List<Player> participants;
    private final List<Team> teams;

    private EventState eventState;

    private final EventAPI eventAPI;

    public EventHandlerImpl(EventAPI api) {
        participants = new ArrayList<>();
        teams = new ArrayList<>();
        eventAPI = api;

        eventState = EventState.PENDING;
    }

    @Override
    public List<Player> getParticipants() {
        return participants;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public void startEvent(boolean forceStart) {
        if (isEventStarted()) return;

        setState(EventState.STARTING);
        if (forceStart) {
            setState(EventState.STARTED);
            return;
        }

        for (Player p : participants) {
            p.sendTitle("§3L'évent va commencer", "§7Tenez-vous prêts !", 0, 80, 40);
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }

        new BukkitRunnable() {
            int seconds = 10;

            @Override
            public void run() {
                if (seconds == 0) {
                    setState(EventState.STARTED);
                    for (Player p : participants) {
                        p.sendTitle("§3Début de l'évent", "§7Bon courage !", 0, 60, 40);
                        p.playSound(p, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    }
                    this.cancel();
                }
                if (seconds <= 5 && seconds > 0) {
                    for (Player p : participants) {
                        p.sendTitle("§4§l" + seconds, "", 0, 15, 5);
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    }
                }
                seconds--;
            }
        }.runTaskTimer(eventAPI, 0L, 20L);
    }

    @Override
    public void endEvent(boolean forceEnd) {
        if (!isEventStarted()) return;

        setState(EventState.ENDED);
        //Do something
    }

    @Override
    public void resetEvent(boolean forceReset) {
        if (isEventStarted()) return;

        setState(EventState.PENDING);
        //Do something
    }

    @Override
    public void addParticipant(Player player) throws EventStartedException {
        if (eventState != EventState.PENDING) {
            throw new EventStartedException("L'évent a déjà débuté !");
        }
        if (!isParticipating(player)) {
            participants.add(player);
        }
    }

    @Override
    public void removeParticipant(Player player) {
        participants.remove(player);
    }

    @Override
    public boolean isParticipating(Player player) {
        for (Player p : participants) {
            if (p.getName().equals(player.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEventStarted() {
        return eventState == EventState.STARTING || eventState == EventState.STARTED;
    }

    @Override
    public EventState getEventState() {
        return eventState;
    }

    @Override
    public void setState(EventState state) {
        eventState = state;
        Bukkit.getPluginManager().callEvent(new EventStateChangeEvent(eventState));
    }
}
