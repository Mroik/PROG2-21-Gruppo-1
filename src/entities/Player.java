package entities;

import java.awt.Color;

import base_classes.ColorPalette;
import base_classes.EntityChars;

public class Player implements Entity {
    
    private int x;

    private int y;

    private char c;

    private Color color;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        c = EntityChars.PLAYER_CHAR;
        color = ColorPalette.PLAYER_COLOR;
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

    public void moveUp() {
        y--;
    }

    public void moveDown() {
        y++;
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }
    
}
