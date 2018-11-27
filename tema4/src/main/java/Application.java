import com.google.common.base.Stopwatch;
import solver.TowersOfHanoiSolver;
import solver.strategy.api.HanoiStrategy;
import solver.strategy.impl.AStarHanoiStrategy;
import solver.strategy.impl.HillClimbingHanoiStrategy;
import solver.strategy.impl.OptimizedHanoiStrategy;
import solver.strategy.impl.RandomHanoiStrategy;

import java.time.Duration;
import java.util.*;

public class Application {
    public static void main(String[] args) {
//        testOneInstance();
        runComparison();
    }

    private static void runComparison() {
        List<HanoiStrategy> hanoiStrategies = new LinkedList<>();
        hanoiStrategies.add(new RandomHanoiStrategy());
        hanoiStrategies.add(new AStarHanoiStrategy());
        hanoiStrategies.add(new HillClimbingHanoiStrategy());
        hanoiStrategies.add(new OptimizedHanoiStrategy());

        Map<String, List<Statistics>> strategyStatistics = new HashMap<>();
        Random random = new Random(System.currentTimeMillis());
        for (HanoiStrategy strategy : hanoiStrategies) {
            System.out.println("Running: " + strategy);
            List<Statistics> statistics = new LinkedList<>();
            for (int i = 0; i < 25; i++) {
                int numberOfDisks = random.nextInt(4) + 2;
                int numberOfRods = random.nextInt(4) + 2;
                Statistics stat = runSolverAndGetStatistics(strategy, numberOfDisks, numberOfRods, numberOfDisks * 5000, 100000);
                statistics.add(stat);
            }
            strategyStatistics.put(strategy.getClass().getName(), statistics);
        }
        printStats(strategyStatistics);
    }

    private static void printStats(Map<String, List<Statistics>> strategyStatistics) {
        for(Map.Entry<String, List<Statistics>> entry: strategyStatistics.entrySet()){
            printStats(entry.getKey(), entry.getValue());
        }
    }

    private static void printStats(String strategy, List<Statistics> statistics) {
        int size = statistics.size();

        int solved = (int)statistics.stream().filter(Statistics::isSolved).count();
        int avgLength = statistics.stream().filter(Statistics::isSolved).mapToInt(Statistics::getLength).sum() / (solved == 0 ? 1 : solved);
        int avgStates = statistics.stream().filter(Statistics::isSolved).mapToInt(Statistics::getTestedStates).sum() / (solved == 0 ? 1 : solved);
        long avgDuration = statistics.stream().filter(Statistics::isSolved).mapToLong(s -> s.getDuration().toMillis()).sum() / (solved == 0 ? 1 : solved);

        System.out.println();
        System.out.println("Strategy: " + strategy);
        System.out.println("Average length: " + avgLength);
        System.out.println("Average duration: " + avgDuration / 1000d + " seconds.");
        System.out.println("Average tested states: " + avgStates);
        System.out.println("Solved times: " + solved);
    }

    private static void testOneInstance() {
        int numberOfDisks = 4;
        int numberOfRods = 6;
        int maxTransitions = numberOfDisks * 5000;
        int solvableBoundTransitions = 100000;
//        HanoiStrategy strategy = new UniformCostHanoiStrategy();
        HanoiStrategy strategy = new HillClimbingHanoiStrategy();

        Statistics statistics = runSolverAndGetStatistics(strategy, numberOfDisks, numberOfRods, maxTransitions, solvableBoundTransitions);
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

        return new Statistics(strategy.getClass().toString(), solver.getSolution(), duration, solver.getTestedStates(), solver.isSolvable());
    }
}
