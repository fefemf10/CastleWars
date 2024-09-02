package com.fefemf10.castleWars;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;
import pk.ajneb97.model.internal.GiveKitInstructions;

import java.io.Serializable;

public class CastleWarsGame implements Serializable {
    public CastleWarsGame(String name) {
        this.name = name;
        maxCapturesFlag = 5;
        friendlyFire = false;
        gameState = GameState.NORUNNING;

        board = Bukkit.getScoreboardManager().getNewScoreboard();
        redteam = board.registerNewTeam("REDTEAM");
        redteam.color(NamedTextColor.RED);
        redteam.setAllowFriendlyFire(friendlyFire);
        redteam.prefix(Component.text("[RED]"));
        blueteam = board.registerNewTeam("BLUETEAM");
        blueteam.color(NamedTextColor.BLUE);
        blueteam.setAllowFriendlyFire(friendlyFire);
        blueteam.prefix(Component.text("[BLUE]"));

        Objective objective = board.registerNewObjective("score", Criteria.DUMMY, Component.text("Score"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        redteamscore = objective.getScore("Red team");
        blueteamscore = objective.getScore("Blue team");
        redteamscore.setScore(0);
        blueteamscore.setScore(0);
        kitredteam = "cwred";
        kitblueteam = "cwblue";
    }
    public String name;
    public Score redteamscore;
    public Score blueteamscore;
    public int maxCapturesFlag;
    public boolean friendlyFire;
    public Location spawnPointRed;
    public Location flagPointRed;
    public Location spawnPointBlue;
    public Location flagPointBlue;
    public GameState gameState;
    public Scoreboard board;
    public Team redteam;
    public Team blueteam;
    public String kitredteam;
    public String kitblueteam;
    public ItemStack headredteam;
    public ItemStack headblueteam;
    public void AddPlayer(Player player) {
        player.getInventory().clear();
        boolean redteamf = redteam.getSize() < blueteam.getSize();
        if (redteamf)
        {
            redteam.addPlayer(player);
            player.setScoreboard(board);
            CastleWars.pk.getKitsManager().giveKit(player, kitredteam, new GiveKitInstructions(false, false, true, true));
            headredteam = player.getInventory().getHelmet();
            player.teleport(spawnPointRed);
        }
        else
        {
            blueteam.addPlayer(player);
            player.setScoreboard(board);
            CastleWars.pk.getKitsManager().giveKit(player, kitblueteam, new GiveKitInstructions(false, false, true, true));
            headblueteam = player.getInventory().getHelmet();
            player.teleport(spawnPointBlue);
        }
    }
    public void RemovePlayerIfExist(Player player) {
        if (redteam.hasPlayer(player)) {
            redteam.removePlayer(player);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.teleport(player.getWorld().getSpawnLocation());
        }
        else if (blueteam.hasPlayer(player)){
            blueteam.removePlayer(player);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }
    public boolean ContainsRedPlayer(Player player) {
        return redteam.hasPlayer(player);
    }
    public boolean ContainsBluePlayer(Player player) {
        return blueteam.hasPlayer(player);
    }
    public boolean ContainsPlayer(Player player) {
        return redteam.hasPlayer(player) || blueteam.hasPlayer(player);
    }

    public void RespawnPlayer(Player player) {
        if (redteam.hasPlayer(player)){
            CastleWars.pk.getKitsManager().giveKit(player, kitredteam, new GiveKitInstructions(false, false, true, true));
        }
        else if (blueteam.hasPlayer(player)){
            CastleWars.pk.getKitsManager().giveKit(player, kitblueteam, new GiveKitInstructions(false, false, true, true));
        }
    }
}
