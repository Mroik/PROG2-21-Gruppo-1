package map;

public class Hallway {

    private int x;

    private int y;

    private int length;

    private boolean isHorizontal;

    public Hallway(int x, int y, boolean isHorizontal, int length) {
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;
        this.length = length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int length() {
        return length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

}
