package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Blue implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Blue(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> negativeCooldown = new HashMap<>();
    public static HashMap<Player,Integer> invisibleCooldown = new HashMap<>();
    public static HashMap<Player,Integer> swapCooldown = new HashMap<>();

    public static HashMap<Player,Integer> invisibleActiveTimePlayer = new HashMap<>();

    public static String negativeComboCombination = "P P L ";
    public static String invisibleComboCombination = "L L P ";
    public static String swapComboCombination = "L P P ";

    public static int negativeCooldownTime = 30;
    public static int invisibleCooldownTime = 30;
    public static int swapCooldownTime = 60;

    public static int invisibleActiveTime = 5;

    public static int swapPlayerDistance = 10;

    public static void comboPlayerSwapActive(Player player) {
        Location loc = player.getLocation().add(0, 1, 0).clone();
        Vector dir = loc.getDirection();
        for (double i = 1; i < swapPlayerDistance; i += 0.5) {
            dir.multiply(i);
            loc.add(dir);
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone(), 10, 0.5, 0.5, 0.5, 0, new Particle.DustOptions(Color.AQUA, 2), true);
            dir.normalize();
            boolean teleportPlayer = false;
            ArrayList<Entity> nearbyEntities = new ArrayList<>();
            nearbyEntities = (ArrayList<Entity>) loc.getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5);
            for (Entity entities : nearbyEntities) {
                if (entities instanceof Player) {
                    if (!player.equals(((Player) entities).getPlayer())) {
                        entities.getWorld().spawnParticle(Particle.REDSTONE, entities.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.PURPLE, 2));
                        Location teleportTo = player.getLocation().clone();
                        player.teleport(entities);
                        entities.teleport(teleportTo);
                        teleportPlayer=true;
                        swapCooldown.put(player, swapCooldownTime);
                        break;
                    }
                }
            }
            if(teleportPlayer){
               break;
            }
        }
    }

    public static void comboEffectActivate(Player player){
        ArrayList<Entity> nearbyEntities = new ArrayList<>();
        nearbyEntities = (ArrayList<Entity>) player.getNearbyEntities(10.0, 10.0, 10.0);
        for (Entity entities : nearbyEntities) {
            if (entities instanceof LivingEntity) {
                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, entities.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.BLUE, 2));
                ((LivingEntity) entities).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30 * 20, 3, false, false));
                ((LivingEntity) entities).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30 * 20, 3, false, false));
                ((LivingEntity) entities).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30 * 20, 3, false, false));
                if (entities.getWorld().getTime() > 13000 && entities.getWorld().getTime() < 23000) {
                    ((LivingEntity) entities).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30 * 20, 3, false, false));
                    ((LivingEntity) entities).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30 * 20, 3, false, false));
                }
            }
        }
        for (int i = 0; i <= 10; i += 2) {
            for (int d = 0; d <= 90; d += 1) {
                Location particleLoc = player.getLocation().clone();
                particleLoc.setX(player.getLocation().getX() + Math.cos(d) * i);
                particleLoc.setZ(player.getLocation().getZ() + Math.sin(d) * i);
                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(Color.AQUA, 3));
            }
        }
        negativeCooldown.put(player, negativeCooldownTime);
    }
    public static void comboInvisibleActivate(Player player){
        for (Player hidePlayer : Bukkit.getOnlinePlayers()) {
            hidePlayer.hidePlayer(plugin, player);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, invisibleActiveTime * 20, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, invisibleActiveTime * 20, 1, false, false));
        invisibleActiveTimePlayer.put(player, invisibleActiveTime);
    }
}