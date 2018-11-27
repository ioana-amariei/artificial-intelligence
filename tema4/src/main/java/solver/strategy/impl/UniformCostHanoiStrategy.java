package solver.strategy.impl;

import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.Set;

public class UniformCostHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        State minCostState = states.iterator().next();

        for (State state : states) {
            // Uniform Cost Search gives the minimum cumulative cost the maximum priority.
            if (cost(state) < cost(minCostState)) {
                minCostState = state;
            }
        }

        states.remove(minCostState);

        return minCostState;
    }

    private int cost(State state) {
        return state.getDistanceFromInitialState();
    }
}
