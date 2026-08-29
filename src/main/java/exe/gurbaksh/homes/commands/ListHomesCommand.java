package exe.gurbaksh.homes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import exe.gurbaksh.homes.HomesPlugin;

/**
 * Command to list all homes
 */
public class ListHomesCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public ListHomesCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (!sender.hasPermission("homes.listhomes")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        var homes = plugin.getStorage().getPlayerHomes(player.getUniqueId());

        if (homes.isEmpty()) {
            player.sendMessage("§cYou have no homes set!");
            return true;
        }

        player.sendMessage("§6=== Your Homes ===");
        homes.forEach((name, home) -> {
            var loc = home.getLocation();
            if (loc != null) {
                player.sendMessage("§a" + name + " §7- " + loc.getWorld().getName() + 
                    " (" + (int)loc.getX() + ", " + (int)loc.getY() + ", " + (int)loc.getZ() + ")");
            }
        });
        return true;
    }
}