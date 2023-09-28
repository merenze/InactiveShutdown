package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class HelpCommand implements CommandExecutor {
    public static final String NAME = BaseCommand.NAME + " " + "help";

    @Override
    public void execute(CommandSender sender, Map<String, String[]> args) {
        sender.sendMessage(
            String.format("/%s - Display plugin information.", BaseCommand.NAME),
            String.format("/%s - Display this message.", NAME),
            String.format("/%s afterplayerleave - Configure options for when all players leave.", BaseCommand.NAME),
            String.format("/%s afterstartup - Configure options for if no players join.", BaseCommand.NAME),
            String.format("/%s reloadconfig - Reload config.yml options from the disk.", BaseCommand.NAME)
        );
    }
}
