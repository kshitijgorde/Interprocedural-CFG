// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import java.awt.Color;
import java.awt.FontMetrics;

public class WrappedPlainView extends BoxView implements TabExpander
{
    FontMetrics metrics;
    Segment lineBuffer;
    boolean widthChanging;
    int tabBase;
    int tabSize;
    boolean wordWrap;
    int sel0;
    int sel1;
    Color unselected;
    Color selected;
    
    public WrappedPlainView(final Element element) {
        this(element, false);
    }
    
    public WrappedPlainView(final Element element, final boolean wordWrap) {
        super(element, 1);
        this.lineBuffer = new Segment();
        this.wordWrap = wordWrap;
    }
    
    protected int calculateBreakPosition(final int n, final int n2) {
        this.loadText(n, n2);
        int n3;
        if (this.wordWrap) {
            n3 = n + Utilities.getBreakLocation(this.lineBuffer, this.metrics, this.tabBase, this.tabBase + this.getWidth(), this, n);
        }
        else {
            n3 = n + Utilities.getTabbedTextOffset(this.lineBuffer, this.metrics, this.tabBase, this.tabBase + this.getWidth(), this, n);
        }
        return n3;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.updateChildren(documentEvent, shape);
    }
    
    protected void drawLine(final int n, final int n2, final Graphics graphics, int drawText, final int n3) {
        final Element element = this.getElement();
        final Element element2 = element.getElement(element.getElementIndex(n));
        try {
            if (element2.isLeaf()) {
                this.drawText(element2, n, n2, graphics, drawText, n3);
            }
            else {
                for (int i = element2.getElementIndex(n); i <= element2.getElementIndex(n2); ++i) {
                    final Element element3 = element2.getElement(i);
                    drawText = this.drawText(element3, Math.max(element3.getStartOffset(), n), Math.min(element3.getEndOffset(), n2), graphics, drawText, n3);
                }
            }
        }
        catch (BadLocationException ex) {
            throw new StateInvariantError("Can't render: " + n + "," + n2);
        }
    }
    
    protected int drawSelectedText(final Graphics graphics, final int n, final int n2, final int n3, final int n4) throws BadLocationException {
        graphics.setColor(this.selected);
        this.getDocument().getText(n3, n4 - n3, this.lineBuffer);
        return Utilities.drawTabbedText(this.lineBuffer, n, n2, graphics, this, n3);
    }
    
    private int drawText(final Element element, final int n, int min, final Graphics graphics, int n2, final int n3) throws BadLocationException {
        min = Math.min(this.getDocument().getLength(), min);
        final AttributeSet attributes = element.getAttributes();
        if (Utilities.isComposedTextAttributeDefined(attributes)) {
            graphics.setColor(this.unselected);
            n2 = Utilities.drawComposedText(attributes, graphics, n2, n3, n - element.getStartOffset(), min - element.getStartOffset());
        }
        else if (this.sel0 == this.sel1) {
            n2 = this.drawUnselectedText(graphics, n2, n3, n, min);
        }
        else if (n >= this.sel0 && n <= this.sel1 && min >= this.sel0 && min <= this.sel1) {
            n2 = this.drawSelectedText(graphics, n2, n3, n, min);
        }
        else if (this.sel0 >= n && this.sel0 <= min) {
            if (this.sel1 >= n && this.sel1 <= min) {
                n2 = this.drawUnselectedText(graphics, n2, n3, n, this.sel0);
                n2 = this.drawSelectedText(graphics, n2, n3, this.sel0, this.sel1);
                n2 = this.drawUnselectedText(graphics, n2, n3, this.sel1, min);
            }
            else {
                n2 = this.drawUnselectedText(graphics, n2, n3, n, this.sel0);
                n2 = this.drawSelectedText(graphics, n2, n3, this.sel0, min);
            }
        }
        else if (this.sel1 >= n && this.sel1 <= min) {
            n2 = this.drawSelectedText(graphics, n2, n3, n, this.sel1);
            n2 = this.drawUnselectedText(graphics, n2, n3, this.sel1, min);
        }
        else {
            n2 = this.drawUnselectedText(graphics, n2, n3, n, min);
        }
        return n2;
    }
    
    protected int drawUnselectedText(final Graphics graphics, final int n, final int n2, final int n3, final int n4) throws BadLocationException {
        graphics.setColor(this.unselected);
        this.getDocument().getText(n3, n4 - n3, this.lineBuffer);
        return Utilities.drawTabbedText(this.lineBuffer, n, n2, graphics, this, n3);
    }
    
    protected final Segment getLineBuffer() {
        return this.lineBuffer;
    }
    
    public float getMaximumSpan(final int n) {
        this.updateMetrics();
        return super.getMaximumSpan(n);
    }
    
    public float getMinimumSpan(final int n) {
        this.updateMetrics();
        return super.getMinimumSpan(n);
    }
    
