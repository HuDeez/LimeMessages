package me.hudeez.limemessages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SchedulerMessage extends BukkitRunnable {
    private Plugin plugin;
    private List<String> messages;

    public SchedulerMessage(Plugin plugin, List<String> messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public void run() {
        if (plugin.getConfig().getBoolean("notifications.chat.enable")) {
            String message = ChatColor.translateAlternateColorCodes('&', messages.get(ThreadLocalRandom.current().nextInt(messages.size())));
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.hasPermission("limemessages.notifications")) player.sendMessage(message);
            }
        }
    }
}
