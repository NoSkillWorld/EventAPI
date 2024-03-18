package fr.noskillworld.eventapi.event;

import org.bukkit.entity.Player;

import java.util.List;

public interface EventHandler {

    /**
     * Returns the participants of the event
     *
     * @return A list of players
     */
    List<Player> getParticipants();

    /**
     * Returns all the registered teams
     *
     * @return A list of teams
     */
    List<Team> getTeams();

    /**
     * Get the players in a team by id
     *
     * @return A list of players
     */
    List<Player> getPlayersInTeam(int id);

    /**
     * Get the players in a team by name
     *
     * @return A list of players
     */
    List<Player> getPlayersInTeam(String name);

    /**
     * Returns the team of a chosen player
     *
     * @param player The target player
     * @return The team of the player
     */
    Team getPlayerTeam(Player player);

    /**
     * Creates a new empty team
     *
     * @return The created team
     */
    Team createTeam();

    /**
     * Sets a player into a specific team
     *
     * @param player The player to set in a team
     * @param team The team to set the player in
     */
    void setPlayerTeam(Player player, Team team);

    /**
     * Starts the event
     */
    void startEvent();

    /**
     * Ends the event
     */
    void endEvent();

    /**
     * Aborts the event
     */
    void abortEvent();

    /**
     * Returns the current state of the event<br>
     * States available:<br>
     * PENDING | STARTING | STARTED | ENDED | ABORTED
     *
     * @return The state of the event
     */
    EventState getEventState();
}