    public float getPreferredSpan(final int n) {
        this.updateMetrics();
        return super.getPreferredSpan(n);
    }
    
    protected int getTabSize() {
        final Integer n = (Integer)this.getDocument().getProperty("tabSize");
        return (n != null) ? n : 8;
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.updateChildren(documentEvent, shape);
        final Rectangle rectangle = (shape != null && this.isAllocationValid()) ? this.getInsideAllocation(shape) : null;
        final View viewAtPosition = this.getViewAtPosition(documentEvent.getOffset(), rectangle);
        if (viewAtPosition != null) {
            viewAtPosition.insertUpdate(documentEvent, rectangle, viewFactory);
        }
    }
    
    protected void loadChildren(final ViewFactory viewFactory) {
        final Element element = this.getElement();
        final int elementCount = element.getElementCount();
        if (elementCount > 0) {
            final View[] array = new View[elementCount];
            for (int i = 0; i < elementCount; ++i) {
                array[i] = new WrappedLine(element.getElement(i));
            }
            this.replace(0, 0, array);
        }
    }
    
    final void loadText(final int n, final int n2) {
        try {
            this.getDocument().getText(n, n2 - n, this.lineBuffer);
        }
        catch (BadLocationException ex) {
            throw new StateInvariantError("Can't get line text");
        }
    }
    
    public float nextTabStop(final float n, final int n2) {
        return this.tabBase + (((int)n - this.tabBase) / this.tabSize + 1) * this.tabSize;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        this.tabBase = ((Rectangle)shape).x;
        final JTextComponent textComponent = (JTextComponent)this.getContainer();
        this.sel0 = textComponent.getSelectionStart();
        this.sel1 = textComponent.getSelectionEnd();
        this.unselected = (textComponent.isEnabled() ? textComponent.getForeground() : textComponent.getDisabledTextColor());
        this.selected = (textComponent.getCaret().isSelectionVisible() ? textComponent.getSelectedTextColor() : this.unselected);
        graphics.setFont(textComponent.getFont());
        super.paint(graphics, shape);
    }
    
