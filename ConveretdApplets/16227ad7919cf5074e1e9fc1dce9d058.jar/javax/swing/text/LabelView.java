// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

public class LabelView extends View implements TabableView
{
    Font font;
    FontMetrics metrics;
    Color fg;
    Color bg;
    Segment text;
    boolean underline;
    boolean strike;
    boolean superscript;
    boolean subscript;
    boolean rightToLeft;
    TabExpander expander;
    int x;
    
    public LabelView(final Element element) {
        super(element);
        this.text = new Segment();
        final int startOffset = element.getStartOffset();
        final int endOffset = element.getEndOffset();
        final Document document = this.getDocument();
        if (document instanceof AbstractDocument) {
            this.rightToLeft = (((AbstractDocument)document).isLeftToRight(startOffset, endOffset) ^ true);
        }
    }
    
    public View breakView(final int n, final int n2, final float n3, final float n4) {
        if (n == 0) {
            this.sync();
            this.loadText(n2, this.getEndOffset());
            final LabelFragment labelFragment = new LabelFragment(this.getElement(), n2, n2 + Utilities.getBreakLocation(this.text, this.metrics, (int)n3, (int)(n3 + n4), this.expander, n2));
            labelFragment.x = (int)n3;
            return labelFragment;
        }
        return this;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.font = null;
    }
    
    public View createFragment(final int n, final int n2) {
        return new LabelFragment(this.getElement(), n, n2);
    }
    
    public float getAlignment(final int n) {
        if (n == 1) {
            final float n2 = this.metrics.getHeight();
            return (n2 - this.metrics.getDescent()) / n2;
        }
        return super.getAlignment(n);
    }
    
    public int getBreakWeight(final int n, final float n2, final float n3) {
        return this.getBreakWeight(n, n2, n3, this.getStartOffset(), this.getEndOffset());
    }
    
    int getBreakWeight(final int n, final float n2, final float n3, final int n4, final int n5) {
        if (n != 0) {
            return super.getBreakWeight(n, n2, n3);
        }
        this.sync();
        this.loadText(n4, n5);
        final int tabbedTextOffset = Utilities.getTabbedTextOffset(this.text, this.metrics, (int)n2, (int)(n2 + n3), this.expander, n4);
        if (tabbedTextOffset == 0) {
            return 0;
        }
        for (int i = this.text.offset + Math.min(tabbedTextOffset, this.text.count - 1); i >= this.text.offset; --i) {
            if (Character.isWhitespace(this.text.array[i])) {
                return 2000;
            }
        }
        return 1000;
    }
    
    protected Font getFont() {
        this.sync();
        return this.font;
    }
    
    protected FontMetrics getFontMetrics() {
        this.sync();
        return this.metrics;
    }
    
