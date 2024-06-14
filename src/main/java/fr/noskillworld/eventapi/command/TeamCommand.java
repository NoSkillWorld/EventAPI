package fr.noskillworld.eventapi.command;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.team.Team;
import fr.noskillworld.eventapi.api.team.exception.TeamNotExistsException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    private final EventAPI eventAPI;

    public TeamCommand(EventAPI api) {
        this.eventAPI = api;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            if (args.length >= 1) {
                switch (args[0]) {
                    case "join" -> {
                        System.out.println("join command");
                        try {
                            Team team = eventAPI.getTeamHandler().getTeamById(Integer.parseInt(args[1]));
                            eventAPI.getTeamHandler().setPlayerTeam(player, team);
                        } catch (TeamNotExistsException e) {
                            player.sendMessage(e.getMessage());
                        }
                    }
                    case "leave" -> {
                        System.out.println("leave command");
                        eventAPI.getTeamHandler().setPlayerTeam(player, null);
                    }
                }
            } else {
                player.sendMessage("§fliste des teams:");

                for (Team team : eventAPI.getTeamHandler().getTeams()) {
                    player.sendMessage("§6§l#" + team.getTeamId() + "§f: §3" + team.getName());
                    for (Player p : team.getPlayers()) {
                        p.sendMessage("    §8- §3" + p.getName());
                    }
                }
            }
        }
        return true;
    }
}
