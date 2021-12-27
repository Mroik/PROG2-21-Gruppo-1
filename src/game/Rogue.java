package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

// FOR TEST AND DEBUG SECTION
import java.util.concurrent.TimeUnit;

public class Rogue {

    /**
     * Its the JTextPane window responsible for the game display
     */
    private MainWindow mw;
    
    /**
     * Creates the Game Window and every loop
     * @param title the title of the window
     * @param rows the number of rows the text window has
     * @param cols the length of each row of the text window
     */
    public Rogue(String title, int rows, int cols) {
        // Gets the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Creates the Game window with the given title
        JFrame frame = new JFrame(title);

        // Window Settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // (Tested only in MacOS)
        // By creating the window without any content inside and by setting
        // the window size to the maximum size possible that is the screen size
        // the OS automatically shrink the window to the maximum possible without
        // overlapping other elements like the menu bar and the dock.
        // After this change we retrieve the window size and sets this to the
        // minimum window size so its still possible to make it fullscreen without
        // destroying the text matrix
        frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight() - 50);
        frame.setVisible(true);

        // Gets the window size
        Dimension windowSize = frame.getSize();

        // Sets the minimum window size to the current window size
        frame.setMinimumSize(windowSize);

        // Creates the game window
        mw = new MainWindow(Color.black, rows, cols);
        mw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Creates the JTextPane container and makes it unscrollable
        JScrollPane sp = new JScrollPane(mw);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);

        // Hides the window, adds the container created above and
        // redraws the window
        frame.setVisible(false);
        frame.add(sp, new GridBagConstraints());
        frame.setVisible(true);
    }

    public void updateBase(int x, int y, char c, Color color) {
        mw.updateBase(x, y, c, color);
    }

    public void updateLevel(int level, int x, int y, char c, Color color) {
        mw.updateLevel(level, x, y, c, color);
    }

    public void initRenderLoop(int fps) {
        mw.initRenderLoop(fps);
    }

    public void stopRenderLoop() {
        mw.stopRenderLoop();
    }

    public void renderWindow() {
        mw.renderWindow();
    }

    public int getRows() {
        return mw.getRows();
    }

    public int getCols() {
        return mw.getCols();
    }

    //
    // TEST AND DEBUG
    //
    public static void main(String[] args) {
        Rogue game = new Rogue("Rogue", 30, 120);

        createWindow(game);
        test(game);
    }

    public static void test(Rogue game) {
        try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) {}

        for (int i = 0; i < 10000000; i++) {
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

            game.updateLevel(0, x, y, '@', color);
        }
    }

    public static void createWindow(Rogue game) {
        for (int y = 0; y < game.getRows(); y++) {
            for (int x = 0; x < game.getCols(); x++) {
                game.updateBase(x, y, '#', null);
            }
        }
    }
}
