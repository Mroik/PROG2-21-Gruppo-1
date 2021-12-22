package game;

import base_classes.movement.BasePathFinder;
import exceptions.InvalidCoordinatesException;
import exceptions.NoPathException;

import java.util.ArrayList;

public class LimitedPathFinder extends BasePathFinder {
    private int maxLength;
    private ArrayList<MapPosition> path;

    /**
     * @param map The map associated to this pathfinder
     * @param x The starting x coordinate
     * @param y The starting y coordinate
     * @param maxLength The max amount of cells the algorithm can look ahead
     */
    public LimitedPathFinder(GameMap map, int x, int y, int maxLength) throws InvalidCoordinatesException {
        super(map, x, y);
        this.maxLength = maxLength;
        this.path = new ArrayList();
    }

    private boolean findPathR(MapPosition current, MapPosition end, int depth) {
        if(depth > this.maxLength)
            return false;

        this.path.add(current);
        if(current.equals(end))
            return true;
        for(MapPosition x:current.getWalkable(this.map)) {
            if(this.path.contains(x))
                continue;
            if(this.findPathR(x, end, depth))
                return true;
        }
        this.path.remove(this.path.size()-1);
        return false;
    }

    /**
     * @param end The cell to get to
     * @return The path the algorithm is going to take
     * @throws NoPathException
     */
    public MapPosition[] findPath(MapPosition end) throws NoPathException {
        MapPosition[] result;

        if(this.findPathR(this.position, end, 1))
            throw new NoPathException();

        result = (MapPosition[])this.path.toArray();
        this.path = new ArrayList();
        return result;
    }

    public MapPosition nextMove(MapPosition target) throws NoPathException {
        return findPath(target)[0];
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }
}
