# EventAPI

## Introduction:

A simple tool belt for easier management of our *awesome* events with team handling and game management.

## Getting started:

Add those to your pom.xml
#### The repository:
```xml
<repositories>
    <repository>
        <id>NSW</id>
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

## Registering the API :

Add this code in you onEnable() spigot plugin method
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

Exemple :

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