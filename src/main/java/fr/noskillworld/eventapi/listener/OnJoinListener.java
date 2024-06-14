package fr.noskillworld.eventapi.listener;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.event.exception.EventStartedException;
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
            event.setJoinMessage("§8§l»§r §3§l" + player.getName() + " §fa rejoint l'évent !");
        } catch (EventStartedException e) {
            if (eventAPI.getEventHandler().isParticipating(player)) {
                event.setJoinMessage("§8§l»§r §3§l" + player.getName() + " §fs'est reconnecté.");
                return;
            }
            player.setGameMode(GameMode.SPECTATOR);
            event.setJoinMessage("§8§l»§r §3§l" + player.getName() + " §fest spectateur.");
        }
        if (eventAPI.getEventHandler().isEventStarted() && eventAPI.getEventHandler().isParticipating(player)) return;
        player.setInvulnerable(true);
        player.setCanPickupItems(false);
        if (eventAPI.getSpawnLocation() == null) return;
        player.teleport(eventAPI.getSpawnLocation());
    }
}
