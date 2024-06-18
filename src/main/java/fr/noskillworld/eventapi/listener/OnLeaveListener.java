package fr.noskillworld.eventapi.listener;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.utils.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class OnLeaveListener implements Listener {

    private final EventAPI eventAPI;

    public OnLeaveListener(EventAPI api) {
        this.eventAPI = api;
    }

    @EventHandler
    public void onPlayerLeave(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (eventAPI.getEventHandler().isEventStarted()) {
            event.setQuitMessage(String.format(MessageManager.PLAYER_DISCONNECT.getInfoMessage(), player.getName()));
        } else {
            eventAPI.getEventHandler().removeParticipant(player);
            event.setQuitMessage(String.format(MessageManager.PLAYER_LEAVE.getInfoMessage(), player.getName()));
        }
    }
}
