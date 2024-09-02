package com.fefemf10.castleWars;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnPointCommand extends SubCommand {
    @Override
    public boolean process(CommandSender sender, Command command, String label, String[] args) {
        CastleWarsGame game = CastleWars.getGame(args[1]);
        if (game != null) {
            Player player = (Player) sender;
            if (args[2].equals("r")) {
                sender.sendMessage("Saved spawnpoint for red team");
                game.spawnPointRed = new Location(player.getWorld(), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
            }
            else if (args[2].equals("b")) {
                sender.sendMessage("Saved spawnpoint for blue team");
                game.spawnPointBlue = new Location(player.getWorld(), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
            }
            else {
                sender.sendMessage("Wrong team");
                return true;
            }
        }
        else {
            sender.sendMessage("Don't have game");
        }
        return true;
    }
}
