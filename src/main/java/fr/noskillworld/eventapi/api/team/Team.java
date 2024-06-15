package fr.noskillworld.eventapi.api.team;

import org.bukkit.entity.Player;

import java.util.List;

public class Team {

    private String teamName;
    private final int teamId;
    private final List<Player> teamPlayers;
    private TeamStatus status;

    public Team(String name, int id, List<Player> players) {
        teamName = name;
        teamId = id;
        teamPlayers = players;
        status = TeamStatus.PENDING;
    }

    public List<Player> getPlayers() {
        return teamPlayers;
    }

    public String getName() {
        if (teamName == null) {
            return "Team " + (teamId + 1);
        }
        return teamName;
    }

    public void setName(String name) {
        teamName = name;
    }

    public int getTeamId() {
        return teamId;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }
}
