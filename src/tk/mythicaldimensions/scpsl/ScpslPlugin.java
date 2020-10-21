package tk.mythicaldimensions.scpsl;

import org.bukkit.plugin.java.*;
import java.util.logging.*;
import tk.mythicaldimensions.scpsl.config.*;
import org.bukkit.scoreboard.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import tk.mythicaldimensions.scpsl.commands.*;
import tk.mythicaldimensions.essentials.utility.*;
import org.bukkit.command.*;
import org.bukkit.inventory.*;
import tk.mythicaldimensions.scpsl.utility.*;
import org.bukkit.event.*;
import tk.mythicaldimensions.scpsl.events.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;

public class ScpslPlugin extends JavaPlugin
{
    public static final String pTexture = "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDczMTYzMSwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNkZGQ3ZDAwNGVhMGI5ZjkyNWM2ODczMDYyOWYzYTY1YWE0NGRjYjIzNDFmZjk3ZjAyNjI3NzUwMzExMjhiYSIKICAgIH0KICB9Cn0=";
    public static final String pSignature = "wi2zs+xx9zTdjT2KEcQ2aYcKYcahQnUo87Il7qg1ziL2CAWeeGyd/q1sWj4DUH7RHMYEYN7K2pQJptdVvT9T56K+kzFUwZB9E/TtD3AEUMXN3Bluu6YT+sXN/P3saPIP/LfDAJqQXeWBFBrWj0athGNFxkTmf7Zij+0w88vrnI8vkgJLy0Ay5Y0n/eLfw8ygpYuuAYPlkO8LGAlMgVCLzZRdp42RuUVd+g+gFE+wKkfrJWMwv5RTW4tdOv+pctxlHjNf9maY0WMxAFD9HMgGQNEkJW711FXzi2F0WBdonUEjISE/svu2TN5X9WkWDVtuwTVdqMwW2I+oecIf4xMxmxTORJAWsd/az5mC7GP9CFXFU59lxTy2a9BEJasyaIIvlTUZXQXkBBCyPCVMCmbb+2KgKB24dchYBfAJ7Mi3Mw7k8twSf7cfgdV3qVC2hSle4xH9k7X5WTbrmiY7RcSV9BuUns2LNp6Qzgs8q9qf1kIY+yNY2iPfCW3BhhTMzBjb67iAFTpADZauRxnmkoZ6hnWNHo+md1HW6bXXQXf5HRN1v/ouayHC0mP9iARXijLBLk3KOzcZwreIa2KeokgmfaSzZZLlHs3HjOe5qdCTp27mGyS4y/UAq4Q+rl4UrHhuwOQs+e1blpjvjn3Fa1A5QeyJz7X1Keaqdvc7AJTJKXQ=";
    public static final String sTexture = "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDU5NjYyOCwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVkN2Q0NTFlMDUyODExYmQ0YzA1MjJhOWJkOTY3YmJlOWRiZjRiMWMxOGQ3ZjllYzNhY2QxMjk4YjA2YjljYyIKICAgIH0KICB9Cn0=";
    public static final String sSignature = "K6tateLF7UASqjLS+oEumiQT1lFDSpZyNVdiMfiLvPSScTTrmTszcj4sEoECrtZFjVTJsSUMmFA85DG5F1VKcsQi0TM7hsuSBpkDF9oyyLDxuR/iIYNJbYlfSz2SlSgIkQW//H1LXy0R2QDtItnVDfUJTMo11u0uDCdEwq0cVzpSX1G++jnHcQM+oCuqtEMT9+/5auczSCgl2fJMHC/QvUxMgFmkrdQsO+SnCkn3+pGZn0fTxK/76Tw8bQOMMxlkReR+ocU13WQ7KoEM5hEchq/ncr8wEEJd4I5b0CMrdYT2Kf6emKGYktIsMWvf0JANNmBlUKXx0jmH/Kxxm+NqKe1e0Si0JpeLFxgUYKt8hmgqwwonatHCYbzdFmmU/Do0DheRlKgLPvNtXL1OP6coB/LDpQMyZnaQYmN60ASx+rSEr9I29MBwh4zM5aF5ZbIC9XEBZKHq+sSzWwYNkUZCI0KOFPiLgTUVJ47NPt2H3JHG1zFGxJ+12QShFqF6YUc0emHzW76YGc+4vqnydMxWO5l/a9r21LRXb8+86MhUbnB1gi29pHTRgTMS67ST6HG+M1uU7NT4OUiXRTsoYS76/mXCThgV4sdPcszXD7+AaQ71VBYDXBbUxYbZWxo7oRyOBy7+PVemOQIhlWr0qwn3tvIr1/GyXgwYcCpio/h06Tc=";
    public static final String dTexture = "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDgzOTI0MiwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JkYmUxN2MzNzQwOTY5OGE4Yjk5YjJlMDM5ZjFlMjJlOTdmYmMxM2IyMjk0NmUzZjc5ZGMxYTNhYjNmYjViZiIKICAgIH0KICB9Cn0=";
    public static final String dSignature = "vYemrJEfHYIgUn3eoDcogIg9FMetDIEGlFx9nSXpjZ+amwPfFXK9wx/BQw9jyuUXptYVtlxyD40bj17xSNECa3k+9JIFJaYIQxOd78zvGhppCB4r0vbkr73uNmCX5MekbUyTRl1m+zH/DIJEwjB5MhwoMhrr29osnDRqSDS44CzczR3/T+4X0p8f5M7Vr+o0xZfuvY5cGGgWQPoyXyHuZEcWJV3H5y8iTtRT8o6nkASB91nk13Cq6k7smz2w3Hir4Vmjk0jQ2sPhmxTNh8N9qonC621yDsYinkzq7kffO70wSpczguhcm1HWVD1ELKUNfB65Umop0y79jqc6bowkEGy5UG1/w8JX9gfLU3apmjs7FIB6FQCupcdekuaAB/UcU4cL3CCF6tI19SVk8rXAYw5W6wmGL5zSwHLQ8gxpsVD7jMvDFAbRpbFeD08n8Wq3sK9XLBp5RfTp5+4L08p56RB5wWUw1v1oc/37TE2+Tz2WqzRJ+c+9668fYutE/CzzLgCFZR6PSUe5SysVweTcurODv1lVRJScDZzexiSZcxM/8rm9QJzGy+BcQz5PS0vjloj8HMEK3zqBWRLgnfOK7LFBGeX1KAzv+NSbnytbm8UyJZ3lPgJl535cHOvxU6OvijsfqrT/HzSCfDXeYy1s4CSIp2bxIQFC3k8GwRw5e9Q=";
    public static final String oTexture = "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDg4NDc4NywKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk4ZDQ2N2UzMDAyODc4NGRlMjQ5YzhiMjBiYjllZWM0ZmFhOGM1NTJjZGJkNzUxZjkwYTJmZmE5NTE3M2RkMyIKICAgIH0KICB9Cn0=";
    public static final String oSignature = "lj4PeyrWTnnvCHmpHjVhW7VWaeK4LndcXnZjgvIeSXbHYpzVpqR7ZEbrhMUqAR+84uvPbOyvrZJmJ/3p99tWcdpdovzY20S/L3ySTXu+f4pSoSKtBuVWwdt3bvRARv/Y1qVaiBEDXub922G+8wi0L1k9l0lL0AhIVMKFqa/DzCAwi0V8tgEOoHjzp6l5JByEh+AkUFVEyAm242Y94ii/vd9qM4V0lxnyK6Ga8r1Njml94CEswuBeUs2lPMuA85BOxhcEsBn4/loNYGpcBLqaUZvFqze8LOu3cshLPSER0r0dCq9pzqyjlj4WFCjQgOCjXezDLsI8+y2vWbglsX4JQ3kIvz/5j+9D324WEHLjveKHAau62amQPvYvo5dOwR65+zXdIFXyEmssN80C/ueNI71OSCiAK0V92SNA9gd+wWb+5wwaTOlP/jSj821WaqkqqeSs0GLPIS5xAdRlI7PxfEvwrxSGU61o/cY8/eUnwnjFgci8Pnshgvm3j6TPOJ83+Y6TJJGiI+NbW0WF9O5jUFvCJE3JRjkHYuZm7zr4ibq115f7dV9a31izEO0AKeBMtr1+hTmgtxlj8gWcgdbfGfkZSp/aOp6070zW/e7fU1YlXSebRQtr9BrlZm48Ht2SC8ijdRxJTZ6kXY3U2SvQFDaF9pMHSQaGx++ptReYoC4=";
    private final Logger logger;
    private static ScpslConfig config;
    private static ScpslPlugin plugin;
    private static ScoreboardManager manager;
    double randomNum;
    public static boolean started;
    public static boolean ended;
    public static Location keyWall;
    public static Material keycardLevel;
    public static HashMap<String, Boolean> inGame;
    public static HashMap<String, String> team;
    public static HashMap<String, String> scp;
    public static int beatTime;
    public static long startTime;
    int respawnI;
    int respawnA;
    public static boolean debug;
    public static boolean pause;
    public static boolean forceStart;
    public static boolean forceRespawn;
    public static boolean forceEnd;
    
