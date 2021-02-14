package eu.SkyFull.VoteParty.Utils;

import eu.SkyFull.VoteParty.VoteConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.util.List;

public class RewardsUtils {

    public static class NormalVote {

        public static void runVoteCommands(Player p) throws IOException, InvalidConfigurationException {
            List<String> commands = null;
            if(getEmptySlots(p) >= 1) {
                commands = VoteConfiguration.getConfig().getStringList("on-vote.commands");
            } else {
                commands = VoteConfiguration.getConfig().getStringList("on-vote.full-inv");
            }
            for (String cmd : commands) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c(p.getName(), cmd));
            }
        }

        public static void sendThankMessage(Player p) throws IOException, InvalidConfigurationException {
            List<String> messages = VoteConfiguration.getConfig().getStringList("on-vote.messages");
            for (String msg : messages) {
                p.sendMessage(c(p.getName(), msg));
            }
        }

    }

    public static class VoteParty{
        public static void runPartyCommands(Player p) throws IOException, InvalidConfigurationException {
            List<String> commands = null;
            if(getEmptySlots(p) >= 1) {
                commands = VoteConfiguration.getConfig().getStringList("voteparty.command");
            } else {
                commands = VoteConfiguration.getConfig().getStringList("voteparty.full-inv");
            }
            for (String cmd : commands) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c(p.getName(), cmd));
            }
        }

        public static void sendVoteOfflineMessage(Player p) throws IOException, InvalidConfigurationException {
            List<String> messages = VoteConfiguration.getConfig().getStringList("voteparty.offline");
            for (String msg : messages) {
                p.sendMessage(c(p.getName(), msg));
            }
        }
    }


    public static String c(String player, String str){
        return ChatColor.translateAlternateColorCodes('&', str).replace("%player_name%", player);
    }

    public static String c(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }


    public static int getEmptySlots(Player p) {
        PlayerInventory inventory = p.getInventory();
        ItemStack[] cont = inventory.getContents();
        int i = 0;
        for (ItemStack item : cont)
            if (item != null && item.getType() != Material.AIR) {
                i++;
            }
        return 36 - i;
    }
}
