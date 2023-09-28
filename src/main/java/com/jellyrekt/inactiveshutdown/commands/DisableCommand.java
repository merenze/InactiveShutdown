package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import com.jellyrekt.inactiveshutdown.InactiveShutdown;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class DisableCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " disable";

    private final JavaPlugin plugin;

    public DisableCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> map) {
        if (plugin.getConfig().getBoolean("enabled")) {
            plugin.getConfig().set("enabled", false);
            plugin.saveConfig();
        }
        sender.sendMessage("Inactivity timeout disabled.");
    }
}
