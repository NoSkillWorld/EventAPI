package fr.noskillworld.eventapi;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.eventapi.event.impl.EventHandlerImpl;
import fr.noskillworld.eventapi.team.impl.TeamHandlerImpl;
import org.bukkit.Location;

public class EventAPI {

    private Location spawnLocation;

    private static EventAPI instance;
    private static EventHandlerImpl eventHandler;
    private static TeamHandlerImpl teamHandler;

    private static NSWAPI nswapi;

    public EventAPI(Credentials credentials) {
        instance = this;

        eventHandler = new EventHandlerImpl();
        teamHandler = new TeamHandlerImpl();
        setAPI(credentials);
    }

    private void setAPI(Credentials credentials) {
        nswapi = NSWAPI.create(credentials);
    }

    public static EventAPI getInstance() {
        return instance;
    }

    public static NSWAPI getAPI() {
        return nswapi;
    }

    public static EventHandlerImpl getEventHandler() {
        return eventHandler;
    }

    public static TeamHandlerImpl getTeamHandler() {
        return teamHandler;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location location) {
        spawnLocation = location;
    }
}
