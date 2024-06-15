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
                        try {
                            Team team = eventAPI.getTeamHandler().getTeamById(Integer.parseInt(args[1]));
                            eventAPI.getTeamHandler().setPlayerTeam(player, team);
                        } catch (TeamNotExistsException e) {
                            player.sendMessage(e.getMessage());
                        }
                    }
                    case "leave" -> eventAPI.getTeamHandler().setPlayerTeam(player, null);
                    case "list" -> {
                        if (!player.hasPermission("event.admin")) {
                            player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande.");
                            return true;
                        }
                        getTeamList(player);
                    }
                    case "init" -> {
                        if (!player.hasPermission("event.admin")) {
                            player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande.");
                            return true;
                        }
                        int teamCount = 0;
                        if (args.length >= 2) {
                            teamCount = Integer.parseInt(args[1]);
                        }
                        eventAPI.getTeamHandler().distributePlayersIntoTeams(teamCount);
                    }
                }
            } else {
                getTeamList(player);
            }
        }
        return true;
    }

    private void getTeamList(@NotNull Player player) {
        if (eventAPI.getTeamHandler().getTeams().isEmpty()) {
            player.sendMessage("§fIl n'y a actuellement aucune team enregistrée.");
            return;
        }
        player.sendMessage("§fliste des teams:");

        for (Team team : eventAPI.getTeamHandler().getTeams()) {
            player.sendMessage("§6§l#" + (team.getTeamId() + 1) + " §8(§3" + team.getName() + "§8)");
            for (Player p : team.getPlayers()) {
                player.sendMessage("    §8- §3" + p.getName());
            }
        }
    }
}
