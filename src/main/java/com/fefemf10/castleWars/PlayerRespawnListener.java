package com.fefemf10.castleWars;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        CastleWarsGame castleWarsGame = CastleWars.getGame(player);
        if (castleWarsGame != null) {
            castleWarsGame.RespawnPlayer(player);
            if (castleWarsGame.ContainsRedPlayer(player))
                event.setRespawnLocation(castleWarsGame.spawnPointRed);
            else
                event.setRespawnLocation(castleWarsGame.spawnPointBlue);
        }
    }
}
