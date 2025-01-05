package fr.noskillworld.eventapi;

import fr.noskillworld.api.NSWAPI;
import fr.noskillworld.api.gui.GuiManager;
import fr.noskillworld.api.utils.Credentials;
import fr.noskillworld.eventapi.api.event.EventConfig;
import fr.noskillworld.eventapi.api.event.impl.EventHandlerImpl;
import fr.noskillworld.eventapi.api.team.impl.TeamHandlerImpl;
import fr.noskillworld.eventapi.command.EventCommand;
import fr.noskillworld.eventapi.command.TeamCommand;
import fr.noskillworld.eventapi.command.completion.EventTabCompletion;
import fr.noskillworld.eventapi.command.completion.TeamTabCompletion;
import fr.noskillworld.eventapi.gui.TeamSelectGUI;
import fr.noskillworld.eventapi.gui.TestGUI;
import fr.noskillworld.eventapi.listener.InventoryClickListener;
import fr.noskillworld.eventapi.listener.OnJoinListener;
import fr.noskillworld.eventapi.listener.OnLeaveListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class EventAPI extends JavaPlugin {

    private static String prefix;

    private EventAPI instance;

    private boolean isSetup = false;
    private EventConfig eventConfig = null;

    private final EventHandlerImpl eventHandler;
    private final TeamHandlerImpl teamHandler;
    private final Logger logger;

    private static NSWAPI nswapi;

    public EventAPI() {
        instance = this;
        prefix = "§8[§3Event§8] §r";

        eventHandler = new EventHandlerImpl(this);
        teamHandler = new TeamHandlerImpl(this);
        logger = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        setAPI();

        //Register the spigot service provider
        getServer().getServicesManager().register(EventAPI.class, this, this, ServicePriority.High);

        //Register commands
        Objects.requireNonNull(this.getCommand("event")).setExecutor(new EventCommand(this));
        Objects.requireNonNull(this.getCommand("team")).setExecutor(new TeamCommand(this));

        //register TabCompleters
        Objects.requireNonNull(this.getCommand("event")).setTabCompleter(new EventTabCompletion());
        Objects.requireNonNull(this.getCommand("team")).setTabCompleter(new TeamTabCompletion());

        //Register GUIs
        registerGUIs();

        //Register listeners
        getServer().getPluginManager().registerEvents(new OnJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new OnLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);

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
        instance.getAPI().getDatabaseManager().getConnector().close();
        getServer().getServicesManager().unregisterAll(this);

        instance = null;
        logger.info(String.format("[%s] Plugin shut down successfully", getDescription().getName()));
    }

    public void setup(EventConfig eventConfig) {
        isSetup = true;
        this.eventConfig = eventConfig;
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

    public EventConfig getEventConfig() {
        return eventConfig;
    }

    public EventAPI getInstance() {
        return instance;
    }

    public NSWAPI getAPI() {
        return nswapi;
    }

    public EventHandlerImpl getEventHandler() {
        return eventHandler;
    }

    public TeamHandlerImpl getTeamHandler() {
        return teamHandler;
    }

    public static String getPrefix() {
        return prefix;
    }

    private void registerGUIs() {
        GuiManager guiManager = nswapi.getGuiManager();

        guiManager.addMenu(new TestGUI());
        guiManager.addMenu(new TeamSelectGUI());
    }

    public boolean isSetup() {
        if (eventConfig == null) return false;
        return isSetup;
    }
}
