package git.havensgames.objects;


import git.havensgames.objects.listener.KurosakiListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Getter
public class KurosakiHandler {

    @Getter private static KurosakiHandler instance;
    private final Map<UUID, Kurosaki> kurosakiMap;
    private final JavaPlugin main;

    public KurosakiHandler(JavaPlugin plugin) {
        instance = this;
        this.main = plugin;
        this.kurosakiMap = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new KurosakiListener(), plugin);
    }

    public Optional<Kurosaki> getOpenedMenu(Player player) {
        return Optional.ofNullable(KurosakiHandler.getInstance().getKurosakiMap().getOrDefault(player.getUniqueId(), null));
    }

    public void addMenu(Player player, Kurosaki kurosaki) {
        this.kurosakiMap.put(player.getUniqueId(), kurosaki);
    }

    public void removeMenu(Player player) {
        this.kurosakiMap.remove(player.getUniqueId());
    }

}
