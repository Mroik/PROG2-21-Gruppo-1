package render;

public class Coordinate implements Comparable<Coordinate> {
    
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.y != o.y) {
            return this.y - o.y;
        }

        return this.x - o.x;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate))
            return false;
        
        Coordinate other = (Coordinate) o;

        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "( " + x + " , " + y + " )";
    }
}
