package io.pingy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by thlappy on 7/11/2016.
 */
public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Starting up.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Shutting down.");
    }
    public String nick = "";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command may only be used by players!");
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("nick")) {

            String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "SimplyNicks" + ChatColor.GRAY + "] ";

            if (args.length == 0) {
                player.sendMessage(prefix + ChatColor.AQUA + "Please enter a nick name!");
                return true;
            }

            for(String arg : args) {

                nick += arg + " ";

            }
            nick = nick.substring(0, nick.length() - 1);
            nick = nick.replaceAll("&", "§");
            player.sendMessage(prefix + ChatColor.BLUE + "You have successfully changed your nickname to " + nick);
            this.getConfig().set(player.getName(), nick);
            this.saveConfig();
            if (args.length == 1){

            }
        }
        return true;
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (this.getConfig().getString(event.getPlayer().getName()) != null) {
            String truenick = ChatColor.BLUE + "*" + this.getConfig().getString(event.getPlayer().getName());
            event.getPlayer().setDisplayName(truenick + ChatColor.RESET);
        }
    }
}
