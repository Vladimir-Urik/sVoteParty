package eu.SkyFull.VoteParty;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VoteStorage {

    public static class NormalVotes {

        public static void createFile() throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            if(!file.exists()){
                file.createNewFile();
            }
        }

        public static int getTotalVotes(String player){
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            return yaml.getInt("votes."+ player +".total");
        }

        public static void addVote(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("votes."+ player +".total") != null){
                int votes = yaml.getInt("votes."+ player +".total") + 1;
                yaml.set("votes."+ player +".total", votes);
            } else {
                yaml.set("votes."+ player +".total", 1);
            }
            yaml.save(file);
        }


        public static void addOfflineVote(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("votes."+ player +".pending") != null){
                int votes = yaml.getInt("votes."+ player +".pending") + 1;
                yaml.set("votes."+ player +".pending", votes);
            } else {
                yaml.set("votes."+ player +".pending", 1);
            }
            yaml.save(file);
        }

        public static int getOfflineVotes(String player) throws IOException {
            int p = 0;
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("votes."+ player +".pending") != null){
                p = yaml.getInt("votes."+ player +".pending");
            } else {
                yaml.set("votes."+ player +".pending", 0);
            }
            yaml.save(file);
            return p;
        }

        public static void removeOfflineVotes(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/votes.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("votes."+ player +".pending") != null){
                int votes = yaml.getInt("votes."+ player +".pending") - 1;
                yaml.set("votes."+ player +".pending", votes);
            } else {
                yaml.set("votes."+ player +".pending", 0);
            }
            yaml.save(file);
        }
    }

    public static class VoteParty {

        public static void createFile() throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/party.yml");
            if(!file.exists()){
                file.createNewFile();
            }
        }

        public static void addParty(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/party.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("party."+ player +".total") != null){
                int votes = yaml.getInt("party."+ player +".total") + 1;
                yaml.set("party."+ player +".total", votes);
            } else {
                yaml.set("party."+ player +".total", 1);
            }
            yaml.save(file);
        }


        public static void addOfflineParty(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/party.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("party."+ player +".pending") != null){
                int votes = yaml.getInt("party."+ player +".pending") + 1;
                yaml.set("party."+ player +".pending", votes);
            } else {
                yaml.set("party."+ player +".pending", 1);
            }
            yaml.save(file);
        }

        public static void removeOfflineParty(String player) throws IOException {
            File file = new File(Main.getInstance().getDataFolder() + "/party.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("party."+ player +".pending") != null){
                int votes = yaml.getInt("party."+ player +".pending") - 1;
                yaml.set("party."+ player +".pending", votes);
            } else {
                yaml.set("party."+ player +".pending", 0);
            }
            yaml.save(file);
        }

        public static int getOfflineParty(String player) throws IOException {
            int p = 0;
            File file = new File(Main.getInstance().getDataFolder() + "/party.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if(yaml.get("party."+ player +".pending") != null){
                p = yaml.getInt("party."+ player +".pending");
            } else {
                yaml.set("party."+ player +".pending", 0);
            }
            yaml.save(file);
            return p;
        }
    }

}
