import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Rod {
    private int number;
    private List<Disk> disks;

    public Rod(int number) {
        this.number = number;
        disks = new LinkedList<>();
    }

    public Rod(Rod that){
        this.number = that.number;
        this.disks = new LinkedList<>();

        for(Disk disk : that.getDisks()){
            disks.add(new Disk(disk.getSize()));
        }
    }

    public int getNumber() {
        return number;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void add(Disk disk) {
        // add disk at the end of the list
        disks.add(disk);
    }

    public Disk removeFirst() {
        // remove a disk from the end of the list
//        System.out.println(Arrays.toString(disks.toArray()));
        Disk disk = disks.get(disks.size() - 1);
        disks.remove(disks.size() - 1);

        return disk;
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public boolean hasValidConfiguration() {
        for (int i = 1; i < disks.size(); i++) {
            Disk smallerDisk = disks.get(i);
            Disk largerDisk = disks.get(i - 1);
            if (largerDisk.getSize() < smallerDisk.getSize()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Rod{" +
                "number=" + number +
                ", disks=" + disks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rod rod = (Rod) o;

        if (number != rod.number) return false;
        return disks != null ? disks.equals(rod.disks) : rod.disks == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (disks != null ? disks.hashCode() : 0);
        return result;
    }
}
