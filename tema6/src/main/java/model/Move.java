package model;

public enum Move {

    Rock("Rock"),
    Paper("Paper"),
    Scissors("Scissors");

    String value;

    Move(String value) {
        this.value = value;
    }

    public static Move getMoveThatBeats(Move move) {
        if (move == Rock) {
            return Paper;
        } else if (move == Paper) {
            return Scissors;
        } else {
            return Rock;
        }
    }

    public int compareMoves(Move other) {
        if (this == other) {
            return 0;
        }

        if (this == Move.Rock) {
            return (other == Scissors ? 1 : -1);
        } else if (this == Move.Paper) {
            return (other == Rock ? 1 : -1);
        } else if (this == Move.Scissors) {
            return (other == Paper ? 1 : -1);
        }

        return 0;
    }

    @Override
    public String toString() {
        return value;
    }
}