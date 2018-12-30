package strategy.impl;

import model.Move;
import strategy.api.RockPaperScissorsStrategy;

import java.util.List;
import java.util.Random;

public class RandomRockPaperScissorsStrategy implements RockPaperScissorsStrategy {

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public Move nextMove(List<Move> history) {
        Move[] moves = Move.values();
        int index = random.nextInt(moves.length);

        return moves[index];
    }
}
