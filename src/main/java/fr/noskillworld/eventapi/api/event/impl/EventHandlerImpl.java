package fr.noskillworld.eventapi.api.event.impl;

import fr.noskillworld.eventapi.api.event.EventHandler;
import fr.noskillworld.eventapi.api.event.EventState;
import fr.noskillworld.eventapi.api.event.exception.EventStartedException;
import fr.noskillworld.eventapi.api.team.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerImpl implements EventHandler {

    private final List<Player> participants;
    private final List<Team> teams;

    private EventState eventState;

    public EventHandlerImpl() {
        participants = new ArrayList<>();
        teams = new ArrayList<>();

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
        eventState = EventState.STARTING;
        //Do something
    }

    @Override
    public void endEvent(boolean forceEnd) {
        eventState = EventState.ENDED;
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
}
