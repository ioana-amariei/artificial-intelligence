package solver.strategy.api;

import model.State;

import java.util.Set;

public interface HanoiStrategy {
    State selectNextState(State currentState, Set<State> states);
}
