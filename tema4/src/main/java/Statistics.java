import model.State;

import java.time.Duration;
import java.util.List;

public class Statistics {
    private final boolean solved;
    private String strategy;
    private List<State> solution;
    private Duration duration;
    private int testedStates;

    public Statistics(String strategy, List<State> solution, Duration duration, int testedStates, boolean solvable) {
        this.strategy = strategy;
        this.solution = solution;
        this.duration = duration;
        this.testedStates = testedStates;
        this.solved = solvable;
    }

    public int getTestedStates() {
        return testedStates;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getStrategy() {
        return strategy;
    }

    public List<State> getSolution() {
        return solution;
    }

    public Duration getDuration() {
        return duration;
    }

    public void printGeneralStatistics() {
        System.out.println();
        System.out.println("Strategy: " + strategy);
        System.out.println("Duration: " + duration.toMillis() / 1000d + " seconds.");
        System.out.println("Tested states: " + testedStates);
        if (solved) {
            System.out.println("Solution length: " + solution.size());
        } else {
            System.out.println("No solution could be found.");
        }
    }

    public int getLength(){
        return solution.size();
    }

    public void printEntireStatistics() {
        printGeneralStatistics();

        for (State state : solution) {
            System.out.println(state.getTransition() == null ? "Initial state" : state.getTransition());
        }
    }
}
