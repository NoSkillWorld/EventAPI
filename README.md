# EventAPI

## Introduction:

A simple tool belt for easier management of our *awesome* events with team handling and game management.

## Getting started:

Add those to your pom.xml
#### The repository:
```xml
<repositories>
    <repository>
        <id>repo-nsw</id>
        <url>https://nexus.noskillworld.fr/repository/EventAPI/</url>
    </repository>
    ...
</repositories>
```

#### The dependency:
```xml
<dependencies>
    <dependency>
        <groupId>fr.noskillworld.eventapi</groupId>
        <artifactId>eventapi</artifactId>
        <version>0.0.1</version>
        <scope>provided</scope>
    </dependency>
    ...
</dependencies>
```

## Registering the API:

Add this code in you onEnable() spigot plugin method:
```java
if (getServer().getPluginManager().getPlugin("EventAPI") == null) {
    logger.warning("No EventAPI found! Disabling the plugin.");
    getPluginLoader().disablePlugin(this);
    return;
}
RegisteredServiceProvider<EventAPI> provider = getServer().getServicesManager().getRegistration(EventAPI.class);
if (provider != null) {
    eventAPI = provider.getProvider();
}
```

Exemple:

```java
import fr.noskillworld.eventapi.EventAPI;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Event extends JavaPlugin {

    private EventAPI eventAPI;
    private Event instance;

    private final Logger logger;

    public Event() {
        instance = this;
        logger = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        setAPI();
        logger.info(String.format("[%s] Plugin loaded successfully", getDescription().getName()));
    }

    @Override
    public void onDisable() {
        instance = null;
        logger.info(String.format("[%s] Plugin shut down successfully", getDescription().getName()));
    }

    private void setAPI() {
        if (getServer().getPluginManager().getPlugin("EventAPI") == null) {
            logger.warning("No EventAPI found! Disabling the plugin.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<EventAPI> provider = getServer().getServicesManager().getRegistration(EventAPI.class);
        if (provider != null) {
            eventAPI = provider.getProvider();
        }
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

    public Event getInstance() {
        return instance;
    }
}
```

And don't forget to add the EventAPI as a dependency in your `plugin.yml` file of your plugin:
```yml
depend: [ EventAPI ]
```

## Using the API:

### Event setup

First, you will need to set up the event you want to create using the EventAPI.setup() method:
```java
eventAPI.setup(new EventConfig(
                new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 90, 0), //The spawn location
                "Test", //The name of your event
                4, //The number of player per team
                2, //The number of teams
                TeamSelectMode.CHOOSE //The method for putting players into teams (SOLO, RANDOM & CHOOSE are the possible values)
        ));
```

Now, it should look like this:
```java
public class Event extends JavaPlugin {

    private EventAPI eventAPI;
    private Event instance;

    private final Logger logger;

    public Event() {
        instance = this;
        logger = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        setAPI();

        eventAPI.setup(new EventConfig(
                new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 90, 0),
                "Test",
                4,
                2,
                TeamSelectMode.CHOOSE
        ));

        logger.info(String.format("[%s] Plugin loaded successfully", getDescription().getName()));
    }

    @Override
    public void onDisable() {
        instance = null;
        logger.info(String.format("[%s] Plugin shut down successfully", getDescription().getName()));
    }

    private void setAPI() {
        if (getServer().getPluginManager().getPlugin("EventAPI") == null) {
            logger.warning("No EventAPI found! Disabling the plugin.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<EventAPI> provider = getServer().getServicesManager().getRegistration(EventAPI.class);
        if (provider != null) {
            eventAPI = provider.getProvider();
        }
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

    public Event getInstance() {
        return instance;
    }
}
```

### Listeners

The API provides a custom event listener, called `EventStateChangeEvent` triggered every time the event state changes:
```java
@EventHandler
public void onEventStateChange(@NotNull EventStateChangeEvent event) {
    EventState state = event.getEventState();

    switch (state) {
        case PENDING -> System.out.println("DEBUG: event pending");
        case STARTING -> System.out.println("DEBUG: event starting");
        case STARTED -> System.out.println("DEBUG: event started");
        case ENDED -> System.out.println("DEBUG: event ended");
    }
}
```

Don't forget to register the event listener by calling the `registerEvents()` method in your `onEnable()`:
```java
getServer().getPluginManager().registerEvents(new OnEventStateChange(this), this);
```

Finally, you should have this:
```java
public class Event extends JavaPlugin implements Listener {

    private EventAPI eventAPI;
    private Event instance;

    private final Logger logger;

    public Event() {
        instance = this;
        logger = Logger.getLogger("Minecraft");
    }

    @Override
    public void onEnable() {
        setAPI();

        eventAPI.setup(new EventConfig(
                new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 90, 0),
                "Test",
                4,
                2,
                TeamSelectMode.CHOOSE
        ));

        logger.info(String.format("[%s] Plugin loaded successfully", getDescription().getName()));
    }

    @Override
    public void onDisable() {
        instance = null;
        logger.info(String.format("[%s] Plugin shut down successfully", getDescription().getName()));
    }

    @EventHandler
    public void onEventStateChange(@NotNull EventStateChangeEvent event) {
        EventState state = event.getEventState();

        switch (state) {
            case PENDING -> System.out.println("DEBUG: event pending");
            case STARTING -> System.out.println("DEBUG: event starting");
            case STARTED -> System.out.println("DEBUG: event started");
            case ENDED -> System.out.println("DEBUG: event ended");
        }
    }

    private void setAPI() {
        if (getServer().getPluginManager().getPlugin("EventAPI") == null) {
            logger.warning("No EventAPI found! Disabling the plugin.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<EventAPI> provider = getServer().getServicesManager().getRegistration(EventAPI.class);
        if (provider != null) {
            eventAPI = provider.getProvider();
        }
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

    public Event getInstance() {
        return instance;
    }
}
```