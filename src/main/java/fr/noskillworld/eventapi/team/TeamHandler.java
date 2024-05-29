package fr.noskillworld.eventapi.team;

import fr.noskillworld.eventapi.team.exception.PlayerNotInTeamException;
import fr.noskillworld.eventapi.team.exception.TeamNotExistsException;
import org.bukkit.entity.Player;

import java.util.List;

public interface TeamHandler {

    /**
     * Get the players in a team by id
     *
     * @return A list of players
     */
    List<Player> getPlayersInTeam(int id) throws TeamNotExistsException;

    /**
     * Get the players in a team by name
     *
     * @return A list of players
     */
    List<Player> getPlayersInTeam(String name) throws TeamNotExistsException;

    /**
     * Returns the team of a chosen player
     *
     * @param player The target player
     * @return The team of the player
     */
    Team getPlayerTeam(Player player) throws PlayerNotInTeamException;

    /**
     * Sets a player into a specific team
     *
     * @param player The player to set in a team
     * @param team   The team to set the player in
     */
    void setPlayerTeam(Player player, Team team) throws TeamNotExistsException;

    /**
     * Distributes players into a certain amount of teams
     *
     * @param count The wanted team count
     */
    void distributePlayersIntoTeams(int count);

    /**
     * Creates a new empty team
     *
     * @param id The unique id of the team to create
     * @return The created team
     */
    Team createTeam(int id);

    /**
     * Deletes a team by its id
     *
     * @param id The id of the team to delete
     */
    void deleteTeam(int id) throws TeamNotExistsException;
}
