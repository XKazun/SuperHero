package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Blue implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Blue(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> negativeCooldown = new HashMap<>();
    public static HashMap<Player,Integer> invisibleCooldown = new HashMap<>();
    public static HashMap<Player,Integer> invisibleActiveTimePlayer = new HashMap<>();
    public static String negativeComboCombination = "P P L ";
    public static String invisibleComboCombination = "L L P ";
    public static int negativeCooldownTime = 30;
    public static int invisibleCooldownTime = 30;
    public static int invisibleActiveTime = 5;
}