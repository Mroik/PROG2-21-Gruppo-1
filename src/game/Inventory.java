package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;
import exception.InvalidDimensionException;

/**
 *  - Objects in this class model inventories
 *  - Objects in this class are mutable
 */
public class Inventory {
    /**
     *  Record used to store all tipe of Collectible:
     *  - if Stackable, then stack >= 1
     *  - if not Stackable, then stack == 1
     */
    public record Stack(Collectible c, int stack) {
        public Stack(Collectible c, int stack) {
            this.c = c;
            this.stack = stack;
        }
    }

    
    /**
     *  List of all Collectible elements owned by the entity 
     */
    private final List<Stack> inventory;
    /**
     *  Max dimension of the inventory
     */
    private final int maxDimension;


    /**
     * Create a new inventory
     * @param dimension
     * @throws InvalidDimensionException if dimension is not positive
     */
    public Inventory(int dimension) throws IllegalArgumentException {
        if (dimension <= 0)
            throw new IllegalArgumentException("Dimension must be positive");
        
        inventory = new LinkedList<>();
        maxDimension = dimension;
    }

    
    /**
     * Adds 'c' to 'inventory' if numberOfElements() < 'maxDimension' and return 'true'
     * Otherwise, 'c' is not added to inventory and return 'false' 
     * @param c
     * @return 'true' if numberOfElements() < maxDimension, 'false' otherwise
     */
    public boolean insert(Collectible c) {
        Objects.requireNonNull(c);

        if (numberOfElements() == maxDimension)
            return false;
        
        String name = c.getName();

        for (int i = 0; i < inventory.size(); i++) {
            String cname = inventory.get(i).c.getName();
            if (name.compareTo(cname) < 0) {
                inventory.add(i, new Stack(c, 1));
                return true;
            }
            if (name.compareTo(cname) == 0) {
                inventory.set(i, new Stack(c, inventory.get(i).stack + 1));
                return true;
            }
        }

        inventory.add(new Stack(c, 1));
        
        return true;
    }

    /**
     * @return the number of Collectible elements in the inventory
     */
    private int numberOfElements() {
        int counter = 0;
        for (Stack s : inventory) {
            counter += s.stack;
        }
        return counter;
    }

    /**
     * @param name
     * @return the Collectible element that has name == 'name'
     * The method returns 'null' if there is no element with name == 'name'
     */
    public Stack remove(String name) {
        Objects.requireNonNull(name);

        for (int i = 0; i < inventory.size(); i++) {
            String cname = inventory.get(i).c.getName();
            if (name.equals(cname)) {
                Stack ret = new Stack(inventory.get(i).c, inventory.get(i).stack);
                inventory.remove(i);
                return ret;
            }
        }

        return null;
    }

    /**
     * 
     * @return
     */
    public void clear() {
        inventory.clear();
    }
    
    /**
     * @return an iterator over all the elements in the inventory
     */
    public Iterator<Stack> iterator() {
        return inventory.iterator();
    }

    // DEBUG AND TESTING
    //@Override
    //public String toString() {
    //    StringBuilder sb = new StringBuilder("[");
    //    for (int i = 0; i < inventory.size(); i++) {
    //        Stack current = inventory.get(i);
    //        sb.append(current.c.getName() + ": " + current.stack);
    //        if (i != inventory.size() - 1)
    //            sb.append(", ");
    //    }
    //    sb.append("]");
    //
    //    return sb.toString();
    //}

    // DEBUG AND TESTING
    //private boolean repOk() {
    //    boolean ok = true;
    //    for (Stack s : inventory) {
    //        ok = ok && s != null && s.stack >= 1 && s.c != null;
    //    }
    //    return inventory != null && numberOfElements() <= maxDimension && ok;
    //}
}
