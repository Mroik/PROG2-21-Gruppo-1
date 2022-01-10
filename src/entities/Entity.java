package entities;

import java.awt.Color;

public interface Entity {
    
    public int getX();

    public int getY();

    public char getChar();

    public Color getColor();

    public void moveUp();

    public void moveDown();

    public void moveRight();

    public void moveLeft();

}
