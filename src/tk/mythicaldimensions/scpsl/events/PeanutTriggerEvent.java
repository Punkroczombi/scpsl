package tk.mythicaldimensions.scpsl.events;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PeanutTriggerEvent extends Event implements Cancellable
{
    private final Player player;
    private final Player peanut;
    @SuppressWarnings("unused")
	private boolean isCancelled;
    private static final HandlerList HANDLERS;
    
    static {
        HANDLERS = new HandlerList();
    }
    
    public PeanutTriggerEvent(final Player player, final Player target) {
        this.player = player;
        this.peanut = target;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Player getTarget() {
        return this.peanut;
    }
    
    public HandlerList getHandlers() {
        return PeanutTriggerEvent.HANDLERS;
    }
    
    public static HandlerList getHandlerList() {
        return PeanutTriggerEvent.HANDLERS;
    }
    
    public boolean isCancelled() {
        return false;
    }
    
    public void setCancelled(final boolean arg) {
        this.isCancelled = arg;
    }
}
