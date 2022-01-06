package base_classes;

/**
 * OVERVIEW: Instances of subclasses of this class represent entities of the game, such as enemies, the player,
 * collectible objects, etc...
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected String name;
    protected char render;

    // CONSTRUCTORS

    /**
     * @param x coordinate x
     * @param y coordinate y
     */
    protected Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // METHODS

    /**
     * 
     * @return this.x
     */
    public int getX() {
        return this.x;
    }

    /**
     * 
     * @return this.y
     */
    public int getY() {
        return this.y;
    }

    /**
     * 
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return this.render
     */
    public char getRender() {
        return this.render;
    }

    /**
     * Moves this to its new location
     * @param x coordinate x
     * @param y coordinate y
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
