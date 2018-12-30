package solver.strategy.impl;

import model.Rod;
import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.Set;

public class HillClimbingHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        states.removeIf(state -> fitness(state) < fitness(currentState));
        State nextState = currentState;
        for (State state : states) {
            if (fitness(state) > fitness(nextState)) {
                nextState = state;
            }
        }

        states.remove(nextState);
        return nextState;
    }

    private int fitness(State currentState){
        int score = 0;

        for (Rod rod: currentState.getRods()){
            score += (rod.getNumber() * rod.getDisks().size());
        }

        return score;
    }
}
