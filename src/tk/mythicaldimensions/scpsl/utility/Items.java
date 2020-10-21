package tk.mythicaldimensions.scpsl.utility;

import org.bukkit.inventory.*;
import tk.mythicaldimensions.scpsl.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

@SuppressWarnings("unused")
public final class Items
{
    private static final Location GRINDER_GUN;
    private static final Location GRINDER_LOCKER;
    private static final Location MUSIC;
    private static final Location L_ARMOURY_TABLE_A;
    private static final Location L_ARMOURY_TABLE_B;
    private static final Location L_ARMOURY_TABLE_C;
    private static final Location BATHROOM_TABLE_A;
    private static final Location BATHROOM_TABLE_B;
    private static final Location PUBLIC_CLASS;
    private static final Location H_ARMOURY_TABLE;
    private static final Location H_ARMOURY_FLOOR;
    private static final Location SHYGUY_FLOOR;
    private static final Location SERVER_FLOOR;
    private static final Location MICRO_TABLE;
    private static final Location NUKE_TABLE_A;
    private static final Location NUKE_TABLE_B;
    private static final Location DOCTOR_TABLE_A;
    private static final Location DOCTOR_TABLE_B;
    private static final Location GUARD_LOCKER_A;
    private static final Location GUARD_LOCKER_B;
    private static final Location GUARD_LOCKER_C;
    private static final Location GUARD_LOCKER_D;
    private static final ItemStack HEALTH_KIT;
    private static final ItemStack LVL_1_KEYCARD;
    private static final ItemStack LVL_2_KEYCARD;
    private static final ItemStack LVL_3_KEYCARD;
    private static final ItemStack LVL_4_KEYCARD;
    private static final ItemStack LVL_5_KEYCARD;
	private static final ItemStack O5_KEYCARD;
    private static final ItemStack MICRO_HID;
    private static final ItemStack PISTOL;
    private static final ItemStack SMG;
    private static final ItemStack RIFLE;
    private static final ItemStack LMG;
    private static final ItemStack HEAVY_PISTOL;
    private static final ItemStack PISTOL_AMMO;
    private static final ItemStack SMG_AMMO;
    private static final ItemStack RIFLE_AMMO;
    private static final ItemStack LMG_AMMO;
    private static final ItemStack HEAVY_PISTOL_AMMO;
    static double randomNum;
    
