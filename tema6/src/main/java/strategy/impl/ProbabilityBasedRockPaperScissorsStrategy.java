package strategy.impl;

import model.Move;
import strategy.api.RockPaperScissorsStrategy;

import java.util.List;
import java.util.Random;

public class ProbabilityBasedRockPaperScissorsStrategy implements RockPaperScissorsStrategy {
    private Random random = new Random(System.currentTimeMillis());

    /*
    Based on the history of the user's moves, we can determine the probability with which he chooses a move;
    so we can generate with the same probability the move to counter.
     */
    @Override
    public Move nextMove(List<Move> history) {
        int index = random.nextInt(history.size());
        Move move = history.get(index);

        return Move.getMoveThatBeats(move);
    }
}
