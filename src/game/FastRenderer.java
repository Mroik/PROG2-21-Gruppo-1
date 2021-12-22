package render;
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
    public FastRenderer(MainWindow mw) throws BadLocationException {
        super();
        l = new ArrayList<>();

        // Retreive the text and style matrix
        List<List<Pixel>> matrix = mw.getMatrix();

        // Starts the list creation with the default style
        AttributeSet currentAttr = mw.getDefaultAttrSet();

        // For every row ...
        for (int i = 0; i < matrix.size(); i++) {
            List<Pixel> row = matrix.get(i);
            // For every char in the row ...
            for (int j = 0; j < row.size(); j++) {
                // Updates the current style
                currentAttr = row.get(j).attr;

                // Creates a sequence of characters that uses the same
                // style to make the writing more efficient. This sequence
                // starts with "row.get(j) character"
                String concatRow = String.valueOf(row.get(j).c);

                // Cicles throw the row until the line its finished or the next
                // character has not the same style
                while(j+1 < row.size() && matrix.get(i).get(j+1).attr == currentAttr) {
                    j++;
                    concatRow += String.valueOf(row.get(j).c);
                }

                // Appends the sequence with the common style in the list of ElementSpec
                l.add(new ElementSpec(currentAttr, ElementSpec.ContentType, concatRow.toCharArray(), 0, concatRow.length()));
            }

            // Appends a line-feed to the list
            this.appendLineFeed(currentAttr);
        }

        l.remove(l.size() - 1);
        this.createDocument();
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
     * Writes into the document the list of ElementSpec
     * @throws BadLocationException
     */
    private void createDocument() throws BadLocationException {
        ElementSpec[] inserts = new ElementSpec[l.size()];
        l.toArray(inserts);

        super.insert(0, inserts);
    }
}
