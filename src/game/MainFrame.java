package game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import base_classes.ColorPalette;

public class MainFrame extends JFrame implements KeyListener {

    private MainWindow mw;

    private GameEngine ge;
    
    public MainFrame(String title, int rows, int cols) {

        super(title);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        getContentPane().setBackground(ColorPalette.FRAME_COLOR);

        setSize((int)screenSize.getWidth(), (int)screenSize.getHeight() - 50);
        setVisible(true);

        Dimension windowSize = getSize();

        setMinimumSize(windowSize);

        mw = new MainWindow(rows, cols);
        mw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane sp = new JScrollPane(mw);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        sp.setBackground(ColorPalette.BACKGROUND_COLOR);

        setVisible(false);
        add(sp, new GridBagConstraints());
        setVisible(true);
    }

    public MainWindow getMainWindow() {
        return mw;
    }

    public void setGameEngine(GameEngine ge) {
        this.ge = ge;
    }

    public void startGameLoop() {
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        ge.GameLoop(e);
    }
    
}
