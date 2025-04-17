public class AlreadyNominatedException extends Exception{
    public AlreadyNominatedException(String candidateName) {
        super("Candidate " + candidateName + " is already nominated.");
    }

}
