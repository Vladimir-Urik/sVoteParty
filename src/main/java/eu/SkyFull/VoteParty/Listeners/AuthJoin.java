package eu.SkyFull.VoteParty.Listeners;

import eu.SkyFull.VoteParty.Utils.RewardsUtils;
import eu.SkyFull.VoteParty.VoteStorage;
import fr.xephi.authme.events.AuthMeAsyncPreLoginEvent;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;

public class AuthJoin implements Listener {

    @EventHandler
    public void onJoinAndLogin(LoginEvent e) throws IOException, InvalidConfigurationException {
        int m = VoteStorage.NormalVotes.getOfflineVotes(e.getPlayer().getName());
        if(m != 0){
            for(int i = 0; i < m; i++){
                VoteStorage.NormalVotes.removeOfflineVotes(e.getPlayer().getName());
                RewardsUtils.NormalVote.runVoteCommands(e.getPlayer());
                RewardsUtils.NormalVote.sendThankMessage(e.getPlayer());
            }
        }

        int p = VoteStorage.VoteParty.getOfflineParty(e.getPlayer().getName());
        if(p != 0){
            for(int i = 0; i < p; i++){
                VoteStorage.VoteParty.removeOfflineParty(e.getPlayer().getName());
                RewardsUtils.VoteParty.runPartyCommands(e.getPlayer());
                RewardsUtils.VoteParty.sendVoteOfflineMessage(e.getPlayer());
            }
        }
    }
}
