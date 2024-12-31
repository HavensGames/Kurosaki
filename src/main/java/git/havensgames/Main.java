package git.havensgames;

import git.havensgames.example.ExampleCommand;
import git.havensgames.objects.KurosakiHandler;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Main extends JavaPlugin {

    private KurosakiHandler kurosakiHandler;

    @Override
    public void onEnable() {
        this.kurosakiHandler = new KurosakiHandler(this);
        this.getCommand("example").setExecutor(new ExampleCommand());
    }

    @Override
    public void onDisable() {

    }

}
