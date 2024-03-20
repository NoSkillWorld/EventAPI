package fr.noskillworld.eventapi.team;

import org.bukkit.entity.Player;

import java.util.List;

public class Team {

    private String teamName;
    private final int teamId;
    private final List<Player> teamPlayers;

    public Team(String name, int id, List<Player> players) {
        teamName = name;
        teamId = id;
        teamPlayers = players;
    }

    public List<Player> getPlayers() {
        return teamPlayers;
    }

    public String getName() {
        if (teamName == null) {
            return "Team " + teamId;
        }
        return teamName;
    }

    public void setName(String name) {
        teamName = name;
    }

    public int getTeamId() {
        return teamId;
    }
}