    public int getNextVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        return this.getNextVisualPositionFrom(n, bias, shape, n2, array, this.rightToLeft, this.getStartOffset(), this.getEndOffset());
    }
    
    int getNextVisualPositionFrom(int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array, final boolean b, final int n3, final int n4) throws BadLocationException {
        switch (n2) {
            case 3: {
                if (n3 == this.getDocument().getLength()) {
                    if (n == -1) {
                        array[0] = Position.Bias.Forward;
                        return n3;
                    }
                    return -1;
                }
                else if (b) {
                    if (n == -1) {
                        this.loadText(n4 - 1, n4);
                        if (this.text.array[this.text.offset] == '\n') {
                            array[0] = Position.Bias.Forward;
                            return n4 - 1;
                        }
                        array[0] = Position.Bias.Backward;
                        return n4;
                    }
                    else {
                        if (n == n3) {
                            return -1;
                        }
                        array[0] = Position.Bias.Forward;
                        return n - 1;
                    }
                }
                else {
                    if (n == -1) {
                        array[0] = Position.Bias.Forward;
                        return n3;
                    }
                    if (n == n4) {
                        return -1;
                    }
                    if (++n == n4) {
                        this.loadText(n4 - 1, n4);
                        if (this.text.array[this.text.offset] == '\n') {
                            return -1;
                        }
                        array[0] = Position.Bias.Backward;
                    }
                    else {
                        array[0] = Position.Bias.Forward;
                    }
                    return n;
                }
                break;
            }
            case 7: {
                if (n3 == this.getDocument().getLength()) {
                    if (n == -1) {
                        array[0] = Position.Bias.Forward;
                        return n3;
                    }
                    return -1;
                }
                else if (b) {
                    if (n == -1) {
                        array[0] = Position.Bias.Forward;
                        return n3;
                    }
                    if (n == n4) {
                        return -1;
                    }
                    if (++n == n4) {
                        this.loadText(n4 - 1, n4);
                        if (this.text.array[this.text.offset] == '\n') {
                            return -1;
                        }
                        array[0] = Position.Bias.Backward;
                    }
                    else {
                        array[0] = Position.Bias.Forward;
                    }
                    return n;
                }
                else if (n == -1) {
                    this.loadText(n4 - 1, n4);
                    if (this.text.array[this.text.offset] == '\n') {
                        array[0] = Position.Bias.Forward;
                        return n4 - 1;
                    }
                    array[0] = Position.Bias.Backward;
                    return n4;
                }
                else {
                    if (n == n3) {
                        return -1;
                    }
                    array[0] = Position.Bias.Forward;
                    return n - 1;
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Bad direction: " + n2);
            }
            case 1:
            case 5: {
                return n;
            }
        }
    }
    
    public float getPartialSpan(final int n, final int n2) {
        this.sync();
        int tabbedTextWidth = 0;
        try {
            final Segment segment = new Segment();
            this.getDocument().getText(n, n2 - n, segment);
            tabbedTextWidth = Utilities.getTabbedTextWidth(segment, this.metrics, this.x, this.expander, n);
        }
        catch (BadLocationException ex) {}
        return tabbedTextWidth;
    }
    
    public float getPreferredSpan(final int n) {
        return this.getPreferredSpan(n, this.getStartOffset(), this.getEndOffset(), this.x);
    }
    
    final float getPreferredSpan(final int n, final int n2, final int n3, final int n4) {
        this.sync();
        switch (n) {
            case 0: {
                this.loadText(n2, n3);
                return Math.max(Utilities.getTabbedTextWidth(this.text, this.metrics, n4, this.expander, n2), 1);
            }
            case 1: {
                return this.metrics.getHeight();
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public float getTabbedSpan(final float n, final TabExpander expander) {
        this.expander = expander;
        this.x = (int)n;
        return this.getPreferredSpan(0, this.getStartOffset(), this.getEndOffset(), this.x);
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.insertUpdate(documentEvent, shape, viewFactory);
        final int startOffset = this.getStartOffset();
        final int endOffset = this.getEndOffset();
        final Document document = this.getDocument();
        if (document instanceof AbstractDocument) {
            this.rightToLeft = (((AbstractDocument)document).isLeftToRight(startOffset, endOffset) ^ true);
        }
    }
    
    final void loadText(final int n, final int n2) {
        try {
            this.getDocument().getText(n, n2 - n, this.text);
        }
        catch (BadLocationException ex) {
            throw new StateInvariantError("LabelView: Stale view: " + ex);
        }
    }
    
    Shape modelToView(final int n, final Shape shape, final int n2, final int n3, final Position.Bias bias, final boolean b) throws BadLocationException {
        final Rectangle bounds = shape.getBounds();
        if (n == n3) {
            this.loadText(n2, n2 + 1);
            this.sync();
            if (!b) {
                return new Rectangle(bounds.x + bounds.width, bounds.y, 0, this.metrics.getHeight());
            }
            return new Rectangle(bounds.x, bounds.y, 0, this.metrics.getHeight());
        }
        else {
            if (n < n2 || n > n3) {
                throw new BadLocationException("modelToView - can't convert", n3);
            }
            this.loadText(n2, n);
            this.sync();
            final int tabbedTextWidth = Utilities.getTabbedTextWidth(this.text, this.metrics, bounds.x, this.expander, n2);
            if (b) {
                return new Rectangle(bounds.x + bounds.width - tabbedTextWidth, bounds.y, 0, this.metrics.getHeight());
            }
            return new Rectangle(bounds.x + tabbedTextWidth, bounds.y, 0, this.metrics.getHeight());
        }
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        return this.modelToView(n, shape, this.getStartOffset(), this.getEndOffset(), bias, this.rightToLeft);
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Container container = this.getContainer();
        if (this.bg != null) {
            final Rectangle rectangle = (Rectangle)((shape instanceof Rectangle) ? shape : shape.getBounds());
            graphics.setColor(this.bg);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if (container instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent)container;
            final Highlighter highlighter = textComponent.getHighlighter();
            if (highlighter instanceof LayeredHighlighter) {
                ((LayeredHighlighter)highlighter).paintLayeredHighlights(graphics, this.getStartOffset(), this.getEndOffset(), shape, textComponent, this);
            }
        }
        this.paintText(container, graphics, shape, this.getStartOffset(), this.getEndOffset(), false, this.rightToLeft);
    }
    
    void paintText(final Component component, final Graphics graphics, final Shape shape, final int n, final int n2, final boolean b, final boolean b2) {
        final Rectangle bounds = shape.getBounds();
        this.sync();
        final int n3 = bounds.y + bounds.height - this.metrics.getDescent();
        graphics.setFont(this.font);
        boolean b3 = false;
        if (component instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent)component;
            final Color selectedTextColor = textComponent.getSelectedTextColor();
            if (selectedTextColor != null && !selectedTextColor.equals(this.fg)) {
                final Position.Bias[] array = { null };
                final Position.Bias[] array2 = { null };
                final int selectionStart = textComponent.getSelectionStart(array);
                final int selectionEnd = textComponent.getSelectionEnd(array2);
                if (selectionStart != selectionEnd) {
                    int min;
                    if (selectionStart <= n) {
                        min = n;
                    }
                    else {
                        min = Math.min(selectionStart, n2);
                    }
                    int max;
                    if (selectionEnd >= n2) {
                        max = n2;
                    }
                    else {
                        max = Math.max(selectionEnd, n);
                    }
                    if (min != max) {
                        int n4 = bounds.x;
                        b3 = true;
                        if (min > n) {
                            n4 = this.paintTextUsingColor(n4, n3, graphics, n, min, this.fg, b);
                        }
                        final int paintTextUsingColor = this.paintTextUsingColor(n4, n3, graphics, min, max, selectedTextColor, b);
                        if (max < n2) {
                            this.paintTextUsingColor(paintTextUsingColor, n3, graphics, max, n2, this.fg, b);
                        }
                    }
                }
            }
        }
        if (!b3) {
            this.paintTextUsingColor(bounds.x, n3, graphics, n, n2, this.fg, b);
        }
    }
    
    final int paintTextUsingColor(int drawTabbedText, int n, final Graphics graphics, final int n2, final int n3, final Color color, final boolean b) {
        final int n4 = drawTabbedText;
        graphics.setColor(color);
        this.loadText(n2, n3);
        if (this.superscript) {
            n -= this.metrics.getAscent() / 2;
        }
        else if (this.subscript) {
            n += this.metrics.getDescent();
        }
        drawTabbedText = Utilities.drawTabbedText(this.text, drawTabbedText, n, graphics, this.expander, n2);
        if (this.underline || this.strike) {
            int n5 = drawTabbedText - n4;
            if (b) {
                while (this.text.count > 0 && Character.isWhitespace(this.text.array[this.text.count - 1])) {
                    n5 -= this.metrics.charWidth(this.text.array[this.text.count - 1]);
                    final Segment text = this.text;
                    --text.count;
                }
            }
            if (this.underline) {
                ++n;
            }
            else if (this.strike) {
                n -= this.metrics.getAscent() / 2;
            }
            graphics.drawLine(n4, n, drawTabbedText, n);
        }
        return drawTabbedText;
    }
    
    protected void setPropertiesFromAttributes() {
        final AttributeSet attributes = this.getAttributes();
        if (attributes != null) {
            final Document document = this.getDocument();
            if (!(document instanceof StyledDocument)) {
                throw new StateInvariantError("LabelView needs StyledDocument");
            }
            final StyledDocument styledDocument = (StyledDocument)document;
            this.font = styledDocument.getFont(attributes);
            this.fg = styledDocument.getForeground(attributes);
            if (attributes.isDefined(StyleConstants.Background)) {
                this.bg = styledDocument.getBackground(attributes);
            }
            else {
                this.bg = null;
            }
            this.setUnderline(StyleConstants.isUnderline(attributes));
            this.setStrikeThrough(StyleConstants.isStrikeThrough(attributes));
            this.setSuperscript(StyleConstants.isSuperscript(attributes));
            this.setSubscript(StyleConstants.isSubscript(attributes));
            this.metrics = Toolkit.getDefaultToolkit().getFontMetrics(this.font);
        }
    }
    
    protected void setStrikeThrough(final boolean strike) {
        this.strike = strike;
    }
    
    protected void setSubscript(final boolean subscript) {
        this.subscript = subscript;
    }
    
    protected void setSuperscript(final boolean superscript) {
        this.superscript = superscript;
    }
    
    protected void setUnderline(final boolean underline) {
        this.underline = underline;
    }
    
    final void sync() {
        if (this.font == null) {
            this.setPropertiesFromAttributes();
        }
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        array[0] = Position.Bias.Forward;
        return this.viewToModel(n, n2, shape, array, this.getStartOffset(), this.getEndOffset(), this.rightToLeft);
    }
    
    int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array, final int n3, final int n4, final boolean b) {
        final Rectangle bounds = shape.getBounds();
        this.sync();
        this.loadText(n3, n4);
        final int tabbedTextOffset = Utilities.getTabbedTextOffset(this.text, this.metrics, bounds.x, (int)n, this.expander, n3);
        int n5;
        if (b) {
            n5 = n4 - tabbedTextOffset;
        }
        else {
            n5 = n3 + tabbedTextOffset;
        }
        if (n5 == n4) {
            array[0] = Position.Bias.Backward;
        }
        else {
            array[0] = Position.Bias.Forward;
        }
        return n5;
    }
    
    class LabelFragment extends View implements TabableView
    {
        short offset;
        short length;
        int x;
        boolean rightToLeft;
        
        public LabelFragment(final Element element, final int n, final int n2) {
            super(element);
            this.offset = (short)(n - element.getStartOffset());
            this.length = (short)(n2 - n);
            final Document document = this.getDocument();
            if (document instanceof AbstractDocument) {
                this.rightToLeft = (((AbstractDocument)document).isLeftToRight(n, n2) ^ true);
            }
        }
        
        public View breakView(final int n, final int n2, final float n3, final float n4) {
            return LabelView.this.breakView(n, n2, n3, n4);
        }
        
        public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
            LabelView.this.changedUpdate(documentEvent, shape, viewFactory);
        }
        
        public float getAlignment(final int n) {
            return LabelView.this.getAlignment(n);
        }
        
        public AttributeSet getAttributes() {
            return LabelView.this.getAttributes();
        }
        
        public int getBreakWeight(final int n, final float n2, final float n3) {
            return LabelView.this.getBreakWeight(n, n2, n3, this.getStartOffset(), this.getEndOffset());
        }
        
        public int getEndOffset() {
            return this.getElement().getStartOffset() + this.offset + this.length;
        }
        
        public int getNextVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
            return LabelView.this.getNextVisualPositionFrom(n, bias, shape, n2, array, this.rightToLeft, this.getStartOffset(), this.getEndOffset());
        }
        
        public float getPartialSpan(final int n, final int n2) {
            return LabelView.this.getPartialSpan(n, n2);
        }
        
        public float getPreferredSpan(final int n) {
            return LabelView.this.getPreferredSpan(n, this.getStartOffset(), this.getEndOffset(), this.x);
        }
        
        public int getStartOffset() {
            return this.getElement().getStartOffset() + this.offset;
        }
        
        public float getTabbedSpan(final float n, final TabExpander expander) {
            LabelView.this.expander = expander;
            this.x = (int)n;
            return LabelView.this.getPreferredSpan(0, this.getStartOffset(), this.getEndOffset(), this.x);
        }
        
        public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
            return LabelView.this.modelToView(n, shape, this.getStartOffset(), this.getEndOffset(), bias, this.rightToLeft);
        }
        
        public void paint(final Graphics graphics, final Shape shape) {
            final Container container = this.getContainer();
            if (LabelView.this.bg != null) {
                final Rectangle rectangle = (Rectangle)((shape instanceof Rectangle) ? shape : shape.getBounds());
                graphics.setColor(LabelView.this.bg);
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            if (container instanceof JTextComponent) {
                final JTextComponent textComponent = (JTextComponent)container;
                final Highlighter highlighter = textComponent.getHighlighter();
                if (highlighter instanceof LayeredHighlighter) {
                    ((LayeredHighlighter)highlighter).paintLayeredHighlights(graphics, this.getStartOffset(), this.getEndOffset(), shape, textComponent, this);
                }
            }
            LabelView.this.paintText(container, graphics, shape, this.getStartOffset(), this.getEndOffset(), true, this.rightToLeft);
        }
        
        public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
            array[0] = Position.Bias.Forward;
            return LabelView.this.viewToModel(n, n2, shape, array, this.getStartOffset(), this.getEndOffset(), this.rightToLeft);
        }
    }
}
