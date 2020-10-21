package tk.mythicaldimensions.scpsl.events;

import org.bukkit.event.*;
import tk.mythicaldimensions.scpsl.utility.*;
import org.bukkit.block.data.type.*;
import org.bukkit.inventory.*;
import tk.mythicaldimensions.scpsl.*;
import org.bukkit.block.*;
import org.bukkit.event.inventory.*;
import tk.mythicaldimensions.essentials.events.*;
import org.bukkit.potion.*;
import org.bukkit.event.block.*;
import tk.mythicaldimensions.essentials.utility.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

public class ScpslEvents implements Listener
{
    @EventHandler
    public void onFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
        final Player player = (Player)event.getEntity();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreakEvent(final BlockBreakEvent event) {
        if (ScpslUtil.checkIfInGame(event.getPlayer().getDisplayName())) {
            event.setCancelled(true);
        }
    }
    
    @SuppressWarnings({ "incomplete-switch", "deprecation" })
	@EventHandler
    public void onBlockPlaceEvent(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (ScpslUtil.checkIfInGame(player.getDisplayName()) && Scp.checkIfKeyWork(event.getBlock())) {
            final Switch button = (Switch)event.getBlock().getBlockData();
            BlockFace face = button.getFacing();
            switch (button.getFace()) {
                case FLOOR: {
                    face = BlockFace.UP;
                    break;
                }
                case CEILING: {
                    face = BlockFace.DOWN;
                    break;
                }
            }
            final Block behind = event.getBlock().getRelative(face.getOppositeFace());
            ItemStack itemStack = event.getItemInHand();
            switch (behind.getType()) {
                case YELLOW_WOOL: {
                    itemStack = new ItemStack(Material.OAK_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.YELLOW_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
                case ORANGE_WOOL: {
                    itemStack = new ItemStack(Material.SPRUCE_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.ORANGE_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
                case RED_WOOL: {
                    itemStack = new ItemStack(Material.BIRCH_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.RED_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
                case GREEN_WOOL: {
                    itemStack = new ItemStack(Material.JUNGLE_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.GREEN_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
                case BLUE_WOOL: {
                    itemStack = new ItemStack(Material.ACACIA_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.BLUE_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
                case BLACK_WOOL: {
                    itemStack = new ItemStack(Material.DARK_OAK_BUTTON);
                    player.getInventory().setItemInMainHand(itemStack);
                    ScpslPlugin.keycardLevel = Material.BLACK_WOOL;
                    ScpslPlugin.keyWall = behind.getLocation();
                    break;
                }
            }
            behind.setType(Material.REDSTONE_BLOCK);
            event.getBlock().setType(Material.AIR);
        }
    }
    
    @EventHandler
    public void onServerLeaveEvent(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        ScpslUtil.removeFromGame(player.getDisplayName());
        if (ScpslPlugin.debug) {
            ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " left");
        }
    }
    
    @EventHandler
    public void LockInv(final InventoryClickEvent event) {
        try {
            final int clickedSlot = event.getSlot();
            final Player player = (Player)event.getWhoClicked();
            final ItemStack clicked = player.getInventory().getItem(clickedSlot);
            final int[] blackList = { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 80, 81, 82, 83, 100, 101, 102, 103 };
            if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
                if (clicked != null) {
                    int[] array;
                    for (int length = (array = blackList).length, i = 0; i < length; ++i) {
                        final int it = array[i];
                        if (clickedSlot == it) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
                if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
                    event.setCancelled(true);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {}
    }
    
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        final Player victim = event.getEntity();
        final Player attacker = victim.getKiller();
        if (ScpslUtil.checkIfInGame(victim.getDisplayName())) {
            event.setDeathMessage("");
            final String pastTeam = ScpslPlugin.team.get(victim.getDisplayName());
            ScpslUtil.removeFromTeam(victim.getDisplayName(), pastTeam);
            if (ScpslUtil.getScpPlayer("PEANUT") == victim.getDisplayName()) {
                ScpslUtil.removeFromScp("PEANUT", victim.getDisplayName());
            }
            else if (ScpslUtil.getScpPlayer("SHYGUY") == victim.getDisplayName()) {
                ScpslUtil.removeFromScp("SHYGUY", victim.getDisplayName());
            }
            else if (ScpslUtil.getScpPlayer("DOGGO") == victim.getDisplayName()) {
                ScpslUtil.removeFromScp("DOGGO", victim.getDisplayName());
            }
            else if (ScpslUtil.getScpPlayer("OLDMAN") == victim.getDisplayName()) {
                ScpslUtil.removeFromScp("OLDMAN", victim.getDisplayName());
            }
            ScpslUtil.addToTeam(victim.getDisplayName(), "DEATH");
            if (ScpslPlugin.debug) {
                ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + victim.getDisplayName() + " was killed , they where " + pastTeam + ", and was killed by " + attacker);
            }
        }
    }
    
    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            event.setRespawnLocation(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 103.5, 135.0, 97.5));
            player.sendTitle("You have been Killed", "Don't worry you'll repsawn soon...", 10, 70, 20);
            if (ScpslPlugin.debug) {
                ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was add to Death Team");
            }
        }
    }
    
    @EventHandler
    public void onClick(final RightClickNPC event) {
        final Player player = event.getPlayer();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            if (event.getNPC().getName().equalsIgnoreCase("SCP: 173")) {
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 90.5, 63.0, 128.5));
                final Location blockAAt = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 134.0, 73.5);
                final Location blockBAt = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 135.0, 73.5);
                blockAAt.getBlock().setType(Material.BARRIER);
                blockBAt.getBlock().setType(Material.BARRIER);
                ScpslUtil.addToScp("PEANUT", player.getDisplayName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 19, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 99, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 6, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 6, false, false));
                if (ScpslPlugin.debug) {
                    ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was set 173");
                }
            }
            else if (event.getNPC().getName().equalsIgnoreCase("SCP: 096")) {
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 83.5, 74.0, 138.5));
                final Location location = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 134.0, 79.5);
                final Location blockBAt2 = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 102.5, 135.0, 79.5);
                location.getBlock().setType(Material.BARRIER);
                blockBAt2.getBlock().setType(Material.BARRIER);
                ScpslUtil.addToScp("SHYGUY", player.getDisplayName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 19, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 6, false, false));
                if (ScpslPlugin.debug) {
                    ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was set 096");
                }
            }
            else if (event.getNPC().getName().equalsIgnoreCase("SCP: 939")) {
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 69.5, 65.0, 116.5));
                final Location location2 = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 105.5, 134.0, 76.5);
                final Location blockBAt3 = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 105.5, 135.0, 76.5);
                location2.getBlock().setType(Material.BARRIER);
                blockBAt3.getBlock().setType(Material.BARRIER);
                ScpslUtil.addToScp("DOGGO", player.getDisplayName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 19, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 6, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 99, false, false));
                if (ScpslPlugin.debug) {
                    ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was set 939");
                }
            }
            else if (event.getNPC().getName().equalsIgnoreCase("SCP: 106")) {
                player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 40.5, 65.0, 128.5));
                final Location location3 = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 99.5, 134.0, 76.5);
                final Location blockBAt4 = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 99.5, 135.0, 76.5);
                location3.getBlock().setType(Material.BARRIER);
                blockBAt4.getBlock().setType(Material.BARRIER);
                ScpslUtil.addToScp("OLDMAN", player.getDisplayName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 19, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 6, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 2, false, false));
                if (ScpslPlugin.debug) {
                    ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " was set 106");
                }
            }
        }
    }
    
    @EventHandler
    public void onShyGuyTrigger(final ShyGuyTriggerEvent event) {
        final Player player = event.getPlayer();
        final Player target = event.getTarget();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 7, false, false));
            target.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30, 7, false, false));
        }
    }
    
    @EventHandler
    public void onPeanutTrigger(final PeanutTriggerEvent event) {
        final Player player = event.getPlayer();
        final Player target = event.getTarget();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 99, false, false));
        }
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        try {
            final Player player = event.getPlayer();
            if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
                switch (event.getClickedBlock().getType()) {
                    case STONE_BUTTON: {
                        if (!event.getClickedBlock().getLocation().equals((Object)new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 54.0, 51.0, 46.0))) {
                            break;
                        }
                        final World world = Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld());
                        final Location loc = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 54.0, 52.0, 46.0);
                        final Object[] control = world.getNearbyEntities(loc, 1.0, 1.0, 1.0).toArray();
                        if (control[0] instanceof ItemFrame) {
                            Scp.machineLogic(((ItemFrame)control[0]).getRotation());
                            break;
                        }
                        break;
                    }
                    case CAKE: {
                        event.getItem().setAmount(0);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 2, 1, false, false));
                        break;
                    }
                    default: {
                        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                            Gun.useGun(player.getDisplayName());
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (NullPointerException e) {}
    }
    
    @EventHandler
    public void onPlayerEntityInteract(final PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.PLAYER) {
            final Player player = event.getPlayer();
            final Player target = (Player)event.getRightClicked();
            if (ScpslUtil.checkIfInGame(player.getDisplayName()) && event.getPlayer() == Bukkit.getPlayer(ScpslUtil.getScpPlayer("OLDMAN"))) {
                target.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 39.5, 95.0, 101.5));
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1, 2, false, false));
            }
        }
    }
    
    @SuppressWarnings({ "unused", "incomplete-switch" })
	@EventHandler
    public void onPlayerMainHandChange(final PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStackPrev = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        final ItemStack itemStackNew = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            if (itemStackPrev != null && itemStackPrev.getItemMeta() != null) {
                final String displayName;
                switch (displayName = itemStackPrev.getItemMeta().getDisplayName()) {
                    case "pistol ammo": {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.RED_DYE));
                        break;
                    }
                    case "smg ammo": {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.BLUE_DYE));
                        break;
                    }
                    case "rifle ammo": {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.GREEN_DYE));
                        break;
                    }
                    case "lmg ammo": {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.YELLOW_DYE));
                        break;
                    }
                    case "heavy pistol ammo": {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.PURPLE_DYE));
                        break;
                    }
                    default:
                        break;
                }
            }
            if (itemStackNew != null && itemStackNew.getItemMeta() != null) {
                final ItemMeta itemStackMetaNew = itemStackNew.getItemMeta();
                switch (itemStackNew.getType()) {
                    case RED_DYE: {
                        itemStackMetaNew.setDisplayName("pistol ammo");
                        itemStackNew.setItemMeta(itemStackMetaNew);
                        break;
                    }
                    case BLUE_DYE: {
                        itemStackMetaNew.setDisplayName("smg ammo");
                        itemStackNew.setItemMeta(itemStackMetaNew);
                        break;
                    }
                    case GREEN_DYE: {
                        itemStackMetaNew.setDisplayName("rifle ammo");
                        itemStackNew.setItemMeta(itemStackMetaNew);
                        break;
                    }
                    case YELLOW_DYE: {
                        itemStackMetaNew.setDisplayName("lmg ammo");
                        itemStackNew.setItemMeta(itemStackMetaNew);
                        break;
                    }
                    case PURPLE_DYE: {
                        itemStackMetaNew.setDisplayName("heavy pistol ammo");
                        itemStackNew.setItemMeta(itemStackMetaNew);
                        break;
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("incomplete-switch")
	@EventHandler
    public void onPlayerItemPickup(final EntityPickupItemEvent event) {
        final Player player = (Player)event.getEntity();
        final ItemStack itemStack = event.getItem().getItemStack();
        final ItemMeta itemStackMeta = itemStack.getItemMeta();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            switch (event.getItem().getItemStack().getType()) {
                case OAK_BUTTON: {
                    itemStackMeta.setDisplayName("Level 1 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case SPRUCE_BUTTON: {
                    itemStackMeta.setDisplayName("Level 2 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BIRCH_BUTTON: {
                    itemStackMeta.setDisplayName("Level 3 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case JUNGLE_BUTTON: {
                    itemStackMeta.setDisplayName("Level 4 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case ACACIA_BUTTON: {
                    itemStackMeta.setDisplayName("Level 5 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case DARK_OAK_BUTTON: {
                    itemStackMeta.setDisplayName("O5 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case PAPER: {
                    itemStackMeta.setDisplayName("Playing Card");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GOLD_NUGGET: {
                    itemStackMeta.setDisplayName("Credit Card");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case WOODEN_HOE: {
                    itemStackMeta.setDisplayName("Pistol");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case STONE_HOE: {
                    itemStackMeta.setDisplayName("SMG");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_HOE: {
                    itemStackMeta.setDisplayName("MTF Rifle");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GOLDEN_HOE: {
                    itemStackMeta.setDisplayName("LMG");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case DIAMOND_HOE: {
                    itemStackMeta.setDisplayName("Heavy Pistol");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case RED_DYE: {
                    itemStackMeta.setDisplayName("pistol ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BLUE_DYE: {
                    itemStackMeta.setDisplayName("smg ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GREEN_DYE: {
                    itemStackMeta.setDisplayName("rifle ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case YELLOW_DYE: {
                    itemStackMeta.setDisplayName("lmg ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case PURPLE_DYE: {
                    itemStackMeta.setDisplayName("heavy pistol ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BOW: {
                    itemStackMeta.setDisplayName("MICRO-HID");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_NUGGET: {
                    itemStackMeta.setDisplayName("Coin");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_INGOT: {
                    itemStackMeta.setDisplayName("Steel");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case CAKE: {
                    itemStackMeta.setDisplayName("Health Kit");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
            }
        }
    }
    
    @SuppressWarnings("incomplete-switch")
	@EventHandler
    public void onPlayerArmourstandManipulate(final PlayerArmorStandManipulateEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getArmorStandItem();
        final ItemMeta itemStackMeta = itemStack.getItemMeta();
        if (ScpslUtil.checkIfInGame(player.getDisplayName())) {
            switch (event.getArmorStandItem().getType()) {
                case OAK_BUTTON: {
                    itemStackMeta.setDisplayName("Level 1 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case SPRUCE_BUTTON: {
                    itemStackMeta.setDisplayName("Level 2 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BIRCH_BUTTON: {
                    itemStackMeta.setDisplayName("Level 3 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case JUNGLE_BUTTON: {
                    itemStackMeta.setDisplayName("Level 4 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case ACACIA_BUTTON: {
                    itemStackMeta.setDisplayName("Level 5 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case DARK_OAK_BUTTON: {
                    itemStackMeta.setDisplayName("O5 Keycard");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case PAPER: {
                    itemStackMeta.setDisplayName("Playing Card");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GOLD_NUGGET: {
                    itemStackMeta.setDisplayName("Credit Card");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case WOODEN_HOE: {
                    itemStackMeta.setDisplayName("Pistol");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case STONE_HOE: {
                    itemStackMeta.setDisplayName("SMG");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_HOE: {
                    itemStackMeta.setDisplayName("MTF Rifle");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GOLDEN_HOE: {
                    itemStackMeta.setDisplayName("LMG");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case DIAMOND_HOE: {
                    itemStackMeta.setDisplayName("Heavy Pistol");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case RED_DYE: {
                    itemStackMeta.setDisplayName("pistol ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BLUE_DYE: {
                    itemStackMeta.setDisplayName("smg ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case GREEN_DYE: {
                    itemStackMeta.setDisplayName("rifle ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case YELLOW_DYE: {
                    itemStackMeta.setDisplayName("lmg ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case PURPLE_DYE: {
                    itemStackMeta.setDisplayName("heavy pistol ammo");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case BOW: {
                    itemStackMeta.setDisplayName("MICRO-HID");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_NUGGET: {
                    itemStackMeta.setDisplayName("Coin");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case IRON_INGOT: {
                    itemStackMeta.setDisplayName("Steel");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
                case CAKE: {
                    itemStackMeta.setDisplayName("Health Kit");
                    itemStack.setItemMeta(itemStackMeta);
                    break;
                }
            }
        }
    }
}
