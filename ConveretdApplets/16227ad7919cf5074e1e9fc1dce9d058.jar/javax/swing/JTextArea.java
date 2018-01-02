// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.text.Element;
import javax.accessibility.AccessibleContext;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class JTextArea extends JTextComponent
{
    private static final String uiClassID = "TextAreaUI";
    private int rows;
    private int columns;
    private int columnWidth;
    private int rowHeight;
    private boolean wrap;
    private boolean word;
    
    public JTextArea() {
        this(null, null, 0, 0);
    }
    
    public JTextArea(final int n, final int n2) {
        this(null, null, n, n2);
    }
    
    public JTextArea(final String s) {
        this(null, s, 0, 0);
    }
    
    public JTextArea(final String s, final int n, final int n2) {
        this(null, s, n, n2);
    }
    
    public JTextArea(final Document document) {
        this(document, null, 0, 0);
    }
    
    public JTextArea(Document defaultModel, final String text, final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        if (defaultModel == null) {
            defaultModel = this.createDefaultModel();
        }
        this.setDocument(defaultModel);
        if (text != null) {
            this.setText(text);
        }
    }
    
    public void append(final String s) {
        final Document document = this.getDocument();
        if (document != null) {
            try {
                document.insertString(document.getLength(), s, null);
            }
            catch (BadLocationException ex) {}
        }
    }
    
    protected Document createDefaultModel() {
        return new PlainDocument();
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJTextArea();
        }
        return super.accessibleContext;
    }
    
    protected int getColumnWidth() {
        if (this.columnWidth == 0) {
            this.columnWidth = this.getFontMetrics(this.getFont()).charWidth('m');
        }
        return this.columnWidth;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int getLineCount() {
        final Element defaultRootElement = this.getDocument().getDefaultRootElement();
        final int elementCount = defaultRootElement.getElementCount();
        final Element element = defaultRootElement.getElement(elementCount - 1);
        if (element.getEndOffset() - element.getStartOffset() > 1) {
            return elementCount;
        }
        return elementCount - 1;
    }
    
    public int getLineEndOffset(final int n) throws BadLocationException {
        final Element defaultRootElement = this.getDocument().getDefaultRootElement();
        if (n < 0) {
            throw new BadLocationException("Negative line", -1);
        }
        if (n >= defaultRootElement.getElementCount()) {
            throw new BadLocationException("No such line", this.getDocument().getLength() + 1);
        }
        return defaultRootElement.getElement(n).getEndOffset();
    }
    
    public int getLineOfOffset(final int n) throws BadLocationException {
        final Document document = this.getDocument();
        if (n < 0) {
            throw new BadLocationException("Can't translate offset to line", -1);
        }
        if (n > document.getLength()) {
            throw new BadLocationException("Can't translate offset to line", document.getLength() + 1);
        }
        return this.getDocument().getDefaultRootElement().getElementIndex(n);
    }
    
    public int getLineStartOffset(final int n) throws BadLocationException {
        final Element defaultRootElement = this.getDocument().getDefaultRootElement();
        if (n < 0) {
            throw new BadLocationException("Negative line", -1);
        }
        if (n >= defaultRootElement.getElementCount()) {
            throw new BadLocationException("No such line", this.getDocument().getLength() + 1);
        }
        return defaultRootElement.getElement(n).getStartOffset();
    }
    
    public boolean getLineWrap() {
        return this.wrap;
    }
    
    public Dimension getPreferredScrollableViewportSize() {
        final Dimension preferredScrollableViewportSize = super.getPreferredScrollableViewportSize();
        final Dimension dimension = (preferredScrollableViewportSize == null) ? new Dimension(400, 400) : preferredScrollableViewportSize;
        dimension.width = ((this.columns == 0) ? dimension.width : (this.columns * this.getColumnWidth()));
        dimension.height = ((this.rows == 0) ? dimension.height : (this.rows * this.getRowHeight()));
        return dimension;
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        final Dimension dimension = (preferredSize == null) ? new Dimension(400, 400) : preferredSize;
        if (this.columns != 0) {
            dimension.width = Math.max(dimension.width, this.columns * this.getColumnWidth());
        }
        if (this.rows != 0) {
            dimension.height = Math.max(dimension.height, this.rows * this.getRowHeight());
        }
        return dimension;
    }
    
    protected int getRowHeight() {
        if (this.rowHeight == 0) {
            this.rowHeight = this.getFontMetrics(this.getFont()).getHeight();
        }
        return this.rowHeight;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return this.wrap || super.getScrollableTracksViewportWidth();
    }
    
    public int getScrollableUnitIncrement(final Rectangle rectangle, final int n, final int n2) {
        switch (n) {
            case 1: {
                return this.getRowHeight();
            }
            case 0: {
                return this.getColumnWidth();
            }
            default: {
                throw new IllegalArgumentException("Invalid orientation: " + n);
            }
        }
    }
    
    public int getTabSize() {
        int intValue = 8;
        final Document document = this.getDocument();
        if (document != null) {
            final Integer n = (Integer)document.getProperty("tabSize");
            if (n != null) {
                intValue = n;
            }
        }
        return intValue;
    }
    
    public String getUIClassID() {
        return "TextAreaUI";
    }
    
    public boolean getWrapStyleWord() {
        return this.word;
    }
    
    public void insert(final String s, final int n) {
        final Document document = this.getDocument();
        if (document != null) {
            try {
                document.insertString(n, s, null);
            }
            catch (BadLocationException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
    }
    
    public boolean isManagingFocus() {
        return true;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",colums=" + this.columns + ",columWidth=" + this.columnWidth + ",rows=" + this.rows + ",rowHeight=" + this.rowHeight + ",word=" + (this.word ? "true" : "false") + ",wrap=" + (this.wrap ? "true" : "false");
    }
    
    protected void processComponentKeyEvent(final KeyEvent keyEvent) {
        super.processComponentKeyEvent(keyEvent);
        if (this.isManagingFocus() && (keyEvent.getKeyCode() == 9 || keyEvent.getKeyChar() == '\t')) {
            keyEvent.consume();
        }
    }
    
    public void replaceRange(final String s, final int n, final int n2) {
        if (n2 < n) {
            throw new IllegalArgumentException("end before start");
        }
        final Document document = this.getDocument();
        if (document != null) {
            try {
                document.remove(n, n2 - n);
                document.insertString(n, s, null);
            }
            catch (BadLocationException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
    }
    
    public void setColumns(final int columns) {
        final int columns2 = this.columns;
        if (columns < 0) {
            throw new IllegalArgumentException("columns less than zero.");
        }
        if (columns != columns2) {
            this.columns = columns;
            this.invalidate();
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.rowHeight = 0;
        this.columnWidth = 0;
    }
    
    public void setLineWrap(final boolean wrap) {
        this.firePropertyChange("lineWrap", this.wrap, this.wrap = wrap);
    }
    
    public void setRows(final int rows) {
        final int rows2 = this.rows;
        if (rows < 0) {
            throw new IllegalArgumentException("rows less than zero.");
        }
        if (rows != rows2) {
            this.rows = rows;
            this.invalidate();
        }
    }
    
    public void setTabSize(final int n) {
        final Document document = this.getDocument();
        if (document != null) {
            final int tabSize = this.getTabSize();
            document.putProperty("tabSize", new Integer(n));
            this.firePropertyChange("tabSize", tabSize, n);
        }
    }
    
    public void setWrapStyleWord(final boolean word) {
        this.firePropertyChange("wrapStyleWord", this.word, this.word = word);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("TextAreaUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJTextArea extends AccessibleJTextComponent
    {
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            accessibleStateSet.add(AccessibleState.MULTI_LINE);
            return accessibleStateSet;
        }
    }
}
