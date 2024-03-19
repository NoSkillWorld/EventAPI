package fr.noskillworld.eventapi;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.eventapi.event.impl.EventHandlerImpl;
import fr.noskillworld.eventapi.team.impl.TeamHandlerImpl;
import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Location;

public class EventAPI {

    private Location spawnLocation;

    private static EventAPI instance;
    private static EventHandlerImpl eventHandler;
    private static TeamHandlerImpl teamHandler;

    private static NSWAPI nswapi;

    public EventAPI() {
        instance = this;

        eventHandler = new EventHandlerImpl(null);
        teamHandler = new TeamHandlerImpl();
        setAPI();
    }

    private void setAPI() {
        Dotenv dotenv = Dotenv.configure()
                .directory("/env/")
                .filename(".env")
                .load();

        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        String name = dotenv.get("DB_NAME");

        nswapi = NSWAPI.create(new Credentials(user, password, name));
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
