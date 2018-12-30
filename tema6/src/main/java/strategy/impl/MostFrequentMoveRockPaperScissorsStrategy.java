package strategy.impl;

import model.Move;
import strategy.api.RockPaperScissorsStrategy;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class MostFrequentMoveRockPaperScissorsStrategy implements RockPaperScissorsStrategy {

    @Override
    public Move nextMove(List<Move> history) {
        Map<Move, Long> handShapeToOccurrences =
                history.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map.Entry<Move, Long> mostFrequent = null;
        for (Map.Entry<Move, Long> entry : handShapeToOccurrences.entrySet()) {
            if (isNull(mostFrequent)) {
                mostFrequent = entry;
            } else if (mostFrequent.getValue() < entry.getValue()) {
                mostFrequent = entry;
            }
        }

        Move move = mostFrequent.getKey();
        return Move.getMoveThatBeats(move);
    }
}
