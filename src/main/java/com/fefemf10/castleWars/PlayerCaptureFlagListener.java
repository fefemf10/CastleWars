package com.fefemf10.castleWars;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerCaptureFlagListener implements Listener {
    @EventHandler(ignoreCancelled = false)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CastleWarsGame castleWarsGame = CastleWars.getGame(player);
        if (castleWarsGame != null){
            boolean redteam = castleWarsGame.ContainsRedPlayer(player);
            Location blockLocation = event.getBlock().getLocation();
            if (redteam && blockLocation.equals(castleWarsGame.flagPointBlue) && event.getBlock().getBlockData().getMaterial().equals(Material.BLUE_WOOL)) {
                ItemStack blueFlag = new ItemStack(Material.BLUE_BANNER, 1);
                blueFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                player.getInventory().setHelmet(blueFlag);
                event.setDropItems(false);
            }
            else if (!redteam && blockLocation.equals(castleWarsGame.flagPointRed) && event.getBlock().getBlockData().getMaterial().equals(Material.RED_WOOL)) {
                ItemStack redFlag = new ItemStack(Material.RED_BANNER, 1);
                redFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
                player.getInventory().setHelmet(redFlag);
                event.setDropItems(false);
            }
            else if (redteam && blockLocation.equals(castleWarsGame.flagPointRed)) {
                event.setCancelled(true);
            }
            else if (!redteam && blockLocation.equals(castleWarsGame.flagPointBlue)) {
                event.setCancelled(true);
            }
            else if (blockLocation.equals(castleWarsGame.spawnPointRed) || blockLocation.equals(castleWarsGame.spawnPointBlue))
                event.setCancelled(true);
        }
    }

}
