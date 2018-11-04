import java.util.LinkedList;
import java.util.List;

public class TowersOfHanoi {
    private List<Rod> currentState;
    private List<Rod> initialState;
    private List<Rod> finalState;

    public TowersOfHanoi(int numberOfDisks, int numberOfRods) {
        initialize(numberOfDisks, numberOfRods);
    }

    public void moveDisk(int fromRod, int toRod) {
        Disk disk = currentState.get(fromRod).removeFirst();
        currentState.get(toRod).add(disk);
    }

    public boolean isSolved() {
        return currentState.equals(finalState);
    }

    public List<Rod> getCurrentState() {
        return currentState;
    }

    public List<Rod> getInitialState() {
        return initialState;
    }

    private void initialize(int numberOfDisks, int numberOfRods) {
        // initialize game state
        currentState = createRods(numberOfRods);
        addDisks(numberOfDisks, currentState.get(0));

        // save initial state for future references
        initialState = new LinkedList<>(currentState);

        // save final state for future references
        finalState = createRods(numberOfRods);
        addDisks(numberOfDisks, finalState.get(numberOfRods - 1));
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
