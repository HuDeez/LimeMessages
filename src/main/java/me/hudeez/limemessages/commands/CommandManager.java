package me.hudeez.limemessages.commands;

import me.hudeez.limemessages.LimeMessages;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;


public class CommandManager {
    protected LimeMessages plugin;

    private String PREFIX_MESSAGE;
    private String RELOAD_MESSAGE;
    private String ENABLE_NOTIFICATION_MESSAGE;
    private String DISABLE_NOTIFICATION_MESSAGE;
    private String NO_PERMISSION_MESSAGE;
    private String USAGE_COMMAND_MESSAGE;
    private List<String> HELP_COMMANDS;
    private String TOGGLE_DISABLED;
    private String ERROR_MESSAGE;

    protected String translateColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public CommandManager(LimeMessages plugin) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
        this.plugin = plugin;
        this.PREFIX_MESSAGE = translateColor(config.getString("prefix"));
        this.RELOAD_MESSAGE = translateColor(config.getString("reload-plugin"));
        this.ENABLE_NOTIFICATION_MESSAGE = translateColor(config.getString("enable-notification"));
        this.DISABLE_NOTIFICATION_MESSAGE = translateColor(config.getString("disable-notification"));
        this.NO_PERMISSION_MESSAGE = translateColor(config.getString("no-permission"));
        this.USAGE_COMMAND_MESSAGE = translateColor(config.getString("usage-command"));
        this.HELP_COMMANDS = config.getStringList("help-command");
        this.TOGGLE_DISABLED = translateColor(config.getString("toggle-disabled"));
        this.ERROR_MESSAGE = translateColor(config.getString("error-message"));
    }

    public String getPrefixMessage() {
        return PREFIX_MESSAGE;
    }

    public String getReloadMessage() {
        return RELOAD_MESSAGE;
    }

    public String getEnableNotificationMessage() {
        return ENABLE_NOTIFICATION_MESSAGE;
    }

    public String getDisableNotificationMessage() {
        return DISABLE_NOTIFICATION_MESSAGE;
    }

    public String getNoPermissionMessage() {
        return NO_PERMISSION_MESSAGE;
    }

    public String getUsageCommandMessage() {
        return USAGE_COMMAND_MESSAGE;
    }

    public List<String> getHelpCommands() {
        return HELP_COMMANDS;
    }

    public String getToggleDisabled() {
        return TOGGLE_DISABLED;
    }
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