    static {
        GRINDER_GUN = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 25.5, 51.95, 57.5);
        GRINDER_LOCKER = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 22.5, 69.95, 62.5);
        MUSIC = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 59.5, 44.95, 81.0);
        L_ARMOURY_TABLE_A = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 53.5, 50.95, 113.5);
        L_ARMOURY_TABLE_B = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 54.5, 50.95, 113.5);
        L_ARMOURY_TABLE_C = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 55.5, 50.95, 113.5);
        BATHROOM_TABLE_A = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 60.5, 50.95, 94.5);
        BATHROOM_TABLE_B = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 60.5, 50.95, 88.5);
        PUBLIC_CLASS = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 0.5, 49.95, 103.5);
        H_ARMOURY_TABLE = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 78.5, 73.95, 95.5);
        H_ARMOURY_FLOOR = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 80.5, 72.95, 96.5);
        SHYGUY_FLOOR = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 82.5, 72.95, 139.5);
        SERVER_FLOOR = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 106.5, 72.95, 126.5);
        MICRO_TABLE = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 108.5, 73.95, 104.5);
        NUKE_TABLE_A = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 87.5, 58.95, 80.5);
        NUKE_TABLE_B = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 87.5, 57.95, 82.5);
        DOCTOR_TABLE_A = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 57.5, 241.95, 106.5);
        DOCTOR_TABLE_B = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 57.5, 241.95, 105.5);
        GUARD_LOCKER_A = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 192.5, 75.95, 89.5);
        GUARD_LOCKER_B = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 192.5, 75.95, 85.5);
        GUARD_LOCKER_C = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 201.5, 73.95, 87.5);
        GUARD_LOCKER_D = new Location(Bukkit.getWorld(ScpslPlugin.getInternalConfig().getMiniWorld()), 200.5, 72.95, 82.5);
        HEALTH_KIT = new ItemStack(Material.CAKE);
        LVL_1_KEYCARD = new ItemStack(Material.OAK_BUTTON);
        LVL_2_KEYCARD = new ItemStack(Material.SPRUCE_BUTTON);
        LVL_3_KEYCARD = new ItemStack(Material.BIRCH_BUTTON);
        LVL_4_KEYCARD = new ItemStack(Material.JUNGLE_BUTTON);
        LVL_5_KEYCARD = new ItemStack(Material.ACACIA_BUTTON);
        O5_KEYCARD = new ItemStack(Material.DARK_OAK_BUTTON);
        MICRO_HID = new ItemStack(Material.BOW);
        PISTOL = new ItemStack(Material.WOODEN_HOE);
        SMG = new ItemStack(Material.STONE_HOE);
        RIFLE = new ItemStack(Material.IRON_HOE);
        LMG = new ItemStack(Material.GOLDEN_HOE);
        HEAVY_PISTOL = new ItemStack(Material.DIAMOND_HOE);
        PISTOL_AMMO = new ItemStack(Material.RED_DYE);
        SMG_AMMO = new ItemStack(Material.BLUE_DYE);
        RIFLE_AMMO = new ItemStack(Material.GREEN_DYE);
        LMG_AMMO = new ItemStack(Material.YELLOW_DYE);
        HEAVY_PISTOL_AMMO = new ItemStack(Material.PURPLE_DYE);
        Items.randomNum = Math.random() * 101.0;
    }
    
    public static void spawnLight() {
        if (Items.randomNum > 60.0) {
            spawnItem(Items.GRINDER_GUN, Items.PISTOL);
        }
        spawnItem(Items.GRINDER_LOCKER, Items.LVL_3_KEYCARD);
        spawnItem(Items.MUSIC, Items.LVL_2_KEYCARD);
        spawnItem(Items.L_ARMOURY_TABLE_A, Items.HEAVY_PISTOL);
        spawnItem(Items.L_ARMOURY_TABLE_B, Items.HEALTH_KIT);
        spawnItem(Items.L_ARMOURY_TABLE_C, Items.SMG);
        if (Items.randomNum >= 50.0) {
            spawnItem(Items.BATHROOM_TABLE_A, Items.PISTOL);
        }
        if (Items.randomNum <= 50.0) {
            spawnItem(Items.BATHROOM_TABLE_B, Items.LVL_1_KEYCARD);
        }
        spawnItem(Items.PUBLIC_CLASS, Items.LVL_2_KEYCARD);
    }
    
    public static void spawnHeavy() {
        spawnItem(Items.H_ARMOURY_TABLE, Items.SMG_AMMO);
        spawnItem(Items.H_ARMOURY_FLOOR, Items.RIFLE_AMMO);
        spawnItem(Items.SHYGUY_FLOOR, Items.LVL_4_KEYCARD);
        spawnItem(Items.SERVER_FLOOR, Items.LVL_3_KEYCARD);
        spawnItem(Items.MICRO_TABLE, Items.MICRO_HID);
        spawnItem(Items.NUKE_TABLE_A, Items.RIFLE);
        spawnItem(Items.NUKE_TABLE_B, Items.RIFLE_AMMO);
        spawnItem(Items.DOCTOR_TABLE_A, Items.RIFLE);
        spawnItem(Items.DOCTOR_TABLE_B, Items.HEALTH_KIT);
    }
    
    public static void spawnEntrance() {
        spawnItem(Items.GUARD_LOCKER_A, Items.SMG);
        spawnItem(Items.GUARD_LOCKER_B, Items.LVL_4_KEYCARD);
        spawnItem(Items.GUARD_LOCKER_C, Items.HEALTH_KIT);
        spawnItem(Items.GUARD_LOCKER_D, Items.SMG_AMMO);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void spawnItem(final Location loc, final ItemStack item) {
        final ArmorStand itemHolder = (ArmorStand)loc.getWorld().spawn(loc, (Class)ArmorStand.class);
        itemHolder.setInvulnerable(false);
        itemHolder.setVisible(false);
        itemHolder.setGravity(false);
        itemHolder.setCanPickupItems(false);
        itemHolder.getEquipment().setHelmet(new ItemStack(item));
        itemHolder.setHeadPose(new EulerAngle(100.0, 105.0, 0.0));
    }
    
    public static void purgeItems(final World world) {
        for (final String it : ScpslPlugin.inGame.keySet()) {
            Bukkit.dispatchCommand((CommandSender)Bukkit.getPlayer(it), "clear");
        }
        for (final Entity e : world.getEntities()) {
            if (e.getType() == EntityType.ARMOR_STAND) {
                e.remove();
            }
        }
    }
}
