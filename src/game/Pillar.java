package game;

import base_classes;

/**
 * OVERVIEW: Instances of this class represent pillars which the player may find inside some rooms of the dungeon
 */
public class Pillar extends StaticEntity {
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     */
    public Pillar(int x, int y) {
        super(x, y);
        render = '|';
    }
}
