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

public class Rogue {

    /**
     * Its the JTextPane window responsible for the text matrix
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

        // Creates the JTextPane container
        JScrollPane sp = new JScrollPane(mw);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);

        // Hides the window, adds the container created above and
        // redraws the window
        frame.setVisible(false);
        frame.add(sp, new GridBagConstraints());
        frame.setVisible(true);

        // Optional function, this overrides the default text matrix (made of blank spaces)
        // with a grid full of '#'
        mw.createWindow();
    }

    public void updatePixel(int nLevel, int x, int y, char c, Color color) {
        mw.updatePixel(nLevel, x, y, c, color);
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
}
