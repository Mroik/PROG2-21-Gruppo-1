package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import base_classes.ColorPalette;
import base_classes.EntityChars;
import game.CoordinatePixel;

public class Tunnel implements Iterable<CoordinatePixel> {
    
    private List<Hallway> hallways;

    private boolean isEmpty;

    private int x;

    private int y;

    public Tunnel(int x, int y) {
        hallways = new ArrayList<>();

        this.x = x;
        this.y = y;

        isEmpty = true;
    }

    public void addHallway(boolean isHorizontal, int length) {
        isEmpty = false;

        hallways.add(new Hallway(x, y, isHorizontal, length));
        if (isHorizontal) {
            if (length > 0) {
                x += length - 1;
            } else {
                x += length + 1;
            }
        } else {
            if (length > 0) {
                y += length - 1;
            } else {
                y += length + 1;
            }
        }
    }

    @Override
    public Iterator<CoordinatePixel> iterator() {
       
        return new Iterator<CoordinatePixel>() {

            private int nextHallway = 0;

            private int nextPixel = 0;

            @Override
            public boolean hasNext() {
                if (isEmpty)
                    return false;
                
                if (nextHallway >= hallways.size())
                    return false;
                
                if (nextPixel > Math.abs(hallways.get(nextHallway).length()) - 1) {
                    return false;
                }

                return true;
            }

            @Override
            public CoordinatePixel next() {
                Hallway h = hallways.get(nextHallway);
                CoordinatePixel result = null;

                if (h.isHorizontal()) {
                    if (h.length() > 0) {
                        result = new CoordinatePixel(h.getX() + nextPixel, h.getY(), EntityChars.TUNNEL_CHAR, ColorPalette.TUNNEL_COLOR);
                    } else {
                        result = new CoordinatePixel(h.getX() - nextPixel, h.getY(), EntityChars.TUNNEL_CHAR, ColorPalette.TUNNEL_COLOR);
                    }
                } else {
                    if (h.length() > 0) {
                        result = new CoordinatePixel(h.getX(), h.getY() + nextPixel, EntityChars.TUNNEL_CHAR, ColorPalette.TUNNEL_COLOR);
                    } else {
                        result = new CoordinatePixel(h.getX(), h.getY() - nextPixel, EntityChars.TUNNEL_CHAR, ColorPalette.TUNNEL_COLOR);
                    }
                }

                nextPixel++;
                if (nextPixel == Math.abs(h.length())) {
                    nextHallway++;
                    nextPixel = 0;
                }

                return result;
            }
            
        };
    }
}
