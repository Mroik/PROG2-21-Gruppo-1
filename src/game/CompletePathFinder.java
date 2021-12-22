package game;

import base_classes.movement.BasePathFinder;
import exceptions.InvalidCoordinatesException;
import exceptions.NoPathException;

import java.util.ArrayList;

/**
 * A pathfinder that looks for a path in
 */
public class CompletePathFinder extends BasePathFinder {
    private ArrayList<MapPosition> path;
    private ArrayList<MapPosition> visited;

    /**
     * @param map The map associated to this pathfinder
     * @param x Starting x coordinate
     * @param y Starting y coordinate
     */
    public CompletePathFinder(GameMap map, int x, int y) throws InvalidCoordinatesException {
        super(map, x, y);
        this.path = new ArrayList();
        this.visited = new ArrayList();
    }

    private boolean getFullPathR(MapPosition current, MapPosition end) {
        this.visited.add(current);
        this.path.add(current);
        if(current.equals(end))
            return true;
        for(MapPosition x:current.getWalkable(this.map)) {
            if(this.visited.contains(x))
                continue;
            if(this.getFullPathR(x, end))
                return true;
        }
        this.path.remove(this.path.size()-1);
        return false;
    }

    /**
     * Returns the full path that would be used in this instance to get to end.
     * This method is kept public in case the hypothetical path to take needs to be known.
     *
     * @param end The position to arrive to
     * @return The path to take to get to end
     */
    public MapPosition[] getFullPath(MapPosition end) throws NoPathException {
        MapPosition[] result;

        if(!getFullPathR(this.position, end))
            throw new NoPathException();

        result = (MapPosition[])this.path.toArray();
        this.path = new ArrayList();
        this.visited = new ArrayList();
        return result;
    }

    public MapPosition nextMove(MapPosition target) throws NoPathException {
        return getFullPath(target)[0];
    }
}
