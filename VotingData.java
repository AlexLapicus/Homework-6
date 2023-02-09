// Imports
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class VotingData {

    // Fields
    LinkedList<String> ballot = new LinkedList<String>();
    LinkedList<String> votes = new LinkedList<String>();
    Scanner keyboard = new Scanner(System.in);

    // Hashmaps
    private HashMap<String, Integer> firstVotes = new HashMap<>();
    private HashMap<String, Integer> secondVotes = new HashMap<>();
    private HashMap<String, Integer> thirdVotes = new HashMap<>();

    // Constructor
    VotingData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
        this.ballot.add("Ziggy");
        this.ballot.add("Chrom");
        this.ballot.add("Slime");
    }

    // Methods
    public int countVotes(String forcand) {
        int numvotes = 0;
        for (String s : votes) {
            if (s.equals(forcand))
                numvotes = numvotes+1;
        }
        return numvotes;
    }

    /**
     * The method submitVote takes three strings and returns void. It stores a single voter's choice in the data structure.
     * @param firstChoice is a new string.
     * @param secondChoice is a new string.
     * @param thirdChoice is a new string.
     * @throws CandidateChosenMoreThanOnceException if there are multiple candidates in the hashmap, then a new exception is thrown.
     * @throws CandidateNotFoundException if there is no candidate in the hashmap, a new exception is thrown.
     */
    public void submitVote(String firstChoice, String secondChoice, String thirdChoice) throws CandidateNotFoundException, CandidateChosenMoreThanOnceException {

        if (firstChoice.equals(secondChoice) || firstChoice.equals(thirdChoice)) {
            throw new CandidateChosenMoreThanOnceException(firstChoice);
        } else if (secondChoice.equals(thirdChoice)) {
            throw new CandidateChosenMoreThanOnceException(secondChoice);
        }

        if(!ballot.contains(firstChoice)) {
            throw new CandidateNotFoundException(firstChoice);
        } else if(!ballot.contains(secondChoice)) {
            throw new CandidateNotFoundException(secondChoice);
        } else if(!ballot.contains(thirdChoice)) {
            throw new CandidateNotFoundException(thirdChoice);
        }

        firstVotes.put(firstChoice, firstVotes.get(firstChoice) + 1);
        secondVotes.put(secondChoice, secondVotes.get(secondChoice) + 1);
        thirdVotes.put(thirdChoice, thirdVotes.get(thirdChoice) + 1);
    }

    /**
     * A method that takes one string and adds the candidate to the ballot, returning void.
     * @param candidate is a new string.
     * @throws RedundantCandidateException if the named candidate was already on the ballot.
     */
    public void nominateCandidate(String candidate) throws RedundantCandidateException {
        if (ballot.contains(candidate)) throw new RedundantCandidateException(candidate);
        ballot.add(candidate);
        firstVotes.put(candidate, 0);
        secondVotes.put(candidate, 0);
        thirdVotes.put(candidate, 0);
    }

    /**
     * The method finds the winner which a candidate with more than 50% of first place votes.
     * @return the winner (candidate).
     */
    public String pickWinnerMostFirstChoice() {
        float total = 0;
        for (Map.Entry<String, Integer> candidate : firstVotes.entrySet()) {
            total = total + candidate.getValue();
        }
        for (Map.Entry<String, Integer> candidate : firstVotes.entrySet()) {
            if (candidate.getValue() > total/2) {
                return candidate.getKey();
            }
        }
        return "*Requires Runoff Poll*";
    }


    /**
     * A method that takes the winner which is the candidate with the most points calculated from the highest amount of votes from any tier.
     * @return the winner (candidate).
     */
    public String pickWinnerMostAgreeable() {
        HashMap<String, Integer> candidatesBiggestVotes =  new HashMap<>();

        for (String candidateName: firstVotes.keySet()) {
            Integer maxValue = 0;
           if (firstVotes.get(candidateName) > secondVotes.get(candidateName) && firstVotes.get(candidateName) > thirdVotes.get(candidateName)) {
               maxValue = firstVotes.get(candidateName);
            } else if (secondVotes.get(candidateName) > thirdVotes.get(candidateName)) {
               maxValue = secondVotes.get(candidateName);
            } else {
               maxValue = thirdVotes.get(candidateName);
            }

           candidatesBiggestVotes.put(candidateName, maxValue);
        }

        // Find max value out of all candidates
        String maxCandidateName = "";
        Integer currentMaxVoters = Integer.MIN_VALUE;

        for (String candidateName: candidatesBiggestVotes.keySet()) {
            if ( candidatesBiggestVotes.get(candidateName) > currentMaxVoters) {
                currentMaxVoters = candidatesBiggestVotes.get(candidateName);
                maxCandidateName = candidateName;
            }

        }
        return maxCandidateName;
    }
}