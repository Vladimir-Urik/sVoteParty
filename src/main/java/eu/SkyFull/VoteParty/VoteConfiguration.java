package eu.SkyFull.VoteParty;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class VoteConfiguration {

    public static YamlConfiguration getConfig() throws IOException, InvalidConfigurationException {
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        yamlConfiguration.load(new File(Main.getInstance().getDataFolder() +"/config.yml"));

        return yamlConfiguration;
    }
}
