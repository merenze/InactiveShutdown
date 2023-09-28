package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import com.jellyrekt.inactiveshutdown.InactiveShutdown;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class StatusCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " status";

    private final JavaPlugin plugin;

    public StatusCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> map) {
        FileConfiguration config = plugin.getConfig();

        sender.sendMessage(
                "== InactiveShutdown Status ==",
                "Enabled: " + (config.getBoolean("enabled") ? "enabled" : "disabled"),
                "Delay: " + InactiveShutdown.formatTime(config.getInt("delay"))
        );
    }
}
