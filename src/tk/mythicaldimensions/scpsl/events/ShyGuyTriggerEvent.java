package tk.mythicaldimensions.scpsl.events;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class ShyGuyTriggerEvent extends Event implements Cancellable
{
    private final Player player;
    private final Player shyguy;
    @SuppressWarnings("unused")
	private boolean isCancelled;
    private static final HandlerList HANDLERS;
    
    static {
        HANDLERS = new HandlerList();
    }
    
    public ShyGuyTriggerEvent(final Player player, final Player target) {
        this.player = player;
        this.shyguy = target;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Player getTarget() {
        return this.shyguy;
    }
    
    public HandlerList getHandlers() {
        return ShyGuyTriggerEvent.HANDLERS;
    }
    
    public static HandlerList getHandlerList() {
        return ShyGuyTriggerEvent.HANDLERS;
    }
    
    public boolean isCancelled() {
        return false;
    }
    
    public void setCancelled(final boolean arg) {
        this.isCancelled = arg;
    }
}
