package com.jellyrekt.inactiveshutdown;

import com.jellyrekt.commandtree.CommandTree;
import com.jellyrekt.inactiveshutdown.commands.InactiveShutdownCommand;
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

        commandTree.add("inactiveshutdown")
            .addAliases("insh")
            .setPermission("inactiveshutdown.command.base")
            .setExecutor(new InactiveShutdownCommand());

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
}
