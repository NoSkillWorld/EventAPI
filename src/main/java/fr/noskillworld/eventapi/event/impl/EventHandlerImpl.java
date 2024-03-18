package fr.noskillworld.eventapi.event.impl;

import fr.noskillworld.eventapi.event.EventHandler;
import fr.noskillworld.eventapi.event.EventState;
import fr.noskillworld.eventapi.event.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandlerImpl implements EventHandler {

    private final List<Player> participants;

    private final List<Team> teams;
    private final Map<Player, Team> playerTeam;

    private EventState eventState;

    public EventHandlerImpl(List<Player> players) {
        teams = new ArrayList<>();
        playerTeam = new HashMap<>();

        eventState = EventState.PENDING;
        participants = players;
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
    public List<Player> getPlayersInTeam(int id) {
        for (Team team : teams) {
            if (team.getTeamId() == id) {
                return team.getPlayers();
            }
        }
        return null;
    }

    @Override
    public List<Player> getPlayersInTeam(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team.getPlayers();
            }
        }
        return null;
    }

    @Override
    public Team getPlayerTeam(Player player) {
        for (Team team : teams) {
            if (team.getPlayers().contains(player)) {
                return team;
            }
        }
        return null;
    }

    @Override
    public Team createTeam() {
        List<Player> players = new ArrayList<>();
        Team team = new Team(null, teams.size(), players);
        teams.add(team);
        return team;
    }

    @Override
    public void setPlayerTeam(Player player, Team team) {
        if (!teams.contains(team)) {
            // Do something
            return;
        }
        if (playerTeam.get(player) != null) {
            playerTeam.replace(player, team);
        } else {
            playerTeam.putIfAbsent(player, team);
        }
    }

    @Override
    public void startEvent() {
        eventState = EventState.STARTING;
        //Do something
    }

    @Override
    public void endEvent() {
        eventState = EventState.ENDED;
        //Do something
    }

    @Override
    public void abortEvent() {
        eventState = EventState.ABORTED;
        //Do something
    }

    @Override
    public EventState getEventState() {
        return eventState;
    }
}
