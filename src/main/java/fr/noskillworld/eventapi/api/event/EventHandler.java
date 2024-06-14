package fr.noskillworld.eventapi.api.event;

import fr.noskillworld.eventapi.api.event.exception.EventStartedException;
import fr.noskillworld.eventapi.api.team.Team;
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
     * Returns the name set for the event
     *
     * @return A String (if null, default is "Event")
     */
    String getName();

    /**
     * Sets the name of the event
     *
     * @param name the name to set
     */
    void setName(String name);

    /**
     * Starts the event
     *
     * @param forceStart Force the start of the event or not
     */
    void startEvent(boolean forceStart);

    /**
     * Ends the event
     *
     * @param forceEnd Force the end of the event or not
     */
    void endEvent(boolean forceEnd);

    /**
     * Resets the event
     *
     * @param forceReset Force the reset of the event or not
     */
    void resetEvent(boolean forceReset);

    /**
     * adds a participant to the event
     *
     * @param player the player to add into the event
     */
    void addParticipant(Player player) throws EventStartedException;

    /**
     * removes a participant to the event
     *
     * @param player the player to remove from the event
     */
    void removeParticipant(Player player) throws EventStartedException;

    /**
     * Checks if the player is participating at the event
     *
     * @param player the player to check
     * @return true if the player is participating, false if not
     */
    boolean isParticipating(Player player);

    /**
     * Checks if the event has started or not
     *
     * @return true if the event has started, returns false if not
     */
    boolean isEventStarted();

    /**
     * Returns the current state of the event<br>
     * States available:<br>
     * PENDING | STARTING | STARTED | ENDED
     *
     * @return The state of the event
     */
    EventState getEventState();

    /**
     * Overrides the current state with a new one
     *
     * @param state The new state to set
     */
    void setState(EventState state);
}
