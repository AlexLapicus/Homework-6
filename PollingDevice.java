public class PollingDevice {

    VotingData votingData = new VotingData();

    public void printBallot(VotingData v) {
        System.out.println("The candidates are ");
        for (String s : v.ballot) {
            System.out.println(s);
        }
    }

    public void screen() {
        this.printBallot(votingData);
        System.out.println("Who do you want to vote for?");
        String firstCandidate = votingData.keyboard.next();
        System.out.println("Who do you want to vote for?");
        String secondCandidate = votingData.keyboard.next();
        System.out.println("Who do you want to vote for?");
        String thirdCandidate = votingData.keyboard.next();
        System.out.println("You voted for " + firstCandidate + ", " + secondCandidate + ", " + thirdCandidate);

        try {
            votingData.submitVote(firstCandidate, secondCandidate, thirdCandidate);
        }
        catch(CandidateNotFoundException e) {
            System.out.println("Candidate cannot be found. Would you like to add them to the ballot (Y or N)");
            String choice = votingData.keyboard.next();
            if(choice.equals("y") || choice.equals("Y")) {
                addWriteIn(e.getChoice());
                screen();
            }
        }
        catch(CandidateChosenMoreThanOnceException e) {
            System.out.println("Same candidate cannot be voted twice");
            screen();
        }
    }

    public void addWriteIn(String choice) {
        try {
            votingData.nominateCandidate(choice);
        } catch (RedundantCandidateException e) {
            System.out.println("Candidate already exists");
        }
    }
}