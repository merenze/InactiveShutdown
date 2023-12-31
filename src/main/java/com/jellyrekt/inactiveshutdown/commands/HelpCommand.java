package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class HelpCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " help";

    @Override
    public void execute(CommandSender sender, Map<String, String[]> args) {
        sender.sendMessage(
            String.format("/%s - Display plugin information.", BaseCommand.NAME),
            String.format("/%s - Display this message.", NAME),
            String.format("/%s - Display configuration status.", StatusCommand.NAME),
            String.format("/%s - Enable the inactivity shutdown timer.", EnableCommand.NAME),
            String.format("/%s - Disable the inactivity shutdown timer.", DisableCommand.NAME),
            String.format("/%s - Reload config.yml options from the disk.", ReloadConfigCommand.NAME)
        );
    }
}
