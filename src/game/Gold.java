package game;

import base_classes.Collectible;
import base_classes.Stackable;

/**
 * OVERVIEW: Instances of this class represent piles of gold coins that the player can find around the dungeon.
 */
public class Gold extends Collectible implements Stackable {

    protected int quantity;
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     * @param quantity How much gold
     */
    public Gold(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
        name = "Gold";
        render = '*';
    }

    // METHODS

    /**
     * 
     * @return this.quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
}
