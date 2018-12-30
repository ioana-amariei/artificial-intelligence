package model;

public enum HandShape {

    Rock("Rock"),
    Paper("Paper"),
    Scissors("Scissors");

    String value;

    HandShape(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int compareMoves(HandShape other) {
        if (this == other)
            return 0;

        switch (this) {
            case Rock:
                return (other == Scissors ? 1 : -1);
            case Paper:
                return (other == Rock ? 1 : -1);
            case Scissors:
                return (other == Paper ? 1 : -1);
        }

        return -1;
    }
}