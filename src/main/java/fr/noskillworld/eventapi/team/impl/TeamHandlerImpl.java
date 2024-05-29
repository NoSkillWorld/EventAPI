package fr.noskillworld.eventapi.team.impl;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.team.Team;
import fr.noskillworld.eventapi.team.TeamHandler;
import fr.noskillworld.eventapi.team.exception.PlayerNotInTeamException;
import fr.noskillworld.eventapi.team.exception.TeamNotExistsException;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
    public List<Player> getPlayersInTeam(int id) throws TeamNotExistsException {
        Team team;
        try {
            team = getTeamById(id);
        } catch (TeamNotExistsException e) {
            throw new TeamNotExistsException("La team n'existe pas.");
        }
        return team.getPlayers();
    }

    @Override
    public List<Player> getPlayersInTeam(String name) throws TeamNotExistsException {
        Team team;
        try {
            team = getTeamByName(name);
        } catch (TeamNotExistsException e) {
            throw new TeamNotExistsException("La team n'existe pas.");
        }
        return team.getPlayers();
    }

    @Override
    public Team getPlayerTeam(Player player) throws PlayerNotInTeamException {
        for (Team team : teams) {
            if (team.getPlayers().contains(player)) {
                return team;
            }
        }
        throw new PlayerNotInTeamException("Le joueur n'est dans aucune team.");
    }

    @Override
    public Team createTeam(int id) {
        List<Player> players = new ArrayList<>();
        Team team = new Team(null, id, players);
        teams.add(team);
        return team;
    }

    @Override
    public void deleteTeam(int id) throws TeamNotExistsException {
        Team team;

        try {
            team = getTeamById(id);
        } catch (TeamNotExistsException e) {
            throw new TeamNotExistsException("La team n'existe pas.");
        }
        teams.remove(team);
    }

    @Override
    public void setPlayerTeam(Player player, Team team) throws TeamNotExistsException {
        if (!teams.contains(team)) {
            throw new TeamNotExistsException("La team n'existe pas.");
        }
        if (playerTeam.get(player) != null) {
            playerTeam.replace(player, team);
        } else {
            playerTeam.putIfAbsent(player, team);
        }
    }

    @Override
    public void distributePlayersIntoTeams(int count) {
        List<Player> participants = EventAPI.getEventHandler().getParticipants();
        Team current;
        int teamId = 0;

        if (count == 0 || count > participants.size()) {
            count = participants.size();
        }

        for (Player p : participants) {
            try {
                current = getTeamById(teamId);
            } catch (TeamNotExistsException e) {
                current = createTeam(teamId);
            }
            playerTeam.put(p, current);
            teamId = (teamId + 1) % count;
        }
    }

    private @NotNull Team getTeamByName(String name) throws TeamNotExistsException {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        throw new TeamNotExistsException("Team '" + name + "' non trouvée.");
    }

    private @NotNull Team getTeamById(int id) throws TeamNotExistsException {
        for (Team team : teams) {
            if (team.getTeamId() == id) {
                return team;
            }
        }
        throw new TeamNotExistsException("Team avec l'id " + id + " non trouvée.");
    }
}
