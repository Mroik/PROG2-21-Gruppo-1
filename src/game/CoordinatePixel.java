package game;

import java.awt.Color;

public class CoordinatePixel extends Pixel {
    
    private Coordinate p;

    public CoordinatePixel(int x, int y, char c, Color color) {
        super(c, color);
        
        this.p = new Coordinate(x, y);
    }

    public int getX() {
        return p.getX();
    }

    public int getY() {
        return p.getY();
    }

    @Override
    public String toString() {
        return p + " : " + super.toString();
    }

}
