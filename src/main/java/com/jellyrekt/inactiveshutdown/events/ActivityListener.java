package com.jellyrekt.inactiveshutdown.events;

import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import com.jellyrekt.inactiveshutdown.InactiveShutdown;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityListener implements Listener {
    private final InactiveShutdown plugin;
    Timer timer = null;
    TimerTask doShutdown = new TimerTask() {
        @Override
        public void run() {
            plugin.getLogger().info("Stopping server.");
            // Run pre-shutdown scripts
            for (String script : plugin.getConfig().getStringList("scripts")) {
                try {
                    // run script
                    new ProcessBuilder(script).start();
                } catch (IOException e) {
                    e.printStackTrace();
                    plugin.getLogger().warning("Failed to run script: " + script);
                }
            }
            // Stop the server
            plugin.getServer().shutdown();
        }
    };

    public ActivityListener(InactiveShutdown plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerConnectionCloseEvent e) {
        if (!doSetTimer()) {
            return;
        }
        setTimer();
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        if (!doSetTimer()) {
            return;
        }
        setTimer();
    }

    private void setTimer() {
        plugin.getLogger().info("Scheduling shutdown in " + plugin.formatDelay());
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(doShutdown, 1000L * plugin.getConfig().getLong("delay"));
    }

    private boolean doSetTimer() {
        FileConfiguration config = plugin.getConfig();
        if (!config.getBoolean("enabled")) {
            return false;
        }
        plugin.getLogger().info("" + plugin.getServer().getOnlinePlayers().size() + " players still online.");
        if (plugin.getServer().getOnlinePlayers().size() != 0) {
            return false;
        }
        return true;
    }
}
