package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
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
    public static String teleportComboCombination = "P P L ";
    public static String dodgeComboCombination = "P L P ";
    public static int teleportCooldownTime = 15;
}