package fr.noskillworld.eventapi.command.completion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EventTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("event")) {
            List<String> subCommands = new ArrayList<>();

            if (!commandSender.hasPermission("event.admin")) {
                return null;
            }
            if (args.length == 1) {
                subCommands.add("start");
                subCommands.add("end");
                subCommands.add("reset");
                subCommands.add("setspawn");
                subCommands.add("statut");
                return subCommands;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("reset")) {
                    return "force".lines().toList();
                }
            }
        }
        return null;
    }
}
