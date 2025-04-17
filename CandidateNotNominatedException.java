public class CandidateNotNominatedException extends Exception {
    private String candidateName;


    public CandidateNotNominatedException(String candidateName) {
        super("Candidate " + candidateName + " was never nominated.");
        this.candidateName = candidateName;
    }


    public String getCandidate() {
        return candidateName;
    }
}
