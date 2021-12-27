package game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class MainWindow extends JTextPane {

    /**
     * Its used to create new Styles in the form of AttributeSets
     */
    private static final StyleContext sc = StyleContext.getDefaultStyleContext();

    /**
     * The default style to be used
     */
    private AttributeSet defaultAttrSet;

    private Color defaultColor;

    /**
     * The text base made of String but every string MUST BE
     * of length 1 like a char
     */
    private List<List<Pixel>> base;

    private Levels levels;

    /**
     * The number of lines of the text base
     */
    private int rows;

    /**
     * The length of every line excluding the new-lines
     */
    private int cols;

    /**
     * The font size to be used.
     * This should not be modified via, the danger is to
     * destroy the text base
     */
    private int fontSize = 18;

    private RenderLoop rl;

    /**
     * The thread reponsible for the window rendering loop
     */
    private Thread renderer;

    /**
     * Tthe timestamp of the render event. Every time the render
     * thread activates updates the timestamp. If the changeTime
     * is past the updateTime the renderer knows that something has
     * changed and perform the render, otherwise saves resources and
     * does nothing
     */
    private Date updateTime;

    /**
     * The timestamp of the change event. Every time a change is
     * done to the base the timestamp changes
     */
    private Date changeTime;

    /**
     * Creates a blank uneditable window full of white spaces.
     * @param color the default color of the text base
     * @param rows the number of lines of the text base
     * @param cols the length of the text base except the new-lines
     */
    public MainWindow(final Color color, int rows, int cols) {
        this.setEditable(false);

        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        this.setForeground(color);
        this.defaultColor = color;
        this.defaultAttrSet = this.getLogicalStyle().copyAttributes();
        this.defaultColor = color;

        this.rows = rows;
        this.cols = cols;

        base = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            base.add(new ArrayList<>(this.cols));

            for (int j = 0; j < this.cols; j++) {
                base.get(i).add(new Pixel(' ', color));
            }
        }

        levels = new Levels(3, rows, cols);

        this.changeTime = new Date(System.currentTimeMillis());
        this.initBlankWindow();
        this.updateTime = new Date(System.currentTimeMillis());

        initRenderLoop(30);
    }

    private void initBlankWindow() {
        String text = "";
        for (int j = 0; j < cols; j++) {
            text += " ";
        }
        for (int i = 1; i < rows; i++) {
            text += "\n";
            for (int j = 0; j < cols; j++) {
                text += " ";
            }
        }

        setText(text);

        base = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            base.add(new ArrayList<>(this.cols));
            for (int j = 0; j < this.cols; j++) {
                base.get(i).add(new Pixel(' ', this.defaultColor));
            }
        }

        levels = new Levels(3, rows, cols);
    }

    // W IL MULTITHREADING

    public class RenderLoop implements Runnable {
        private MainWindow mw;

        private int fps;

        private boolean running = false;
        private boolean stop = false;

        public RenderLoop(MainWindow mw, int fps) {
            this.mw = mw;
            this.fps = fps;
        }

        public void run() {
            if (running) {
                return;
            }

            while(!stop) {
                try { TimeUnit.MILLISECONDS.sleep(1000 / fps); } catch (Exception e) {}
                mw.renderWindow();
            }
        }

        public void stop() {
            stop = true;
        }
    }

    // W IL MULTITHREADING

    public void initRenderLoop(int fps) {
        if (rl != null) {
            return;
        }
        rl = new RenderLoop(this, fps);

        renderer = new Thread(rl, "Render Loop");
        renderer.start();
    }

    public void stopRenderLoop() {
        if (rl == null) {
            return;
        }
        
        rl.stop();
        rl = null;
    }

    public AttributeSet getDefaultAttrSet() {
        return defaultAttrSet;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public AttributeSet createAttrSet(AttributeSet attr, Object name, Object value) {
        return sc.addAttribute(attr, name, value);
    }

    public AttributeSet createColor(Color color) {
        return sc.addAttribute(defaultAttrSet, StyleConstants.Foreground, color);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<List<Pixel>> getbase() {
        return base;
    }

    public void renderWindow() {
        if (!changeTime.after(updateTime)) {
            return;
        }

        try {
            updateTime = new Date(changeTime.getTime());

            FastRenderer fr = new FastRenderer(this, this.base, this.levels);

            try { TimeUnit.NANOSECONDS.sleep(1); } catch (Exception e) {}

            this.setDocument(fr);
        } catch (BadLocationException e) {
            System.out.println("EXCEPTION MESSAGE" + e);
        }
    }

    public void updateBase(int x, int y, char c, Color color) {
        base.get(y).get(x).c = c;
        if (color != null)
            base.get(y).get(x).color = color;

        changeTime = new Date(System.currentTimeMillis());
    }

    public void updateLevel(int level, int x, int y, char c, Color color) {
        levels.addPixelLevel(level, x, y, c, color);

        changeTime = new Date(System.currentTimeMillis());
    }
}
