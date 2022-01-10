package base_classes;

/**
 * OVERVIEW: Instances of subclasses of this class represent several foods that the player may eat
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
    protected Food(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
        render = ':';
        canEat = true;
        canRead = false;
        canQuaff = false;
        canWield = true;
        canWear = false;
    }

    // METHODS

    /**
     * 
     * @return this.quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get how much can this satiate the player
     * @return this.foodValue
     */
    public int getFoodValue() {
        return foodValue;
    }
}
