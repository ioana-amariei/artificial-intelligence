package solver;

import model.Disk;
import model.Rod;
import model.State;
import model.Transition;
import solver.strategy.api.HanoiStrategy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TowersOfHanoiSolver {
    private HanoiStrategy solvingStrategy;
    private boolean solvable;
    private State currentState;
    private State initialState;
    private State finalState;
    private Set<State> visitedStates;

    private int numberOfRods;
    private int numberOfDisks;

    private int maxTransitions;
    private int remainingTransitions;
    private int solvableBoundTransitions;
    private Set<State> viableTransitions;

    public TowersOfHanoiSolver(HanoiStrategy solvingStrategy, int numberOfDisks, int numberOfRods, int maxTransitions, int solvableBoundTransitions) {
        this.solvingStrategy = solvingStrategy;
        this.numberOfDisks = numberOfDisks;
        this.numberOfRods = numberOfRods;
        this.solvable = true;
        this.maxTransitions = maxTransitions;
        this.solvableBoundTransitions = solvableBoundTransitions;
        this.remainingTransitions = maxTransitions;
        this.viableTransitions = new HashSet<>();

        initialize(numberOfDisks, numberOfRods);
    }

    public void solve() {
        while (!isSolved()) {
            System.out.println(currentState.getTransition() == null ? "Initial state" : currentState.getTransition());
            startSearchFromInitialStateIfSearchLimitHasBeenReached();
            determineIfItIsStillSolvable();

            Set<State> viableTransitionsFromCurrentState = computeViableTransitionsFrom(currentState);
            viableTransitions.addAll(viableTransitionsFromCurrentState);

            if (viableTransitions.isEmpty()) {
                System.out.println("There are no viable transitions");
                restartSearch();
            } else {
                State nextState = solvingStrategy.selectNextState(viableTransitions);
                nextState.setPrevious(currentState);
                currentState = nextState;
                visitedStates.add(nextState);
            }
        }

        if (solvable) {
            System.out.println(currentState.getTransition());
        } else {
            System.out.println("No solution could be found");
        }
    }

    public int getSolutionLength() {
        return getSolution().size();
    }

    public List<State> getSolution() {
        List<State> path = new LinkedList<>();

        while (currentState != null) {
            path.add(0, currentState);
            currentState = currentState.getPrevious();
        }

        return path;
    }

    private void determineIfItIsStillSolvable() {
        --solvableBoundTransitions;

        if (solvableBoundTransitions < 0) {
            solvable = false;
        }
    }

    private void startSearchFromInitialStateIfSearchLimitHasBeenReached() {
        if (--remainingTransitions < 0) {
            restartSearch();
        }
    }

    private void restartSearch() {
        System.out.println("restarting the search from the initial position");
        remainingTransitions = maxTransitions;
        currentState = initialState;
        visitedStates.clear();
    }

    private Set<State> computeViableTransitionsFrom(State currentState) {
        Set<State> possibleTransitions = new HashSet<>();

        for (Rod rod : currentState.getRods()) {
            if (!rod.isEmpty()) {
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
                .filter(this::wasNotVisitedBefore)
                .collect(Collectors.toSet());
    }

    private boolean wasNotVisitedBefore(State state) {
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
