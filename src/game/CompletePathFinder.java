package game;

import base_classes.movement.BasePathFinder;
import base_classes.movement.NoPathException;

import java.util.ArrayList;

public class CompletePathFinder extends BasePathFinder {
    private MapPosition current;
    private ArrayList<MapPosition> path;

    public CompletePathFinder(GameMap map, int x, int y) {
        super(map, x, y);
        this.current = null;
        this.path = new ArrayList();
    }

    private void getFullPathR(MapPosition end) throws NoPathException {
        MapPosition nextPosition;

        if(this.current.equals(end))
            return;
        nextPosition = super.nextMove(end);
        this.path.add(nextPosition);
        this.current = nextPosition;
        this.getFullPathR(end);
    }

    /**
     * Returns the full path that would be used in this instance to get to {@code end}.
     * This method is kept public in case the hypothetical path to take needs to be known.
     *
     * @param end
     * @return
     */
    public MapPosition[] getFullPath(MapPosition end) throws NoPathException {
        MapPosition[] result;

        this.current = this.position;
        try {
            getFullPathR(this.current);
        } catch (NoPathException e) {
            this.current = null;
            throw e;
        }

        this.current = null;
        result = (MapPosition[])this.path.toArray();
        this.path = new ArrayList();
        return result;
    }

    public MapPosition nextMove(MapPosition target) throws NoPathException {
        return getFullPath(target)[0];
    }
}
