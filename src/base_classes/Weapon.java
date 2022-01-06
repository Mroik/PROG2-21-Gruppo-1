package base_classes;

/**
 * OVERVIEW: Instances of subclasses this class represent weapons that the player can use to fight enemies in the dungeon
 */
public abstract class Weapon extends Collectible {

    protected boolean isMeleeWeapon;
    protected boolean isRangedWeapon;
    protected boolean isThrowableWeapon;

    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    protected Weapon(int x, int y) {
        super(x, y);
        render = '/';
        this.canEat = false;
        this.canRead = false;
        this.canQuaff = false;
        this.canWield = true;
        this.canWear = false;
    }

    // METHODS

    /**
     * 
     * @return this.isMeleeWeapon
     */
    public boolean isMeleeWeapon() {
        return this.isMeleeWeapon;
    }

    /**
     * 
     * @return this.isRangedWeapon
     */
    public boolean isRangedWeapon() {
        return this.isRangedWeapon;
    }

    /**
     * 
     * @return this.isThrowableWeapon
     */
    public boolean isThrowableWeapon() {
        return this.isThrowableWeapon;
    }

}
