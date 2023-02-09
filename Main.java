public class Main {
    public static void main(String[] args) {
        VotingData vd = new VotingData();
        PollingDevice pd = new PollingDevice();
        pd.screen();
        System.out.println("Votes for Gompei: " +
                vd.countVotes("Gompei"));
        System.out.println("Votes for Husky: " + vd.countVotes("Husky"));
    }
}