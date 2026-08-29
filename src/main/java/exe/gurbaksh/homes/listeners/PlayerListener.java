package exe.gurbaksh.homes.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import exe.gurbaksh.homes.HomesPlugin;

/**
 * Player event listeners
 */
public class PlayerListener implements Listener {

    private final HomesPlugin plugin;

    public PlayerListener(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Optional: Clear from cache if memory optimization is needed
        // plugin.getStorage().clearPlayerHomes(event.getPlayer().getUniqueId());
    }
}