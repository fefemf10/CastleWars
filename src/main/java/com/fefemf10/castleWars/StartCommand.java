package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game != null) {
            Player player = (Player) sender;
            game.gameState = GameState.RUNNING;
            sender.sendMessage(String.format("Starting %s", game.name));
        }
        else {
            sender.sendMessage("Don't have game");
        }
        return true;
    }
}
