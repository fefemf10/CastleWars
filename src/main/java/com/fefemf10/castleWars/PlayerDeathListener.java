package com.fefemf10.castleWars;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    ItemStack redFlag = new ItemStack(Material.RED_BANNER, 1);
    ItemStack blueFlag = new ItemStack(Material.BLUE_BANNER, 1);
    public PlayerDeathListener(){
        redFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
        blueFlag.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        CastleWarsGame castleWarsGame = CastleWars.getGame(player);
        if (castleWarsGame != null) {
            event.setShouldDropExperience(false);
            event.getDrops().clear();
            boolean redteam = castleWarsGame.ContainsRedPlayer(player);
            if (redteam && player.getInventory().getHelmet().equals(blueFlag))
                player.getWorld().getBlockAt(castleWarsGame.flagPointBlue).setType(Material.BLUE_WOOL);
            else if (!redteam && player.getInventory().getHelmet().equals(redFlag))
                player.getWorld().getBlockAt(castleWarsGame.flagPointRed).setType(Material.RED_WOOL);
        }
    }
}
