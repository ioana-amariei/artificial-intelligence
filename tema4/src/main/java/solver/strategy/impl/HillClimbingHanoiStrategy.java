package solver.strategy.impl;

import model.Disk;
import model.Rod;
import model.State;
import solver.strategy.api.HanoiStrategy;

import java.util.Set;

public class HillClimbingHanoiStrategy implements HanoiStrategy {
    @Override
    public State selectNextState(State currentState, Set<State> states) {
        states.removeIf(state -> fitness2(state) < fitness2(currentState));
        State nextState = currentState;
        for (State state : states) {
            if (fitness2(state) > fitness2(nextState)) {
                nextState = state;
            }
        }

        states.remove(nextState);
        return nextState;
    }

    private int fitness(State currentState) {
        int score = 0;

        for (Rod rod: currentState.getRods()){
            for (Disk disk: rod.getDisks()){
                score += (rod.getNumber() * disk.getSize() * 15);
            }
        }

        return score;
    }

    private int fitness2(State currentState){
        int score = 0;

        for (Rod rod: currentState.getRods()){
            score += (rod.getNumber() * rod.getDisks().size());
        }

        return score;
    }
}
