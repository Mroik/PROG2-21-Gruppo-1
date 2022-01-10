package game;

import base_classes.Food;

/**
 * OVERVIEW: A banana is a fruit, it satiates the player's hunger by a small amount
 */
public class Banana extends Food {
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     * @param quantity Number of items stacked together
     */
    public Banana(int x, int y, int quantity) {
        super(x, y, quantity);
        foodValue = 5;
        name = "Banana";
    }
}
