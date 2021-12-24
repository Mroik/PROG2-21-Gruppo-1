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

    /**
     * The default color to be used
     */
    private Color defaultColor;

    /**
     * 
     */
    private List<List<Pixel>> base;

    /**
     * 
     */
    private Levels levels;

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
    public MainWindow(final Color color, int rows, int cols) {
        this.setEditable(false);

        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        this.setForeground(color);
        this.defaultColor = color;
        this.defaultAttrSet = this.getLogicalStyle().copyAttributes();

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

    public AttributeSet getDefaultAttrSet() {
        return defaultAttrSet;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public AttributeSet createAttrSet(AttributeSet attr, Object name, Object value) {
        return sc.addAttribute(attr, name, value);
    }

    public AttributeSet createColorAttrSet(Color color) {
        return sc.addAttribute(
            this.defaultAttrSet,
            StyleConstants.Foreground,
            color
        );
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public List<List<Pixel>> getBase() {
        return base;
    }

    public List<List<Pixel>> getCopyOfBase() {
        List<List<Pixel>> copy = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            copy.add(new ArrayList<>(this.cols));

            for (int j = 0; j < this.cols; j++) {
                copy.get(i).add(new Pixel(this.base.get(i).get(j).c, this.base.get(i).get(j).color));
            }
        }

        return copy;
    }

    public Levels getLevels() {
        return levels;
    }

    public void renderWindow() {
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
        for (List<Pixel> row : base) {
            for (Pixel p : row) {
                p.c = '#';
            }
        }

        changeTime = new Date(System.currentTimeMillis());
        this.renderWindow();
    }

    public void updatePixel(int nLevel, int x, int y, char c, Color color) {
        levels.set(nLevel, x, y, c, color);

        changeTime = new Date(System.currentTimeMillis());
    }
}
