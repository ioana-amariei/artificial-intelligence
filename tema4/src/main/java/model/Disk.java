package model;

public class Disk {
    private int size;

    public Disk(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disk disk = (Disk) o;

        return size == disk.size;
    }

    @Override
    public String toString() {
        return "model.Disk{" +
                "size=" + size +
                '}';
    }

    @Override
    public int hashCode() {
        return size;
    }
}
