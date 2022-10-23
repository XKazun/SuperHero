package kazun.superhero.Powers;

import kazun.superhero.Main.SuperHero;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Yellow implements Listener {
    public static Plugin plugin = SuperHero.getPlugin(SuperHero.class);

    public Yellow(SuperHero plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static HashMap<Player,Integer> lightningsCooldown = new HashMap<>();
    public static String lightningsComboCombination = "P P L ";
    public static String lightningComboCombination = "P L P ";
    public static int lightningsCooldownTime = 30;
}