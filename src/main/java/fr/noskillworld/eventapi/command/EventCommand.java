package fr.noskillworld.eventapi.command;

import fr.noskillworld.eventapi.EventAPI;
import fr.noskillworld.eventapi.api.team.exception.PlayerNotInTeamException;
import fr.noskillworld.eventapi.utils.MessageManager;
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
                player.sendMessage(MessageManager.NO_PERMISSION.getMessage());
                return true;
            }
            boolean isForced = getForced(args);

            if (args.length >= 1) {
                switch (args[0]) {
                    case "start" -> eventAPI.getEventHandler().startEvent(isForced);
                    case "end" -> eventAPI.getEventHandler().endEvent(isForced);
                    case "reset" -> eventAPI.getEventHandler().resetEvent(isForced);
                    case "setspawn" -> eventAPI.setSpawnLocation(player.getLocation());
                    case "infos" -> {
                        int playerCount = eventAPI.getEventHandler().getParticipants().size();
                        String statusDesc = eventAPI.getEventHandler().getEventState().getDescription();

                        player.sendMessage(String.format(MessageManager.EVENT_STATUS.getMessage(), statusDesc, playerCount, getParticipants()));
                    }
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

    private @NotNull String getParticipants() {
        StringBuilder sb = new StringBuilder();

        for (Player p : eventAPI.getEventHandler().getParticipants()) {
            sb.append("    §8§l»§r ").append(p.getName());
            try {
                String teamName = eventAPI.getTeamHandler().getPlayerTeam(p).getName();
                sb.append(" §8(§f").append(teamName).append("§8)\n");
            } catch (PlayerNotInTeamException e) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
