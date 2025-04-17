import java.util.Optional;
import java.util.Scanner;

public class VotingMachine {
    static ElectionData eData;
    public VotingMachine() {
        eData = new ElectionData(new MostFirstVotesStrategy());
    }
    public void nominate() {
        Scanner keyboard = new Scanner(System.in);
        String nominee= "prompt";

        System.out.println("Who would you like to nominate?");
        nominee = keyboard.nextLine();
        try {
            nominate(nominee);
        } catch (AlreadyNominatedException ane) {
            System.err.printf(ane.getMessage());
            System.out.println();
        }
    }
    public void nominate(String nominee) throws AlreadyNominatedException {
        eData.nominateCandidate(nominee);
        System.out.println(nominee + " is nominated.");
    }
    public void vote(){
        Scanner keyboard = new Scanner(System.in);
        String first;
        String second;
        String third;
        String nominateNew;

        System.out.println("Who is your first vote?");
        first = keyboard.nextLine();

        System.out.println("Who is your second vote?");
        second = keyboard.nextLine();

        System.out.println("Who is your third vote?");
        third = keyboard.nextLine();

        try {
            vote(first, second, third);
        } catch (CandidateNotNominatedException cnne) {
            System.err.printf(cnne.getMessage());
            System.out.println("Would you like to nominate the candidate?" + " y" + " or" + " n?");
            nominateNew = keyboard.nextLine();
            if (nominateNew.substring(0,1).equalsIgnoreCase("y")){
                String nominee = cnne.getCandidate();
                try {
                    nominate(nominee);
                } catch (AlreadyNominatedException ane) {
                    System.err.printf(ane.getMessage());
                    System.out.println();
                }
            }
        }catch (MoreThanOnceException mtoe){
            System.err.printf(mtoe.getMessage());
            System.out.println();
        }
    }
    public void vote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{
        eData.submitVote(first, second, third);
        System.out.println("voted successfully: "+ first + " " + second + " " + third);
    }
    public void stratChange(){
        Scanner keyboard = new Scanner(System.in);
        String stratChoice;
        System.out.println("Which strategy would you like to use? most [f] for first votes or most [a] for agreeable?");
        stratChoice = keyboard.nextLine();
        if (stratChoice.substring(0,1).equalsIgnoreCase("f")){
            eData.setStrategy(new MostFirstVotesStrategy());
            System.out.println("Strategy Changed to first votes");
        }
        else if (stratChoice.substring(0,1).equalsIgnoreCase("a")){
            eData.setStrategy(new MostAgreeableStrategy());
            System.out.println("Strategy Changed to agreeable");
        }
        else{
            System.out.println("not a correct or valid strategy, nothing has been changed");
        }
    }
    public void getWinner(){
        Optional<String> winner = eData.calculateWinner();
        if (winner.isPresent()) {
            System.out.println("The winner is......: " + winner.get());
        } else {
            System.out.println("........No clear winner under the current strategy sadly.");
        }

    }
    public static void main(String[] args) {
        VotingMachine votingMachine = new VotingMachine();
        votingMachine.run();
    }
    public void run()  {
        Scanner keyboard = new Scanner(System.in);
        String action = "prompt";
        String response = "p";
        while(!action.equals("exit")) {
            System.out.println(eData.getCandidates());
            System.out.println("Do you want to [n] to nominate someone, [v] to vote for someone, to change winner [s] strategy,or to see who [w] for won, or [q] for quit?");
            action = keyboard.nextLine();
            if (action.length() > 0){
                response = action.substring(0,1);
            }
            else {
                response = "p";
            }
            if(response.equalsIgnoreCase("n")) {
                this.nominate();
            }
            if(response.toLowerCase().equals("v")) {
                this.vote();
            }
            if(response.toLowerCase().equals("s")) {
                this.stratChange();
            }
            if(response.toLowerCase().equals("w")) {
                this.getWinner();
            }
            if(response.toLowerCase().equals("q")) {
                System.out.println("Bye now");
                action = "exit";
            }
        }
    }
}
