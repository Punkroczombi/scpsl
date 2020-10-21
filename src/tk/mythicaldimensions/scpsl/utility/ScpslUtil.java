package tk.mythicaldimensions.scpsl.utility;

import tk.mythicaldimensions.scpsl.*;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.permissions.*;

public final class ScpslUtil
{
    public static final String SCPSL_SET_LOCATION_PERM = "scpsl.location";
    public static final String SCPSL_ADD_TO_GAME_PERM = "scpsl.game.add";
    public static final String SCPSL_REMOVE_FROM_GAME_PERM = "scpsl.game.remove";
    public static final String SCPSL_RELOAD_PERM = "scpsl.reload";
    public static final ArrayList<Permission> perms;
    
    static {
        perms = new ArrayList<Permission>();
    }
    
    public static void addToScp(final String teamName, final String playerName) {
        ScpslPlugin.scp.put(teamName, playerName);
    }
    
    public static void removeFromScp(final String teamName, final String playerName) {
        ScpslPlugin.scp.remove(teamName, playerName);
    }
    
    public static String getScpPlayer(final String teamName) {
        final String check = ScpslPlugin.scp.get(teamName);
        return check;
    }
    
    public static void purgeScp() {
        ScpslPlugin.scp.clear();
    }
    
    public static void addToTeam(final String playerName, final String teamName) {
        ScpslPlugin.team.put(playerName, teamName);
    }
    
    public static void removeFromTeam(final String playerName, final String teamName) {
        ScpslPlugin.team.remove(playerName, teamName);
    }
    
    public static String checkIfInTeam(final String playerName) {
        final String check = ScpslPlugin.team.get(playerName);
        return check;
    }
    
    public static int checkTeamNumber(final String teamName) {
        int amount = 0;
        for (final String it : ScpslPlugin.team.values()) {
            if (it == teamName) {
                ++amount;
            }
        }
        return amount;
    }
    
    public static void purgeTeams() {
        ScpslPlugin.team.clear();
    }
    
    public static void addToGame(final String playerName) {
        if (!checkIfInGame(playerName)) {
            final Player player = Bukkit.getPlayer(playerName);
            Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(playerName), "essentials loadkit " + playerName + " empty");
            ScpslPlugin.inGame.put(playerName, true);
            final int[] blackList = { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 80, 81, 82, 83, 100, 101, 102, 103 };
            int[] array;
            for (int length = (array = blackList).length, i = 0; i < length; ++i) {
                final int it = array[i];
                player.getInventory().setItem(it, new ItemStack(Material.BLACK_STAINED_GLASS));
            }
            if (ScpslPlugin.debug) {
                ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was added from the game");
            }
            player.setResourcePack("https://www.dropbox.com/s/csmrzdd5y5wbit8/SCP%20Site%2019%20Resourcepack.zip?dl=1");
            if (ScpslPlugin.started) {
                messageToAll("&FThe game has started");
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 103.0, 135.0, 97.0));
                addToTeam(player.getDisplayName(), "DEATH");
            }
            else {
                messageToAll("&FThere are &e(&b" + countHowManyInGame() + "&e)&F out of &e(&b" + ScpslPlugin.getInternalConfig().amountToStart() + "&e) &FPlayers, in the lobby.");
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 38.5, 242.0, 71.5));
            }
        }
    }
    
    public static void removeFromGame(final String playerName) {
        final Player player = Bukkit.getPlayer(playerName);
        ScpslPlugin.inGame.remove(playerName);
        player.setResourcePack("http://www.curseforge.com/minecraft/texture-packs/minecraft-resource-pack-template/download/3029942/file");
        if (ScpslPlugin.debug) {
            ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was removed from the game");
        }
        if (ScpslPlugin.started) {
            messageToAll("&c" + playerName + "&FHas left the game");
            player.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        }
        else {
            messageToAll("&FThere are &e(&b" + countHowManyInGame() + "&e)&F out of &e(&b" + ScpslPlugin.getInternalConfig().amountToStart() + "&e) &FPlayers, in the lobby.");
            player.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        }
    }
    
    public static boolean checkIfInGame(final String playerName) {
        boolean check = false;
        check = ScpslPlugin.inGame.containsKey(playerName);
        return check;
    }
    
    public static void messageToAll(final String message) {
        for (final String it : ScpslPlugin.inGame.keySet()) {
            final Player player = Bukkit.getPlayer(it);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
    
    public static int countHowManyInGame() {
        return ScpslPlugin.inGame.size();
    }
    
    public static double getPlayerDistance(final Player p1, final Player p2) {
        final double distance = Math.sqrt(Math.pow(p2.getLocation().getX() - p1.getLocation().getX(), 2.0) + Math.pow(p2.getLocation().getZ() - p1.getLocation().getZ(), 2.0));
        return distance;
    }
    
    public static void registerPermissions() {
        ScpslUtil.perms.add(new Permission("scpsl.location", "Allows player to set locations", PermissionDefault.OP));
        ScpslUtil.perms.add(new Permission("scpsl.game.add", "Allows player to add people to the game", PermissionDefault.OP));
        ScpslUtil.perms.add(new Permission("scpsl.game.remove", "Allows players to remove people from the game", PermissionDefault.OP));
        ScpslUtil.perms.add(new Permission("scpsl.reload", "Allows players to reload the config", PermissionDefault.OP));
        for (final Permission perm : ScpslUtil.perms) {
            Bukkit.getPluginManager().addPermission(perm);
            ScpslPlugin.getInstance().getLogger().fine("Registered Permission: " + perm.getName());
        }
    }
    
    public static void unregisterPermissions() {
        for (final Permission perm : ScpslUtil.perms) {
            Bukkit.getPluginManager().removePermission(perm);
            ScpslPlugin.getInstance().getLogger().fine("Unregistered Permission: " + perm.getName());
        }
        ScpslUtil.perms.clear();
    }
}