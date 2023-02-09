public class CandidateNotFoundException extends Exception {
    private String choice;

    public CandidateNotFoundException(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
