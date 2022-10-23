package kazun.superhero.Main;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String chat (String s) {
        if(Bukkit.getServer().getVersion().contains("1.19") || Bukkit.getServer().getVersion().contains("1.17")|| Bukkit.getServer().getVersion().contains("1.18")){
            Matcher match = pattern.matcher(s);
            while (match.find()){
                String color = s.substring(match.start(),match.end());
                s = s.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(s);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void removeItemStack(Player player, ItemStack itemStack, Integer amount) {
        int removeAmount=0;
        for(ItemStack item:player.getInventory().getStorageContents()){
            if(isSimilar(item,itemStack)){
                removeAmount += item.getAmount();
                if(removeAmount<=amount) {
                    item.setAmount(0);
                    if(removeAmount ==amount){
                        break;
                    }
                }else{
                    item.setAmount(removeAmount-amount);
                    break;
                }
            }
        }
    }
    public static void removeItem(Player player, ItemStack itemStack, Integer amount) {
        int removeAmount=0;
        for(ItemStack item:player.getInventory().getStorageContents()) {
            if (item != null) {
                if (item.getType().equals(itemStack.getType()) && !item.hasItemMeta()) {
                    removeAmount += item.getAmount();
                    if (removeAmount <= amount) {
                        item.setAmount(0);
                        if (removeAmount == amount) {
                            break;
                        }
                    } else {
                        item.setAmount(removeAmount - amount);
                        break;
                    }
                }
            }
        }
    }
    public static ItemStack createItem(Inventory inv, Material materialId, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item;
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<String> lore = new ArrayList();
        item = new ItemStack(materialId, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot -1, item);
        return item;
    }
    public static ItemStack createItemNoName(Inventory inv, Material materialId, int amount, int invSlot) {
        ItemStack item;
        item = new ItemStack(materialId, amount);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
        inv.setItem(invSlot -1, item);
        return item;
    }
    public static ItemStack createItemByte(Inventory inv, Material materialId, int byteId, int amount, int invSlot, String displayName, String... loreString) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<String> lore = new ArrayList();
        @SuppressWarnings("deprecation")
        ItemStack item = new ItemStack(materialId, amount, (short) byteId);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot -1, item);
        return item;
    }
    public static ItemStack createItemStack(Inventory inv, ItemStack itemStack, int invSlot, String displayName, String... loreString) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<String> lore = new ArrayList();
        ItemStack item = itemStack;
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot -1, item);
        return item;
    }
    public static ItemStack createItemStackNoName(Inventory inv, ItemStack itemStack, int invSlot) {
        ItemStack item = itemStack;
        inv.setItem(invSlot -1, item);
        return item;
    }
    public static void displayTitle(Player p, String Title, String SubTitle, int toShow, int show, int afterShow) {
        p.sendTitle(Utils.chat(Title),Utils.chat(SubTitle),toShow,show,afterShow);
    }
    public static void actionBar(Player p, String message) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(Utils.chat(message)).create());
    }
    public static void GiveOrDrop(Player player,ItemStack item){
        for(ItemStack i : player.getInventory().getStorageContents()) {
            if (i == null || (i.isSimilar(item) && i.getAmount() < item.getMaxStackSize())) {
                player.getInventory().addItem(item);
                return;
            }
        }
        player.getWorld().dropItemNaturally(player.getLocation(), item);
    }
    public static boolean isSimilar(ItemStack item1,ItemStack item2){
        if(item1 != null && item2 != null) {
            if (item1.hasItemMeta() && item2.hasItemMeta()) {
                if (item1.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName()) {
                    if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
