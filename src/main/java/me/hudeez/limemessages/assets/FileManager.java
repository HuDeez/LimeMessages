package me.hudeez.limemessages.assets;

import me.hudeez.limemessages.LimeMessages;

import java.io.File;

public class FileManager {
    private LimeMessages plugin;
    public FileManager(LimeMessages plugin) {
        this.plugin = plugin;
    }

    public void loadFiles() {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
    }
}
