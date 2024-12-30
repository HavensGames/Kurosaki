package git.havensgames.objects.listener;

import git.havensgames.objects.Kurosaki;
import git.havensgames.objects.KurosakiHandler;
import git.havensgames.objects.button.Button;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Map;
import java.util.Optional;


public class KurosakiListener implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Optional<Kurosaki> kurosakiMenu = KurosakiHandler.getInstance().getOpenedMenu(player);

        if (kurosakiMenu.isPresent() && event.getCurrentItem() != null) {
            event.setCancelled(true);
            try{
                for (Map.Entry<Integer, Button> entry : kurosakiMenu.get().getButtons().entrySet()) {
                    if (entry.getKey() == event.getSlot()) {
                        event.setCancelled(!entry.getValue().isInteractable());
                        entry.getValue().setAction(event);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }



    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        final Optional<Kurosaki> kurosakiMenu = KurosakiHandler.getInstance().getOpenedMenu(player);

        if (kurosakiMenu.isPresent()) {
            try{
                kurosakiMenu.get().onClose();
                kurosakiMenu.get().setUpdate(false);
                KurosakiHandler.getInstance().removeMenu(player);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


}
