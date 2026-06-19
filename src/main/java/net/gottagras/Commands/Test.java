package net.gottagras.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(
        CommandSender sender,
        Command cmd,
        String label,
        String[] args
    ) {
        Bukkit.getLogger().info(
            sender.toString() + " " + cmd.toString() + " " + label + " " + args
        );
        return false;
    }
}
