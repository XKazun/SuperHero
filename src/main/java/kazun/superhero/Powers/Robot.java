package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Timers;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class Robot implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Robot(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> healWaveCooldown = new HashMap<>();
    public static String healWaveComboCombination = "P P L ";
    public static int healWaveCooldownTime = 30;
    public static int healWavePower = 7;

    public static void healWave(Player player) {
        Location location = player.getLocation().add(0, 1, 0);
        Location loc = location.clone();
        Vector dir = loc.getDirection();
        ArrayList<LivingEntity> helled = new ArrayList<>();
        for (double i = 1; i < 3; i += 0.2) {
            double finalI = i;
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                dir.multiply(finalI);
                loc.add(dir);
                ovalparticles(player, 1, loc.clone());
                ovalparticles(player, 2, loc.clone());
                ovalparticles(player, 3, loc.clone());
                dir.normalize();
                ArrayList<Entity> nearbyEntities = new ArrayList<>();
                nearbyEntities = (ArrayList<Entity>) loc.getWorld().getNearbyEntities(loc, 3, 1.2, 3);
                for (Entity entities : nearbyEntities) {
                    boolean itsUsingPlayer = false;
                    if (entities instanceof LivingEntity) {
                        if (!helled.contains((LivingEntity) entities)) {
                            if (entities instanceof Player) {
                                if (((Player) entities).getPlayer().equals(player)) {
                                    itsUsingPlayer = true;
                                }
                            }
                            if (!itsUsingPlayer) {
                                loc.getWorld().spawnParticle(Particle.HEART, entities.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5);
                            }
                            if (20 <= ((LivingEntity) entities).getHealth() + healWavePower) {
                                ((LivingEntity) entities).setHealth(20);
                            } else
                                ((LivingEntity) entities).setHealth(((LivingEntity) entities).getHealth() + healWavePower);
                            helled.add((LivingEntity) entities);
                        }
                    }
                }
            }, (long) (5 * i));
        }
    }
    private static Vector rotateAroundAxisX(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    private static Vector rotateAroundAxisX1(Vector v, double angle) {
        angle = Math.toRadians(angle);
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;

        y = y * 0.5;

        return v.setY(y).setZ(z);
    }

    private static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static void ovalparticles(Entity player, double f,Location location) {

        double points = f * 20; //amount of points to be generated
        for (int i = 0; i < 360; i += 360 / points) {
            double angle = (i * Math.PI / 180);
            double x = f * Math.cos(angle);
            double z = f * Math.sin(angle);

            Vector v0 = new Vector(x, 0, z);
            rotateAroundAxisX1(v0, 90);
            Vector v01 = new Vector(v0.getX(), v0.getY(), v0.getZ());
            rotateAroundAxisX(v01, -90);

            Vector v = new Vector(v01.getX(), v01.getY(), v01.getZ());
            rotateAroundAxisX(v, location.clone().getPitch() - 90);
            Vector v2 = new Vector(v.getX(), v.getY(), v.getZ());
            rotateAroundAxisY(v2,location.clone().getYaw());

            Location loc = location.clone().add(v2.getX(), v2.getY(), v2.getZ());

            player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, (float) (loc.getX()), (float) loc.getY(), (float) (loc.getZ()), 2, 0, 0, 0, 0, null, true);
        }}
}