package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Fire implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Fire(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public static HashMap<Player,Integer> FireJumpActiveTime = new HashMap<>();
    public static HashMap<Player,Integer> FireJumpCooldown = new HashMap<>();
    public static int activeTime = 30;
    public static int cooldownTime = 90;
    public static String burnComboCombination = "P P L ";
    public static HashMap<Player,String> ActualComboCombination = new HashMap<>();
    public static HashMap<Player,Integer> ActualComboTime = new HashMap<>();
    public static HashMap<Player,Integer> burnComboCooldown = new HashMap<>();
    public static int millisecondsToMakeCombination = 10;
    public static int burnCooldownTime = 60;
    public static HashMap<Player,Integer>lavaWalkTime = new HashMap<>();
    public static HashMap<Player,Integer> lavaWalkCooldown = new HashMap<>();
    public static int lavaWalkCooldownTime = 60;
    public static int lavaWalkActiveTime = 20;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!e.getPlayer().hasPlayedBefore()){
            plugin.getConfig().set("Players."+ e.getPlayer().getName() +".Power", "Brak");
            plugin.saveConfig();
        }else{
            ArrayList<String> configPlayers = new ArrayList<>(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("Players.")).getKeys(false));
            if(!configPlayers.contains(p.getName())) {
                plugin.getConfig().set("Players."+ e.getPlayer().getName() +".Power", "Brak");
                plugin.saveConfig();
            }
        }
    }
    @EventHandler
    public void onPlayerUseBurn(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (p.getInventory().getItemInMainHand() != null) {
                if (p.getInventory().getItemInMainHand().isSimilar(new ItemStack(Material.LEATHER_HORSE_ARMOR))) {
                    if (!ActualComboCombination.isEmpty()) {
                        if (ActualComboCombination.containsKey(p)) {
                            ActualComboCombination.put(p, ActualComboCombination.get(p) + "L ");
                        } else {
                            ActualComboCombination.put(p, "L ");
                        }
                    } else {
                        ActualComboCombination.put(p, "L ");
                    }
                }
                Utils.actionBar(p, "&aCombo: &6" + ActualComboCombination.get(p));
                ActualComboTime.put(p, millisecondsToMakeCombination);
            }
        }
    }
    @EventHandler
    public void onPlayerUseBurn(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getHand() != EquipmentSlot.HAND) return;
        if(e.getItem()!= null) {
            if (e.getItem().isSimilar(new ItemStack(Material.LEATHER_HORSE_ARMOR))) {
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    if (!ActualComboCombination.isEmpty()) {
                        if (ActualComboCombination.containsKey(p)) {
                            ActualComboCombination.put(p, ActualComboCombination.get(p) + "P ");
                        } else {
                            ActualComboCombination.put(p, "P ");
                        }
                    } else {
                        ActualComboCombination.put(p, "P ");
                    }
                }
                if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
                    if (!ActualComboCombination.isEmpty()) {
                        if (ActualComboCombination.containsKey(p)) {
                            ActualComboCombination.put(p, ActualComboCombination.get(p) + "L ");
                        } else {
                            ActualComboCombination.put(p, "L ");
                        }
                    } else {
                        ActualComboCombination.put(p, "L ");
                    }
                }
                Utils.actionBar(p, "&aCombo: &6" + ActualComboCombination.get(p));
                ActualComboTime.put(p, millisecondsToMakeCombination);
            }
        }
    }
    @Deprecated
    @EventHandler
    public void onPlayerUseFlyBurst(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.isOnGround()){
            if (e.getPlayer ().getGameMode () == GameMode.CREATIVE || e.getPlayer ().getGameMode () == GameMode.SPECTATOR) return;
            if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                if(p.getInventory().getChestplate()!=null) {
                    if (p.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
                        p.getInventory().setChestplate(null);
                    }
                }
            }
        }
        if(p.isInWater()){
            if (e.getPlayer ().getGameMode () == GameMode.CREATIVE || e.getPlayer ().getGameMode () == GameMode.SPECTATOR) return;
            if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                if (!FireJumpActiveTime.isEmpty()) {
                    if (FireJumpActiveTime.containsKey(p)) {
                        if (p.getInventory().getChestplate() != null) {
                            FireJumpActiveTime.remove(p);
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 0);
                            if (p.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
                                p.getInventory().setChestplate(null);
                            }
                        }
                    }
                }
            }
        }
        if(p.isGliding()){
            if (p.isSneaking()) {
                if (!p.getAllowFlight()) {
                    if (!FireJumpActiveTime.isEmpty()) {
                        if (FireJumpActiveTime.containsKey(e.getPlayer())) {
                            if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                                if (p.getVelocity().length() < p.getLocation().getDirection().multiply(1.5).length()) {
                                    p.setVelocity(p.getLocation().getDirection().multiply(1.5));
                                    p.getLocation().getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 20, 0.7, 0.2, 0.7, new Particle.DustOptions(Color.RED, 1));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!p.getAllowFlight()) {
            if (p.isSneaking()) {
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                    Location location = p.getPlayer().getLocation();
                    location = location.subtract(0, 0.5, 0);
                    Block block = location.getBlock();
                    if (block.getType().isSolid()){
                        p.setAllowFlight(true);
                    }
                }
            }
        }
        if (!FireJumpActiveTime.isEmpty()) {
            if(FireJumpActiveTime.containsKey(p)) {
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                    p.spawnParticle(Particle.FLAME, p.getLocation().add(0, -0.3, 0), 25, 0.2, 0.2, 0.2, 0);
                }
            }
        }
    }
    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        if (plugin.getConfig().getString("Players." + e.getPlayer().getName() + ".Power").equalsIgnoreCase("Fire")) {
            if (FireJumpActiveTime.isEmpty()) {
                FireJumpActiveTime.put(e.getPlayer(), activeTime);
            } else if (!FireJumpActiveTime.containsKey(e.getPlayer())) {
                FireJumpActiveTime.put(e.getPlayer(), activeTime);
            }
        }
        if (!FireJumpCooldown.isEmpty()) {
            if (FireJumpCooldown.containsKey(e.getPlayer())) {
                Utils.actionBar(e.getPlayer(), "&cUmiejętność jeszcze nie gotowa, poczekaj &6" + FireJumpCooldown.get(e.getPlayer()) + " sekund");
                return;
            }
        }
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;
        e.setCancelled(true);
        Player p = e.getPlayer();
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 1);
        e.setCancelled(true);
        e.getPlayer().setAllowFlight(false);
        p.setVelocity(p.getLocation().getDirection().multiply(0.6d).setY(2.0d));
        p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
        for (int i = 0; i <= 3; i += 1) {
            for (int d = 0; d <= 90; d += 1) {
                Location particleLoc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
                particleLoc.setX(p.getLocation().getX() + Math.cos(d) * i);
                p.getLocation().getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(Color.RED, 1));
            }
        }
    }
    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerToggleFlight(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL) || e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
                    return;
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerMoveOnLava(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location goTo = e.getTo();
        Block block = goTo.getBlock();
        block = block.getLocation().add(0,-0.5,0).getBlock();
        if (block.getType().equals(Material.LAVA)) {
            BlockData blockData = block.getBlockData();
            if(((Levelled) blockData).getLevel() ==0) {
                if (plugin.getConfig().getString("Players." + p.getName() + ".Power").equalsIgnoreCase("Fire")) {
                    if (lavaWalkCooldown.containsKey(p) && !lavaWalkTime.containsKey(p)) {
                        Utils.actionBar(e.getPlayer(), "&6Chodzenie po lawie &abędzie aktywne za &6&l" + lavaWalkCooldown.get(p) + " sekund");
                        return;
                    }
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
                    replaceLavaToObsidian(block, 2);
                    if (!lavaWalkTime.containsKey(p)) {
                        lavaWalkTime.put(p, lavaWalkActiveTime);
                    }
                    return;
                }
            }
        }
    }
    void replaceLavaToObsidian(Block block,int radius){
        int radiusSquared = radius * radius;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if ((x * x) + (z * z) <= radiusSquared) {
                    Location placeLocation = block.getLocation();
                    int finalX = x;
                    int finalZ = z;
                    if(placeLocation.add(finalX, 0, finalZ).getBlock().getType().equals(Material.LAVA)) {
                        Block blockPlace = placeLocation.getBlock();
                        BlockData blockDataPlace = blockPlace.getBlockData();
                        if (((Levelled) blockDataPlace).getLevel() == 0) {
                            placeLocation.getBlock().setType(Material.OBSIDIAN);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                                placeLocation.getBlock().setType(Material.LAVA);
                            }, 20 * 5);
                        }
                    }
                }
            }
        }
    }
}