package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

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

    public static void openChooseInv(Player player){
        Inventory Inv = Bukkit.createInventory(null, 9, Utils.chat("&0Wybierz gracza do zamiany"));
        int slot =1;
        for(Player onlinePlayers:Bukkit.getOnlinePlayers()){
            if(!onlinePlayers.equals(player)) {
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
                playerHeadMeta.setOwningPlayer(onlinePlayers);
                playerHead.setItemMeta(playerHeadMeta);
                Utils.createItemStack(Inv, playerHead, slot, "&a" + onlinePlayers.getName());
                slot++;
                if (slot > Inv.getSize()) {
                    Inv = Bukkit.createInventory(null, Inv.getSize() + 9, Utils.chat("&0Wybierz gracza do zamiany"));
                }
            }
        }
        player.openInventory(Inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().contains(Utils.chat("&0Wybierz gracza do zamiany"))) {
            e.setCancelled(true);
            if (e.isShiftClick()) return;
            if (e.getCurrentItem() == null || (e.getCurrentItem().getType().equals(Material.AIR))) {
                return;
            } else {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                name = name.replace(Utils.chat("&a"),"");
                Player player = Bukkit.getPlayer(name);
                Location playerLocation = player.getLocation();
                Location playerLocation2 = p.getLocation();
                player.teleport(playerLocation2);
                p.teleport(playerLocation);

                player.getLocation().getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 20, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.AQUA, 1));
                p.getLocation().getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 20, 0.5, 0.5, 0.5, new Particle.DustOptions(Color.AQUA, 1));

                p.closeInventory();
                return;
            }
        }
    }
}