import java.util.concurrent.TimeUnit;

import render.Rogue;

import java.awt.Color;

public class Test {

    public static void main(String[] args) {
        Rogue game = new Rogue("Rogue", 30, 120);

        test(game);
    }

    /**
     * Demo for the grid functionality
     */
    public static void test(Rogue game) {
        try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {}

        for (int i = 0; i < 1000000; i++) {
            int x = i % game.getCols();
            int y = (i / game.getCols()) % game.getRows();

            Color color = Color.black;
            switch ((i / (game.getCols() / 16)) % 7) {
                case 1:
                    color = Color.blue;
                    break;
                case 2:
                    color = Color.red;
                    break;
                case 3:
                    color = Color.green;
                    break;
                case 4:
                    color = Color.orange;
                    break;
                case 5:
                    color = Color.pink;
                    break;
                case 6:
                    color = Color.yellow;
                    break;
            }

            try { TimeUnit.MILLISECONDS.sleep(1); } catch (Exception e) {}
            game.updatePixel(x, y, '@', color);
        }
    }

}