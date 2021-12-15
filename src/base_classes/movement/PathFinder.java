package base_classes.movement;

import game.MapPosition;

public interface PathFinder {
    public MapPosition nextMove(MapPosition target);
}
