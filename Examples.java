import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Examples {

    // method to set up a ballot and cast votes

    VotingData setup1() {

        VotingData vd = new VotingData();

        // put candidates on the ballot
        try {

            vd.nominateCandidate("gompei");
            vd.nominateCandidate("husky");
            vd.nominateCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            vd.submitVote("gompei", "husky", "ziggy");
            vd.submitVote("gompei", "ziggy", "husky");
            vd.submitVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(vd);

    }

    VotingData setup2() {

        VotingData vd = new VotingData();

        // put candidates on the ballot
        try {

            vd.nominateCandidate("gompei");
            vd.nominateCandidate("husky");
            vd.nominateCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            vd.submitVote("gompei", "husky", "ziggy");
            vd.submitVote("husky", "ziggy", "gompei");
            vd.submitVote("ziggy", "gompei", "husky");

        } catch (Exception e) {}

        return(vd);

    }

    VotingData setup3() {

        VotingData vd = new VotingData();

        // put candidates on the ballot
        try {

            vd.nominateCandidate("gompei");
            vd.nominateCandidate("husky");
            vd.nominateCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            vd.submitVote("gompei", "gompei", "gompei");
            vd.submitVote("gompei", "gompei", "gompei");
            vd.submitVote("gompei", "gompei", "gompei");

        } catch (Exception e) {}

        return(vd);

    }

    // now run a test on a specific election

    @Test
    public void testMostFirstWinner_noRunoff() {
        assertEquals("gompei", this.setup1().pickWinnerMostFirstChoice());
    }

    @Test
    public void testMostFirstWinner_runoff() {
        assertEquals("*Requires Runoff Poll*", this.setup2().pickWinnerMostFirstChoice());
    }

    @Test
    public void testMostAgreeableWinner_noRunoff() {
        assertEquals("ziggy", this.setup1().pickWinnerMostAgreeable());
    }

    @Test
    public void testMostAgreeableWinner_runoff() {
        assertEquals("*Requires Runoff Poll*", this.setup2().pickWinnerMostFirstChoice());
    }

    @Test(expected=CandidateChosenMoreThanOnceException.class)
    public void testCandidateChosenMoreThanOnceException() throws CandidateChosenMoreThanOnceException {
        try {
            setup3().submitVote("Connor Tam", "Connor Tam", "Connor Tam");
        }
        catch(CandidateNotFoundException e) {

        }
    }

    @Test(expected=CandidateNotFoundException.class)
    public void testCandidateNotFoundException() throws CandidateNotFoundException {
        try {
            setup3().submitVote("Connor Tam", "Jared Sangil", "Brandon Luong");
        }
        catch (CandidateChosenMoreThanOnceException e) {
        }
    }

    @Test(expected=RedundantCandidateException.class)
    public void testRedundantCandidateException() throws RedundantCandidateException {
        {
            VotingData vd = new VotingData();
            vd.nominateCandidate("Connor Tam");

            PollingDevice pd = new PollingDevice();
            pd.addWriteIn("Connor Tam");
            pd.printBallot(vd);
        }
    }
}
