package me.hudeez.limemessages;

import me.hudeez.limemessages.assets.FileManager;
import me.hudeez.limemessages.commands.CommandMain;
import me.hudeez.limemessages.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class LimeMessages extends JavaPlugin {
    
    private CommandManager commandManager;
    private FileManager fileManager;
    private SchedulerMessage schedulerMessage;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.fileManager = new FileManager(this);
        this.fileManager.loadFiles();
        this.schedulerMessage = new SchedulerMessage(this, getConfig().getStringList("notifications.chat.messages"));
        this.commandManager = new CommandManager(this);
        this.schedulerMessage.runTaskTimer(this, 0L, (long) getConfig().getInt("notifications.chat.time")*20);
        this.getCommand("limemessages").setExecutor(new CommandMain(this));

        Bukkit.getConsoleSender().sendMessage("§7================= §6  UP  §7=================");
        Bukkit.getConsoleSender().sendMessage("                 LimeMessages                  ");
        Bukkit.getConsoleSender().sendMessage("                 §aPLUGIN ENABLED              ");
        Bukkit.getConsoleSender().sendMessage("                                               ");
        Bukkit.getConsoleSender().sendMessage("§7================= §6  UP  §7=================");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7================= §6  UP  §7=================");
        Bukkit.getConsoleSender().sendMessage("                 LimeMessages                  ");
        Bukkit.getConsoleSender().sendMessage("                 §cPLUGIN DISABLED             ");
        Bukkit.getConsoleSender().sendMessage("                                               ");
        Bukkit.getConsoleSender().sendMessage("§7================= §6  UP  §7=================");
    }

    public void reloadPlugin(CommandSender commandSender, CommandManager commandManager) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder(), "messages.yml"));
        if (commandSender.hasPermission("limemessages.reload")) {
            try {
                this.reloadConfig();
                config.load(new File(this.getDataFolder(), "messages.yml"));
                this.schedulerMessage.cancel();
                this.schedulerMessage = new SchedulerMessage(this, getConfig().getStringList("notifications.chat.messages"));
                this.schedulerMessage.runTaskTimer(this, 0L, (long) getConfig().getInt("notifications.chat.time")*20);

                commandSender.sendMessage(commandManager.getPrefixMessage() + commandManager.getReloadMessage());
            } catch (Exception exception) {
                this.getLogger().severe(exception.toString());
                commandSender.sendMessage(commandManager.getPrefixMessage() + commandManager.getErrorMessage());
            }
        } else {
            commandSender.sendMessage(commandManager.getPrefixMessage() + commandManager.getNoPermissionMessage());
        }
    }

}
