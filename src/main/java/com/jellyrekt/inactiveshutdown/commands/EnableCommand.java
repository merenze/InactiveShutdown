package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import com.jellyrekt.inactiveshutdown.InactiveShutdown;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class EnableCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " enable";

    private final JavaPlugin plugin;

    public EnableCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> map) {
        if (!plugin.getConfig().getBoolean("enabled")) {
            plugin.getConfig().set("enabled", true);
            plugin.saveConfig();
        }
        sender.sendMessage(String.format("Server will stop %s after last activity", InactiveShutdown.formatTime(plugin.getConfig().getInt("delay"))));
    }
}
