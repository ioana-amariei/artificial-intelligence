package strategy.impl;

import model.HandShape;
import strategy.api.RockPaperScissorsStrategy;

public class OnlyOneRockPaperScissorsStrategy implements RockPaperScissorsStrategy {
    @Override
    public HandShape selectNextShape() {
        return HandShape.Rock;
    }
}
