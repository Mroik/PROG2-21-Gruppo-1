package base_classes.movement;

import game.GameMap;
import game.MapPosition;

import static java.lang.Math.sqrt;

public abstract class BasePathFinder {
    protected MapPosition previousPosition;
    protected GameMap map;
    protected MapPosition position;

    public BasePathFinder(GameMap map, int x, int y) {
        this.map = map;
        this.position = new MapPosition(x, y);
        this.previousPosition = null;
    }

    protected float distance(MapPosition start, MapPosition target) {
        float x, y;
        x = start.getX() - target.getX();
        x *= x;
        y = start.getY() - target.getY();
        y *= y;
        return (float)sqrt(x+y);
    }

    public MapPosition nextMove(MapPosition target) throws NoPathException {
        float min;
        int choice;
        float temp_distance;
        MapPosition[] walkable;

        choice = 0;
        walkable = this.position.getWalkable(this.map);
        if(walkable.length == 0)
            throw new NoPathException();
        min = distance(walkable[0], target);

        for(int x = 1; x < walkable.length; x++) {
            if(walkable[x].equals(this.previousPosition))
                continue;
            temp_distance = distance(walkable[x], target);
            if(temp_distance < min) {
                min = temp_distance;
                choice = x;
            }
        }

        if(walkable[choice].equals(this.previousPosition))
            throw new NoPathException();
        return walkable[choice];
    }
}
