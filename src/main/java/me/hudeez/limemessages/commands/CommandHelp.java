package me.hudeez.limemessages.commands;

import me.hudeez.limemessages.LimeMessages;
import org.bukkit.command.CommandSender;

public class CommandHelp extends CommandManager{

    public CommandHelp(LimeMessages plugin) {
        super(plugin);
    }

    public void help(CommandSender commandSender) {
        commandSender.sendMessage(getPrefixMessage());
        for (String line : getHelpCommands()) {
            commandSender.sendMessage(translateColor(line));
        }
    }
}
