public class CandidateExistsException {
    private String choice;

    public CandidateExistsException(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
