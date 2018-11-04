package model;

public class Transition {
    private int disk;
    private int fromRod;
    private int toRod;

    public Transition(int disk, int from, int to) {
        this.disk = disk;
        this.fromRod = from;
        this.toRod = to;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "disk=" + disk +
                ", fromRod=" + fromRod +
                ", toRod=" + toRod +
                '}';
    }
}
