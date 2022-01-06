package base_classes;

/**
 * OVERVIEW: Instances of subclasses of this class represent pieces of armor that the player can wear in order to increase their protection
 * against all sort of attacks
 */
public class Armor extends Collectible {
    
    protected int armorClass;

    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    protected Armor(int x, int y) {
        super(x, y);
        render = ']';
        this.canEat = false;
        this.canRead = false;
        this.canQuaff = false;
        this.canWield = true;
        this.canWear = true;
    }

    // METHODS

    /**
     * Get the armorClass value of this.
     * A higher value indicates better protection
     * @return this.armorClass
     */
    public int getArmorClass() {
        return this.armorClass;
    }
}
