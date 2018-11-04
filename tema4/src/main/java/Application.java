import solver.TowersOfHanoiSolver;
import solver.strategy.impl.RandomHanoiStrategy;

public class Application {
    public static void main(String[] args) {
        int numberOfDisks = 4;
        int numberOfRods = 3;
        int maxTransitions = (numberOfDisks + numberOfRods) * 10;

        TowersOfHanoiSolver solver =
                new TowersOfHanoiSolver(
                        new RandomHanoiStrategy(),
                        numberOfDisks,
                        numberOfRods,
                        10000,
                        1000000);
        solver.solve();
    }
}
