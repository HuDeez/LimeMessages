package me.hudeez.limemessages.commands;

import me.hudeez.limemessages.LimeMessages;
import me.hudeez.limemessages.permissions.PermissionManager;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CommandToggle extends CommandManager{

    private boolean permission;

    public CommandToggle(LimeMessages plugin) {
        super(plugin);
        this.permission = plugin.getConfig().getBoolean("notifications.chat.permission");
    }

    public void toggle(CommandSender commandSender) {
        UUID userID = commandSender.getServer().getPlayerUniqueId(commandSender.getName());
        if (this.permission) {
            if (commandSender.hasPermission("limemessages.toggle") && commandSender.hasPermission("limemessages.notifications")) {
                String message = getPrefixMessage() + getDisableNotificationMessage();
                PermissionManager.addPermission(userID, "limemessages.notifications", false);
                commandSender.sendMessage(message);
            } else if (commandSender.hasPermission("limemessages.toggle") && !commandSender.hasPermission("limemessages.notifications")) {
                String message = getPrefixMessage() + getEnableNotificationMessage();
                PermissionManager.removePermission(userID, "limemessages.notifications");
                commandSender.sendMessage(message);
            } else if (!commandSender.hasPermission("limemessages.toggle")) {
                String message = getPrefixMessage() + getNoPermissionMessage();
                commandSender.sendMessage(message);
            }
        } else {
            String message = getPrefixMessage() + getToggleDisabled();
            commandSender.sendMessage(message);
        }
    }
}




