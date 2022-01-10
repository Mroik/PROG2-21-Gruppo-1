package base_classes;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * OVERVIEW: Instances of subclasses this class represent weapons that the player can use to fight enemies in the dungeon
 */
public abstract class Weapon extends Collectible {

    // flags regarding the type of weapon
    protected boolean isRangedWeapon;

    // chance of weapon breaking after using it
    // must be a number between 0 and 100
    protected int breakingChanceMelee;
    protected int breakingChanceThrow;

    // these lists contain info about damage and status the weapon inflicts
    protected List<EntityData.Damage> meleeDamage;
    protected List<EntityData.Damage> throwDamage;
    protected List<EntityData.Status> statusInflicted;

    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    protected Weapon(int x, int y) {
        super(x, y);
        render = '/';
        canEat = false;
        canRead = false;
        canQuaff = false;
        canWield = true;
        canWear = false;
        meleeDamage = new ArrayList<>();
        throwDamage = new ArrayList<>();
        statusInflicted = new ArrayList<>();
    }

    // METHODS

    /**
     * 
     * @return this.isRangedWeapon
     */
    public boolean isRangedWeapon() {
        return isRangedWeapon;
    }

    /**
     * 
     * @return this.breakingChanceMelee
     */
    public int getBreakingChanceMelee() {
        return breakingChanceMelee;
    }

    /**
     * 
     * @return this.breakingChanceThrow
     */
    public int getBreakingChanceThrow() {
        return breakingChanceThrow;
    }

    public Iterator<EntityData.Damage> meleeDamageIterator() {
        return new Iterator<EntityData.Damage>() {
            int nextIndex = 0;

            public boolean hasNext() {
                return (nextIndex < meleeDamage.size());
            }

            public EntityData.Damage next() {
                return meleeDamage.get(nextIndex++);
            }
        };
    }

    public Iterator<EntityData.Damage> throwDamageIterator() {
        return new Iterator<EntityData.Damage>() {
            int nextIndex = 0;

            public boolean hasNext() {
                return (nextIndex < throwDamage.size());
            }

            public EntityData.Damage next() {
                return throwDamage.get(nextIndex++);
            }
        };
    }

    public Iterator<EntityData.Status> statusInflictedIterator() {
        return new Iterator<EntityData.Status>() {
            int nextIndex = 0;

            public boolean hasNext() {
                return (nextIndex < statusInflicted.size());
            }

            public EntityData.Status next() {
                return statusInflicted.get(nextIndex++);
            }
        };
    }

}
