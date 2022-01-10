package base_classes;

/**
 * OVERVIEW: This class contains useful enums and records regarding damage and status
 */
public abstract class EntityData {
    
    public enum DamageType {
        SLASHING,
        BLUDGEONING,
        PIERCING,
        FORCE,
        FIRE,
        COLD,
        LIGHTNING,
        THUNDER,
        POISON,
        ACID,
        PSYCHIC,
        NECROTIC,
        RADIANT
    }

    public enum StatusType {
        POISONING,
        BLEEDING,
        CONFUSION,
        BLINDNESS,
        HALLUCINATION,
        BURNING,
        FROSTBITE,
        FREEZING,
        PARALYSIS,
        SLEEP,
        WEAKNESS,
        REGENERATION,
        FOCUS,
        SUPERVISION,
        AGILITY,
        SUPERSTRENGTH,
        LEVITATION,
        INVISIBILITY,
        SEEINVISIBLE,
        MONSTERDETECTION,
        MAGICDETECTION,
        FOODDETECTION
    }

    /**
     * Instances of this record represent damage inflicted by a weapon.
     * It is required numOfDices > 0 && diceType > 0
     */
    public record Damage(DamageType damageType, int numOfDices, int diceType) {
        public Damage {
            if(numOfDices <= 0 || diceType <= 0) throw new IllegalArgumentException("It is required numOfDices > 0 && diceType > 0");
        }
    }

    /**
     * Instances of this record represent a status an enemy/weapon/potion/... can inflict to a mob.
     * Chance represent the % of times the stated status is actually inflicted.
     * It is required 0 < chance <= 100
     */
    public record Status(StatusType statusType, int chance) {
        public Status {
            if(chance <= 0 || chance > 100) throw new IllegalArgumentException("It is required 0 < chance <= 100");
        }
    }

}
