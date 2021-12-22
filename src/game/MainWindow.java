package render;
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

    /**
     * The text matrix made of String but every string MUST BE
     * of length 1 like a char
     */
    private List<List<Pixel>> matrix;

    /**
     * The number of lines of the text matrix
     */
    private int rows;

    /**
     * The length of every line excluding the new-lines
     */
    private int cols;

    /**
     * The font size to be used.
     * This should not be modified via, the danger is to
     * destroy the text matrix
     */
    private int fontSize = 18;

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
     * done to the matrix the timestamp changes
     */
    private Date changeTime;

    /**
     * Creates a blank uneditable window full of white spaces.
     * @param color the default color of the text matrix
     * @param rows the number of lines of the text matrix
     * @param cols the length of the text matrix except the new-lines
     */
    public MainWindow(Color color, int rows, int cols) {
        this.setEditable(false);

        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        this.setForeground(color);
        this.defaultAttrSet = this.getLogicalStyle().copyAttributes();

        this.rows = rows;
        this.cols = cols;

        this.initBlankWindow();
        this.initRenderLoop();
    }

    private void initBlankWindow() {
        String text = "";
        for (int j = 0; j < this.cols; j++) {
            text += " ";
        }
        for (int i = 1; i < this.rows; i++) {
            text += "\n";
            for (int j = 0; j < this.cols; j++) {
                text += " ";
            }
        }

        this.setText(text);
    }

    // W IL MULTITHREADING

    private class RenderLoop implements Runnable {
        private MainWindow mw;

        private int fps;

        public RenderLoop(MainWindow mw, int fps) {
            this.mw = mw;
            this.fps = fps;
        }

        public void run() {
            while(true) {
                try { TimeUnit.MILLISECONDS.sleep(1000 / fps); } catch (Exception e) {}
                mw.renderWindow();
            }
        }
    }

    // W IL MULTITHREADING

    private void initRenderLoop() {
        this.changeTime = new Date(System.currentTimeMillis());
        this.updateTime = new Date(System.currentTimeMillis());

        RenderLoop rl = new RenderLoop(this, 30);

        renderer = new Thread(rl, "Render Loop");
        renderer.start();
    }

    public AttributeSet getDefaultAttrSet() {
        return defaultAttrSet;
    }

    public AttributeSet createAttrSet(AttributeSet attr, Object name, Object value) {
        return sc.addAttribute(attr, name, value);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<List<Pixel>> getMatrix() {
        return matrix;
    }

    private void renderWindow() {
        if (!changeTime.after(updateTime)) {
            return;
        }

        try {
            updateTime = new Date(changeTime.getTime());

            final MainWindow mwCopy = this;
            FastRenderer fr = new FastRenderer(mwCopy);

            try { TimeUnit.NANOSECONDS.sleep(1); } catch (Exception e) {}

            this.setDocument(fr);
        } catch (BadLocationException e) {
            System.out.println("EXCEPTION MESSAGE" + e);
        }
    }

    public void createWindow() {
        matrix = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            matrix.add(new ArrayList<>(this.cols));
            for (int j = 0; j < this.cols; j++) {
                matrix.get(i).add(new Pixel('#', this.defaultAttrSet));
            }
        }

        changeTime = new Date(System.currentTimeMillis());
    }

    public void updatePixel(int x, int y, char c, Color color) {
        matrix.get(y).get(x).c = c;
        matrix.get(y).get(x).attr = this.createAttrSet(this.defaultAttrSet, StyleConstants.Foreground, color);

        changeTime = new Date(System.currentTimeMillis());
    }
}
