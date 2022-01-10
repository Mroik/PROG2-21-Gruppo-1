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

import map.Map;

public class Rogue {

    /**
     * Its the JTextPane window responsible for the game display
     */
    private MainWindow mw;
    
    /**
     * Creates the Game Window and every loop
     * @param title the title of the window
     * @param rows the number of rows of text
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

        this.map = mw.map;
    }

    /**
     * Modifies the base matrix with a character in the given position
     * of the matrix. For reference the matrix has the top-left corner
     * in ( 0 , 0 ) and the bottom-right in ( getCols() - 1 , getRows() - 1 ).
     * The color can be null: in this case the old color will be used as
     * the new one.
     * @param x
     * @param y
     * @param c
     * @param color
     */
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

    public Map map;

    //
    // TEST AND DEBUG
    //
    public static void main(String[] args) {
        Rogue game = new Rogue("Rogue", 70, 240);

        test(game);
    }

    public static void test(Rogue game) {
        Map map = game.map;

        for (CoordinatePixel p : map) {
            game.updateBase(p.getX(), p.getY(), p.c, p.color);
        }
    }
}
