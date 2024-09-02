package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game == null) {
            CastleWarsGame newGame = new CastleWarsGame(args[1]);
            CastleWars.castleWarsGames.add(newGame);
            sender.sendMessage("Game has been created");
        }
        else {
            sender.sendMessage("Game already exist");
        }
        return true;
    }
}
