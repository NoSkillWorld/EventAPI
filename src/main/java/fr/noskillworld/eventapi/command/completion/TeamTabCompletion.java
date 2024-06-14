package fr.noskillworld.eventapi.command.completion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TeamTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("team")) {
            List<String> subCommands = new ArrayList<>();

            if (args.length == 1) {
                subCommands.add("join");
                subCommands.add("leave");
                return subCommands;
            }
        }
        return null;
    }
}
