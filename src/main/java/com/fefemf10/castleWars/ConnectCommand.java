package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConnectCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game != null && sender instanceof Player) {
            if (game.ContainsPlayer((Player) sender)) {
                sender.sendMessage("You already in game");
                return true;
            }
            if (game.kitredteam.isEmpty()) {
                sender.sendMessage("Game kit red team is empty");
                return true;
            }
            if (game.kitblueteam.isEmpty()) {
                sender.sendMessage("Game kit red team is empty");
                return true;
            }
            if (game.gameState == GameState.NORUNNING) {
                sender.sendMessage("Game is not running");
                return true;
            }
            game.AddPlayer((Player) sender);
        }
        else {
            sender.sendMessage("Don't have game");
        }
        return true;
    }
}
