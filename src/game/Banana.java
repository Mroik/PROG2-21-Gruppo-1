public class Banana extends Food {
    
    // CONSTRUCTOR

    /**
     * Initialize this
     * @param x Coordinate x
     * @param y Coordinate y
     * @param quantity Number of items stacked together
     */
    public Banana(int x, int y, int quantity) {
        super(x, y, quantity);
        foodValue = 5;
        name = "Banana";
    }
}
