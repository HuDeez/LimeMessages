package me.hudeez.limemessages.commands;

import me.hudeez.limemessages.LimeMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandMain extends CommandManager implements CommandExecutor {


    public CommandMain(LimeMessages plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < 1) {
            String message = getPrefixMessage() + getUsageCommandMessage();
            commandSender.sendMessage(message);
            return true;
        }

        String subcommand = strings[0];
        if (subcommand.equalsIgnoreCase("reload")) {
            plugin.reloadPlugin(commandSender, this);
            return true;
        } else if (subcommand.equalsIgnoreCase("toggle")) {
            CommandToggle commandToggle = new CommandToggle(plugin);
            commandToggle.toggle(commandSender);
            return true;
        } else if (subcommand.equalsIgnoreCase("help")) {
            CommandHelp commandHelp = new CommandHelp(plugin);
            commandHelp.help(commandSender);
            return true;
        } else {
            commandSender.sendMessage(getPrefixMessage());
            for (String line : getHelpCommands()) {
                commandSender.sendMessage(translateColor(line));
            }
            return true;
        }
    }
}
