package strategy.api;

import model.Move;

import java.util.List;

public interface RockPaperScissorsStrategy {
    Move nextMove(List<Move> history);
}
