package com.fefemf10.castleWars;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFinishedFlagListener implements Listener{
    ItemStack redFlag = new ItemStack(Material.RED_BANNER, 1);
    ItemStack blueFlag = new ItemStack(Material.BLUE_BANNER, 1);
    public PlayerFinishedFlagListener(){
        redFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
        blueFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.hasChangedBlock()) {
        Player player = event.getPlayer();
        CastleWarsGame castleWarsGame = CastleWars.getGame(player);
        if (castleWarsGame != null) {
            boolean redteam = castleWarsGame.ContainsRedPlayer(player);
            if (redteam && player.getInventory().getHelmet().equals(blueFlag)
                    && event.getTo().getBlockX() == castleWarsGame.spawnPointRed.getBlockX()
                    && event.getTo().getBlockY() - 1 == castleWarsGame.spawnPointRed.getBlockY()
                    && event.getTo().getBlockZ() == castleWarsGame.spawnPointRed.getBlockZ()) {
                player.getInventory().setHelmet(castleWarsGame.headredteam);
                player.getWorld().getBlockAt(castleWarsGame.flagPointBlue).setType(Material.BLUE_WOOL);
                castleWarsGame.redteamscore.setScore(castleWarsGame.redteamscore.getScore() + 1);
            } else if (!redteam && player.getInventory().getHelmet().equals(redFlag)
                    && event.getTo().getBlockX() == castleWarsGame.spawnPointBlue.getBlockX()
                    && event.getTo().getBlockY() - 1 == castleWarsGame.spawnPointBlue.getBlockY()
                    && event.getTo().getBlockZ() == castleWarsGame.spawnPointBlue.getBlockZ()) {
                player.getInventory().setHelmet(castleWarsGame.headblueteam);
                player.getWorld().getBlockAt(castleWarsGame.flagPointRed).setType(Material.RED_WOOL);
                castleWarsGame.blueteamscore.setScore(castleWarsGame.blueteamscore.getScore() + 1);
            }
            }
        }
    }
}
