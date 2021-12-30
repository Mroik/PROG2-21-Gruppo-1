/**
 * OVERVIEW: Instances of this class represent non-moving objects in the dungeon, such as pillars
 */
public abstract class StaticEntity extends Entity {
    
    // CONSTRUCTORS

    /**
     * Initialize this
     * @param x coordinate x
     * @param y coordinate y
     */
    public StaticEntity(int x, int y) {
        super(x, y);
    }
}
