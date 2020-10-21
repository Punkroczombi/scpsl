package tk.mythicaldimensions.scpsl.utility;

import org.bukkit.block.data.type.*;
import org.bukkit.block.*;
import tk.mythicaldimensions.scpsl.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public final class Scp
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Entity getNearestEntityInSight(final Player player, final int range) {
        final ArrayList<Entity> entities = (ArrayList<Entity>)player.getNearbyEntities((double)range, (double)range, (double)range);
        final ArrayList<Block> sightBlock = (ArrayList<Block>)player.getLineOfSight((Set)null, range);
        final ArrayList<Location> sight = new ArrayList<Location>();
        for (int i = 0; i < sightBlock.size(); ++i) {
            sight.add(sightBlock.get(i).getLocation());
        }
        for (int i = 0; i < sight.size(); ++i) {
            for (int k = 0; k < entities.size(); ++k) {
                if (Math.abs(entities.get(k).getLocation().getX() - sight.get(i).getX()) < 1.3 && Math.abs(entities.get(k).getLocation().getY() - sight.get(i).getY()) < 1.5 && Math.abs(entities.get(k).getLocation().getZ() - sight.get(i).getZ()) < 1.3) {
                    return entities.get(k);
                }
            }
        }
        return null;
    }
    
    @SuppressWarnings({ "incomplete-switch", "deprecation" })
	public static boolean checkIfKeyWork(final Block keycard) {
        boolean check = false;
        try {
            final Switch button = (Switch)keycard.getBlockData();
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
            final Block behind = keycard.getRelative(face.getOppositeFace());
            switch (behind.getType()) {
                case YELLOW_WOOL: {
                    if (keycard.getType() == Material.OAK_BUTTON || keycard.getType() == Material.SPRUCE_BUTTON || keycard.getType() == Material.BIRCH_BUTTON || keycard.getType() == Material.JUNGLE_BUTTON || keycard.getType() == Material.ACACIA_BUTTON || keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
                case ORANGE_WOOL: {
                    if (keycard.getType() == Material.SPRUCE_BUTTON || keycard.getType() == Material.BIRCH_BUTTON || keycard.getType() == Material.JUNGLE_BUTTON || keycard.getType() == Material.ACACIA_BUTTON || keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
                case RED_WOOL: {
                    if (keycard.getType() == Material.BIRCH_BUTTON || keycard.getType() == Material.JUNGLE_BUTTON || keycard.getType() == Material.ACACIA_BUTTON || keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
                case GREEN_WOOL: {
                    if (keycard.getType() == Material.JUNGLE_BUTTON || keycard.getType() == Material.ACACIA_BUTTON || keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
                case BLUE_WOOL: {
                    if (keycard.getType() == Material.ACACIA_BUTTON || keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
                case BLACK_WOOL: {
                    if (keycard.getType() == Material.DARK_OAK_BUTTON) {
                        check = true;
                        break;
                    }
                    break;
                }
            }
        }
        catch (ClassCastException e) {
            return check;
        }
        return check;
    }
    
    @SuppressWarnings("unused")
	public static void machineLogic(final Rotation control) {
        final Entity[] items = new Entity[64];
        final Location in = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 48.0, 51.0, 43.0);
        final Location out = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 60.5, 51.0, 43.5);
        final World world = Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld());
        final Collection<Entity> itemEntities = (Collection<Entity>)world.getNearbyEntities(in, 2.0, 2.0, 1.0);
        int i = 0;
        for (final Entity it : itemEntities) {
            if (it instanceof Item) {
                (items[i] = it).remove();
                ++i;
            }
        }
        switch (control) {
            case FLIPPED_45: {
                break;
            }
            case COUNTER_CLOCKWISE: {
                Entity[] array;
                for (int length = (array = items).length, j = 0; j < length; ++j) {
                    final Entity it = array[j];
                    final String displayName;
                    switch (displayName = ((Item)it).getItemStack().getItemMeta().getDisplayName()) {
                        case "Pistol": {
                            world.dropItem(out, new ItemStack(Material.IRON_INGOT));
                            continue;
                        }
                        case "MICRO-HID": {
                            world.dropItem(out, new ItemStack(Material.DIAMOND_HOE));
                            continue;
                        }
                        case "Level 1 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "pistol ammo": {
                            world.dropItem(out, new ItemStack(Material.IRON_NUGGET));
                            continue;
                        }
                        case "Level 3 Keycard": {
                            world.dropItem(out, new ItemStack(Material.SPRUCE_BUTTON));
                            continue;
                        }
                        case "smg ammo": {
                            world.dropItem(out, new ItemStack(Material.RED_DYE));
                            continue;
                        }
                        case "Playing Card": {
                            world.dropItem(out, new ItemStack(Material.IRON_NUGGET));
                            continue;
                        }
                        case "rifle ammo": {
                            world.dropItem(out, new ItemStack(Material.BLUE_DYE));
                            continue;
                        }
                        case "Level 5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.JUNGLE_BUTTON));
                            continue;
                        }
                        case "LMG": {
                            world.dropItem(out, new ItemStack(Material.IRON_HOE));
                            continue;
                        }
                        case "SMG": {
                            world.dropItem(out, new ItemStack(Material.WOODEN_HOE));
                            continue;
                        }
                        case "Steel": {
                            world.dropItem(out, new ItemStack(Material.IRON_NUGGET));
                            continue;
                        }
                        case "lmg ammo": {
                            world.dropItem(out, new ItemStack(Material.GREEN_DYE));
                            continue;
                        }
                        case "Heavy Pistol": {
                            world.dropItem(out, new ItemStack(Material.GOLDEN_HOE));
                            continue;
                        }
                        case "Level 2 Keycard": {
                            world.dropItem(out, new ItemStack(Material.OAK_BUTTON));
                            continue;
                        }
                        case "O5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.ACACIA_BUTTON));
                            continue;
                        }
                        case "heavy pistol ammo": {
                            world.dropItem(out, new ItemStack(Material.IRON_INGOT));
                            continue;
                        }
                        case "MTF Rifle": {
                            world.dropItem(out, new ItemStack(Material.STONE_HOE));
                            continue;
                        }
                        case "Level 4 Keycard": {
                            world.dropItem(out, new ItemStack(Material.BIRCH_BUTTON));
                            continue;
                        }
                        default:
                            break;
                    }
                    world.dropItem(out, ((Item)it).getItemStack());
                }
                break;
            }
            case NONE: {
                Entity[] array2;
                for (int length2 = (array2 = items).length, k = 0; k < length2; ++k) {
                    final Entity it = array2[k];
                    final String displayName2;
                    switch (displayName2 = ((Item)it).getItemStack().getItemMeta().getDisplayName()) {
                        case "Pistol": {
                            world.dropItem(out, new ItemStack(Material.STONE_HOE));
                            continue;
                        }
                        case "MICRO-HID": {
                            world.dropItem(out, new ItemStack(Material.IRON_NUGGET));
                            continue;
                        }
                        case "Level 1 Keycard": {
                            world.dropItem(out, new ItemStack(Material.SPRUCE_BUTTON));
                            continue;
                        }
                        case "pistol ammo": {
                            world.dropItem(out, new ItemStack(Material.BLUE_DYE));
                            continue;
                        }
                        case "Level 3 Keycard": {
                            world.dropItem(out, new ItemStack(Material.GOLD_NUGGET));
                            continue;
                        }
                        case "smg ammo": {
                            world.dropItem(out, new ItemStack(Material.GREEN_DYE));
                            continue;
                        }
                        case "Playing Card": {
                            world.dropItem(out, new ItemStack(Material.OAK_BUTTON));
                            continue;
                        }
                        case "rifle ammo": {
                            world.dropItem(out, new ItemStack(Material.YELLOW_DYE));
                            continue;
                        }
                        case "Level 5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.DARK_OAK_BUTTON));
                            continue;
                        }
                        case "LMG": {
                            world.dropItem(out, new ItemStack(Material.DIAMOND_HOE));
                            continue;
                        }
                        case "SMG": {
                            world.dropItem(out, new ItemStack(Material.IRON_HOE));
                            continue;
                        }
                        case "Steel": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "lmg ammo": {
                            world.dropItem(out, new ItemStack(Material.PURPLE_DYE));
                            continue;
                        }
                        case "Heavy Pistol": {
                            world.dropItem(out, new ItemStack(Material.BOW));
                            continue;
                        }
                        case "Level 2 Keycard": {
                            world.dropItem(out, new ItemStack(Material.BIRCH_BUTTON));
                            continue;
                        }
                        case "O5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.GOLD_INGOT));
                            continue;
                        }
                        case "heavy pistol ammo": {
                            world.dropItem(out, new ItemStack(Material.IRON_NUGGET));
                            continue;
                        }
                        case "MTF Rifle": {
                            world.dropItem(out, new ItemStack(Material.GOLDEN_HOE));
                            continue;
                        }
                        case "Level 4 Keycard": {
                            world.dropItem(out, new ItemStack(Material.ACACIA_BUTTON));
                            continue;
                        }
                        default:
                            break;
                    }
                    world.dropItem(out, ((Item)it).getItemStack());
                }
                break;
            }
            case CLOCKWISE_45: {
                break;
            }
            default: {
                Entity[] array3;
                for (int length3 = (array3 = items).length, l = 0; l < length3; ++l) {
                    final Entity it = array3[l];
                    final String displayName3;
                    switch (displayName3 = ((Item)it).getItemStack().getItemMeta().getDisplayName()) {
                        case "Level 1 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "Level 3 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "Playing Card": {
                            world.dropItem(out, new ItemStack(Material.OAK_BUTTON));
                            continue;
                        }
                        case "Level 5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "Steel": {
                            world.dropItem(out, new ItemStack(Material.GOLD_NUGGET));
                            continue;
                        }
                        case "Level 2 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "O5 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        case "Credit Card": {
                            world.dropItem(out, new ItemStack(Material.BIRCH_BUTTON));
                            continue;
                        }
                        case "Level 4 Keycard": {
                            world.dropItem(out, new ItemStack(Material.PAPER));
                            continue;
                        }
                        default:
                            break;
                    }
                    world.dropItem(out, ((Item)it).getItemStack());
                }
                break;
            }
        }
    }
    
    public static void escape() {
        final Location loc = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 164.0, 86.0, 6.0);
        final World world = Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld());
        final Collection<Entity> entities = (Collection<Entity>)world.getNearbyEntities(loc, 2.0, 2.0, 2.0);
        if (!entities.isEmpty()) {
            for (final Entity it : entities) {
                if (it instanceof Player) {
                    final Player player = Bukkit.getPlayer(((Player)it).getDisplayName());
                    final String s;
                    switch ((s = ScpslPlugin.team.get(player.getDisplayName())).hashCode()) {
                        case -1658902972: {
                            if (!s.equals("SCIENCE")) {
                                continue;
                            }
                            ScpslUtil.removeFromTeam(player.getDisplayName(), "SCIENCE");
                            ScpslUtil.addToTeam(player.getDisplayName(), "MTF");
                            player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 214.5, 96.0, 7.5));
                            if (it != null) {
                                Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(player.getDisplayName()), "essentials loadkit " + it + " mtf");
                            }
                            player.sendTitle("You Escaped", "Secure the facility", 10, 70, 20);
                            if (ScpslPlugin.debug) {
                                ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " escaped as SCIENCE");
                                continue;
                            }
                            continue;
                        }
                        case 2010987412: {
                            if (!s.equals("DCLASS")) {
                                continue;
                            }
                            ScpslUtil.removeFromTeam(player.getDisplayName(), "DCLASS");
                            ScpslUtil.addToTeam(player.getDisplayName(), "CHAOS");
                            player.teleport(new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 214.5, 91.0, 119.5));
                            if (it != null) {
                                Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(player.getDisplayName()), "essentials loadkit " + it + " chaos");
                            }
                            player.sendTitle("You Escaped", "Capture the objective", 10, 70, 20);
                            if (ScpslPlugin.debug) {
                                ScpslPlugin.getInstance().getLogger().info("[SCPSL] " + player.getDisplayName() + " escaped as DCLASS");
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public static void nukeBoom() {
        final BoundingBox aaBB1 = new BoundingBox(225.0, 84.0, 157.0, -11.0, 51.0, 38.0);
        final BoundingBox aaBB2 = new BoundingBox(66.0, 237.0, 79.0, -15.0, 250.0, 117.0);
        final World world = Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld());
        final Collection<Entity> entities1 = (Collection<Entity>)world.getNearbyEntities(aaBB1);
        final Collection<Entity> entities2 = (Collection<Entity>)world.getNearbyEntities(aaBB2);
        for (final Entity it : entities1) {
            if (it instanceof Player) {
                final Player player = Bukkit.getPlayer(it.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 0.0f);
                ((Damageable)it).damage(1000.0);
            }
        }
        for (final Entity it : entities2) {
            if (it instanceof Player) {
                final Player player = Bukkit.getPlayer(it.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 0.0f);
                ((Damageable)it).damage(1000.0);
            }
        }
    }
}
