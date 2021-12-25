package game;

import java.awt.Color;

public class LevelPixel extends Pixel {
    
    private int level;

    public LevelPixel(int level, char c, Color color) {
        super(c, color);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Level: " + level + ", Char: '" + c + "', Color: RGB( " + color.getRed() + " , " + color.getGreen() + " , " + color.getBlue() + ")";
    }
}
