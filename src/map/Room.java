package map;

import java.util.Iterator;

import base_classes.ColorPalette;
import base_classes.EntityChars;
import render.Coordinate;
import render.CoordinatePixel;

public class Room implements Iterable<CoordinatePixel> {

    private Coordinate start;

    private int length;

    private int height;

    public Room(int x, int y, int length, int height) {
        if (length < 3 || height < 3)
            throw new RoomToSmallException("Room length and height must be at least 3, found (l x h): " + length + " x " + height);

        this.start = new Coordinate(x, y);
        this.length = length;
        this.height = height;
    }

    @Override
    public Iterator<CoordinatePixel> iterator() {
        
        return new Iterator<CoordinatePixel>() {

            private final Coordinate lastPixel = new Coordinate(start.getX(), start.getY() + height);

            private Coordinate nextPixel = start;

            private int row = 0;

            @Override
            public boolean hasNext() {
               return !nextPixel.equals(lastPixel);
            }

            @Override
            public CoordinatePixel next() {
                int x = nextPixel.getX();
                int y = nextPixel.getY();

                CoordinatePixel result = null;

                if (x < start.getX() + length - 1) {
                    if (row == 0 || row == height - 1) {
                        result = new CoordinatePixel(x, y, EntityChars.ROOM_WALL_HORIZONTAL_CHAR, ColorPalette.ROOM_WALL_COLOR);
                        nextPixel = new Coordinate(x + 1, y);
                    } else {
                        result = new CoordinatePixel(x, y, EntityChars.ROOM_WALL_VERTICAL_CHAR, ColorPalette.ROOM_WALL_COLOR);
                        nextPixel = new Coordinate(start.getX() + length - 1, y);
                    }
                } else {
                    if (row == 0 || row == height - 1) {
                        result = new CoordinatePixel(x, y, EntityChars.ROOM_WALL_HORIZONTAL_CHAR, ColorPalette.ROOM_WALL_COLOR);
                    } else {
                        result = new CoordinatePixel(x, y, EntityChars.ROOM_WALL_VERTICAL_CHAR, ColorPalette.ROOM_WALL_COLOR);
                    }
                    
                    nextPixel = new Coordinate(start.getX(), y + 1);
                    row++;
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
