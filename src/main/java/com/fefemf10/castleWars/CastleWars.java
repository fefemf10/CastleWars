package com.fefemf10.castleWars;

import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pk.ajneb97.PlayerKits2;

import java.io.*;
import java.util.ArrayList;

public final class CastleWars extends JavaPlugin {

    public static ArrayList<CastleWarsGame> castleWarsGames;
    public static JavaPlugin jp;
    public static World world;
    public static PlayerKits2 pk;
    @Override
    public void onEnable() {
        pk = (PlayerKits2)(getServer().getPluginManager().getPlugin("PlayerKits2"));
        PluginCommand castleWarsCommand = getServer().getPluginCommand("cw");
        castleWarsCommand.setExecutor(new CastleWarsCommand());
        castleWarsCommand.setTabCompleter(new CastleWarsCommand());
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerCaptureFlagListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFinishedFlagListener(), this);
        jp = this;
        try {
            File file = new File(jp.getDataFolder().getPath() + "cwmaps.bin");
            if (file.exists()) {
                FileInputStream filestream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(filestream);
                castleWarsGames = (ArrayList<CastleWarsGame>) in.readObject();
                in.close();
                filestream.close();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        if (castleWarsGames == null)
            castleWarsGames = new ArrayList<>();
    }

    @Override
    public void onDisable() {
        try {
            FileOutputStream file = new FileOutputStream(jp.getDataFolder().getPath() + "cwmaps.bin");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(castleWarsGames);
            out.close();
            file.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    public static CastleWarsGame getGame(String name)
    {
        for (int i = 0; i < castleWarsGames.size(); i++) {
            if (castleWarsGames.get(i).name.equals(name))
                return castleWarsGames.get(i);
        }
        return null;
    }
    public static CastleWarsGame getGame(Player player)
    {
        for (int i = 0; i < castleWarsGames.size(); i++) {
            if (castleWarsGames.get(i).ContainsPlayer(player))
                return castleWarsGames.get(i);
        }
        return null;
    }
}
