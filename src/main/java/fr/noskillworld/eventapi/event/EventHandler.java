package fr.noskillworld.eventapi.event;

import fr.noskillworld.eventapi.team.Team;
import org.bukkit.entity.Player;

import java.util.List;

public interface EventHandler {

    /**
     * Initialize the event
     *
     * @param players the players already present in the game
     */
    void init(List<Player> players);

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
