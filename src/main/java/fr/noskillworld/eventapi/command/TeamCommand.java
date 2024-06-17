package fr.noskillworld.eventapi.command;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.team.Team;
import fr.noskillworld.eventapi.api.team.exception.TeamNotExistsException;
import fr.noskillworld.eventapi.utils.MessageManager;
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
                            player.sendMessage(MessageManager.NO_PERMISSION.getMessage());
                            return true;
                        }
                        getTeamList(player);
                    }
                    case "init" -> {
                        if (!player.hasPermission("event.admin")) {
                            player.sendMessage(MessageManager.NO_PERMISSION.getMessage());
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
            player.sendMessage(MessageManager.NO_TEAM_REGISTERED.getMessage());
            return;
        }
        player.sendMessage(MessageManager.TEAM_LIST_HEAD.getMessage());

        for (Team t : eventAPI.getTeamHandler().getTeams()) {
            player.sendMessage(String.format(MessageManager.TEAM_LIST.getRawMessage(), t.getTeamId() + 1, t.getName()));
            for (Player p : t.getPlayers()) {
                player.sendMessage(String.format(MessageManager.TEAM_PLAYER_LIST.getRawMessage(), p.getName()));
            }
        }
    }
}
