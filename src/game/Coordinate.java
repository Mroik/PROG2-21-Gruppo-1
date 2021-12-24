package game;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) o;

        if (this.x != other.x) {
            return false;
        }

        if (this.y != other.y) {
            return false;
        }

        return true;
    }
}
