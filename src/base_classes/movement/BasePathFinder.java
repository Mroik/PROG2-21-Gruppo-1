package base_classes.movement;

import game.GameMap;
import game.MapPosition;

import static java.lang.Math.sqrt;

public abstract class BasePathFinder implements PathFinder {
    private GameMap map;
    private MapPosition position;

    public BasePathFinder(GameMap map, int x, int y) {
        this.map = map;
        this.position = new MapPosition(x, y);
    }

    private float distance(MapPosition start, MapPosition target) {
        float x, y;
        x = start.getX() - target.getX();
        x *= x;
        y = start.getY() - target.getY();
        y *= y;

        return (float)sqrt(x+y);
    }

    @Override
    public MapPosition nextMove(MapPosition target) {
        float min;
        int choice;
        float temp_distance;
        MapPosition temp_position;

        choice = 0;
        temp_position = this.position.getWalkable(this.map)[0];
        min = distance(temp_position, target);
        for(int x = 1; x < this.position.getWalkable(this.map).length; x++) {
            temp_distance = distance(this.position.getWalkable(this.map)[x], target);
            if(temp_distance < min) {
                min = temp_distance;
                choice = x;
            }
        }
        return this.position.getWalkable(this.map)[choice];
    }
}
