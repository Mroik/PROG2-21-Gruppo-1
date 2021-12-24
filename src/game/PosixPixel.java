package game;

import java.awt.Color;

public class PosixPixel {

    private int x;

    private int y;

    private char c;

    private Color color;

    public PosixPixel(int x, int y, char c, Color color) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getChar() {
        return c;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Character: '" + c + "' - Color: RGB( " + color.getRed() + " ; " + color.getGreen() + " ; " + color.getBlue() + " )";
    }

}