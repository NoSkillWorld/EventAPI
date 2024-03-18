package fr.noskillworld.eventapi;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.utils.Credentials;
import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.Location;

public class EventAPI {

    private Location spawnLocation;

    private static EventAPI instance;

    private static NSWAPI nswapi;

    public EventAPI() {
        instance = this;

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

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location location) {
        spawnLocation = location;
    }
}
