package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            x += length - 1;
        } else {
            y += length - 1;
        }
    }

    private int nextHallway;

    private int nextPixel;

    @Override
    public Iterator<CoordinatePixel> iterator() {
        nextHallway = 0;
        nextPixel = 0;

        return new Iterator<CoordinatePixel>() {

            @Override
            public boolean hasNext() {
                if (isEmpty)
                    return false;
                
                if (nextHallway >= hallways.size())
                    return false;
                
                if (nextPixel > hallways.get(nextHallway).length() - 1) {
                    return false;
                }

                return true;
            }

            @Override
            public CoordinatePixel next() {
                Hallway h = hallways.get(nextHallway);
                CoordinatePixel result = null;

                if (h.isHorizontal()) {
                    result = new CoordinatePixel(h.getX() + nextPixel, h.getY(), Maps.TUNNEL_CHAR, Maps.TUNNEL_COLOR);
                } else {
                    result = new CoordinatePixel(h.getX(), h.getY() + nextPixel, Maps.TUNNEL_CHAR, Maps.TUNNEL_COLOR);
                }

                nextPixel++;
                if (nextPixel == h.length()) {
                    nextHallway++;
                    nextPixel = 0;
                }

                return result;
            }
            
        };
    }
}
