package fr.noskillworld.eventapi.event.impl;

import fr.noskillworld.eventapi.event.EventHandler;
import fr.noskillworld.eventapi.event.EventState;
import fr.noskillworld.eventapi.team.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandlerImpl implements EventHandler {

    private final List<Player> participants;
    private final List<Team> teams;

    private EventState eventState;

    public EventHandlerImpl(List<Player> players) {
        teams = new ArrayList<>();

        eventState = EventState.PENDING;
        participants = players;
    }

    @Override
    public List<Player> getParticipants() {
        return participants;
    }

    @Override
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public void startEvent() {
        eventState = EventState.STARTING;
        //Do something
    }

    @Override
    public void endEvent() {
        eventState = EventState.ENDED;
        //Do something
    }

    @Override
    public void abortEvent() {
        eventState = EventState.ABORTED;
        //Do something
    }

    @Override
    public EventState getEventState() {
        return eventState;
    }
}
