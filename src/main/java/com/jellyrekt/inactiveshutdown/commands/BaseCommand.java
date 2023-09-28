package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class BaseCommand implements CommandExecutor {
    public static final String NAME = "inactiveshutdown";

    private final JavaPlugin plugin;

    public BaseCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> args) {
        sender.sendMessage(
            String.format("InactiveShutdown - version %s", plugin.getPluginMeta().getVersion()),
            "Use '/interactiveshutdown help' to view all available commands."
        );
    }
}
