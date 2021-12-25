package game;

public class Coordinate implements Comparable<Coordinate> {
    
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.y != o.y) {
            return this.y - o.y;
        }

        return this.x - o.x;
    }
}
