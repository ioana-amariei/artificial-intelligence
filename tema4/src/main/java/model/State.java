package model;

import java.util.LinkedList;
import java.util.List;

public class State {
    private List<Rod> rods;
    // specifies the transition(move) used to reach this state
    private Transition transition;

    public State(List<Rod> rods) {
        this.rods = rods;
    }

    public State(State that) {
        this.rods = new LinkedList<>();

        for (Rod rod : that.rods){
            this.rods.add(new Rod(rod));
        }
    }

    public Transition getTransition() {
        return transition;
    }

    public List<Rod> getRods() {
        return rods;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    @Override
    public String toString() {
        return "model.State{" +
                "rods=" + rods +
                ", transition=" + transition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return rods != null ? rods.equals(state.rods) : state.rods == null;
    }

    @Override
    public int hashCode() {
        return rods != null ? rods.hashCode() : 0;
    }
}
