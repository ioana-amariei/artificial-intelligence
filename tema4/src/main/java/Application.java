import com.google.common.base.Stopwatch;
import solver.TowersOfHanoiSolver;
import solver.strategy.api.HanoiStrategy;
import solver.strategy.impl.AStarHanoiStrategy;
import solver.strategy.impl.OptimizedHanoiStrategy;
import solver.strategy.impl.RandomHanoiStrategy;

import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        int numberOfDisks = 6;
        int numberOfRods = 4;
        int maxTransitions = numberOfDisks * 5000;
        int solvableBoundTransitions = 100000;

        Statistics statistics = runSolverAndGetStatistics(new AStarHanoiStrategy(), numberOfDisks, numberOfRods, maxTransitions, solvableBoundTransitions);
        statistics.printEntireStatistics();
        statistics.printGeneralStatistics();
    }

    private static Statistics runSolverAndGetStatistics(HanoiStrategy strategy, int numberOfDisks, int numberOfRods, int maxTransitions, int solvableBoundTransitions) {
        TowersOfHanoiSolver solver =
                new TowersOfHanoiSolver(
                        strategy,
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
