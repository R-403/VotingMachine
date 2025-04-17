import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
public class ElectionData {
    HashMap<String, Votes> voteMap;
    I3VoteStrategy winnerCalc;
    public ElectionData(I3VoteStrategy strategy){
        voteMap = new HashMap<>();
        winnerCalc = strategy;
    }
    public Set<String> getCandidates(){
        return new HashSet<>(voteMap.keySet());
    }
    public void setStrategy(I3VoteStrategy strategy){
        winnerCalc = strategy;
    }
    public void nominateCandidate(String person) throws AlreadyNominatedException {
        if (voteMap.containsKey(person)){
            throw new AlreadyNominatedException(person);
        } else {
            voteMap.put(person, new Votes(0,0,0));
        }
    }
    public void submitVote(String first, String second, String third)
            throws CandidateNotNominatedException, MoreThanOnceException {
        if (!voteMap.containsKey(first)) {
            throw new CandidateNotNominatedException(first);
        }
        if (!voteMap.containsKey(second)) {
            throw new CandidateNotNominatedException(second);
        }
        if (!voteMap.containsKey(third)) {
            throw new CandidateNotNominatedException(third);
        }
        if (first.equals(second) || first.equals(third) || second.equals(third)) {
            throw new MoreThanOnceException(first);
        }
        voteMap.get(first).voteFirst();
        voteMap.get(second).voteSecond();
        voteMap.get(third).voteThird();
    }
    public Optional<String> calculateWinner(){
        return winnerCalc.calculateWinner(voteMap);
    }
}
