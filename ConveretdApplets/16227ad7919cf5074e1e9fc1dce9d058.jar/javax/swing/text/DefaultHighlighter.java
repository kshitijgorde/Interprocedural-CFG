// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Insets;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.plaf.TextUI;
import java.awt.Color;
import java.util.Vector;

public class DefaultHighlighter extends LayeredHighlighter
{
    private Vector highlights;
    private JTextComponent component;
    private boolean drawsLayeredHighlights;
    public static LayerPainter DefaultPainter;
    
    static {
        DefaultHighlighter.DefaultPainter = new DefaultHighlightPainter(null);
    }
    
    public DefaultHighlighter() {
        this.highlights = new Vector();
        this.drawsLayeredHighlights = true;
    }
    
    public Object addHighlight(final int n, final int n2, final HighlightPainter painter) throws BadLocationException {
        final Document document = this.component.getDocument();
        final TextUI ui = this.component.getUI();
        final Highlight highlight = (this.getDrawsLayeredHighlights() && painter instanceof LayerPainter) ? new LayeredHighlightInfo() : new HighlightInfo();
        ((HighlightInfo)highlight).painter = painter;
        ((HighlightInfo)highlight).p0 = document.createPosition(n);
        ((HighlightInfo)highlight).p1 = document.createPosition(n2);
        this.highlights.addElement(highlight);
        ui.damageRange(this.component, n, n2);
        return highlight;
    }
    
    public void changeHighlight(final Object o, final int n, final int n2) throws BadLocationException {
        final Document document = this.component.getDocument();
        final TextUI ui = this.component.getUI();
        if (o instanceof LayeredHighlightInfo) {
            final LayeredHighlightInfo layeredHighlightInfo = (LayeredHighlightInfo)o;
            if (layeredHighlightInfo.width > 0 && layeredHighlightInfo.height > 0) {
                this.component.repaint(layeredHighlightInfo.x, layeredHighlightInfo.y, layeredHighlightInfo.width, layeredHighlightInfo.height);
            }
            final LayeredHighlightInfo layeredHighlightInfo2 = layeredHighlightInfo;
            final LayeredHighlightInfo layeredHighlightInfo3 = layeredHighlightInfo;
            final boolean b = false;
            layeredHighlightInfo3.height = (b ? 1 : 0);
            layeredHighlightInfo2.width = (b ? 1 : 0);
            ((HighlightInfo)layeredHighlightInfo).p0 = document.createPosition(n);
            ((HighlightInfo)layeredHighlightInfo).p1 = document.createPosition(n2);
            ui.damageRange(this.component, Math.min(n, n2), Math.max(n, n2));
        }
        else {
            final HighlightInfo highlightInfo = (HighlightInfo)o;
            final int offset = highlightInfo.p0.getOffset();
            final int offset2 = highlightInfo.p1.getOffset();
            if (n == offset) {
                ui.damageRange(this.component, Math.min(offset2, n2), Math.max(offset2, n2));
            }
            else if (n2 == offset2) {
                ui.damageRange(this.component, Math.min(n, offset), Math.max(n, offset));
            }
            else {
                ui.damageRange(this.component, offset, offset2);
                ui.damageRange(this.component, n, n2);
            }
            highlightInfo.p0 = document.createPosition(n);
            highlightInfo.p1 = document.createPosition(n2);
        }
    }
    
    public void deinstall(final JTextComponent textComponent) {
        this.component = null;
    }
    
    public boolean getDrawsLayeredHighlights() {
        return this.drawsLayeredHighlights;
    }
    
    public Highlight[] getHighlights() {
        final Highlight[] array = new Highlight[this.highlights.size()];
        this.highlights.copyInto(array);
        return array;
    }
    
