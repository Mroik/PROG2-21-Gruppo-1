package base_classes;

public class Ammo extends Collectible implements Stackable {

    protected int quantity;
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x coordinate x
     * @param y coordinate y
     * @param quantity 
     */
    protected Ammo(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
        render = ';';
        canEat = false;
        canRead = false;
        canQuaff = false;
        canWield = true;
        canWear = false;
    }

    // METHODS

    /**
     * @return this.quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
