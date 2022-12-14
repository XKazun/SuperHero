package kazun.superhero.Main;

import kazun.superhero.Powers.*;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class Timers {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public static void OneSceonds() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                if (!Fire.FireJumpActiveTime.isEmpty()) {
                    if (Fire.FireJumpActiveTime.containsKey(onlinePlayers)) {
                        int timeLeft = Fire.FireJumpActiveTime.get(onlinePlayers);
                        double percentage = ((double) timeLeft) / Fire.activeTime * 100;
                        String message = "";
                        String messageFly = "";
                        if (Fire.lavaWalkTime.containsKey(onlinePlayers)) {
                            int timeLeftFly = Fire.lavaWalkTime.get(onlinePlayers);
                            double percentageFly = ((double) timeLeftFly) / Fire.lavaWalkActiveTime * 100;
                            for (int i = 0; i < 20; i++) {
                                if (i == 10) {
                                    messageFly = messageFly + "&6 " + timeLeftFly + " ";
                                }
                                if (i * 5 < percentageFly) {
                                    messageFly = messageFly + "&a▌";
                                } else {
                                    messageFly = messageFly + "&7▌";
                                }
                            }
                        }
                        for (int i = 0; i < 20; i++) {
                            if (i == 10) {
                                message = message + "&6 " + timeLeft + " ";
                            }
                            if (i * 5 < percentage) {
                                message = message + "&a▌";
                            } else {
                                message = message + "&7▌";
                            }
                        }
                        if (messageFly != "") {
                            message = message + " &b| " + messageFly;
                        }
                        Utils.actionBar(onlinePlayers, message);
                        if (timeLeft <= 0) {
                            Fire.FireJumpActiveTime.remove(onlinePlayers);
                            onlinePlayers.setGliding(false);
                            Fire.FireJumpCooldown.put(onlinePlayers, Fire.cooldownTime);
                        } else {
                            timeLeft--;
                            Fire.FireJumpActiveTime.put(onlinePlayers, timeLeft);
                        }
                    }
                }
                if (!Fire.FireJumpCooldown.isEmpty()) {
                    if (Fire.FireJumpCooldown.containsKey(onlinePlayers)) {
                        int timeLeft = Fire.FireJumpCooldown.get(onlinePlayers);
                        if (timeLeft <= 0) {
                            Fire.FireJumpCooldown.remove(onlinePlayers);
                        } else {
                            timeLeft--;
                            Fire.FireJumpCooldown.put(onlinePlayers, timeLeft);

                        }
                    }
                }
                if (!Fire.burnComboCooldown.isEmpty()) {
                    if (Fire.burnComboCooldown.containsKey(onlinePlayers)) {
                        int timeLeft = Fire.burnComboCooldown.get(onlinePlayers);
                        if (timeLeft <= 0) {
                            Fire.burnComboCooldown.remove(onlinePlayers);
                        } else {
                            timeLeft--;
                            Fire.burnComboCooldown.put(onlinePlayers, timeLeft);

                        }
                    }
                }
                if (!Fire.lavaWalkCooldown.isEmpty()) {
                    if (Fire.lavaWalkCooldown.containsKey(onlinePlayers)) {
                        int i = Fire.lavaWalkCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Fire.lavaWalkCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Fire.lavaWalkCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Purple.teleportCooldown.isEmpty()) {
                    if (Purple.teleportCooldown.containsKey(onlinePlayers)) {
                        int i = Purple.teleportCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Purple.teleportCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Purple.teleportCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Red.waterWalkCooldown.isEmpty()) {
                    if (Red.waterWalkCooldown.containsKey(onlinePlayers)) {
                        int i = Red.waterWalkCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Red.waterWalkCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Red.waterWalkCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Green.SuperStrengthCooldown.isEmpty()) {
                    if (Green.SuperStrengthCooldown.containsKey(onlinePlayers)) {
                        int i = Green.SuperStrengthCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Green.SuperStrengthCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Green.SuperStrengthCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Green.SuperPunchCooldown.isEmpty()) {
                    if (Green.SuperPunchCooldown.containsKey(onlinePlayers)) {
                        int i = Green.SuperPunchCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Green.SuperPunchCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Green.SuperPunchCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Blue.negativeCooldown.isEmpty()) {
                    if (Blue.negativeCooldown.containsKey(onlinePlayers)) {
                        int i = Blue.negativeCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Blue.negativeCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Blue.negativeCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Blue.invisibleCooldown.isEmpty()) {
                    if (Blue.invisibleCooldown.containsKey(onlinePlayers)) {
                        int i = Blue.invisibleCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Blue.invisibleCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Blue.invisibleCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Blue.swapCooldown.isEmpty()) {
                    if (Blue.swapCooldown.containsKey(onlinePlayers)) {
                        int i = Blue.swapCooldown.get(onlinePlayers);
                        i--;
                        if (i < 0) {
                            Blue.swapCooldown.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        } else {
                            Blue.swapCooldown.put(onlinePlayers, i);
                        }
                    }
                }
                if (!Blue.invisibleActiveTimePlayer.isEmpty()) {
                    if (Blue.invisibleActiveTimePlayer.containsKey(onlinePlayers)) {
                        int timeLeft = Blue.invisibleActiveTimePlayer.get(onlinePlayers);
                        if (timeLeft < 0) {
                            Blue.invisibleActiveTimePlayer.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            Blue.invisibleCooldown.put(onlinePlayers, Blue.invisibleCooldownTime);
                            for(Player showPlayer:Bukkit.getOnlinePlayers()){
                                showPlayer.showPlayer(plugin,onlinePlayers);
                            }
                        } else {
                            double percentage = ((double) timeLeft) / Blue.invisibleActiveTime * 100;
                            String message = "";
                            for (int i = 0; i < 20; i++) {
                                if (i == 10) {
                                    message = message + "&6 " + timeLeft + " ";
                                }
                                if (i * 5 < percentage) {
                                    message = message + "&a▌";
                                } else {
                                    message = message + "&7▌";
                                }

                            }
                            Utils.actionBar(onlinePlayers, message);
                            Blue.invisibleActiveTimePlayer.put(onlinePlayers, timeLeft - 1);
                        }
                    }
                }
                if (!Fire.lavaWalkTime.isEmpty()) {
                    if (Fire.lavaWalkTime.containsKey(onlinePlayers)) {
                        int timeLeft = Fire.lavaWalkTime.get(onlinePlayers);
                        if (timeLeft <= 0) {
                            Fire.lavaWalkTime.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            Fire.lavaWalkCooldown.put(onlinePlayers, Fire.lavaWalkCooldownTime);
                        } else {
                            double percentage = ((double) timeLeft) / Fire.lavaWalkActiveTime * 100;
                            String message = "";
                            String messageFly = "";
                            if (Fire.FireJumpActiveTime.containsKey(onlinePlayers)) {
                                int timeLeftFly = Fire.FireJumpActiveTime.get(onlinePlayers);
                                double percentageFly = ((double) timeLeft) / Fire.activeTime * 100;

                                for (int i = 0; i < 20; i++) {
                                    if (i == 10) {
                                        messageFly = messageFly + "&6 " + timeLeftFly + " ";
                                    }
                                    if (i * 5 < percentageFly) {
                                        messageFly = messageFly + "&a▌";
                                    } else {
                                        messageFly = messageFly + "&7▌";
                                    }
                                }

                            }
                            for (int i = 0; i < 20; i++) {
                                if (i == 10) {
                                    message = message + "&6 " + timeLeft + " ";
                                }
                                if (i * 5 < percentage) {
                                    message = message + "&a▌";
                                } else {
                                    message = message + "&7▌";
                                }

                            }
                            if (messageFly != "") {
                                message = message + " &b| " + messageFly;
                            }
                            Utils.actionBar(onlinePlayers, message);
                            Fire.lavaWalkTime.put(onlinePlayers, timeLeft - 1);
                        }
                    }
                }
                if (!Red.waterWalkTime.isEmpty()) {
                    if (Red.waterWalkTime.containsKey(onlinePlayers)) {
                        int timeLeft = Red.waterWalkTime.get(onlinePlayers);
                        if (timeLeft <= 0) {
                            Red.waterWalkTime.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            Red.waterWalkCooldown.put(onlinePlayers, Red.waterWalkCooldownTime);
                            Red.activateSpeed.remove(onlinePlayers);
                        } else {
                            double percentage = ((double) timeLeft) / Red.waterWalkActiveTime * 100;
                            String message = "";
                            String messageFly = "";
                            if (Red.waterWalkCooldown.containsKey(onlinePlayers)) {
                                int timeLeftFly = Red.waterWalkCooldown.get(onlinePlayers);
                                double percentageFly = ((double) timeLeft) / Red.waterWalkActiveTime * 100;

                                for (int i = 0; i < 20; i++) {
                                    if (i == 10) {
                                        messageFly = messageFly + "&6 " + timeLeftFly + " ";
                                    }
                                    if (i * 5 < percentageFly) {
                                        messageFly = messageFly + "&a▌";
                                    } else {
                                        messageFly = messageFly + "&7▌";
                                    }
                                }

                            }
                            for (int i = 0; i < 20; i++) {
                                if (i == 10) {
                                    message = message + "&6 " + timeLeft + " ";
                                }
                                if (i * 5 < percentage) {
                                    message = message + "&a▌";
                                } else {
                                    message = message + "&7▌";
                                }

                            }
                            if (messageFly != "") {
                                message = message + " &b| " + messageFly;
                            }
                            Utils.actionBar(onlinePlayers, message);
                            Red.waterWalkTime.put(onlinePlayers, timeLeft - 1);
                        }
                    }
                }
                if (!Green.SuperStrengthActive.isEmpty()) {
                    if (Green.SuperStrengthActive.containsKey(onlinePlayers)) {
                        int timeLeft = Green.SuperStrengthActive.get(onlinePlayers);
                        if (timeLeft <= 0) {
                            Green.SuperStrengthActive.remove(onlinePlayers);
                            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            Green.SuperStrengthCooldown.put(onlinePlayers, Green.SuperStrengthCooldownTime);
                        } else {
                            double percentage = ((double) timeLeft) / Green.SuperStrengthActiveTime * 100;
                            String message = "";
                            String messageFly = "";
                            if (Green.SuperStrengthCooldown.containsKey(onlinePlayers)) {
                                int timeLeftFly = Green.SuperStrengthCooldown.get(onlinePlayers);
                                double percentageFly = ((double) timeLeft) / Green.SuperStrengthActiveTime * 100;

                                for (int i = 0; i < 20; i++) {
                                    if (i == 10) {
                                        messageFly = messageFly + "&6 " + timeLeftFly + " ";
                                    }
                                    if (i * 5 < percentageFly) {
                                        messageFly = messageFly + "&a▌";
                                    } else {
                                        messageFly = messageFly + "&7▌";
                                    }
                                }

                            }
                            for (int i = 0; i < 20; i++) {
                                if (i == 10) {
                                    message = message + "&6 " + timeLeft + " ";
                                }
                                if (i * 5 < percentage) {
                                    message = message + "&a▌";
                                } else {
                                    message = message + "&7▌";
                                }

                            }
                            if (messageFly != "") {
                                message = message + " &b| " + messageFly;
                            }
                            Utils.actionBar(onlinePlayers, message);
                            Green.SuperStrengthActive.put(onlinePlayers, timeLeft - 1);
                        }
                    }
                }

            }
        }, 0, 20);
    }

    public static void TeleportDelay(int Delay) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {

        }, Delay);
    }

    public static void OneTick() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if(!Fire.ActualComboCombination.isEmpty()) {
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    ArrayList<String> configPlayers = new ArrayList<>(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("Players.")).getKeys(false));
                    if (configPlayers.contains(onlinePlayers.getName())) {
                        if (Fire.ActualComboCombination.containsKey(onlinePlayers)) {
                            if (!Fire.ActualComboTime.isEmpty()) {
                                if (Fire.ActualComboTime.containsKey(onlinePlayers)) {
                                    Fire.ActualComboTime.put(onlinePlayers, Fire.ActualComboTime.get(onlinePlayers) - 1);
                                    int actualTime = Fire.ActualComboTime.get(onlinePlayers);
                                    if (actualTime <= 0) {
                                        String ActualComboCombination = Fire.ActualComboCombination.get(onlinePlayers);
                                        Fire.ActualComboCombination.remove(onlinePlayers);
                                        Fire.ActualComboTime.remove(onlinePlayers);
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Fire")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Fire.burnComboCombination)) {
                                                if (!Fire.burnComboCooldown.isEmpty()) {
                                                    if (Fire.burnComboCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Fire.burnComboCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Fire.burnComboCooldown.put(onlinePlayers, Fire.burnCooldownTime);
                                                for (int i = 1; i <= 7; i += 2) {
                                                    if (i != 1) {
                                                        for (Block b : getBlocks(onlinePlayers.getLocation(), i, true, false)) {
                                                            if (b.isEmpty()) {
                                                                b.setType(Material.FIRE);
                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                                                    b.setType(Material.AIR);
                                                                }, 6 * i);
                                                            }

                                                        }
                                                    }
                                                    ArrayList<Entity> entities = new ArrayList<>();
                                                    entities = (ArrayList<Entity>) onlinePlayers.getWorld().getNearbyEntities(onlinePlayers.getLocation(), i / 1.1, 2.5, i / 1.1);
                                                    for (Entity entity : entities) {
                                                        if (!entity.isVisualFire()) {
                                                            if (entity instanceof LivingEntity) {
                                                                if (!entity.getType().equals(EntityType.PLAYER)) {
                                                                    entity.setFireTicks(20 * 10);
                                                                } else {
                                                                    Player player = ((Player) entity).getPlayer();
                                                                    if (!player.equals(onlinePlayers)) {
                                                                        entity.setFireTicks(20 * 10);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    int finalI = i;
                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                                        for (int d = 0; d <= 90; d += 1) {
                                                            Location particleLoc = new Location(onlinePlayers.getLocation().getWorld(), onlinePlayers.getLocation().getX(), onlinePlayers.getLocation().getY(), onlinePlayers.getLocation().getZ());
                                                            particleLoc.setX(onlinePlayers.getLocation().getX() + Math.cos(d) * finalI);
                                                            particleLoc.setZ(onlinePlayers.getLocation().getZ() + Math.sin(d) * finalI);
                                                            onlinePlayers.getLocation().getWorld().spawnParticle(Particle.FLAME, particleLoc.add(0, 1, 0), 10, 0, 1, 0, 0);
                                                        }
                                                    }, i * 2);
                                                }
                                            }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Red")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Red.speedComboCombination)) {
                                                if (!Red.speedCooldown.isEmpty()) {
                                                    if (Red.speedCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Red.speedCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 8, false, false));
                                                onlinePlayers.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30 * 20, 3, false, false));
                                                Red.activateSpeed.put(onlinePlayers, true);
                                                Red.waterWalkTime.put(onlinePlayers, 30);
                                            }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Purple")) {
                                                if (ActualComboCombination.equalsIgnoreCase(Purple.dodgeComboCombination)) {
                                                    Purple.comboPlayerDodgeActivate(onlinePlayers);
                                                }
                                                if (ActualComboCombination.equalsIgnoreCase(Purple.teleportComboCombination)) {
                                                    if (!Purple.teleportCooldown.isEmpty()) {
                                                        if (Purple.teleportCooldown.containsKey(onlinePlayers)) {
                                                            Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Purple.teleportCooldown.get(onlinePlayers) + " sekund");
                                                            return;
                                                        }
                                                    }
                                                    Purple.comboPlayerTeleportActivate(onlinePlayers);
                                                }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Blue")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Blue.negativeComboCombination)) {
                                                if (!Blue.negativeCooldown.isEmpty()) {
                                                    if (Blue.negativeCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Blue.negativeCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Blue.comboEffectActivate(onlinePlayers);
                                            }
                                            if (ActualComboCombination.equalsIgnoreCase(Blue.invisibleComboCombination)) {
                                                if (!Blue.invisibleCooldown.isEmpty()) {
                                                    if (Blue.invisibleCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Blue.invisibleCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Blue.comboInvisibleActivate(onlinePlayers);
                                            }
                                            if (ActualComboCombination.equalsIgnoreCase(Blue.swapComboCombination)) {
                                                if (!Blue.swapCooldown.isEmpty()) {
                                                    if (Blue.swapCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Blue.swapCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Blue.comboPlayerSwapActive(onlinePlayers);
                                            }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Green")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Green.SuperStrengthComboCombination)) {
                                                if (!Green.SuperStrengthCooldown.isEmpty()) {
                                                    if (Green.SuperStrengthCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Green.SuperStrengthCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Green.SuperStrengthActive.put(onlinePlayers, Green.SuperStrengthActiveTime);
                                            }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Yellow")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Yellow.lightningComboCombination)) {
                                                createLighting(onlinePlayers.getEyeLocation(), 30, onlinePlayers);
                                            }
                                            if (ActualComboCombination.equalsIgnoreCase(Yellow.lightningsComboCombination)) {
                                                Yellow.makeLightningHittingGround(onlinePlayers);
                                            }
                                        }
                                        if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Robot")) {
                                            if (ActualComboCombination.equalsIgnoreCase(Robot.healWaveComboCombination)) {
                                                if (!Robot.healWaveCooldown.isEmpty()) {
                                                    if (Robot.healWaveCooldown.containsKey(onlinePlayers)) {
                                                        Utils.actionBar(onlinePlayers, "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + Robot.healWaveCooldown.get(onlinePlayers) + " sekund");
                                                        return;
                                                    }
                                                }
                                                Robot.healWave(onlinePlayers);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!Green.PunchEntity.isEmpty()) {
                            if (Green.PunchEntity != null) {
                                for (Entity entity : Green.PunchEntity) {
                                    if (entity != null) {
                                        if (entity.isDead()) {
                                            Green.PunchEntity.remove(entity);
                                        }
                                        entity.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, entity.getLocation().add(0, 1, 0), 5, 0.2, 0.2, 0.2, 0, null, true);
                                        if (entity.isOnGround() || entity.getVelocity().length() < 0.1) {
                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                                Green.PunchEntity.remove(entity);
                                            }, 1);

                                        }
                                    }
                                }
                            }
                        }
                        if (!Green.PowerPunchEntity.isEmpty()) {
                            if (Green.PowerPunchEntity != null) {
                                for (Entity entity : Green.PowerPunchEntity) {
                                    if (entity != null) {
                                        if (entity.isDead()) {
                                            Green.PowerPunchEntity.remove(entity);
                                        }
                                        entity.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation(), 5, 0.2, 0.3, 0.2, 1, null, true);
                                        if (entity.getVelocity().length() < 0.1) {
                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                                Green.PowerPunchEntity.remove(entity);
                                            }, 1);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },0, 1);

    }
    public static final Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        // Z_Axis rotation (Pitch)
        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        // Y_Axis rotation (Yaw)
        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }
    public static void createLighting(Location location,int distance,Player player){
        Location loc = location.clone();
        Vector dir = loc.getDirection();
        for (double i = 1; i < distance; i+=0.5) {
            dir.multiply(i);
            loc.add(dir);
            location.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0,-0.7,0),5,0.1,0.1,0.1,0, new Particle.DustOptions(Color.YELLOW, 1),true);
            dir.normalize();
            ArrayList<Entity> nearbyEntities = new ArrayList<>();
            nearbyEntities = (ArrayList<Entity>) loc.getWorld().getNearbyEntities(loc,2.0, 2.0, 2.0);
            for (Entity entities : nearbyEntities) {
                if (entities instanceof LivingEntity) {
                    if(entities instanceof Player) {
                        if (!player.equals(((Player) entities).getPlayer())){
                            loc.getWorld().spawnParticle(Particle.REDSTONE, entities.getLocation().add(0,1,0), 30,0.5,0.5,0.5, new Particle.DustOptions(Color.RED, 2));
                            if(((LivingEntity) entities).getHealth()<=Yellow.lightningDamage){
                                ((LivingEntity) entities).setHealth(0);
                            }else ((LivingEntity) entities).setHealth(((LivingEntity) entities).getHealth() -Yellow.lightningDamage);
                           }
                    }else {
                        loc.getWorld().spawnParticle(Particle.REDSTONE, entities.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.RED, 2));
                        if(((LivingEntity) entities).getHealth()<=Yellow.lightningDamage){
                            ((LivingEntity) entities).setHealth(0);
                        }else ((LivingEntity) entities).setHealth(((LivingEntity) entities).getHealth() -Yellow.lightningDamage);
                    }
                }
            }
            Random random = new Random();
            int randomInt = random.nextInt(3)+1;
            if(randomInt >= 2){
                double distanceFromEyes = (random.nextInt(100)-50)/100+4;
                double distanceFromEyeCenter = (random.nextInt(100)-50)/10;
                double distanceFromEyeCenterDown = (random.nextInt(100)-50)/10;
                Vector vector = rotateVector(new Vector(distanceFromEyes, distanceFromEyeCenterDown, distanceFromEyeCenter), 0, 0);
                double distances = loc.distance(loc.clone().add(vector));
                Vector p1 = loc.toVector();
                Vector p2 = loc.clone().add(vector).toVector();
                Vector vectors = p2.clone().subtract(p1).normalize().multiply(0.5);
                double length = 0;
                for (; length < distances; p1.add(vectors)) {
                    location.getWorld().spawnParticle(Particle.REDSTONE, p1.getX(),p1.getY(),p1.getZ(),5,0.1,0.1,0.1,0, new Particle.DustOptions(Color.YELLOW, 1),true);
                    length += 0.5;
                }
            }
        }


    }
    public static void TenTick() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                ArrayList<String> configPlayers = new ArrayList<>(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("Players.")).getKeys(false));
                if(!configPlayers.contains(onlinePlayers.getName())){
                    return;
                }
                if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Green")) {
                    if(!Green.SuperStrengthActive.isEmpty()) {
                        if (Green.SuperStrengthActive.containsKey(onlinePlayers)) {
                            Location eyeLocation = onlinePlayers.getLocation();

                            double distanceFromEyes = -0.05;
                            double distanceFromEyeCenter = 0.4;
                            Vector leftEye = rotateVector(new Vector(distanceFromEyes, 0.85, distanceFromEyeCenter), eyeLocation.getYaw(), 0);
                            Vector rightEye = rotateVector(new Vector(distanceFromEyes, 0.85, -distanceFromEyeCenter), eyeLocation.getYaw(), 0);

                            Location leftLocation = eyeLocation.clone().add(leftEye);
                            Location rightLocation = eyeLocation.clone().add(rightEye);
                            onlinePlayers.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, leftLocation, 5, 0.15, 0.1, 0.1,0);
                            onlinePlayers.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, rightLocation, 5, 0.15, 0.1, 0.1,0);
                        }
                    }
                }
                if (plugin.getConfig().getString("Players." + onlinePlayers.getName() + ".Power").equalsIgnoreCase("Purple")) {
                    if (Purple.teleportCooldown.isEmpty()) {
                        Purple.spawnTeleportParticle(onlinePlayers);
                    }
                }
            }
        }, 0, 10);
    }
    public static List<Location> circle(Location loc, int radius, int height, boolean hollow, boolean sphere, int plusY) {
        List<Location> circleblocks = new ArrayList<Location>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (sphere ? cy - radius : cy); y < (sphere ? cy
                        + radius : cy + height); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z)
                            + (sphere ? (cy - y) * (cy - y) : 0);

                    if (dist < radius * radius
                            && !(hollow && dist < (radius - 1) * (radius - 1))) {
                        Location l = new Location(loc.getWorld(), x, y + plusY,
                                z);
                        circleblocks.add(l);
                    }
                }
            }
        }

        return circleblocks;
    }
    public static List<Block> getBlocks(Location center, int radius,
                                        boolean hollow, boolean sphere) {
        List<Location> locs = circle(center, radius, radius, hollow, sphere, 0);
        List<Block> blocks = new ArrayList<Block>();

        for (Location loc : locs) {
            blocks.add(loc.getBlock());
        }

        return blocks;
    }
}
