import java.util.HashMap;
import java.util.Optional;
public class MostFirstVotesStrategy implements I3VoteStrategy {


    @Override
    public Optional<String> calculateWinner(HashMap<String,Votes> votes){
        int mostFirstVotes = 0;
        int totalFirstVotes = 0;
        String winner = "";
        String candidateName;
        for(HashMap.Entry<String, Votes> entry : votes.entrySet()){
            candidateName = entry.getKey();
            Votes vote = entry.getValue();


            totalFirstVotes += vote.getFirstVotes();
            if(vote.getFirstVotes() > mostFirstVotes){
                mostFirstVotes = vote.getFirstVotes();
                winner = candidateName;
            }
        }
        if(mostFirstVotes > (totalFirstVotes/2)){
            return Optional.of(winner);
        }
        return Optional.empty();
    }
}

