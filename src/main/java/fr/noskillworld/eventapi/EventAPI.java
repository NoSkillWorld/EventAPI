package fr.noskillworld.eventapi;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.eventapi.event.impl.EventHandlerImpl;
import fr.noskillworld.eventapi.team.impl.TeamHandlerImpl;
import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class EventAPI extends JavaPlugin {

    private Location spawnLocation;

    private static EventAPI instance;
    private static EventHandlerImpl eventHandler;
    private static TeamHandlerImpl teamHandler;
    private static Logger logger;

    private static NSWAPI nswapi;

    public EventAPI() {
        instance = this;

        eventHandler = new EventHandlerImpl();
        teamHandler = new TeamHandlerImpl();
        logger = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        setAPI();

        logger.info(String.format("[%s] Plugin loaded successfully", getDescription().getName()));
        logger.info("""
                \s
                  ______               _            _____ _____\s
                 |  ____|             | |     /\\   |  __ \\_   _|
                 | |____   _____ _ __ | |_   /  \\  | |__) || | \s
                 |  __\\ \\ / / _ \\ '_ \\| __| / /\\ \\ |  ___/ | |\s
                 | |___\\ V /  __/ | | | |_ / ____ \\| |    _| |_\s
                 |______\\_/ \\___|_| |_|\\__/_/    \\_\\_|   |_____|
                """);
    }

    @Override
    public void onDisable() {
        instance = null;
        logger.info(String.format("[%s] Plugin shut down successfully", getDescription().getName()));
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
