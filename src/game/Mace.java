package game;

import base_classes.Weapon;
import base_classes.EntityData;

/**
 * OVERVIEW: basic low-damage weapon, the player starts with one of these
 */
public class Mace extends Weapon {
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    public Mace(int x, int y) {
        super(x, y);
        name = "Mace";
        isRangedWeapon = false;
        breakingChanceMelee = 0;
        breakingChanceThrow = 10;
        meleeDamage.add(new EntityData.Damage(EntityData.DamageType.BLUDGEONING,2,4));
        throwDamage.add(new EntityData.Damage(EntityData.DamageType.BLUDGEONING,1,3));
    }
}
