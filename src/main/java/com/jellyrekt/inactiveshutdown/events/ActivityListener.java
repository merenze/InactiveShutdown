package com.jellyrekt.inactiveshutdown.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ActivityListener implements Listener {
    private final JavaPlugin plugin;

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
        // TODO
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
