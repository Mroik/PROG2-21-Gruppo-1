package game;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.Map;
import java.util.TreeMap;

>>>>>>> render-old
import java.awt.Color;

public class Levels {

<<<<<<< HEAD
    private List<List<List<Pixel>>> levels;

    public Levels(int nLevels, int rows, int cols) {
        levels = new ArrayList<>(nLevels);
        for (int i = 0; i < nLevels; i++) {
            levels.add(new ArrayList<>(rows));

            List<List<Pixel>> level = levels.get(i);
            for (int j = 0; j < rows; j++) {
                level.add(new ArrayList<>(cols));

                List<Pixel> row = level.get(j);
                for (int k = 0; k < cols; k++) {
                    row.add(null);
                }
            }
        }
    }

    public int size() {
        return levels.size();
    }

    public List<PosixPixel> getPixels(int nLevel) {
        List<PosixPixel> pixels = new ArrayList<>();
        List<List<Pixel>> level = levels.get(nLevel);

        for (int y = 0; y < level.size(); y++) {
            List<Pixel> row = level.get(y);
            for (int x = 0; x < row.size(); x++) {
                Pixel p = row.get(x);
                if (p != null) {
                    pixels.add(new PosixPixel(x, y, p.c, p.color));
                }
            }
        }

        return pixels;
    }

    public void set(int nLevel, int x, int y, char c, Color color) {
        Pixel p = levels.get(nLevel).get(y).get(x);

        if (p == null) {
            levels.get(nLevel).get(y).set(x, new Pixel(c, color));
        } else {
            p.c = c;
            p.color = color;
        }
    }

    public Pixel remove(int nLevel, int x, int y) {
        return levels.get(nLevel).get(y).set(x, null);
    }

    public void clear() {
        for (int i = 0; i < levels.size(); i++) {
            List<List<Pixel>> level = levels.get(i);
            for (int j = 0; j < level.size(); j++) {
                List<Pixel> row = level.get(j);

                for (int k = 0; k < row.size(); k++) {
                    Pixel p = row.get(k);
                    if (p != null) {
                        row.set(k, null);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < levels.size(); i++) {
            String levelString = "";
            boolean toPrint = false;

            List<List<Pixel>> level = levels.get(i);
            for (int j = 0; j < level.size(); j++) {
                List<Pixel> row = level.get(j);

                for (int k = 0; k < row.size(); k++) {
                    Pixel p = row.get(k);
                    if (p != null) {
                        toPrint = true;
                        levelString += "\t( " + k + " ; " + j + " ) -> " + p + "\n";
                    }
                }
            }

            s += "Level " + i + "\n";
            if (toPrint) {
                s += levelString;
            } else {
                s += "\tempty\n";
            }
                
        }

        if (s == "") {
            return "No levels";
        }

        return s.stripTrailing();
=======
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
>>>>>>> render-old
    }
}
