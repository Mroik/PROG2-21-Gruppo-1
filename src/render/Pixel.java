package render;

import java.awt.Color;

public class Pixel {

    public char c;

    public Color color;

    public Pixel(char c, Color color) {
        this.c = c;
        this.color = color;
    }

    @Override
    public String toString() {
        return "'" + c + "' RGB(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

}
