package base_classes;

/**
 * OVERVIEW: Instances of subclasses of this class represents collectible objects like gold or weapons, potions and other
 * things that go in your inventory
 */
public abstract class Collectible extends Entity {

    // Qualities a collectible can have, each one corresponds to a specific command
    protected boolean canEat;
    protected boolean canRead;
    protected boolean canQuaff;
    protected boolean canWield;
    protected boolean canWear;
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    protected Collectible(int x, int y) {
        super(x, y);
    }

    // METHODS

    /**
     * 
     * @return this.canEat
     */
    public boolean canEat() {
        return this.canEat;
    }

    /**
     * 
     * @return this.canRead
     */
    public boolean canRead() {
        return this.canRead;
    }

    /**
     * 
     * @return this.canQuaff
     */
    public boolean canQuaff() {
        return this.canQuaff;
    }

    /**
     * 
     * @return this.canWield
     */
    public boolean canWield() {
        return this.canWield;
    }

    /**
     * 
     * @return this.canWear
     */
    public boolean canWear() {
        return this.canWear;
    }

}
