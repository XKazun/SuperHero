package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class Purple implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Purple(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> teleportCooldown = new HashMap<>();
    public static HashMap<Player,Location> teleportLocation = new HashMap<>();
    public static String teleportComboCombination = "P P L ";
    public static String dodgeComboCombination = "P L P ";
    public static int teleportCooldownTime = 15;
    public static int teleportDistance = 30;
    public static int teleportDodgeDistance = 5;

    public static void comboPlayerTeleportActivate(Player player){
        Location locationToTeleport = teleportLocation.get(player);
        locationToTeleport.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 100, 0.5, 0.9, 0.5, 0, new Particle.DustOptions(Color.PURPLE, 1));
        locationToTeleport.getWorld().spawnParticle(Particle.REDSTONE, locationToTeleport, 100, 0.5, 0.9, 0.5, 0, new Particle.DustOptions(Color.PURPLE, 1));
        teleportCooldown.put(player, teleportCooldownTime);
        player.teleport(locationToTeleport);
    }
    public static void spawnTeleportParticle(Player player) {
        if (player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_HORSE_ARMOR)) {
            Location loc = player.getLocation();
            Vector dir = loc.getDirection();
            Location locationToTeleport = player.getLocation();
            for (int i = 0; i < teleportDistance; i++) {
                loc = player.getLocation();
                loc.add(0, 0.5, 0);
                dir = loc.getDirection();
                dir.normalize();
                dir.multiply(i);
                loc.add(dir);
                if (loc.getBlock().isEmpty()) {
                    locationToTeleport = loc;
                }
            }
            teleportLocation.put(player,locationToTeleport);
            player.spawnParticle(Particle.REDSTONE, locationToTeleport.add(0, 1, 0), 25, 0.2, 0.7, 0.2, new Particle.DustOptions(Color.PURPLE, 1));
        }
    }
    public static void comboPlayerDodgeActivate(Player player){
        Location loc = player.getLocation();
        Vector dir = loc.getDirection();
        Location locationToTeleport = player.getLocation();
        for (int i = 0; i < teleportDodgeDistance; i++) {
            loc = player.getLocation();
            loc.add(0, 0.5, 0);
            dir = loc.getDirection();
            dir.normalize();
            dir.multiply(-i);
            loc.add(dir);
            if (loc.getBlock().isEmpty()) {
                locationToTeleport = loc;
            }
        }
        locationToTeleport.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 100, 0.5, 0.9, 0.5, 0, new Particle.DustOptions(Color.PURPLE, 1));
        locationToTeleport.getWorld().spawnParticle(Particle.REDSTONE, locationToTeleport, 100, 0.5, 0.9, 0.5, 0, new Particle.DustOptions(Color.PURPLE, 1));
        Location finalLoc = locationToTeleport;
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.teleport(finalLoc);
        }, 2);
    }
}