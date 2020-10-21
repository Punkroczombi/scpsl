package tk.mythicaldimensions.scpsl.commands;

import org.bukkit.command.*;
import org.bukkit.*;
import tk.mythicaldimensions.scpsl.*;
import java.io.*;
import tk.mythicaldimensions.scpsl.utility.*;

public class ScpslCommands implements CommandExecutor
{
    @SuppressWarnings("unused")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        boolean commandReturn = false;
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("addtoscpsl")) {
                if (sender.hasPermission("scpsl.game.add")) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        final String player = Bukkit.getPlayer(args[1]).getDisplayName();
                        ScpslUtil.addToGame(player);
                        commandReturn = true;
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&F/scpsl &6addtoscpsl &F<player>"));
                        commandReturn = false;
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getNoPermMessage()));
                    commandReturn = false;
                }
            }
            else if (args[0].equalsIgnoreCase("removefromscpsl")) {
                if (sender.hasPermission("scpsl.game.remove")) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        final String player = Bukkit.getPlayer(args[1]).getDisplayName();
                        ScpslUtil.removeFromGame(player);
                        commandReturn = true;
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&F/scpsl &6removefromscpsl &F<player>"));
                        commandReturn = false;
                    }
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getNoPermMessage()));
                    commandReturn = false;
                }
            }
            else if (args[0].equalsIgnoreCase("force") && sender.hasPermission("scpsl.reload") && args[1] != null) {
                Label_0894: {
                    final String s;
                    switch (s = args[1]) {
                        case "end": {
                            ScpslPlugin.forceEnd = true;
                            break Label_0894;
                        }
                        case "dump": {
                            final Boolean started = ScpslPlugin.started;
                            final Boolean ended = ScpslPlugin.ended;
                            final Boolean debug = ScpslPlugin.debug;
                            final Integer beatTime = ScpslPlugin.beatTime;
                            final Long startTime = ScpslPlugin.startTime;
                            final String[] output = { "inGame: " + ScpslPlugin.inGame.toString(), "team: " + ScpslPlugin.team.toString(), "scp: " + ScpslPlugin.scp.toString(), "keywall: " + ScpslPlugin.keyWall.toString(), "keycardLevel: " + ScpslPlugin.keycardLevel.toString(), "started: " + started.toString(), "ended: " + ended.toString(), "debug: " + debug.toString(), "heartBeat: " + beatTime.toString(), "startTime: " + startTime.toString() };
                            final File folder = new File(ScpslPlugin.getInstance().getDataFolder() + File.separator + "debug");
                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            final File file = new File(folder + File.separator + "dump.txt");
                            try {
                                if (file.createNewFile()) {
                                    ScpslPlugin.getInstance().getLogger().info("[SCPSL] dump created: " + file.getName());
                                    final FileWriter myWriter = new FileWriter(file);
                                    String[] array;
                                    for (int length = (array = output).length, i = 0; i < length; ++i) {
                                        final String it = array[i];
                                        myWriter.write(it);
                                    }
                                    myWriter.close();
                                }
                            }
                            catch (IOException e) {
                                ScpslPlugin.getInstance().getLogger().info("[SCPSL] dump error");
                            }
                            break Label_0894;
                        }
                        case "nuke": {
                            Scp.nukeBoom();
                            break Label_0894;
                        }
                        case "start": {
                            ScpslPlugin.forceStart = true;
                            break Label_0894;
                        }
                        case "respawn": {
                            ScpslPlugin.forceRespawn = true;
                            break Label_0894;
                        }
                        default:
                            break;
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getCommandInvalidMessage()));
                }
                commandReturn = true;
            }
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("scpsl.reload")) {
                    ScpslPlugin.getInternalConfig().reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getConfigReloadedMessage()));
                    commandReturn = true;
                }
                else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getNoPermMessage()));
                    commandReturn = false;
                }
            }
            else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&F/scpsl &6removefromgame &F<player>\n&F/scpsl &6addtoscpsl &F<player>\n&F/scpsl &6force &F<event>\n&F/scpsl &6reload\n&F/scpsl &6debug\n&F/scpsl &6pause\n&F/scpsl &6help"));
                commandReturn = true;
            }
            else if (args[0].equalsIgnoreCase("debug")) {
                if (sender.hasPermission("scpsl.reload")) {
                    if (ScpslPlugin.debug) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Debug is &6Disabled"));
                        ScpslPlugin.debug = false;
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Debug is &6Enabled"));
                        ScpslPlugin.debug = true;
                    }
                    commandReturn = true;
                }
            }
            else if (args[0].equalsIgnoreCase("pause")) {
                if (sender.hasPermission("scpsl.reload")) {
                    if (ScpslPlugin.pause) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Game is &6Unpaused"));
                        ScpslPlugin.pause = false;
                    }
                    else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Game is &6Paused"));
                        ScpslPlugin.pause = true;
                    }
                    commandReturn = true;
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getCommandInvalidMessage()));
                commandReturn = false;
            }
        }
        else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ScpslPlugin.getInternalConfig().getCommandInvalidMessage()));
            commandReturn = false;
        }
        return commandReturn;
    }
}
