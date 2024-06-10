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
            boolean isForced = getForced(args);

            if (args.length >= 1) {
                switch (args[0]) {
                    case "start" -> eventAPI.getEventHandler().startEvent(isForced);
                    case "end" -> eventAPI.getEventHandler().endEvent(isForced);
                    case "reset" -> eventAPI.getEventHandler().resetEvent(isForced);
                    case "setspawn" -> eventAPI.setSpawnLocation(player.getLocation());
                    case "statut" -> player.sendMessage(eventAPI.getEventHandler().getEventState().getDescription());
                }
            } else {
                player.sendMessage("oui bon");
            }
        }
        return true;
    }

    private boolean getForced(String @NotNull [] args) {
        return args.length > 1 && args[1].equalsIgnoreCase("force");
    }
}
