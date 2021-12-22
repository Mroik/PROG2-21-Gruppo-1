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
        MapPosition choice;
        float temp_distance;
        MapPosition[] walkable;

        walkable = this.position.getWalkable(this.map);
        if(walkable.length == 0)
            throw new NoPathException();
        choice = walkable[0];
        min = distance(choice, target);

        for(MapPosition x:walkable) {
            if(x.equals(this.previousPosition))
                continue;
            temp_distance = distance(x, target);
            if(temp_distance < min) {
                min = temp_distance;
                choice = x;
            }
        }

        if(choice.equals(this.previousPosition))
            throw new NoPathException();
        return choice;
    }

    public MapPosition getPosition() {
        return this.position;
    }

    public void setPosition(int x, int y) {
        this.position = new MapPosition(x, y);
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
