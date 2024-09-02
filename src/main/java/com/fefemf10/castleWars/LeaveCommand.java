package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            CastleWarsGame castleWarsGame = CastleWars.getGame(player);
            if (castleWarsGame != null)
                castleWarsGame.RemovePlayerIfExist(player);
            else
                sender.sendMessage("You don't play");
            return true;
        }
        return false;
    }
}
