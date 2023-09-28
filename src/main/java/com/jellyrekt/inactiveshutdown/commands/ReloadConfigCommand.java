package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class ReloadConfigCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " reloadconfig";
    private final JavaPlugin plugin;

    public ReloadConfigCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> map) {
        plugin.reloadConfig();
        sender.sendMessage("Reloaded config from disk.");
    }
}
