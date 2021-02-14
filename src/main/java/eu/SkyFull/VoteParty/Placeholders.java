package eu.SkyFull.VoteParty;

import eu.SkyFull.VoteParty.Listeners.Votifier;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "voteparty";
    }

    @Override
    public @NotNull String getAuthor() {
        return "GGGEDR";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier){

        String result = null;

        if(identifier.equals("votes")){
            result = Integer.toString(Votifier.votes);
        }

        if(identifier.equals("max_votes")){
            result = Integer.toString(Votifier.max);
        }

        if(identifier.equals("total")){
            result = Integer.toString(VoteStorage.NormalVotes.getTotalVotes(player.getName()));
        }

        return result;
    }
}
