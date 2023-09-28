package com.jellyrekt.inactiveshutdown;

import com.jellyrekt.commandtree.CommandTree;
import com.jellyrekt.inactiveshutdown.commands.BaseCommand;
import com.jellyrekt.inactiveshutdown.commands.HelpCommand;
import com.jellyrekt.inactiveshutdown.commands.StatusCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class InactiveShutdown extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, World!");
        registerCommands();

    }

    private CommandTree registerCommands() {
        CommandTree commandTree = new CommandTree(this)
            .setPermissionDeniedMessage("Unknown command. Type \"/help\" for help.");

        commandTree.add(BaseCommand.NAME)
            .addAliases("insh")
            .setPermission("inactiveshutdown.command.base")
            .setExecutor(new BaseCommand(this));

        commandTree
            .add(HelpCommand.NAME)
            .addAliases("?")
            .setPermission("inactiveshutdown.command.help")
            .setExecutor(new HelpCommand());

        commandTree
            .add(StatusCommand.NAME)
            .addAliases("s")
            .setPermission("inactiveshutdown.command.status")
            .setExecutor(new StatusCommand(this));

        commandTree.add("inactiveshutdown reloadconfig")
            .setPermission("inactiveshutdown.command.reloadconfig");

        commandTree.add("inactiveshutdown afterplayerleave")
            .setPermission("inactiveshutdown.command.afterplayerleave");

        commandTree.add("inactiveshutdown afterplayerleave enable")
            .setPermission("inactiveshutdown.command.afterplayerleave.enable");

        commandTree.add("inactiveshutdown afterplayerleave disable")
            .setPermission("inactiveshutdown.command.afterplayerleave.disable");

        commandTree.add("inactiveshutdown afterplayerleave delay")
            .setPermission("inactiveshutdown afterplayerleave delay");

        commandTree.add("inactiveshutdown afterstartup")
            .setPermission("inactiveshutdown.command.afterstartup");

        commandTree.add("inactiveshutdown afterstartup enable")
            .setPermission("inactiveshutdown.command.afterstartup.enable");

        commandTree.add("inactiveshutdown afterstartup disable")
            .setPermission("inactiveshutdown.command.afterstartup.disable");

        commandTree.add("inactiveshutdown afterstartup delay")
            .setPermission("inactiveshutdown.command.afterstartup.delay");

        commandTree.register();

        return commandTree;
    }

    /**
     * Format a time (e.g., "1h 1m 1s").
     * Hours will be displayed only if greater than 0.
     * Minutes will be displayed if greater than 0 or hours is displayed.
     *
     * @param s Time in seconds
     * @return Time formatted as 1h 1m 1s, where h, m, and s display on
     */
    public static String formatTime(int s) {
        StringBuilder builder = new StringBuilder();
        if (s > 3600) {
            builder.append(s / 3600).append("h ");
        }
        s %= 3600;
        if (s > 60 || !builder.isEmpty()) {
            builder.append(s / 60).append("m ");
        }
        s %= 60;
        builder.append(s).append("s");
        return builder.toString();
    }
}
