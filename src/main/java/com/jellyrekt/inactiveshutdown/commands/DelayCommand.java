package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import com.jellyrekt.inactiveshutdown.InactiveShutdown;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;

public class DelayCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " delay";
    public static final String HELP_MSG = NAME + " <seconds> - Set the shutdown delay";

    private final InactiveShutdown plugin;

    public DelayCommand(InactiveShutdown plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, Map<String, String[]> map) {
        if (map.get("delay").length <= 0) {
            sender.sendMessage("Delay: " + plugin.formatDelay());
            return;
        }

        FileConfiguration config = plugin.getConfig();
        try {
            config.set("delay", Integer.parseInt(map.get("delay")[0]));
        } catch (NumberFormatException ex) {
            sender.sendMessage(HELP_MSG);
            return;
        }

        sender.sendMessage("Set inactivity shutdown delay to " + plugin.formatDelay());

        if (!config.getBoolean("enabled")) {
            sender.sendMessage("Timer will not take effect until the plugin is enabled.");
        }
    }

}
