package fr.noskillworld.eventapi.team.impl;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.team.Team;
import fr.noskillworld.eventapi.team.TeamHandler;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamHandlerImpl implements TeamHandler {

    private final Map<Player, Team> playerTeam;
    private final List<Team> teams;

    public TeamHandlerImpl() {
        playerTeam = new HashMap<>();
        teams = EventAPI.getEventHandler().getTeams();
    }

    @Override
    public List<Player> getPlayersInTeam(int id) {
        Team team = getTeamById(id);

        if (team == null) {
            return null;
        }
        return team.getPlayers();
    }

    @Override
    public List<Player> getPlayersInTeam(String name) {
        Team team = getTeamByName(name);

        if (team == null) {
            return null;
        }
        return team.getPlayers();
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
    public void deleteTeam(int id) {
        Team team = getTeamById(id);

        if (team == null) {
            return;
        }
        //Do something
    }

    @Override
    public void setPlayerTeam(Player player, Team team) {
        if (!teams.contains(team)) {
            //Do something
            return;
        }
        if (playerTeam.get(player) != null) {
            playerTeam.replace(player, team);
        } else {
            playerTeam.putIfAbsent(player, team);
        }
    }

    @Override
    public void distributePlayersIntoTeams(int count) {
        //Do something
    }

    private @Nullable Team getTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    private @Nullable Team getTeamById(int id) {
        for (Team team : teams) {
            if (team.getTeamId() == id) {
                return team;
            }
        }
        return null;
    }
}
