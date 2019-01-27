package model;

import strategy.api.RockPaperScissorsStrategy;
import strategy.impl.MostFrequentMoveRockPaperScissorsStrategy;
import strategy.impl.ProbabilityBasedRockPaperScissorsStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Computer {

    private Random random = new Random(System.currentTimeMillis());

    private List<RockPaperScissorsStrategy> strategies = Arrays.asList(
            new MostFrequentMoveRockPaperScissorsStrategy(),
            new ProbabilityBasedRockPaperScissorsStrategy()
    );

    public Move getShape(List<Move> userHistory) {
        int index = random.nextInt(strategies.size());
        return strategies.get(index).nextMove(userHistory);
    }
}
