import strategy.TowersOfHanoiRandomSolver;

public class Application {
    public static void main(String[] args) {
        int numberOfDisks = 4;
        int numberOfRods = 3;
        int maxTransitions = (numberOfDisks + numberOfRods) * 10;
        TowersOfHanoiRandomSolver solver = new TowersOfHanoiRandomSolver(numberOfDisks, numberOfRods, 10000, 1000000);
        solver.solve();
    }
}
