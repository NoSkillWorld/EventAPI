package fr.noskillworld.eventapi.api.team;

import fr.noskillworld.eventapi.api.team.exception.PlayerNotInTeamException;
import fr.noskillworld.eventapi.api.team.exception.TeamNotExistsException;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface TeamHandler {

    /**
     * Get the player team map
     *
     * @return A Map containing the player associated to a team
     */
    Map<Player, Team> getPlayerTeamMap();

    /**
     * Get the teams currently registered
     *
     * @return A list of the registered teams
     */
    List<Team> getTeams();

    /**
     * Get the players in a team by id
     *
     * @return A list of players
     * @throws TeamNotExistsException if the team not exists
     */
    List<Player> getPlayersInTeam(int id) throws TeamNotExistsException;

    /**
     * Get the players in a team by name
     *
     * @return A list of players
     * @throws TeamNotExistsException if the team not exists
     */
    List<Player> getPlayersInTeam(String name) throws TeamNotExistsException;

    /**
     * Get a team by its unique ID
     *
     * @param id the id of the team to find
     * @return A team
     * @throws TeamNotExistsException if the team not exists
     */
    Team getTeamById(int id) throws TeamNotExistsException;

    /**
     * Get a team by its name
     *
     * @param name the name of the team to find
     * @return A team
     * @throws TeamNotExistsException if the team not exists
     */
    Team getTeamByName(String name) throws TeamNotExistsException;

    /**
     * Returns the team of a chosen player
     *
     * @param player The target player
     * @return The team of the player
     * @throws PlayerNotInTeamException if the player is not in a team
     */
    Team getPlayerTeam(Player player) throws PlayerNotInTeamException;

    /**
     * Sets a player into a specific team
     *
     * @param player The player to set in a team
     * @param team   The team to set the player in
     */
    void setPlayerTeam(Player player, Team team);

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
     * @throws TeamNotExistsException if the team not exists
     */
    void deleteTeam(int id) throws TeamNotExistsException;
}
