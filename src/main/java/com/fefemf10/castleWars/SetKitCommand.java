package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetKitCommand extends SubCommand {

    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game != null) {
            if (CastleWars.pk.getKitsManager().getKitByName(args[3]) != null) {
                if (args[2].equals("r")) {
                    game.kitredteam = args[3];
                    sender.sendMessage("Saved kit %s for red team", args[3]);
                }
                else if (args[2].equals("b"))
                {
                    game.kitblueteam = args[3];
                    sender.sendMessage(String.format("Saved kit %s for blue team", args[3]));
                }
                else
                    return false;
            }
            else {
                sender.sendMessage("Don't have this kit");
            }
        }
        else {
            sender.sendMessage("Don't have game");
        }
        return true;
    }
}
