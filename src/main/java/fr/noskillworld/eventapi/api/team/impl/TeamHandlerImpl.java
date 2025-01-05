package fr.noskillworld.eventapi.api.team.impl;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.team.Team;
import fr.noskillworld.eventapi.api.team.TeamHandler;
import fr.noskillworld.eventapi.api.team.exception.PlayerNotInTeamException;
import fr.noskillworld.eventapi.api.team.exception.TeamNotExistsException;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamHandlerImpl implements TeamHandler {

    private final EventAPI instance;

    private final Map<Player, Team> playerTeam;
    private final List<Team> teams;

    public TeamHandlerImpl(EventAPI instance) {
        this.instance = instance;

        playerTeam = new HashMap<>();
        teams = new ArrayList<>();
    }

    @Override
    public Map<Player, Team> getPlayerTeamMap() {
        return playerTeam;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
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
    public Team getTeamById(int id) throws TeamNotExistsException {
        for (Team team : teams) {
            if (team.getTeamId() == id) {
                return team;
            }
        }
        throw new TeamNotExistsException("Team avec l'id " + id + " non trouvée.");
    }

    @Override
    public Team getTeamByName(String name) throws TeamNotExistsException {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        throw new TeamNotExistsException("Team '" + name + "' non trouvée.");
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
    public void setPlayerTeam(Player player, Team team) {
        if (team == null) {
            playerTeam.get(player).getPlayers().remove(player);
            playerTeam.put(player, null);
            return;
        }
        if (playerTeam.get(player) != null) {
            playerTeam.get(player).getPlayers().remove(player);
            playerTeam.replace(player, team);
        } else {
            playerTeam.putIfAbsent(player, team);
        }
        team.getPlayers().add(player);
    }

    @Override
    public void distributePlayersIntoTeams() {
        List<Player> participants = instance.getEventHandler().getParticipants();
        int teamCount = instance.getEventConfig().getMinTeams();
        Team current;
        int teamId = 0;

        if (teamCount == 0 || teamCount > participants.size()) {
            teamCount = participants.size();
        }
        if (!teams.isEmpty() || !playerTeam.isEmpty()) {
            teams.clear();
            playerTeam.clear();
        }

        for (Player p : participants) {
            try {
                current = getTeamById(teamId);
            } catch (TeamNotExistsException e) {
                current = createTeam(teamId);
            }
            current.getPlayers().add(p);
            playerTeam.put(p, current);
            teamId = (teamId + 1) % teamCount;
        }
    }
}
