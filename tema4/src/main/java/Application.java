import com.google.common.base.Stopwatch;
import solver.TowersOfHanoiSolver;
import solver.strategy.api.HanoiStrategy;
import solver.strategy.impl.RandomHanoiStrategy;

import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        int numberOfDisks = 4;
        int numberOfRods = 4;
        int maxTransitions = 1000;
        int solvableBoundTransitions = 100000;

        Statistics statistics = runSolverAndGetStatistics(new RandomHanoiStrategy(), numberOfDisks, numberOfRods, maxTransitions, solvableBoundTransitions);
//        statistics.printEntireStatistics();
        statistics.printGeneralStatistics();
    }

    private static Statistics runSolverAndGetStatistics(HanoiStrategy strategy, int numberOfDisks, int numberOfRods, int maxTransitions, int solvableBoundTransitions) {
        TowersOfHanoiSolver solver =
                new TowersOfHanoiSolver(
                        new RandomHanoiStrategy(),
                        numberOfDisks,
                        numberOfRods,
                        maxTransitions,
                        solvableBoundTransitions);

        Stopwatch stopwatch = Stopwatch.createStarted();
        solver.solve();
        Duration duration = stopwatch.elapsed();

        return new Statistics(strategy.getClass().toString(), solver.getSolution(), duration, solver.getTestedStates());
    }
}