    public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.updateChildren(documentEvent, shape);
        final Rectangle rectangle = (shape != null && this.isAllocationValid()) ? this.getInsideAllocation(shape) : null;
        final View viewAtPosition = this.getViewAtPosition(documentEvent.getOffset(), rectangle);
        if (viewAtPosition != null) {
            viewAtPosition.removeUpdate(documentEvent, rectangle, viewFactory);
        }
    }
    
    public void setSize(final float n, final float n2) {
        this.updateMetrics();
        if ((int)n != this.getWidth()) {
            this.preferenceChanged(null, true, true);
            this.widthChanging = true;
        }
        super.setSize(n, n2);
        this.widthChanging = false;
    }
    
    void updateChildren(final DocumentEvent documentEvent, final Shape shape) {
        final DocumentEvent.ElementChange change = documentEvent.getChange(this.getElement());
        if (change != null) {
            final Element[] childrenRemoved = change.getChildrenRemoved();
            final Element[] childrenAdded = change.getChildrenAdded();
            final View[] array = new View[childrenAdded.length];
            for (int i = 0; i < childrenAdded.length; ++i) {
                array[i] = new WrappedLine(childrenAdded[i]);
            }
            this.replace(change.getIndex(), childrenRemoved.length, array);
            if (shape != null) {
                this.preferenceChanged(null, true, true);
                this.getContainer().repaint();
            }
        }
        this.updateMetrics();
    }
    
    final void updateMetrics() {
        final Container container = this.getContainer();
        this.metrics = container.getFontMetrics(container.getFont());
        this.tabSize = this.getTabSize() * this.metrics.charWidth('m');
    }
    
    class WrappedLine extends View
    {
        int nlines;
        
        WrappedLine(final Element element) {
            super(element);
        }
        
        final int calculateLineCount() {
            int n = 0;
            int calculateBreakPosition;
            for (int endOffset = this.getEndOffset(), i = this.getStartOffset(); i < endOffset; i = ((calculateBreakPosition == i) ? endOffset : calculateBreakPosition)) {
                ++n;
                calculateBreakPosition = WrappedPlainView.this.calculateBreakPosition(i, endOffset);
            }
            return n;
        }
        
        public float getPreferredSpan(final int n) {
            switch (n) {
                case 0: {
                    return WrappedPlainView.this.getWidth();
                }
                case 1: {
                    if (this.nlines == 0 || WrappedPlainView.this.widthChanging) {
                        this.nlines = this.calculateLineCount();
                    }
                    return this.nlines * WrappedPlainView.this.metrics.getHeight();
                }
                default: {
                    throw new IllegalArgumentException("Invalid axis: " + n);
                }
            }
        }
        
        public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
            final int calculateLineCount = this.calculateLineCount();
            if (this.nlines != calculateLineCount) {
                this.nlines = calculateLineCount;
                WrappedPlainView.this.preferenceChanged(this, false, true);
                this.getContainer().repaint();
            }
            else if (shape != null) {
                final Container container = this.getContainer();
                final Rectangle rectangle = (Rectangle)shape;
                container.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        
        public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
            final Rectangle bounds = shape.getBounds();
            bounds.height = WrappedPlainView.this.metrics.getHeight();
            bounds.width = 1;
            final int endOffset = this.getEndOffset();
            int i = this.getStartOffset();
            final int n2 = (bias == Position.Bias.Forward) ? n : Math.max(i, n - 1);
            while (i < endOffset) {
                final int calculateBreakPosition = WrappedPlainView.this.calculateBreakPosition(i, endOffset);
                if (n >= i && n2 < calculateBreakPosition) {
                    WrappedPlainView.this.loadText(i, n);
                    final Rectangle rectangle = bounds;
                    rectangle.x += Utilities.getTabbedTextWidth(WrappedPlainView.this.lineBuffer, WrappedPlainView.this.metrics, bounds.x, WrappedPlainView.this, i);
                    return bounds;
                }
                if (calculateBreakPosition == endOffset && n == endOffset) {
                    if (n > i) {
                        WrappedPlainView.this.loadText(i, n);
                        final Rectangle rectangle2 = bounds;
                        rectangle2.x += Utilities.getTabbedTextWidth(WrappedPlainView.this.lineBuffer, WrappedPlainView.this.metrics, bounds.x, WrappedPlainView.this, i);
                    }
                    return bounds;
                }
                i = ((calculateBreakPosition == i) ? endOffset : calculateBreakPosition);
                final Rectangle rectangle3 = bounds;
                rectangle3.y += bounds.height;
            }
            throw new BadLocationException(null, n);
        }
        
        public void paint(final Graphics graphics, final Shape shape) {
            final Rectangle rectangle = (Rectangle)shape;
            int n = rectangle.y + WrappedPlainView.this.metrics.getAscent();
            final int x = rectangle.x;
            final JTextComponent textComponent = (JTextComponent)this.getContainer();
            final Highlighter highlighter = textComponent.getHighlighter();
            final LayeredHighlighter layeredHighlighter = (highlighter instanceof LayeredHighlighter) ? ((LayeredHighlighter)highlighter) : null;
            int calculateBreakPosition;
            for (int endOffset = this.getEndOffset(), i = this.getStartOffset(); i < endOffset; i = ((calculateBreakPosition == i) ? endOffset : calculateBreakPosition), n += WrappedPlainView.this.metrics.getHeight()) {
                calculateBreakPosition = WrappedPlainView.this.calculateBreakPosition(i, endOffset);
                if (layeredHighlighter != null) {
                    if (calculateBreakPosition == endOffset) {
                        layeredHighlighter.paintLayeredHighlights(graphics, i, calculateBreakPosition - 1, shape, textComponent, this);
                    }
                    else {
                        layeredHighlighter.paintLayeredHighlights(graphics, i, calculateBreakPosition, shape, textComponent, this);
                    }
                }
                WrappedPlainView.this.drawLine(i, calculateBreakPosition, graphics, x, n);
            }
        }
        
        public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
            final int calculateLineCount = this.calculateLineCount();
            if (this.nlines != calculateLineCount) {
                this.nlines = calculateLineCount;
                WrappedPlainView.this.preferenceChanged(this, false, true);
                this.getContainer().repaint();
            }
            else if (shape != null) {
                final Container container = this.getContainer();
                final Rectangle rectangle = (Rectangle)shape;
                container.repaint(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        
        public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
            array[0] = Position.Bias.Forward;
            final Rectangle rectangle = (Rectangle)shape;
            this.getDocument();
            final int n3 = (int)n;
            final int n4 = (int)n2;
            if (n4 < rectangle.y) {
                return this.getStartOffset();
            }
            if (n4 > rectangle.y + rectangle.height) {
                return this.getEndOffset() - 1;
            }
            rectangle.height = WrappedPlainView.this.metrics.getHeight();
            final int endOffset = this.getEndOffset();
            int i = this.getStartOffset();
            while (i < endOffset) {
                final int calculateBreakPosition = WrappedPlainView.this.calculateBreakPosition(i, endOffset);
                if (n4 >= rectangle.y && n4 < rectangle.y + rectangle.height) {
                    if (n3 < rectangle.x) {
                        return i;
                    }
                    if (n3 > rectangle.x + rectangle.width) {
                        return calculateBreakPosition;
                    }
                    return Math.min(i + Utilities.getTabbedTextOffset(WrappedPlainView.this.lineBuffer, WrappedPlainView.this.metrics, rectangle.x, n3, WrappedPlainView.this, i), endOffset - 1);
                }
                else {
                    i = ((calculateBreakPosition == i) ? endOffset : calculateBreakPosition);
                    final Rectangle rectangle2 = rectangle;
                    rectangle2.y += rectangle.height;
                }
            }
            return this.getEndOffset() - 1;
        }
    }
}
