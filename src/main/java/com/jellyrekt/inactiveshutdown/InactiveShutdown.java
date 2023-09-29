package com.jellyrekt.inactiveshutdown;

import com.jellyrekt.commandtree.CommandTree;
import com.jellyrekt.inactiveshutdown.commands.*;
import com.jellyrekt.inactiveshutdown.events.ActivityListener;
import org.bukkit.plugin.java.JavaPlugin;

public class InactiveShutdown extends JavaPlugin {
    @Override
    public void onEnable() {
        registerCommands();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ActivityListener(this), this);
    }

    private void registerCommands() {
        CommandTree tree = new CommandTree(this)
            .setPermissionDeniedMessage("Unknown command. Type \"/help\" for help.");

        tree
            .add(BaseCommand.NAME)
            .addAliases("insh")
            .setPermission("inactiveshutdown.command.base")
            .setExecutor(new BaseCommand(this));

        tree
            .add(HelpCommand.NAME)
            .addAliases("?")
            .setPermission("inactiveshutdown.command.help")
            .setExecutor(new HelpCommand());

        tree
            .add(StatusCommand.NAME)
            .addAliases("s")
            .setPermission("inactiveshutdown.command.status")
            .setExecutor(new StatusCommand(this));

        tree
            .add(EnableCommand.NAME)
            .addAliases("on")
            .setPermission("inactiveshutdown.command.enable")
            .setExecutor(new EnableCommand(this));

        tree
            .add(DisableCommand.NAME)
            .addAliases("off")
            .setPermission("inactiveshutdown.command.disable")
            .setExecutor(new DisableCommand(this));

        tree
            .add(DelayCommand.NAME)
            .addAliases("time", "t")
            .setPermission("inactiveshutdown.command.delay")
            .setExecutor(new DelayCommand(this));

        tree
            .add(ReloadConfigCommand.NAME)
            .addAliases("reload", "rc", "r")
            .setPermission("inactiveshutdown.command.reloadconfig")
            .setExecutor(new ReloadConfigCommand(this));

        tree.register();
    }

    /**
     * Format the shutdown delay (e.g., "1h 1m 1s").
     * Hours will be displayed only if greater than 0.
     * Minutes will be displayed if greater than 0 or hours is displayed.
     *
     * @return Time formatted as 1h 1m 1s, where h, m, and s display on
     */
    public String formatDelay() {
        int s = getConfig().getInt("delay");
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
