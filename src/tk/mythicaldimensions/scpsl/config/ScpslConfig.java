package tk.mythicaldimensions.scpsl.config;

import tk.mythicaldimensions.scpsl.*;
import org.bukkit.configuration.file.*;

public final class ScpslConfig
{
    public static final String AMOUNT_KEY = "locale.amount";
    public static final String RESPAWN_KEY = "locale.respawn";
    public static final String NO_PERM_KEY = "locale.noperm";
    public static final String INVALID_PLAYER_KEY = "locale.invalidplayer";
    public static final String ONLY_PLAYERS_KEY = "locale.onlyplayers";
    public static final String CONFIG_RELOAD_KEY = "locale.reload";
    public static final String INVALID_COMMAND_KEY = "locale.invalid";
    public static final String WORLD_KEY = "locale.world";
    private String noPermMessage;
    private String invalidPlayerMessage;
    private String onlyPlayersMessage;
    private String configReloadedMessage;
    private String invalidCommandMessage;
    private String world;
    private int startAmount;
    private int respawnAmount;
    
    public ScpslConfig() {
        this.setDefaults();
        this.loadConfig();
    }
    
    public void loadConfig() {
        final FileConfiguration config = ScpslPlugin.getInstance().getConfig();
        this.startAmount = config.getInt("locale.amount", 6);
        this.respawnAmount = config.getInt("locale.respawn", 2);
        this.noPermMessage = config.getString("locale.noperm", "&4You do not have permission for that!");
        this.invalidPlayerMessage = config.getString("locale.invalidplayer", "&4That is not a valid player!");
        this.onlyPlayersMessage = config.getString("locale.onlyplayers", "&4YOnly players can enter that command!");
        this.configReloadedMessage = config.getString("locale.reload", "&F[&4Scpsl Config Reloaded&F]");
        this.invalidCommandMessage = config.getString("locale.invalid", "&4Invalid Command");
        this.world = config.getString("locale.world", "SCPSL");
    }
    
    public void setDefaults() {
        final FileConfiguration config = ScpslPlugin.getInstance().getConfig();
        config.addDefault("locale.amount", (Object)6);
        config.addDefault("locale.respawn", (Object)2);
        config.addDefault("locale.noperm", (Object)"&4You do not have permission for that!");
        config.addDefault("locale.invalidplayer", (Object)"&4That is not a valid player!");
        config.addDefault("locale.onlyplayers", (Object)"&4YOnly players can enter that command!");
        config.addDefault("locale.reload", (Object)"&F[&4Scpsl Config Reloaded&F]");
        config.addDefault("locale.invalid", (Object)"&4Invalid Command");
        config.addDefault("locale.world", (Object)"SCPSL");
        config.options().copyDefaults(true);
        ScpslPlugin.getInstance().saveConfig();
    }
    
    public void saveConfig() {
        final FileConfiguration config = ScpslPlugin.getInstance().getConfig();
        config.set("locale.amount", (Object)this.startAmount);
        config.set("locale.respawn", (Object)this.respawnAmount);
        config.set("locale.noperm", (Object)this.noPermMessage);
        config.set("locale.invalidplayer", (Object)this.invalidPlayerMessage);
        config.set("locale.onlyplayers", (Object)this.onlyPlayersMessage);
        config.set("locale.reload", (Object)this.configReloadedMessage);
        config.set("locale.invalid", (Object)this.invalidCommandMessage);
        config.set("locale.world", (Object)this.world);
        ScpslPlugin.getInstance().saveConfig();
    }
    
    public void reloadConfig() {
        this.loadConfig();
    }
    
    public int amountToStart() {
        return this.startAmount;
    }
    
    public void setStartAmount(final int startAmount) {
        this.startAmount = startAmount;
    }
    
    public int amountToRespawn() {
        return this.respawnAmount;
    }
    
    public void setRespawnAmount(final int repsawnAmount) {
        this.startAmount = this.respawnAmount;
    }
    
    public String getNoPermMessage() {
        return this.noPermMessage;
    }
    
    public void setNoPermMessage(final String message) {
        this.noPermMessage = message;
    }
    
    public String getInvalidPlayerMessage() {
        return this.invalidPlayerMessage;
    }
    
    public void setInvalidPlayerMessage(final String message) {
        this.invalidPlayerMessage = message;
    }
    
    public String getOnlyPlayersMessage() {
        return this.onlyPlayersMessage;
    }
    
    public void setOnlyPlayersMessage(final String message) {
        this.onlyPlayersMessage = message;
    }
    
    public String getConfigReloadedMessage() {
        return this.configReloadedMessage;
    }
    
    public void setConfigReloadedMessage(final String message) {
        this.configReloadedMessage = message;
    }
    
    public String getCommandInvalidMessage() {
        return this.invalidCommandMessage;
    }
    
    public void setInvalidCommandMessage(final String message) {
        this.invalidCommandMessage = message;
    }
    
    public String getMiniWorld() {
        return this.world;
    }
    
    public void setMiniWorld(final String message) {
        this.world = message;
    }
}
