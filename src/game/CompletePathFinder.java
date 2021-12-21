package game;

import base_classes.movement.BasePathFinder;
import base_classes.movement.NoPathException;

import java.util.ArrayList;

public class CompletePathFinder extends BasePathFinder {
    private ArrayList<MapPosition> path;
    private ArrayList<MapPosition> visited;

    public CompletePathFinder(GameMap map, int x, int y) {
        super(map, x, y);
        this.path = new ArrayList();
        this.visited = new ArrayList();
    }

    private boolean getFullPathR(MapPosition current, MapPosition end) {
        visited.add(current);
        if(current.equals(end))
            return true;
        for(MapPosition x:current.getWalkable(this.map)) {
            if(this.getFullPathR(x, end))
                return true;
        }
        visited.remove(visited.size()-1);
        return false;
    }

    /**
     * Returns the full path that would be used in this instance to get to {@code end}.
     * This method is kept public in case the hypothetical path to take needs to be known.
     *
     * @param end The position to arrive to
     * @return
     */
    public MapPosition[] getFullPath(MapPosition end) throws NoPathException {
        MapPosition[] result;

        if(!getFullPathR(this.position, end))
            throw new NoPathException();

        result = (MapPosition[])this.path.toArray();
        this.path = new ArrayList();
        return result;
    }

    public MapPosition nextMove(MapPosition target) throws NoPathException {
        return getFullPath(target)[0];
    }
}
