package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Red implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Red(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public static boolean activateSpeed = false;
    public static HashMap<Player,Integer> speedActiveTime = new HashMap<>();
    public static HashMap<Player,Integer> speedCooldown = new HashMap<>();
    public static String speedComboCombination = "P P L ";
    public static HashMap<Player,String> ActualComboCombination = new HashMap<>();
    public static HashMap<Player,Integer> ActualComboTime = new HashMap<>();

    public static HashMap<Player,Integer>waterWalkTime = new HashMap<>();
    public static HashMap<Player,Integer> waterWalkCooldown = new HashMap<>();
    public static int waterWalkCooldownTime = 60;
    public static int waterWalkActiveTime = 30;

    @Deprecated
    @EventHandler
    public void onPlayerUseSpeed(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(activateSpeed){
            p.getLocation().getWorld().spawnParticle(Particle.DRIP_LAVA, p.getLocation(), 30, 0.2, 0.7, 0.2, 0.1);
            p.getLocation().getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 30, 0.2, 0.7, 0.2, new Particle.DustOptions(Color.RED, 1));
        }
    }
    @EventHandler
    public void onPlayerMoveOnLava(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location goTo = e.getTo();
        Block block = goTo.getBlock();
        block = block.getLocation().add(0,-0.5,0).getBlock();
        if (block.getType().equals(Material.WATER)) {
            BlockData blockData = block.getBlockData();
            if(((Levelled) blockData).getLevel() ==0) {
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Red")) {
                    if(activateSpeed) {
                        replaceWaterToLeaves(block, 2);
                    }
                }
            }
        }
    }
    void replaceWaterToLeaves(Block block,int radius){
        int radiusSquared = radius * radius;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) <= radiusSquared) {
                    Location placeLocation = block.getLocation();
                    int finalX = x;
                    int finalZ = z;
                    if(placeLocation.add(finalX, 0, finalZ).getBlock().getType().equals(Material.WATER)) {
                        Block blockPlace = placeLocation.getBlock();
                        BlockData blockDataPlace = blockPlace.getBlockData();
                        if (((Levelled) blockDataPlace).getLevel() == 0) {
                            placeLocation.getBlock().setType(Material.BARRIER);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                placeLocation.getBlock().setType(Material.WATER);
                            }, 20 * 2);
                        }
                    }
                }
            }
        }
    }
}