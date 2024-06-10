package fr.noskillworld.eventapi.event;

import fr.noskillworld.eventapi.api.event.EventState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EventStateChangeEvent extends Event implements Cancellable {

    private boolean isCancelled;
    private final EventState eventState;

    private static final HandlerList handlers = new HandlerList();

    public EventStateChangeEvent(EventState state) {
        this.isCancelled = false;
        this.eventState = state;
    }

    public EventState getEventState() {
        return this.eventState;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
