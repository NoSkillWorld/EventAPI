package fr.noskillworld.eventapi.listener;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.event.EventConfig;
import fr.noskillworld.eventapi.api.event.exception.EventStartedException;
import fr.noskillworld.eventapi.utils.MessageManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class OnJoinListener implements Listener {

    private final EventAPI eventAPI;

    public OnJoinListener(EventAPI api) {
        this.eventAPI = api;
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setInvulnerable(false);
        player.setCanPickupItems(true);

        try {
            eventAPI.getEventHandler().addParticipant(player);
            player.setGameMode(GameMode.ADVENTURE);
            event.setJoinMessage(String.format(MessageManager.PLAYER_JOIN.getInfoMessage(), player.getName()));
        } catch (EventStartedException e) {
            if (eventAPI.getEventHandler().isParticipating(player)) {
                event.setJoinMessage(String.format(MessageManager.PLAYER_RECONNECT.getInfoMessage(), player.getName()));
                return;
            }
            player.setGameMode(GameMode.SPECTATOR);
            event.setJoinMessage(String.format(MessageManager.PLAYER_SPECTATES.getInfoMessage(), player.getName()));
        }
        if (eventAPI.getEventHandler().isEventStarted() && eventAPI.getEventHandler().isParticipating(player)) return;
        player.setInvulnerable(true);
        player.setCanPickupItems(false);

        if (eventAPI.isSetup()) {
            if (eventAPI.getEventConfig().getSpawnLocation() == null) return;
            player.teleport(eventAPI.getEventConfig().getSpawnLocation());
        } else {
            player.sendMessage(MessageManager.EVENT_NOT_SETUP.getWarnMessage());
            eventAPI.setup(new EventConfig());
        }
    }
}
