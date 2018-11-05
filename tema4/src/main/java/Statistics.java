import model.State;

import java.time.Duration;
import java.util.List;

public class Statistics {
    private String strategy;
    private List<State> solution;
    private Duration duration;

    public Statistics(String strategy, List<State> solution, Duration duration) {
        this.strategy = strategy;
        this.solution = solution;
        this.duration = duration;
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

    public void printGeneralStatistics(){
        System.out.println();
        System.out.println("Strategy: " + strategy);
        System.out.println("Duration: " + duration.toMillis() / 1000d + " seconds.");
        System.out.println("Solution length: " + solution.size());
    }

    public void printEntireStatistics(){
        printGeneralStatistics();

        for (State state : solution) {
            System.out.println(state.getTransition() == null ? "Initial state" : state.getTransition());
        }
    }
}
