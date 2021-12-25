package game;

<<<<<<< HEAD
public class Coordinate {

=======
public class Coordinate implements Comparable<Coordinate> {
    
>>>>>>> render-old
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
<<<<<<< HEAD
=======
    public int compareTo(Coordinate o) {
        if (this.y != o.y) {
            return this.y - o.y;
        }

        return this.x - o.x;
    }

    /* @Override
>>>>>>> render-old
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) o;
<<<<<<< HEAD

        if (this.x != other.x) {
            return false;
        }

        if (this.y != other.y) {
=======
        if (other.x != this.x) {
            return false;
        }

        if (other.y != this.y) {
>>>>>>> render-old
            return false;
        }

        return true;
<<<<<<< HEAD
    }
=======
    } */
>>>>>>> render-old
}
