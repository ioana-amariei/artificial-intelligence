package solver.strategy.impl;

import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;

public class OptimizedHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        LinkedList<State> list = new LinkedList<>(states);
        // Compares
        Collections.sort(list, new StateComparator());

        State state = list.get(list.size() - 1);
        states.remove(state);

        return state;
    }
}
