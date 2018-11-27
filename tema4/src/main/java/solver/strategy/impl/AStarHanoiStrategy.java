package solver.strategy.impl;

import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.Set;

public class AStarHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        State minState = states.iterator().next();

        for (State state : states) {
            if (f(state) < f(minState)) {
                minState = state;
            }
        }

        states.remove(minState);

        return minState;
    }

    private int f(State state) {
        return g(state) + h(state);
    }

    private int h(State state) {
        int lastRodIndex = state.getRods().size() - 1;
        return -2 * state.getRods().get(lastRodIndex).getDisks().size();
    }

    private int g(State state) {
        return state.getDistanceFromInitialState();
    }
}
