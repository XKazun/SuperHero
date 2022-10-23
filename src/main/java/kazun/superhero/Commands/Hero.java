package kazun.superhero.Commands;

import kazun.superhero.Main.SuperHero;
import kazun.superhero.Main.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class Hero implements CommandExecutor {

    public Plugin plugin;
    public Hero(SuperHero plugin) {
        this.plugin = plugin;
    }
    @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("Hero")) {
            if(args.length==2){
                if(args[0] != null){
                    if(args[1].equalsIgnoreCase("Fire")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                    if(args[1].equalsIgnoreCase("Red")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                    if(args[1].equalsIgnoreCase("Purple")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                    if(args[1].equalsIgnoreCase("Blue")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                    if(args[1].equalsIgnoreCase("Yellow")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                    if(args[1].equalsIgnoreCase("Green")){
                        plugin.getConfig().set("Players."+ args[0] +".Power", args[1]);
                        p.sendMessage(Utils.chat("&aUstawiono moc graczowi &6" + args[0]+" &ana &b" +args[1]));
                        plugin.saveConfig();
                    }
                }
            }
        }

        return false;

    }
}
