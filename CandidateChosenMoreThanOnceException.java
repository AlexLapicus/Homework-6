public class CandidateChosenMoreThanOnceException extends Exception {
    private String choice;

    public CandidateChosenMoreThanOnceException(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
