package fr.noskillworld.eventapi.utils;

import fr.noskillworld.eventapi.EventAPI;
import org.jetbrains.annotations.NotNull;

public enum MessageManager {
    //Error messages
    NO_PERMISSION("§cVous n'avez pas la permission d'exécuter cette commande !"),
    NO_TEAM_REGISTERED("§cIl n'y a actuellement aucune team enregistrée."),

    //Success messages

    //Other messages
    TEAM_LIST_HEAD("§fListe des teams:"),
    TEAM_LIST("§6§l#%d §8(§3%s§8)"),
    TEAM_PLAYER_LIST("    §8- §3%s"),
    ;

    private final String message;

    MessageManager(String s) {
        this.message = s;
    }

    public @NotNull String getMessage() {
        return EventAPI.getInstance().getPrefix() + this.message;
    }

    public @NotNull String getRawMessage() {
        return this.message;
    }
}
