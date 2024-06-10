package fr.noskillworld.eventapi.api.event;

public enum EventState {
    PENDING("L'évent n'a pas encore commencé"),
    STARTING("L'évent est en train de commencer"),
    STARTED("L'évent a commencé"),
    ENDED("L'évent est terminé"),
    ;

    private final String description;

    EventState(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
