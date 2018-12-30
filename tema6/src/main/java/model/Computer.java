package model;

import strategy.impl.OnlyOneRockPaperScissorsStrategy;
import strategy.impl.RandomRockPaperScissorsStrategy;

public class Computer {
    private int currentRound;

    public Computer() {
        this.currentRound = 0;
    }

    public HandShape getShape() {
        if(currentRound % 2 == 0) {
            return new RandomRockPaperScissorsStrategy().selectNextShape();
        } else {
            return new OnlyOneRockPaperScissorsStrategy().selectNextShape();
        }
    }
}
