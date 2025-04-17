import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class Examples {

    @Test
    public void typicalMostFirstVotes()
        throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException {
        ElectionData e = new ElectionData(new MostFirstVotesStrategy());
        e.nominateCandidate("joe");
        e.nominateCandidate("jimmy");
        e.nominateCandidate("chad");
        e.submitVote( "chad","jimmy" ,"joe");
        e.submitVote( "jimmy","chad" ,"joe");
        assertEquals(Optional.empty(), e.calculateWinner());
    }

}
