package fr.noskillworld.eventapi.command;

import fr.noskillworld.eventapi.EventAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EventCommand implements CommandExecutor {

    private final EventAPI eventAPI;

    public EventCommand(EventAPI api) {
        this.eventAPI = api;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player) {
            if (!player.hasPermission("event.admin")) {
                player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande !");
                return true;
            }
            if (args.length >= 1) {
                switch (args[0]) {
                    case "start":
                        eventAPI.getEventHandler().startEvent(false);
                        break;
                    case "forcestart":
                        eventAPI.getEventHandler().startEvent(true);
                        break;
                    case "end":
                        eventAPI.getEventHandler().endEvent(false);
                        break;
                    case "forceend":
                        eventAPI.getEventHandler().endEvent(true);
                        break;
                    case "setspawn":
                        eventAPI.setSpawnLocation(player.getLocation());
                        break;
                    case "statut":
                        player.sendMessage("Statut de l'évent: " + eventAPI.getEventHandler().getEventState());
                        break;
                    default:
                        break;
                }
            } else {
                player.sendMessage("oui bon");
            }
        }
        return true;
    }
}
