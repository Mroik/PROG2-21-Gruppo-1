package game;

import java.util.Map;
import java.util.TreeMap;

import java.awt.Color;

public class Levels {

    public Map<Coordinate, LevelPixel> map;

    public Levels(int nLevels, int rows, int cols) {
        map = new TreeMap<>();
    }

    public boolean containsKey(Coordinate c) {
        return map.containsKey(c);
    }

    public LevelPixel get(Coordinate c) {
        return map.get(c);
    }

    public void addPixelLevel(int level, int x, int y, char c, Color color) {
        Coordinate newCoord = new Coordinate(x, y);
        if (map.containsKey(newCoord)) {
            LevelPixel oldPixel = map.get(newCoord);
            if (oldPixel.getLevel() > level) {
                return;
            }
        }

        map.put(
            newCoord,
            new LevelPixel(level, c, color)
        );
    }

    public void clear() {
        map.clear();
    }
}
