package eu.SkyFull.VoteParty;

import eu.SkyFull.VoteParty.Listeners.AuthJoin;
import eu.SkyFull.VoteParty.Listeners.Votifier;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        try {
        this.getDataFolder().mkdir();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        VoteStorage.NormalVotes.createFile();
        VoteStorage.VoteParty.createFile();
        Bukkit.getPluginManager().registerEvents(new Votifier(), this);
        Bukkit.getPluginManager().registerEvents(new AuthJoin(), this);
        new Placeholders().register();
        // Plugin startup logic
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance(){
        return instance;
    }
}
