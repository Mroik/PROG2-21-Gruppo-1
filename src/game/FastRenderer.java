package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

/**
 * Its the document that can be used my the MainWindow to
 * render faster a matrix of characters with their own
 * individual style
 */
public class FastRenderer extends DefaultStyledDocument {

    /**
     * This is the Object used for the document creation. This is
     * a combination of a string and a style
     */
    private List<ElementSpec> l;

    /**
     * Creates a document that can be used by the MainWindow as its
     * document. When this document its already used its advised to
     * not modify this document but create a new istance of this and
     * replace the window document, otherwise the rendering could be
     * very slow, both for little updates and entire rewrites
     * @param mw the MainWindow with its text and style matrix
     * @throws BadLocationException
     */
    public FastRenderer(MainWindow mw, List<List<Pixel>> matrix, Levels levels) throws BadLocationException {
        l = new ArrayList<>();

<<<<<<< HEAD
        //
        List<List<Pixel>> matrix = mw.getCopyOfBase();

        //
        Levels levels = mw.getLevels();

        // Starts the list creation with the default style
        Color currentColor = mw.getDefaultColor();
        AttributeSet currentAttrSet = mw.getDefaultAttrSet();

        for (int i = 0; i < levels.size(); i++) {
            List<PosixPixel> changedPixels = levels.getPixels(i);

            for (int j = 0; j < changedPixels.size(); j++) {
                PosixPixel p = changedPixels.get(j);
                matrix.get(p.getY()).get(p.getX()).c = p.getChar();
                matrix.get(p.getY()).get(p.getX()).color = p.getColor();
            }
        }

        // For every row ...
        for (int i = 0; i < matrix.size(); i++) {
            List<Pixel> row = matrix.get(i);
            // For every char in the row ...
            for (int j = 0; j < row.size(); j++) {
                // Updates the current style
                currentColor = row.get(j).color;

                // Creates a sequence of characters that uses the same
                // style to make the writing more efficient. This sequence
                // starts with "row.get(j) character"
                String concatRow = String.valueOf(row.get(j).c);

                // Cicles throw the row until the line its finished or the next
                // character has not the same style
                while(j+1 < row.size() && matrix.get(i).get(j+1).color == currentColor) {
                    j++;
                    concatRow += String.valueOf(row.get(j).c);
                }

                currentAttrSet = mw.createColorAttrSet(currentColor);
                // Appends the sequence with the common style in the list of ElementSpec
                l.add(new ElementSpec(
                    currentAttrSet,
                    ElementSpec.ContentType,
                    concatRow.toCharArray(),
                    0,
                    concatRow.length()
                ));
            }

            // Appends a line-feed to the list
            this.appendLineFeed(currentAttrSet);
=======
        Color currentColor = mw.getDefaultColor();
        AttributeSet currentAttrSet = mw.createColor(currentColor);
        String concatRow = "";

        for (int y = 0; y < matrix.size(); y++) {
            List<Pixel> row = matrix.get(y);
            
            for (int x = 0; x < row.size(); x++) {
                char c = row.get(x).c;
                Color color = row.get(x).color;

                Coordinate coord = new Coordinate(x, y);
                if (levels.containsKey(coord)) {
                    LevelPixel p = levels.get(coord);
                    c = p.c;
                    color = p.color;
                }

                if (color == currentColor) {
                    concatRow += String.valueOf(c);
                } else {
                    if (concatRow != "") {
                        l.add(new ElementSpec(
                            currentAttrSet,
                            ElementSpec.ContentType,
                            concatRow.toCharArray(),
                            0, concatRow.length())
                        );
                    }

                    concatRow = String.valueOf(c);
                    currentColor = color;
                    currentAttrSet = mw.createColor(currentColor);
                }
            }

            concatRow += "\n";
            l.add(new ElementSpec(
                currentAttrSet,
                ElementSpec.ContentType,
                concatRow.toCharArray(),
                0, concatRow.length())
            );

            appendEnd(currentAttrSet);
            appendStart();

            concatRow = "";
>>>>>>> render-old
        }

        l.remove(l.size() - 1);
        createDocument();
    }

    /**
     * This is called at the beginning of each row created except
     * the first one
     */
    private void appendStart() {
        l.add(new ElementSpec(null, ElementSpec.StartTagType));
    }

    /**
     * This is called after inserting a new-line. This is a requirement
     * of the ElementSpec object. For optimal use the attr style should
     * be the same style as the previous char
     * @param attr the style to give to the End ElementSpec
     */
    private void appendEnd(AttributeSet attr) {
        l.add(new ElementSpec(attr, ElementSpec.EndTagType));
    }

    /**
<<<<<<< HEAD
     * Appends a line-feed in the list of ElementSpec. For optimal
     * use the attr style should be the same style as the previous
     * char at the end of the line
     * @param attr the style to give to the new-line character
     */
    private void appendLineFeed(AttributeSet attr) {
        l.add(new ElementSpec(attr, ElementSpec.ContentType, "\n".toCharArray(), 0, 1));
        
        this.appendEnd(attr);
        this.appendStart();
    }

    /**
=======
>>>>>>> render-old
     * Writes into the document the list of ElementSpec
     * @throws BadLocationException
     */
    private void createDocument() throws BadLocationException {
        ElementSpec[] inserts = new ElementSpec[l.size()];
        l.toArray(inserts);

        super.insert(0, inserts);
    }
}
