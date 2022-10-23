package kazun.superhero.Main;

import kazun.superhero.Commands.Hero;
import kazun.superhero.Powers.Fire;
import kazun.superhero.Powers.Green;
import kazun.superhero.Powers.Purple;
import kazun.superhero.Powers.Red;
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
        Timers.OneSceonds();
        Timers.OneTick();
        Timers.TenTick();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
