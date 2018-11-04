package solver.strategy.impl;

import model.State;

import java.util.Comparator;

public class StateComparator implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
        int s1 =  o1.getRods().size() - 1;
        int s2 =  o2.getRods().size() - 1;

        return o1.getRods().get(s1).getDisks().size() - o2.getRods().get(s2).getDisks().size();
    }
}
