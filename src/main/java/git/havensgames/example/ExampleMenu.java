package git.havensgames.example;

import com.google.common.collect.Maps;
import git.havensgames.objects.Kurosaki;
import git.havensgames.objects.button.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class ExampleMenu extends Kurosaki {

    public ExampleMenu(Player player) {
        super(player,"&cExampleMenu", 3, true);
        setFill(true);
        setFillItem(new ItemStack(Material.getMaterial("STAINED_GLASS_PANE")));
    }

    private String name = "Example Item";

    @Override
    public Map<Integer, Button> getButtons() {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(11, new Button() {

            @Override
            public ItemStack icon() {
                ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
                item.setItemMeta(meta);
                return item;
            }

            @Override
            public void setAction(InventoryClickEvent event) {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage(ChatColor.GREEN + "You clicked on a sword!");
                name = "Item Example";
            }

            @Override
            public boolean isInteractable() {
                return true;
            }

        });
        return buttons;
    }

    @Override
    public void onClose() {

    }

}