    public void install(final JTextComponent component) {
        this.component = component;
        this.removeAllHighlights();
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle(this.component.getSize());
        final Insets insets = this.component.getInsets();
        final Rectangle rectangle2 = rectangle;
        rectangle2.x += insets.left;
        final Rectangle rectangle3 = rectangle;
        rectangle3.y += insets.top;
        final Rectangle rectangle4 = rectangle;
        rectangle4.width -= insets.left + insets.right;
        final Rectangle rectangle5 = rectangle;
        rectangle5.height -= insets.top + insets.bottom;
        for (int size = this.highlights.size(), i = 0; i < size; ++i) {
            final HighlightInfo highlightInfo = this.highlights.elementAt(i);
            if (!(highlightInfo instanceof LayeredHighlightInfo)) {
                highlightInfo.getPainter().paint(graphics, highlightInfo.getStartOffset(), highlightInfo.getEndOffset(), rectangle, this.component);
            }
        }
    }
    
    public void paintLayeredHighlights(final Graphics graphics, final int n, final int n2, final Shape shape, final JTextComponent textComponent, final View view) {
        for (int i = this.highlights.size() - 1; i >= 0; --i) {
            final Object element = this.highlights.elementAt(i);
            if (element instanceof LayeredHighlightInfo) {
                final LayeredHighlightInfo layeredHighlightInfo = (LayeredHighlightInfo)element;
                final int startOffset = ((HighlightInfo)layeredHighlightInfo).getStartOffset();
                final int endOffset = ((HighlightInfo)layeredHighlightInfo).getEndOffset();
                if ((n < startOffset && n2 > startOffset) || (n >= startOffset && n < endOffset)) {
                    layeredHighlightInfo.paintLayeredHighlights(graphics, n, n2, shape, textComponent, view);
                }
            }
        }
    }
    
    public void removeAllHighlights() {
        final TextUI ui = this.component.getUI();
        if (this.getDrawsLayeredHighlights()) {
            final int size = this.highlights.size();
            if (size != 0) {
                int min = 0;
                int min2 = 0;
                int max = 0;
                int max2 = 0;
                for (int i = 0; i < size; ++i) {
                    final LayeredHighlightInfo layeredHighlightInfo = this.highlights.elementAt(i);
                    min = Math.min(min, layeredHighlightInfo.x);
                    min2 = Math.min(min2, layeredHighlightInfo.y);
                    max = Math.max(max, layeredHighlightInfo.x + layeredHighlightInfo.width);
                    max2 = Math.max(max2, layeredHighlightInfo.y + layeredHighlightInfo.height);
                }
                if (min != max && min2 != max2) {
                    this.component.repaint(min, min2, max - min, max2 - min2);
                }
                this.highlights.removeAllElements();
            }
        }
        else if (ui != null) {
            final int size2 = this.highlights.size();
            if (size2 != 0) {
                int min3 = Integer.MAX_VALUE;
                int max3 = 0;
                for (int j = 0; j < size2; ++j) {
                    final HighlightInfo highlightInfo = this.highlights.elementAt(j);
                    min3 = Math.min(min3, highlightInfo.p0.getOffset());
                    max3 = Math.max(max3, highlightInfo.p1.getOffset());
                }
                ui.damageRange(this.component, min3, max3);
                this.highlights.removeAllElements();
            }
        }
    }
    
    public void removeHighlight(final Object o) {
        if (o instanceof LayeredHighlightInfo) {
            final LayeredHighlightInfo layeredHighlightInfo = (LayeredHighlightInfo)o;
            if (layeredHighlightInfo.width > 0 && layeredHighlightInfo.height > 0) {
                this.component.repaint(layeredHighlightInfo.x, layeredHighlightInfo.y, layeredHighlightInfo.width, layeredHighlightInfo.height);
            }
        }
        else {
            final TextUI ui = this.component.getUI();
            final HighlightInfo highlightInfo = (HighlightInfo)o;
            ui.damageRange(this.component, highlightInfo.p0.getOffset(), highlightInfo.p1.getOffset());
        }
        this.highlights.removeElement(o);
    }
    
    public void setDrawsLayeredHighlights(final boolean drawsLayeredHighlights) {
        this.drawsLayeredHighlights = drawsLayeredHighlights;
    }
    
    public static class DefaultHighlightPainter extends LayerPainter
    {
        private Color color;
        
        public DefaultHighlightPainter(final Color color) {
            this.color = color;
        }
        
