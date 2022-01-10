package map;

import java.util.Iterator;

import game.Coordinate;
import game.CoordinatePixel;

public class Room implements Iterable<CoordinatePixel> {

    private Coordinate start;

    private int length;

    private int height;

    public Room(int x, int y, int length, int height) {
        // Controllo length e height siano maggiori di 2
        // per una stanza con almeno un pixel calpsestabile

        this.start = new Coordinate(x, y);
        this.length = length;
        this.height = height;
    }

    private Coordinate nextPixel;

    private Coordinate lastPixel;

    @Override
    public Iterator<CoordinatePixel> iterator() {
        nextPixel = start;
        lastPixel = new Coordinate(start.getX(), start.getY() + height);

        return new Iterator<CoordinatePixel>() {

            @Override
            public boolean hasNext() {
                return !nextPixel.equals(lastPixel);
            }

            @Override
            public CoordinatePixel next() {
                int x = nextPixel.getX();
                int y = nextPixel.getY();
                CoordinatePixel result = new CoordinatePixel(x, y, Maps.ROOM_WALL_CHAR, Maps.ROOM_WALL_COLOR);

                if (x < start.getX() + length - 1) {
                    nextPixel = new Coordinate(x + 1, y);
                } else {
                    nextPixel = new Coordinate(start.getX(), y + 1);
                }

                return result;
            }
            
        };
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

}
