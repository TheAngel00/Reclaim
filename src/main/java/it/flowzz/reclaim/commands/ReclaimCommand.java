package it.flowzz.reclaim.commands;

import it.flowzz.reclaim.ReclaimPlugin;
import it.flowzz.reclaim.profiles.ReclaimProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ReclaimCommand implements CommandExecutor {

    private ReclaimPlugin plugin;

    public ReclaimCommand(ReclaimPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("Reclaim").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cThis command can only be executed by a player!");
            return false;
        }
        Player player = ((Player) sender).getPlayer();

        for (ReclaimProfile profile : plugin.getProfiles()) {
            if (player.hasPermission(profile.getPermission())) {
                profile.getCommands().forEach(cmd -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%",player.getName())));
                return true;
            }
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.no-Reclaim")));
        return false;
    }
}
