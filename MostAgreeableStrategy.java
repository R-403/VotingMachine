import java.util.HashMap;
import java.util.Optional;
public class MostAgreeableStrategy implements I3VoteStrategy {
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        String winner = "";
        int maxSectionVotes = 0;


        for (HashMap.Entry<String, Votes> entry : votes.entrySet()) {
            String candidate = entry.getKey();
            Votes vote = entry.getValue();
            int maxVotesForCandidate = Math.max(vote.getFirstVotes(), Math.max(vote.getSecondVotes(), vote.getThirdVotes()));
            if (maxVotesForCandidate >= maxSectionVotes) {
                maxSectionVotes = maxVotesForCandidate;
                winner = candidate;
            }
        }
        return Optional.of(winner);
    }}
