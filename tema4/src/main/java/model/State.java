package model;

import java.util.LinkedList;
import java.util.List;

public class State {
    private List<Rod> rods;
    // specifies the transition(move) used to reach this state
    private Transition transition;
    private State previous;
    private int distanceFromInitialState = 0;

    public State(List<Rod> rods) {
        this.rods = new LinkedList<>(rods);
    }

    public State(State that) {
        this.rods = new LinkedList<>();
        this.previous = that.previous;

        for (Rod rod : that.rods) {
            this.rods.add(new Rod(rod));
        }
    }

    public State getPrevious() {
        return previous;
    }

    public void setPrevious(State previous) {
        this.previous = previous;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public List<Rod> getRods() {
        return rods;
    }

    public int getDistanceFromInitialState() {
        return distanceFromInitialState;
    }

    public void setDistanceFromInitialState(int distanceFromInitialState) {
        this.distanceFromInitialState = distanceFromInitialState;
    }

    @Override
    public String toString() {
        return "State{" +
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
