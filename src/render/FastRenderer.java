package render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

import game.MainWindow;

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
     */
    public FastRenderer(MainWindow mw, List<List<Pixel>> matrix, Levels levels) {
        l = new ArrayList<>();

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
     * Writes into the document the list of ElementSpec
     */
    private void createDocument() {
        ElementSpec[] inserts = new ElementSpec[l.size()];
        l.toArray(inserts);

        try {
            super.insert(0, inserts);
        } catch (BadLocationException e) {
            throw new BadLocationRuntimeException(e.getMessage());
        }
    }
}
