/**
 * OVERVIEW: Instances of this class represent portions of food that the player may eat
 */
public abstract class Food extends Collectible implements Eatable, Stackable {

    protected int quantity;
    protected int foodValue;
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x coordinate x
     * @param y coordinate y
     * @param quantity 
     */
    public Food(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
        render = ':';
        this.canEat = true;
        this.canRead = false;
        this.canQuaff = false;
        this.canWield = true;
        this.canWear = false;
    }

    // METHODS

    /**
     * 
     * @return this.quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Get how much can this satiate the player
     * @return this.foodValue
     */
    public int getFoodValue() {
        return this.foodValue;
    }
}
