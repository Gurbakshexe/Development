package exe.gurbaksh.homes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import exe.gurbaksh.homes.HomesPlugin;
import exe.gurbaksh.homes.storage.Home;

/**
 * Command to set a home
 */
public class SetHomeCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public SetHomeCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (!sender.hasPermission("homes.sethome")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        String homeName = args.length > 0 ? args[0] : "home";

        Home home = new Home(homeName, player.getLocation());
        plugin.getStorage().setHome(player.getUniqueId(), home);
        plugin.getStorage().save();

        player.sendMessage("§aHome '" + homeName + "' set successfully!");
        return true;
    }
}