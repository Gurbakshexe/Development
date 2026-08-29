package exe.gurbaksh.homes.storage;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Manages persistent storage of player homes
 * Uses YAML files for efficient storage and fast access
 */
public class HomeStorage {

    private final JavaPlugin plugin;
    private final File dataFile;
    private final Map<UUID, Map<String, Home>> playerHomes = new HashMap<>();
    private FileConfiguration dataConfig;

    public HomeStorage(JavaPlugin plugin) {
        this.plugin = plugin;
        this.dataFile = new File(plugin.getDataFolder(), "homes.yml");
        ensureDataFile();
    }

    private void ensureDataFile() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
    }

    public void load() {
        playerHomes.clear();
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                dataConfig = YamlConfiguration.loadConfiguration(dataFile);
                return;
            }
            dataConfig = YamlConfiguration.loadConfiguration(dataFile);

            Set<String> uuidKeys = dataConfig.getKeys(false);
            for (String uuidKey : uuidKeys) {
                try {
                    UUID uuid = UUID.fromString(uuidKey);
                    var homesSection = dataConfig.getConfigurationSection(uuidKey);
                    if (homesSection != null) {
                        Map<String, Home> homes = new HashMap<>();
                        for (String homeName : homesSection.getKeys(false)) {
                            var homeSection = homesSection.getConfigurationSection(homeName);
                            if (homeSection != null) {
                                Home home = new Home(
                                    homeName,
                                    homeSection.getString("world", "world"),
                                    homeSection.getDouble("x"),
                                    homeSection.getDouble("y"),
                                    homeSection.getDouble("z"),
                                    (float) homeSection.getDouble("yaw"),
                                    (float) homeSection.getDouble("pitch")
                                );
                                homes.put(homeName, home);
                            }
                        }
                        playerHomes.put(uuid, homes);
                    }
                } catch (IllegalArgumentException e) {
                    plugin.getLogger().warning("Invalid UUID key: " + uuidKey);
                }
            }
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to load homes data: " + e.getMessage());
        }
    }

    public void save() {
        try {
            dataConfig = new YamlConfiguration();

            for (var playerEntry : playerHomes.entrySet()) {
                UUID uuid = playerEntry.getKey();
                String uuidKey = uuid.toString();
                var homes = playerEntry.getValue();

                for (var homeEntry : homes.entrySet()) {
                    String homeName = homeEntry.getKey();
                    Home home = homeEntry.getValue();
                    String path = uuidKey + "." + homeName;
                    dataConfig.set(path + ".world", home.getWorld());
                    dataConfig.set(path + ".x", home.getX());
                    dataConfig.set(path + ".y", home.getY());
                    dataConfig.set(path + ".z", home.getZ());
                    dataConfig.set(path + ".yaw", home.getYaw());
                    dataConfig.set(path + ".pitch", home.getPitch());
                }
            }

            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save homes data: " + e.getMessage());
        }
    }

    public Home getHome(UUID uuid, String name) {
        Map<String, Home> homes = playerHomes.get(uuid);
        return homes != null ? homes.get(name) : null;
    }

    public void setHome(UUID uuid, Home home) {
        playerHomes.computeIfAbsent(uuid, k -> new HashMap<>()).put(home.getName(), home);
    }

    public boolean deleteHome(UUID uuid, String name) {
        Map<String, Home> homes = playerHomes.get(uuid);
        if (homes != null) {
            return homes.remove(name) != null;
        }
        return false;
    }

    public Map<String, Home> getPlayerHomes(UUID uuid) {
        return playerHomes.getOrDefault(uuid, new HashMap<>());
    }

    public void clearPlayerHomes(UUID uuid) {
        playerHomes.remove(uuid);
    }
}