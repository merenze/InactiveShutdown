package com.jellyrekt.inactiveshutdown.commands;

import com.jellyrekt.commandtree.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class InactiveShutdownCommand implements CommandExecutor {
    public static final String BASE_COMMAND = "inactiveshutdown";

    @Override
    public void execute(CommandSender sender, Map<String, String[]> args) {
        sender.sendMessage(
            String.format("/%s - Display this message.", BASE_COMMAND),
            String.format("/%s afterplayerleave - Configure options for when all players leave.", BASE_COMMAND),
            String.format("/%s afterstartup - Configure options for if no players join.", BASE_COMMAND),
            String.format("/%s reloadconfig - Reload config.yml options from the disk.", BASE_COMMAND)
        );
    }
}
