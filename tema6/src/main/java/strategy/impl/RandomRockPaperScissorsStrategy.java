package strategy.impl;

import model.HandShape;
import strategy.api.RockPaperScissorsStrategy;

import java.util.Random;

public class RandomRockPaperScissorsStrategy implements RockPaperScissorsStrategy {
    @Override
    public HandShape selectNextShape() {
        HandShape[] moves = HandShape.values();
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}
