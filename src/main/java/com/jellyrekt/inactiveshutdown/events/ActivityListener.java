package com.jellyrekt.inactiveshutdown.events;

import com.google.gson.JsonSerializationContext;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityListener implements Listener {
    private final JavaPlugin plugin;
    Timer timer = new Timer();
    TimerTask doShutdown = new TimerTask() {
        @Override
        public void run() {
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

    public ActivityListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
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
        timer.schedule(doShutdown, plugin.getConfig().getInt("delay"));
    }

    private boolean doSetTimer() {
        FileConfiguration config = plugin.getConfig();
        if (!config.getBoolean("enabled")) {
            return false;
        }
        if (plugin.getServer().getOnlinePlayers().size() != 0) {
            return false;
        }
        return true;
    }
}