    static {
        ScpslPlugin.manager = Bukkit.getScoreboardManager();
        ScpslPlugin.started = false;
        ScpslPlugin.ended = false;
        ScpslPlugin.keyWall = null;
        ScpslPlugin.keycardLevel = null;
        ScpslPlugin.inGame = new HashMap<String, Boolean>();
        ScpslPlugin.team = new HashMap<String, String>();
        ScpslPlugin.scp = new HashMap<String, String>();
        ScpslPlugin.beatTime = 0;
        ScpslPlugin.startTime = System.currentTimeMillis();
        ScpslPlugin.debug = false;
        ScpslPlugin.pause = false;
        ScpslPlugin.forceStart = false;
        ScpslPlugin.forceRespawn = false;
        ScpslPlugin.forceEnd = false;
    }
    
    public ScpslPlugin() {
        this.logger = Logger.getLogger("Scpsl");
        this.randomNum = Math.random() * 101.0;
        this.respawnI = 0;
        this.respawnA = 0;
    }
    
    public void onEnable() {
        ScpslPlugin.plugin = this;
        this.getLogger().info("[SCPSL] Registering Events");
        Bukkit.getPluginManager().registerEvents((Listener)new ScpslEvents(), (Plugin)this);
        this.getLogger().info("[SCPSL] Getting Config");
        ScpslPlugin.config = new ScpslConfig();
        this.getLogger().info("[SCPSL] Registering Commands");
        this.getCommand("scpsl").setExecutor((CommandExecutor)new ScpslCommands());
        this.getLogger().info("[SCPSL] Registering Permissions");
        ScpslUtil.registerPermissions();
        this.getLogger().info("[SCPSL] Getting Scoreboard");
        this.getLogger().info("[SCPSL] Plugin Enabled");
        Npc.createNPC(102.5, 134.0, 72.5, 0.0f, 0.0f, "SCP: 173", "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDczMTYzMSwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNkZGQ3ZDAwNGVhMGI5ZjkyNWM2ODczMDYyOWYzYTY1YWE0NGRjYjIzNDFmZjk3ZjAyNjI3NzUwMzExMjhiYSIKICAgIH0KICB9Cn0=", "wi2zs+xx9zTdjT2KEcQ2aYcKYcahQnUo87Il7qg1ziL2CAWeeGyd/q1sWj4DUH7RHMYEYN7K2pQJptdVvT9T56K+kzFUwZB9E/TtD3AEUMXN3Bluu6YT+sXN/P3saPIP/LfDAJqQXeWBFBrWj0athGNFxkTmf7Zij+0w88vrnI8vkgJLy0Ay5Y0n/eLfw8ygpYuuAYPlkO8LGAlMgVCLzZRdp42RuUVd+g+gFE+wKkfrJWMwv5RTW4tdOv+pctxlHjNf9maY0WMxAFD9HMgGQNEkJW711FXzi2F0WBdonUEjISE/svu2TN5X9WkWDVtuwTVdqMwW2I+oecIf4xMxmxTORJAWsd/az5mC7GP9CFXFU59lxTy2a9BEJasyaIIvlTUZXQXkBBCyPCVMCmbb+2KgKB24dchYBfAJ7Mi3Mw7k8twSf7cfgdV3qVC2hSle4xH9k7X5WTbrmiY7RcSV9BuUns2LNp6Qzgs8q9qf1kIY+yNY2iPfCW3BhhTMzBjb67iAFTpADZauRxnmkoZ6hnWNHo+md1HW6bXXQXf5HRN1v/ouayHC0mP9iARXijLBLk3KOzcZwreIa2KeokgmfaSzZZLlHs3HjOe5qdCTp27mGyS4y/UAq4Q+rl4UrHhuwOQs+e1blpjvjn3Fa1A5QeyJz7X1Keaqdvc7AJTJKXQ=", getInternalConfig().getMiniWorld());
        Npc.createNPC(102.5, 134.0, 80.5, 180.0f, 0.0f, "SCP: 096", "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDU5NjYyOCwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVkN2Q0NTFlMDUyODExYmQ0YzA1MjJhOWJkOTY3YmJlOWRiZjRiMWMxOGQ3ZjllYzNhY2QxMjk4YjA2YjljYyIKICAgIH0KICB9Cn0=", "K6tateLF7UASqjLS+oEumiQT1lFDSpZyNVdiMfiLvPSScTTrmTszcj4sEoECrtZFjVTJsSUMmFA85DG5F1VKcsQi0TM7hsuSBpkDF9oyyLDxuR/iIYNJbYlfSz2SlSgIkQW//H1LXy0R2QDtItnVDfUJTMo11u0uDCdEwq0cVzpSX1G++jnHcQM+oCuqtEMT9+/5auczSCgl2fJMHC/QvUxMgFmkrdQsO+SnCkn3+pGZn0fTxK/76Tw8bQOMMxlkReR+ocU13WQ7KoEM5hEchq/ncr8wEEJd4I5b0CMrdYT2Kf6emKGYktIsMWvf0JANNmBlUKXx0jmH/Kxxm+NqKe1e0Si0JpeLFxgUYKt8hmgqwwonatHCYbzdFmmU/Do0DheRlKgLPvNtXL1OP6coB/LDpQMyZnaQYmN60ASx+rSEr9I29MBwh4zM5aF5ZbIC9XEBZKHq+sSzWwYNkUZCI0KOFPiLgTUVJ47NPt2H3JHG1zFGxJ+12QShFqF6YUc0emHzW76YGc+4vqnydMxWO5l/a9r21LRXb8+86MhUbnB1gi29pHTRgTMS67ST6HG+M1uU7NT4OUiXRTsoYS76/mXCThgV4sdPcszXD7+AaQ71VBYDXBbUxYbZWxo7oRyOBy7+PVemOQIhlWr0qwn3tvIr1/GyXgwYcCpio/h06Tc=", getInternalConfig().getMiniWorld());
        Npc.createNPC(106.5, 134.0, 76.5, 90.0f, 0.0f, "SCP: 939", "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDgzOTI0MiwKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JkYmUxN2MzNzQwOTY5OGE4Yjk5YjJlMDM5ZjFlMjJlOTdmYmMxM2IyMjk0NmUzZjc5ZGMxYTNhYjNmYjViZiIKICAgIH0KICB9Cn0=", "vYemrJEfHYIgUn3eoDcogIg9FMetDIEGlFx9nSXpjZ+amwPfFXK9wx/BQw9jyuUXptYVtlxyD40bj17xSNECa3k+9JIFJaYIQxOd78zvGhppCB4r0vbkr73uNmCX5MekbUyTRl1m+zH/DIJEwjB5MhwoMhrr29osnDRqSDS44CzczR3/T+4X0p8f5M7Vr+o0xZfuvY5cGGgWQPoyXyHuZEcWJV3H5y8iTtRT8o6nkASB91nk13Cq6k7smz2w3Hir4Vmjk0jQ2sPhmxTNh8N9qonC621yDsYinkzq7kffO70wSpczguhcm1HWVD1ELKUNfB65Umop0y79jqc6bowkEGy5UG1/w8JX9gfLU3apmjs7FIB6FQCupcdekuaAB/UcU4cL3CCF6tI19SVk8rXAYw5W6wmGL5zSwHLQ8gxpsVD7jMvDFAbRpbFeD08n8Wq3sK9XLBp5RfTp5+4L08p56RB5wWUw1v1oc/37TE2+Tz2WqzRJ+c+9668fYutE/CzzLgCFZR6PSUe5SysVweTcurODv1lVRJScDZzexiSZcxM/8rm9QJzGy+BcQz5PS0vjloj8HMEK3zqBWRLgnfOK7LFBGeX1KAzv+NSbnytbm8UyJZ3lPgJl535cHOvxU6OvijsfqrT/HzSCfDXeYy1s4CSIp2bxIQFC3k8GwRw5e9Q=", getInternalConfig().getMiniWorld());
        Npc.createNPC(98.5, 134.0, 76.5, 270.0f, 0.0f, "SCP: 106", "ewogICJ0aW1lc3RhbXAiIDogMTYwMDc0NDg4NDc4NywKICAicHJvZmlsZUlkIiA6ICJmNTg1YmU1MTMwNTk0MTdlYWMxZjRhNDhiZDQyODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdW5rcm9jem9tYmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk4ZDQ2N2UzMDAyODc4NGRlMjQ5YzhiMjBiYjllZWM0ZmFhOGM1NTJjZGJkNzUxZjkwYTJmZmE5NTE3M2RkMyIKICAgIH0KICB9Cn0=", "lj4PeyrWTnnvCHmpHjVhW7VWaeK4LndcXnZjgvIeSXbHYpzVpqR7ZEbrhMUqAR+84uvPbOyvrZJmJ/3p99tWcdpdovzY20S/L3ySTXu+f4pSoSKtBuVWwdt3bvRARv/Y1qVaiBEDXub922G+8wi0L1k9l0lL0AhIVMKFqa/DzCAwi0V8tgEOoHjzp6l5JByEh+AkUFVEyAm242Y94ii/vd9qM4V0lxnyK6Ga8r1Njml94CEswuBeUs2lPMuA85BOxhcEsBn4/loNYGpcBLqaUZvFqze8LOu3cshLPSER0r0dCq9pzqyjlj4WFCjQgOCjXezDLsI8+y2vWbglsX4JQ3kIvz/5j+9D324WEHLjveKHAau62amQPvYvo5dOwR65+zXdIFXyEmssN80C/ueNI71OSCiAK0V92SNA9gd+wWb+5wwaTOlP/jSj821WaqkqqeSs0GLPIS5xAdRlI7PxfEvwrxSGU61o/cY8/eUnwnjFgci8Pnshgvm3j6TPOJ83+Y6TJJGiI+NbW0WF9O5jUFvCJE3JRjkHYuZm7zr4ibq115f7dV9a31izEO0AKeBMtr1+hTmgtxlj8gWcgdbfGfkZSp/aOp6070zW/e7fU1YlXSebRQtr9BrlZm48Ht2SC8ijdRxJTZ6kXY3U2SvQFDaF9pMHSQaGx++ptReYoC4=", getInternalConfig().getMiniWorld());
        final BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
            @SuppressWarnings("unused")
			@Override
            public void run() {
                final long elapsedTime = System.currentTimeMillis() - ScpslPlugin.startTime;
                final long elapsedSeconds = elapsedTime / 1000L;
                ScpslPlugin.beatTime = (int)elapsedSeconds;
                if (elapsedSeconds % 60L == 0L && ScpslPlugin.debug) {
                    ScpslPlugin.this.getLogger().info("[SCPSL] Beep Boop Uptime: [" + elapsedSeconds + "] s");
                }
                if ((!ScpslPlugin.started && ScpslUtil.countHowManyInGame() == ScpslPlugin.getInternalConfig().amountToStart() && elapsedSeconds == 0L) || ScpslPlugin.forceStart) {
                    ScpslPlugin.this.getLogger().info("[SCPSL] Starting SCPSL Minigame");
                    for (final String it : ScpslPlugin.inGame.keySet()) {
                        if (ScpslPlugin.this.randomNum >= 25.0) {
                            ScpslUtil.addToTeam(it, "DCLASS");
                            if (!ScpslPlugin.debug) {
                                continue;
                            }
                            ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was set Dclass");
                        }
                        else if (Math.random() * 101.0 <= 50.0) {
                            ScpslUtil.addToTeam(it, "SCIENCE");
                            if (!ScpslPlugin.debug) {
                                continue;
                            }
                            ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was set Science");
                        }
                        else if (Math.random() * 101.0 >= 75.0) {
                            ScpslUtil.addToTeam(it, "GUARD");
                            if (!ScpslPlugin.debug) {
                                continue;
                            }
                            ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was set Guard");
                        }
                        else {
                            if (Math.random() * 101.0 > 75.0) {
                                continue;
                            }
                            ScpslUtil.addToTeam(it, "SCP");
                            if (!ScpslPlugin.debug) {
                                continue;
                            }
                            ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was set Guard");
                        }
                    }
                    for (final String it : ScpslPlugin.team.keySet()) {
                        final Player player = Bukkit.getPlayer(it);
                        final String s;
                        switch (s = ScpslPlugin.team.get(it)) {
                            case "SCIENCE": {
                                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 2.5, 51.0, 99.5));
                                if (it != null) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it), "essentials loadkit " + it + " science");
                                }
                                player.sendTitle("Your a Scientist", "Escape the Facility", 10, 70, 20);
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was setup on Science");
                                    break;
                                }
                                break;
                            }
                            case "SCP": {
                                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 134.0, 76.5));
                                player.sendTitle("YoUr A sCp", "KiLl ThEm AlL...", 10, 70, 20);
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was setup on SCP");
                                    break;
                                }
                                break;
                            }
                            case "GUARD": {
                                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 197.5, 74.0, 85.5));
                                if (it != null) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it), "essentials loadkit " + it + " guard");
                                }
                                player.sendTitle("Your a Guard", "Secure the Facility", 10, 70, 20);
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was setup on Guard");
                                    break;
                                }
                                break;
                            }
                            case "DCLASS": {
                                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 35.5, 51.0, 83.5));
                                if (it != null) {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it), "essentials loadkit " + it + " dclass");
                                }
                                player.sendTitle("Your a D Class", "Escape the Facility", 10, 70, 20);
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " was setup on Dclass");
                                    break;
                                }
                                break;
                            }
                            default:
                                break;
                        }
                        final int[] blackList = { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 80, 81, 82, 83, 100, 101, 102, 103 };
                        int[] array;
                        for (int length = (array = blackList).length, i = 0; i < length; ++i) {
                            final int num = array[i];
                            player.getInventory().setItem(num, new ItemStack(Material.BLACK_STAINED_GLASS));
                        }
                    }
                    Items.spawnLight();
                    Items.spawnHeavy();
                    Items.spawnEntrance();
                    ScpslPlugin.forceStart = false;
                    ScpslPlugin.this.getLogger().info("[SCPSL] Minigame SCPSL started");
                    ScpslPlugin.started = true;
                }
                if (ScpslPlugin.started && !ScpslPlugin.ended) {
                    if (elapsedSeconds == 2700L) {
                        Scp.nukeBoom();
                    }
                    Scp.escape();
                    if (ScpslPlugin.keyWall != null && ScpslPlugin.keycardLevel != null) {
                        ScpslPlugin.keyWall.getBlock().setType(ScpslPlugin.keycardLevel);
                        ScpslPlugin.keyWall = null;
                        ScpslPlugin.keycardLevel = null;
                    }
                    if (ScpslUtil.checkTeamNumber("DEATH") >= ScpslPlugin.getInternalConfig().amountToRespawn() || elapsedSeconds == 120L || ScpslPlugin.forceRespawn) {
                        ScpslPlugin.this.getLogger().info("[SCPSL] Respawning group");
                        for (final String it : ScpslPlugin.team.keySet()) {
                            if (ScpslPlugin.team.get(it) == "DEATH") {
                                ScpslUtil.removeFromTeam(it, "DEATH");
                                ScpslUtil.addToTeam(it, "RESPAWN");
                                if (!ScpslPlugin.debug) {
                                    continue;
                                }
                                ScpslPlugin.this.getLogger().info("[SCPSL] " + it + " added to respawn group");
                            }
                        }
                        ScpslPlugin.this.respawnI = 0;
                        ScpslPlugin.this.respawnA = 0;
                    }
                    final double randNum = ScpslPlugin.this.randomNum;
                    for (final String it2 : ScpslPlugin.team.keySet()) {
                        if (ScpslPlugin.team.get(it2) == "RESPAWN") {
                            if (randNum >= 33.0) {
                                ScpslUtil.addToTeam(it2, "CHOAS");
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was set Choas");
                                }
                            }
                            else if (randNum <= 66.0) {
                                ScpslUtil.addToTeam(it2, "MTF");
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was set MTF");
                                }
                            }
                            else if (randNum >= 66.0) {
                                ScpslUtil.addToTeam(it2, "SHAND");
                                if (ScpslPlugin.debug) {
                                    ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was set Shand");
                                }
                            }
                            final ScpslPlugin this$0 = ScpslPlugin.this;
                            ++this$0.respawnA;
                        }
                    }
                    while (ScpslPlugin.this.respawnI < ScpslPlugin.this.respawnA || ScpslPlugin.this.respawnI != ScpslPlugin.this.respawnA) {
                        for (final String it2 : ScpslPlugin.team.keySet()) {
                            final Player player2 = Bukkit.getPlayer(it2);
                            final String s2;
                            switch (s2 = ScpslPlugin.team.get(it2)) {
                                case "MTF": {
                                    player2.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 214.5, 96.0, 7.5));
                                    if (it2 != null) {
                                        Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it2), "essentials loadkit " + it2 + " mtf");
                                    }
                                    player2.sendTitle("Your a MTF Unit", "Secure the Facility", 10, 70, 20);
                                    if (ScpslPlugin.debug) {
                                        ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was setup on MTF");
                                        break;
                                    }
                                    break;
                                }
                                case "CHOAS": {
                                    player2.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 214.5, 91.0, 119.5));
                                    if (it2 != null) {
                                        Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it2), "essentials loadkit " + it2 + " chaos");
                                    }
                                    player2.sendTitle("Your a Choas", "Capture the facility", 10, 70, 20);
                                    if (ScpslPlugin.debug) {
                                        ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was setup on Chaos");
                                        break;
                                    }
                                    break;
                                }
                                case "SHAND": {
                                    player2.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 168.5, 102.0, 114.5));
                                    if (it2 != null) {
                                        Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it2), "essentials loadkit " + it2 + " shand");
                                    }
                                    player2.sendTitle("Your a Serphant", "Save them", 10, 70, 20);
                                    if (ScpslPlugin.debug) {
                                        ScpslPlugin.this.getLogger().info("[SCPSL] " + it2 + " was setup on Shand");
                                        break;
                                    }
                                    break;
                                }
                                default:
                                    break;
                            }
                            final int[] blackList2 = { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 80, 81, 82, 83, 100, 101, 102, 103 };
                            int[] array2;
                            for (int length2 = (array2 = blackList2).length, j = 0; j < length2; ++j) {
                                final int num2 = array2[j];
                                player2.getInventory().setItem(num2, new ItemStack(Material.BLACK_STAINED_GLASS));
                            }
                        }
                        final ScpslPlugin this$2 = ScpslPlugin.this;
                        ++this$2.respawnI;
                        ScpslPlugin.forceRespawn = false;
                    }
                    if (ScpslUtil.getScpPlayer("PEANUT") != null) {
                        for (final String it2 : ScpslPlugin.team.keySet()) {
                            final Player player2 = Bukkit.getPlayer(it2);
                            final Player peanut = Bukkit.getPlayer(ScpslUtil.getScpPlayer("PEANUT"));
                            if (Scp.getNearestEntityInSight(player2, 50) instanceof HumanEntity && Scp.getNearestEntityInSight(player2, 50).getName() == ScpslUtil.getScpPlayer("PEANUT")) {
                                final PeanutTriggerEvent peanutTriggerEvent = new PeanutTriggerEvent(player2, peanut);
                                Bukkit.getPluginManager().callEvent((Event)peanutTriggerEvent);
                            }
                        }
                    }
                    if (ScpslUtil.getScpPlayer("SHYGUY") != null) {
                        for (final String it2 : ScpslPlugin.team.keySet()) {
                            final Player player2 = Bukkit.getPlayer(it2);
                            final Player shyguy = Bukkit.getPlayer(ScpslUtil.getScpPlayer("SHYGUY"));
                            if (Scp.getNearestEntityInSight(player2, 50) instanceof HumanEntity && Scp.getNearestEntityInSight(player2, 50).getName() == ScpslUtil.getScpPlayer("SHYGUY")) {
                                final ShyGuyTriggerEvent shyGuyTriggerEvent = new ShyGuyTriggerEvent(player2, shyguy);
                                Bukkit.getPluginManager().callEvent((Event)shyGuyTriggerEvent);
                            }
                        }
                    }
                    if (ScpslUtil.getScpPlayer("DOGGO") != null) {
                        for (final String it2 : ScpslPlugin.team.keySet()) {
                            final Player player2 = Bukkit.getPlayer(it2);
                            final Player doggo = Bukkit.getPlayer(ScpslUtil.getScpPlayer("DOGGO"));
                            if (Scp.getNearestEntityInSight(player2, 50) instanceof HumanEntity) {
                                if (!player2.isSprinting()) {
                                    continue;
                                }
                                doggo.showPlayer((Plugin)ScpslPlugin.plugin, player2);
                            }
                            else {
                                doggo.hidePlayer((Plugin)ScpslPlugin.plugin, player2);
                            }
                        }
                    }
                    if (ScpslUtil.checkTeamNumber("CHOAS") == 0 && ScpslUtil.checkTeamNumber("SHAND") == 0 && ScpslUtil.checkTeamNumber("DCLASS") == 0 && ScpslUtil.checkTeamNumber("SCP") == 0 && !ScpslPlugin.pause) {
                        ScpslUtil.messageToAll("MTF Wins The Round");
                        ScpslPlugin.ended = true;
                        ScpslPlugin.this.getLogger().info("[SCPSL] MTF Win");
                    }
                    else if (ScpslUtil.checkTeamNumber("MTF") == 0 && ScpslUtil.checkTeamNumber("SHAND") == 0 && ScpslUtil.checkTeamNumber("SCIENCE") == 0 && ScpslUtil.checkTeamNumber("GUARD") == 0 && ScpslUtil.checkTeamNumber("SCP") == 0 && !ScpslPlugin.pause) {
                        ScpslUtil.messageToAll("Choas Wins The Round");
                        ScpslPlugin.ended = true;
                        ScpslPlugin.this.getLogger().info("[SCPSL] Choas Win");
                    }
                    else if (ScpslUtil.checkTeamNumber("CHOAS") == 0 && ScpslUtil.checkTeamNumber("MTF") == 0 && ScpslUtil.checkTeamNumber("SCIENCE") == 0 && ScpslUtil.checkTeamNumber("GUARD") == 0 && !ScpslPlugin.pause) {
                        ScpslUtil.messageToAll("Serphant's Hand Wins The Round");
                        ScpslPlugin.ended = true;
                        ScpslPlugin.this.getLogger().info("[SCPSL] Serphants Hand Win");
                    }
                    else if (ScpslUtil.checkTeamNumber("CHOAS") == 0 && ScpslUtil.checkTeamNumber("MTF") == 0 && ScpslUtil.checkTeamNumber("SCIENCE") == 0 && ScpslUtil.checkTeamNumber("DCLASS") == 0 && ScpslUtil.checkTeamNumber("GUARD") == 0 && !ScpslPlugin.pause) {
                        ScpslUtil.messageToAll("SCP Wins The Round");
                        ScpslPlugin.ended = true;
                        ScpslPlugin.this.getLogger().info("[SCPSL] SCP Win");
                    }
                }
                if (ScpslPlugin.ended || ScpslPlugin.forceEnd) {
                    ScpslPlugin.this.getLogger().info("[SCPSL] Minigame Ended");
                    ScpslUtil.purgeTeams();
                    ScpslPlugin.startTime = System.currentTimeMillis();
                    ScpslUtil.purgeScp();
                    Items.purgeItems(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()));
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 134.0, 73.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 135.0, 73.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 134.0, 79.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 135.0, 79.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 105.5, 134.0, 76.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 105.5, 135.0, 76.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 99.5, 134.0, 76.5).getBlock().setType(Material.AIR);
                    new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 99.5, 135.0, 76.5).getBlock().setType(Material.AIR);
                    for (final String it : ScpslPlugin.inGame.keySet()) {
                        final Player player = Bukkit.getPlayer(it);
                        player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 38.0, 242.0, 71.0));
                    }
                    if (ScpslPlugin.debug) {
                        ScpslUtil.messageToAll("The round was ended forcefully");
                    }
                    ScpslPlugin.forceEnd = false;
                    ScpslPlugin.started = false;
                    ScpslPlugin.ended = false;
                }
            }
        }, 0L, 20L);
    }
    
    public void onDisable() {
        this.getLogger().info("[SCPSL] Cleaning Game States");
        Items.purgeItems(Bukkit.getWorld(getInternalConfig().getMiniWorld()));
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 102.5, 134.0, 73.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 102.5, 135.0, 73.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 102.5, 134.0, 79.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 102.5, 135.0, 79.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 105.5, 134.0, 76.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 105.5, 135.0, 76.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 99.5, 134.0, 76.5).getBlock().setType(Material.AIR);
        new Location(Bukkit.getWorld(getInternalConfig().getMiniWorld()), 99.5, 135.0, 76.5).getBlock().setType(Material.AIR);
        this.getLogger().info("[SCPSL] Unregistering Permissions");
        ScpslUtil.unregisterPermissions();
        this.getLogger().info("[SCPSL] Plugin Disabled");
    }
    
    public Logger getLogger() {
        return this.logger;
    }
    
    public static ScpslPlugin getInstance() {
        return ScpslPlugin.plugin;
    }
    
    public static ScpslConfig getInternalConfig() {
        return ScpslPlugin.config;
    }
    
    public static ScoreboardManager getScoreboardManager() {
        return ScpslPlugin.manager;
    }
}
