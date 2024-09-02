package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    public abstract boolean process(CommandSender sender, Command command, String label, String[] args);
}
