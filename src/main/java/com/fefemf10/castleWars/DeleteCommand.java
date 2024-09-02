package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game != null) {
            CastleWars.castleWarsGames.remove(game);
            sender.sendMessage("Game has been deleted");
        }
        else {
            sender.sendMessage("Don't have game");
        }
        return true;
    }
}
