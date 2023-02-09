public class RedundantCandidateException extends Exception {
    private String choice;

    public RedundantCandidateException(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
