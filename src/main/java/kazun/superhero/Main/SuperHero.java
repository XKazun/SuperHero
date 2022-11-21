package kazun.superhero.Main;

import kazun.superhero.Commands.Hero;
import kazun.superhero.Powers.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperHero extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("Hero").setExecutor(new Hero(this));
        new Fire(this);
        new Red(this);
        new Purple(this);
        new Green(this);
        new Blue(this);
        new Yellow(this);
        Timers.OneSceonds();
        Timers.OneTick();
        Timers.TenTick();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
