package exe.gurbaksh.homes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import exe.gurbaksh.homes.HomesPlugin;

/**
 * Command to delete a home
 */
public class DelHomeCommand implements CommandExecutor {

    private final HomesPlugin plugin;

    public DelHomeCommand(HomesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if (!sender.hasPermission("homes.delhome")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§cUsage: /delhome [name]");
            return true;
        }

        String homeName = args[0];
        boolean deleted = plugin.getStorage().deleteHome(player.getUniqueId(), homeName);

        if (deleted) {
            plugin.getStorage().save();
            player.sendMessage("§aHome '" + homeName + "' deleted successfully!");
        } else {
            player.sendMessage("§cHome '" + homeName + "' not found!");
        }

        return true;
    }
}