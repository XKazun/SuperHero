package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class Yellow implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Yellow(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> lightningsCooldown = new HashMap<>();
    public static String lightningsComboCombination = "P P L ";
    public static String lightningComboCombination = "P L P ";
    public static int lightningsCooldownTime = 30;
    public static int lightningDamage = 10;


    public static void makeLightningHittingGround(Player player){
        Location location = player.getTargetBlock(null, 50).getLocation();
        location.getWorld().strikeLightning(location);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            location.getWorld().strikeLightning(location);
        }, 10);
        blockVelocityUp(location,5);
        ArrayList<Entity> nearbyEntities = new ArrayList<>();
        location.getWorld().spawnParticle(Particle.CLOUD, location, 100, 3.0, 3.0, 3.0, 0.1,null,true);
        nearbyEntities = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, 2.0, 2.0, 2.0);
        for (Entity entities : nearbyEntities) {
            boolean itsCaster = false;
            if (entities instanceof LivingEntity) {
                if (entities instanceof Player) {
                    if (player.equals(((Player) entities).getPlayer())) {
                        itsCaster= true;
                    }
                }
                if(!itsCaster) {
                    if (((LivingEntity) entities).getHealth() - lightningDamage <=0) {
                        ((LivingEntity) entities).setHealth(0);
                    } else ((LivingEntity) entities).setHealth(((LivingEntity) entities).getHealth() - lightningDamage);
                    location.getWorld().spawnParticle(Particle.REDSTONE, entities.getLocation().add(0, 1, 0), 30, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.RED, 2));
                }
            }
        }
    }
    public static void blockVelocityUp(Location location,int radius){
        ArrayList<Block> blocksLocation = new ArrayList<>();
        blocksLocation = getSphere(location,radius,false);
        for(Block blockLocation:blocksLocation) {
            Block block = blockLocation;
            BlockData emptyBlock = Bukkit.createBlockData(block.getType());
            FallingBlock floatingBlock = location.getWorld().spawnFallingBlock(location, emptyBlock);
            floatingBlock.setGravity(true);
            floatingBlock.setDropItem(false);
            floatingBlock.setHurtEntities(true);
            floatingBlock.setCustomName("Destroy");
            floatingBlock.setCustomNameVisible(false);
            float x = randFloat(-0.7F, 0.7F);
            float y = randFloat(-1.3F, 1.3F);
            float z = randFloat(-0.7F, 0.7F);
            floatingBlock.setVelocity(new Vector(x, y, z));
            BlockData beforeDestroy = block.getBlockData().clone();
            block.setType(Material.AIR);
            Random random = new Random();
            int randomInt = random.nextInt(30)+1;
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                block.setBlockData(beforeDestroy,false);
            }, (randomInt*2) + 60);
        }
    }
    public static float randFloat(float min, float max) {

        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;

    }
    @EventHandler
    public void removeFallingBlock(EntityChangeBlockEvent e) {
        if(e.getEntity().getCustomName() ==null)return;
        if(e.getEntity().getCustomName().contains("Destroy")){
           e.setCancelled(true);
        }
    }
    public static ArrayList<Block> getSphere(Location location, int radius, boolean empty) {
        ArrayList<Block> blocks = new ArrayList<>();

        int bx = location.getBlockX();
        int by = location.getBlockY();
        int bz = location.getBlockZ();

        for (int x = bx - radius; x <= bx + radius; x++) {
            for (int y = by + radius; y >= by - radius; y--) {
                for (int z = bz - radius; z <= bz + radius; z++) {
                    double distance = ((bx - x) * (bx - x) + (bz - z) * (bz - z) + (by - y) * (by - y));
                    if (distance < radius * radius && (!empty && distance < (radius - 1) * (radius - 1))) {
                        if(!new Location(location.getWorld(), x, y, z).getBlock().isLiquid()) {
                            if(!new Location(location.getWorld(), x, y, z).getBlock().isEmpty()) {
                                if(!(new Location(location.getWorld(), x, y, z).getBlock().getState() instanceof TileState)) {
                                    if(!(new Location(location.getWorld(), x, y+1, z).getBlock().getState() instanceof TileState)&&
                                            !(new Location(location.getWorld(), x-1, y, z).getBlock().getState() instanceof TileState)&&
                                            !(new Location(location.getWorld(), x+1, y, z).getBlock().getState() instanceof TileState)&&
                                            !(new Location(location.getWorld(), x, y, z+1).getBlock().getState() instanceof TileState)&&
                                            !(new Location(location.getWorld(), x, y, z-1).getBlock().getState() instanceof TileState)) {
                                        blocks.add(new Location(location.getWorld(), x, y, z).getBlock());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return blocks;
    }
    public static void makeLightningFromHand(){

    }


}