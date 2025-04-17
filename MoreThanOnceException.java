public class MoreThanOnceException extends Exception {
    public MoreThanOnceException(String candidateName) {
        super("Candidate " + candidateName + " was already voted for.");
    }
}