        public Color getColor() {
            return this.color;
        }
        
        public void paint(final Graphics graphics, final int n, final int n2, final Shape shape, final JTextComponent textComponent) {
            final Rectangle bounds = shape.getBounds();
            try {
                final TextUI ui = textComponent.getUI();
                final Rectangle modelToView = ui.modelToView(textComponent, n);
                final Rectangle modelToView2 = ui.modelToView(textComponent, n2);
                final Color color = this.getColor();
                if (color == null) {
                    graphics.setColor(textComponent.getSelectionColor());
                }
                else {
                    graphics.setColor(color);
                }
                if (modelToView.y == modelToView2.y) {
                    final Rectangle union = modelToView.union(modelToView2);
                    graphics.fillRect(union.x, union.y, union.width, union.height);
                }
                else {
                    graphics.fillRect(modelToView.x, modelToView.y, bounds.x + bounds.width - modelToView.x, modelToView.height);
                    if (modelToView.y + modelToView.height != modelToView2.y) {
                        graphics.fillRect(bounds.x, modelToView.y + modelToView.height, bounds.width, modelToView2.y - (modelToView.y + modelToView.height));
                    }
                    graphics.fillRect(bounds.x, modelToView2.y, modelToView2.x - bounds.x, modelToView2.height);
                }
            }
            catch (BadLocationException ex) {}
        }
        
        public Shape paintLayer(final Graphics graphics, final int n, final int n2, final Shape shape, final JTextComponent textComponent, final View view) {
            final Color color = this.getColor();
            if (color == null) {
                graphics.setColor(textComponent.getSelectionColor());
            }
            else {
                graphics.setColor(color);
            }
            if (n == view.getStartOffset() && n2 == view.getEndOffset()) {
                Rectangle bounds;
                if (shape instanceof Rectangle) {
                    bounds = (Rectangle)shape;
                }
                else {
                    bounds = shape.getBounds();
                }
                graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                return bounds;
            }
            try {
                final Shape modelToView = view.modelToView(n, Position.Bias.Forward, n2, Position.Bias.Backward, shape);
                final Rectangle rectangle = (Rectangle)((modelToView instanceof Rectangle) ? modelToView : modelToView.getBounds());
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                return rectangle;
            }
            catch (BadLocationException ex) {
                return null;
            }
        }
    }
    
    class HighlightInfo implements Highlight
    {
        Position p0;
        Position p1;
        HighlightPainter painter;
        
        public int getEndOffset() {
            return this.p1.getOffset();
        }
        
        public HighlightPainter getPainter() {
            return this.painter;
        }
        
        public int getStartOffset() {
            return this.p0.getOffset();
        }
    }
    
    class LayeredHighlightInfo extends HighlightInfo
    {
        int x;
        int y;
        int width;
        int height;
        
        void paintLayeredHighlights(final Graphics graphics, int max, int min, final Shape shape, final JTextComponent textComponent, final View view) {
            final int startOffset = ((HighlightInfo)this).getStartOffset();
            final int endOffset = ((HighlightInfo)this).getEndOffset();
            max = Math.max(startOffset, max);
            min = Math.min(endOffset, min);
            this.union(((LayerPainter)super.painter).paintLayer(graphics, max, min, shape, textComponent, view));
        }
        
        void union(final Shape shape) {
            if (shape == null) {
                return;
            }
            Rectangle bounds;
            if (shape instanceof Rectangle) {
                bounds = (Rectangle)shape;
            }
            else {
                bounds = shape.getBounds();
            }
            if (this.width == 0 || this.height == 0) {
                this.x = bounds.x;
                this.y = bounds.y;
                this.width = bounds.width;
                this.height = bounds.height;
            }
            else {
                this.width = Math.max(this.x + this.width, bounds.x + bounds.width);
                this.height = Math.max(this.y + this.height, bounds.y + bounds.height);
                this.x = Math.min(this.x, bounds.x);
                this.width -= this.x;
                this.y = Math.min(this.y, bounds.y);
                this.height -= this.y;
            }
        }
    }
}
