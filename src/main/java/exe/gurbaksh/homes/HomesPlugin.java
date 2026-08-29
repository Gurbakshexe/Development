package exe.gurbaksh.homes;

import org.bukkit.plugin.java.JavaPlugin;
import exe.gurbaksh.homes.commands.HomeCommand;
import exe.gurbaksh.homes.commands.SetHomeCommand;
import exe.gurbaksh.homes.commands.DelHomeCommand;
import exe.gurbaksh.homes.commands.ListHomesCommand;
import exe.gurbaksh.homes.listeners.PlayerListener;
import exe.gurbaksh.homes.storage.HomeStorage;
import exe.gurbaksh.homes.config.ConfigManager;

/**
 * Main plugin class for Homes
 * Lightweight home management system with optimized performance
 */
public class HomesPlugin extends JavaPlugin {

    private static HomesPlugin instance;
    private HomeStorage storage;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;

        // Initialize configuration
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Initialize storage
        storage = new HomeStorage(this);
        storage.load();

        // Register commands
        registerCommands();

        // Register listeners
        registerListeners();

        getLogger().info("✓ Homes v1.0 enabled successfully");
    }

    @Override
    public void onDisable() {
        if (storage != null) {
            storage.save();
        }
        getLogger().info("✓ Homes v1.0 disabled");
    }

    private void registerCommands() {
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("delhome").setExecutor(new DelHomeCommand(this));
        getCommand("listhomes").setExecutor(new ListHomesCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    public static HomesPlugin getInstance() {
        return instance;
    }

    public HomeStorage getStorage() {
        return storage;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}