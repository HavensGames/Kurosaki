package git.havensgames.objects;

import git.havensgames.objects.button.Button;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Optional;

@Data
public abstract class Kurosaki {

    private int rows;
    private String title;
    private Inventory inventory;
    private boolean fill;
    private ItemStack fillItem;
    private Player player;
    private boolean update;

    public Kurosaki(Player player, String title, int rows, boolean update) {
        this.player = player;
        this.rows = rows;
        this.title = title;
        this.fill = false;
        this.update = update;
    }

    public abstract Map<Integer, Button> getButtons();
    public abstract void onClose();

    public void open(){
        this.inventory = Bukkit.createInventory(null, rows*9, ChatColor.translateAlternateColorCodes('&', title));
        
        if (this.fill){
            for (int i = 0; i < (rows*9); i++){
                if (this.fillItem == null || this.fillItem.getType() == Material.AIR){
                    continue;
                }
                inventory.setItem(i, this.fillItem);
            }  
        }
        
        getButtons().forEach((key, value) -> {
           inventory.setItem(key, value.icon());
        });

        player.openInventory(inventory);
        KurosakiHandler.getInstance().addMenu(player, this);

        if (isUpdate()){
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!isUpdate()) cancel();
                    update();
                }
            }.runTaskTimer(KurosakiHandler.getInstance().getMain(), 0L, 20L);
        }
    }

    public void update(){
        if (this.fill){
            for (int i = 0; i < (rows*9); i++){
                if (this.fillItem == null || this.fillItem.getType() == Material.AIR){
                    continue;
                }
                this.inventory.setItem(i, this.fillItem);
            }
        }

        getButtons().forEach((key, value) -> {
            this.inventory.setItem(key, value.icon());
        });

        player.updateInventory();
//        player.openInventory(inventory);
    }

    public Optional<Kurosaki> getOpenedMenu(Player player) {
        return Optional.ofNullable(KurosakiHandler.getInstance().getKurosakiMap().getOrDefault(player.getUniqueId(), null));
    }

}
