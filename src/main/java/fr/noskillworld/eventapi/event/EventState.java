package fr.noskillworld.eventapi.event;

public enum EventState {
    PENDING("L'évent n'a pas encore commencé"),
    STARTING("L'évent est en train de commencer"),
    STARTED("L'évent à commencé"),
    ENDED("L'évent est terminé"),
    ABORTED("L'évent à été annulé")
    ;

    private final String description;

    EventState(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
