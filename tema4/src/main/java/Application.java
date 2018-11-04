import model.State;
import solver.TowersOfHanoiSolver;
import solver.strategy.impl.OptimizedHanoiStrategy;
import solver.strategy.impl.RandomHanoiStrategy;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        int numberOfDisks = 5;
        int numberOfRods = 5;
        int maxTransitions = (numberOfDisks + numberOfRods) * 10;

        TowersOfHanoiSolver solver =
                new TowersOfHanoiSolver(
                        new RandomHanoiStrategy(),
                        numberOfDisks,
                        numberOfRods,
                        10000,
                        1000000);
        solver.solve();

        printSolution(solver.getSolution());
    }

    private static void printSolution(List<State> solution) {
        System.out.println("Solution length: " + solution.size());

        for(State state : solution){
            System.out.println(state.getTransition() == null ? "Initial state" : state.getTransition());
        }

        System.out.println();
    }
}
