package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.CoordinatePixel;

public class Map implements Iterable<CoordinatePixel> {
    
    private List<Room> rooms;

    private List<Tunnel> tunnels;

    public Map() {
        rooms = new ArrayList<>();
        tunnels = new ArrayList<>();

        populateRooms();
        populateTunnels();
    }

    private void populateRooms() {
        rooms.add(new Room(10, 10, 50, 20));
        rooms.add(new Room(100, 40, 20, 15));
    }

    private void populateTunnels() {
        Tunnel tunnel = new Tunnel(140, 10);
        tunnel.addHallway(true, 40);
        tunnel.addHallway(false, 15);
        tunnel.addHallway(true, 30);
        tunnel.addHallway(false, -15);
        tunnels.add(tunnel);
    }

    @Override
    public Iterator<CoordinatePixel> iterator() {
        
        return new Iterator<CoordinatePixel>() {

            private final Iterator<Room> roomIterator = rooms.iterator();

            private final Iterator<Tunnel> tunnelIterator = tunnels.iterator();

            private Iterator<CoordinatePixel> thisRoomIterator;

            private Iterator<CoordinatePixel> thisTunnelIterator;

            private boolean doingRooms;

            private boolean initialized = false;

            private void initialize() {
                initialized = true;

                if (roomIterator.hasNext()) {
                    thisRoomIterator = roomIterator.next().iterator();
                    doingRooms = true;
                }
                
                if (tunnelIterator.hasNext())
                    thisTunnelIterator = tunnelIterator.next().iterator();
            }

            @Override
            public boolean hasNext() {
                if (!initialized) {
                    initialize();
                }

                if (!thisRoomIterator.hasNext() && !roomIterator.hasNext() && !thisTunnelIterator.hasNext() && !tunnelIterator.hasNext())
                    return false;

                return true;
            }

            @Override
            public CoordinatePixel next() {
                if (!initialized) {
                    initialize();
                }

                CoordinatePixel result = null;

                if (doingRooms) {
                    if (thisRoomIterator.hasNext()) {
                        result = thisRoomIterator.next();
                    } else {
                        if (roomIterator.hasNext()) {
                            thisRoomIterator = roomIterator.next().iterator();

                            result = this.next();
                        } else {
                            doingRooms = false;

                            result = this.next();
                        }
                    }
                } else {
                    if (thisTunnelIterator.hasNext()) {
                        result = thisTunnelIterator.next();
                    } else {
                        if (tunnelIterator.hasNext()) {
                            thisTunnelIterator = tunnelIterator.next().iterator();

                            result = this.next();
                        }
                    }
                }

                return result;
            }
            
        };
    }
}
