package solver.strategy.impl;

import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class RandomHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        int bound = states.size();
        int randomPosition = new Random(System.currentTimeMillis()).nextInt(bound);

        State state = new LinkedList<>(states).get(randomPosition);
        states.remove(state);

        return state;
    }
}
