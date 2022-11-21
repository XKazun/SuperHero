package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class Green implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Green(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> SuperStrengthCooldown = new HashMap<>();
    public static HashMap<Player,Integer> SuperStrengthActive = new HashMap<>();

    public static HashMap<Player,Integer> SuperPunchCooldown = new HashMap<>();

    public static ArrayList<Entity> PunchEntity = new ArrayList<>();
    public static ArrayList<Entity> PowerPunchEntity = new ArrayList<>();
    public static String SuperStrengthComboCombination = "P P L ";
    public static int SuperStrengthCooldownTime = 60;
    public static int SuperStrengthActiveTime = 30;
    public static int SuperPunchCooldownTime = 2;

    @EventHandler
    public void onPlayerSuperStrength(EntityDamageByEntityEvent e) {
        if(e.getDamager()instanceof Player) {
            if(e.getEntity()instanceof LivingEntity) {
                Player p = (Player) e.getDamager();
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Green")) {
                    if (SuperStrengthActive.containsKey(p)) {
                        if(e.getDamage() * 40<= ((LivingEntity)e.getEntity()).getHealth()){
                            ((LivingEntity)e.getEntity()).setHealth(1);
                        }else{
                            e.setDamage(e.getDamage() * 40);
                        }
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            e.getEntity().setVelocity(p.getLocation().getDirection().multiply(1.5d).setY(0.7d));
                            PowerPunchEntity.add(e.getEntity());
                        }, 2);
                        p.setVelocity(p.getLocation().getDirection().multiply(-0.5d).setY(0.3d));
                        return;
                       }

                    if (!SuperPunchCooldown.containsKey(p)) {

                        e.setDamage(e.getDamage() * 10);
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            e.getEntity().setVelocity(p.getLocation().getDirection().multiply(3d).setY(2.0d));
                            PunchEntity.add(e.getEntity());
                        }, 2);
                        e.getEntity().getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, e.getEntity().getLocation(), 30, 1, 1, 1, 0, null, true);

                        SuperPunchCooldown.put(p,SuperPunchCooldownTime);
                    }
                }

            }
        }
    }
    @EventHandler
    public void onPlayerResistance(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Green")) {
                e.setDamage(e.getDamage()/2);
            }
        }
    }
}