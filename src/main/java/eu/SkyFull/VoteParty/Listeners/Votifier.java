package eu.SkyFull.VoteParty.Listeners;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import eu.SkyFull.VoteParty.Utils.RewardsUtils;
import eu.SkyFull.VoteParty.VoteConfiguration;
import eu.SkyFull.VoteParty.VoteStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Votifier implements Listener {

    public static int votes = 0;
    public static int max;

    static {
        try {
            max = VoteConfiguration.getConfig().getInt("voteparty.votes");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    List<String> votedPlayers = new ArrayList<>();

    @EventHandler
    public void onVote(VotifierEvent e) throws IOException, InvalidConfigurationException {
        Vote vote = e.getVote();
        List<String> broadcast = VoteConfiguration.getConfig().getStringList("on-vote.broadcast");
        if(!votedPlayers.contains(vote.getUsername())){
            votedPlayers.add(vote.getUsername());
        }
        for(String str : broadcast){
            Bukkit.broadcastMessage(c(vote.getUsername(), str));
        }
        if(Bukkit.getPlayer(vote.getUsername()) != null) {
            Player p = Bukkit.getPlayer(vote.getUsername());
            RewardsUtils.NormalVote.runVoteCommands(p);
            RewardsUtils.NormalVote.sendThankMessage(p);
            VoteStorage.NormalVotes.addVote(vote.getUsername());
        } else {
            VoteStorage.NormalVotes.addOfflineVote(vote.getUsername());
            VoteStorage.NormalVotes.addVote(vote.getUsername());
        }
        votes++;
        if(votes >= max) {
            List<String> broadcast_party = VoteConfiguration.getConfig().getStringList("voteparty.broadcast");
            for(String str : broadcast_party){
                Bukkit.broadcastMessage(c(str));
            }
            for(String username : votedPlayers) {
                {
                    if (Bukkit.getPlayer(username) != null) {
                        VoteStorage.VoteParty.addParty(username);
                        Player p = Bukkit.getPlayer(username);
                        RewardsUtils.VoteParty.runPartyCommands(p);
                    } else {
                        VoteStorage.VoteParty.addParty(username);
                        VoteStorage.VoteParty.addOfflineParty(username);
                    }
                }
            }
            votes = 0;
            votedPlayers.clear();
        }
    }


    public String c(String player, String str){
        return ChatColor.translateAlternateColorCodes('&', str).replace("%player_name%", player);
    }

    public String c(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
