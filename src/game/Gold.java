/**
 * OVERVIEW: Instances of this class represent piles of gold coins you can find around the dungeon.
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
