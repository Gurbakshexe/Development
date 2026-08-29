package exe.gurbaksh.homes.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import exe.gurbaksh.homes.HomesPlugin;
import exe.gurbaksh.homes.storage.Home;

/**
 * Command to teleport to a home
 */
public class HomeCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public HomeCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (!sender.hasPermission("homes.home")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        String homeName = args.length > 0 ? args[0] : "home";

        Home home = plugin.getStorage().getHome(player.getUniqueId(), homeName);

        if (home == null) {
            player.sendMessage("§cHome '" + homeName + "' not found!");
            return true;
        }

        Location location = home.getLocation();
        if (location == null) {
            player.sendMessage("§cCannot load home location!");
            return true;
        }

        player.teleport(location);
        player.sendMessage("§aTeleported to home '" + homeName + "'");
        return true;
    }
}