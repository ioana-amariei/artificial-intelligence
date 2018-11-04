import java.util.*;
import java.util.stream.Collectors;

public class TowersOfHanoiRandomSolver {
    private State currentState;
    private State initialState;
    private State finalState;

    private Set<State> visitedStates;

    private int numberOfRods;
    private int numberOfDisks;


    public TowersOfHanoiRandomSolver(int numberOfDisks, int numberOfRods) {
        this.numberOfDisks = numberOfDisks;
        this.numberOfRods = numberOfRods;
        initialize(numberOfDisks, numberOfRods);
    }

    public void solve() {
        while (!isSolved()) {
            System.out.println(currentState.getTransition());

            Set<State> viableTransitions = computeViableTransitionFrom(currentState);
            State nextState = selectNextState(viableTransitions);
            currentState = nextState;
            visitedStates.add(nextState);
        }
        System.out.println(currentState.getTransition());
    }

    private State selectNextState(Set<State> viableTransitions) {
        int bound = viableTransitions.size();
        int randomPosition = new Random(System.currentTimeMillis()).nextInt(bound);

        return new LinkedList<>(viableTransitions).get(randomPosition);
    }

    private Set<State> computeViableTransitionFrom(State currentState) {
        Set<State> possibleTransitions = new HashSet<>();

        for (Rod rod : currentState.getRods()) {
//            System.out.println(rod);
            if (!rod.isEmpty()) {
//                System.out.println(rod);
                for (int i = 0; i < numberOfRods; i++) {
                    State nextState = new State(currentState);
                    moveDisk(nextState, rod.getNumber(), i);
                    possibleTransitions.add(nextState);
                }
            }
        }

        return filterToContainOnlyViableTransitions(possibleTransitions);
    }

    private Set<State> filterToContainOnlyViableTransitions(Set<State> possibleTransitions) {
        return possibleTransitions
                .stream()
                .filter(this::hasValidConfiguration)
                .filter(this::wasNotVisitedInThePast)
                .collect(Collectors.toSet());
    }

    private boolean wasNotVisitedInThePast(State state) {
        return !visitedStates.contains(state);
    }

    private boolean hasValidConfiguration(State state) {
        for (Rod rod : state.getRods()) {
            if (!rod.hasValidConfiguration()) {
                return false;
            }
        }

        return true;
    }

    public void moveDisk(State state, int fromRod, int toRod) {
        Disk disk = state.getRods().get(fromRod).removeFirst();
        state.getRods().get(toRod).add(disk);

        Transition transition = new Transition(disk.getSize(), fromRod, toRod);
        state.setTransition(transition);
    }

    public boolean isSolved() {
        return currentState.equals(finalState);
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getInitialState() {
        return initialState;
    }

    private void initialize(int numberOfDisks, int numberOfRods) {
        // save initial state for future references
        initialState = createInitialState(numberOfRods);

        // initialize game state
        currentState = new State(initialState);

        // save final state for future references
        finalState = createFinalState(numberOfRods);

        visitedStates = new HashSet<>();
    }

    private State createFinalState(int numberOfRods) {
        List<Rod> rods = createRods(numberOfRods);
        addDisks(numberOfDisks, rods.get(numberOfRods - 1));

        return new State(rods);
    }

    private State createInitialState(int numberOfRods) {
        List<Rod> rods = createRods(numberOfRods);
        addDisks(numberOfDisks, rods.get(0));

        return new State(rods);
    }

    private List<Rod> createRods(int numberOfRods) {
        List<Rod> rods = new LinkedList<>();

        for (int i = 0; i < numberOfRods; i++) {
            rods.add(new Rod(i));
        }

        return rods;
    }

    private void addDisks(int numberOfDisks, Rod rod) {
        for (int i = numberOfDisks; i > 0; --i) {
            rod.add(new Disk(i));
        }
    }
}
