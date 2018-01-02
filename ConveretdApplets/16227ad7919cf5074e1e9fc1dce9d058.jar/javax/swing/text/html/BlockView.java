// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.SizeRequirements;
import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;
import javax.swing.text.BoxView;

public class BlockView extends BoxView
{
    private AttributeSet attr;
    private StyleSheet.BoxPainter painter;
    
    public BlockView(final Element element, final int n) {
        super(element, n);
        final StyleSheet styleSheet = this.getStyleSheet();
        this.attr = styleSheet.getViewAttributes(this);
        this.painter = styleSheet.getBoxPainter(this.attr);
        this.setPropertiesFromAttributes();
    }
    
    void adjustSizeForCSS(final int n, final SizeRequirements sizeRequirements) {
        if (n == 0) {
            final Object attribute = this.attr.getAttribute(CSS.Attribute.WIDTH);
            if (attribute != null) {
                final int n3;
                final int n2 = n3 = (int)((CSS.LengthValue)attribute).getValue();
                sizeRequirements.preferred = n3;
                sizeRequirements.minimum = n3;
                sizeRequirements.maximum = Math.max(sizeRequirements.maximum, n2);
            }
        }
        else {
            final Object attribute2 = this.attr.getAttribute(CSS.Attribute.HEIGHT);
            if (attribute2 != null) {
                final int n5;
                final int n4 = n5 = (int)((CSS.LengthValue)attribute2).getValue();
                sizeRequirements.preferred = n5;
                sizeRequirements.minimum = n5;
                sizeRequirements.maximum = Math.max(sizeRequirements.maximum, n4);
            }
        }
    }
    
    protected SizeRequirements calculateMajorAxisRequirements(final int n, final SizeRequirements sizeRequirements) {
        final SizeRequirements calculateMajorAxisRequirements = super.calculateMajorAxisRequirements(n, sizeRequirements);
        this.adjustSizeForCSS(n, calculateMajorAxisRequirements);
        return calculateMajorAxisRequirements;
    }
    
    protected SizeRequirements calculateMinorAxisRequirements(final int n, final SizeRequirements sizeRequirements) {
        final SizeRequirements calculateMinorAxisRequirements = super.calculateMinorAxisRequirements(n, sizeRequirements);
        this.adjustSizeForCSS(n, calculateMinorAxisRequirements);
        return calculateMinorAxisRequirements;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.changedUpdate(documentEvent, shape, viewFactory);
        final int offset = documentEvent.getOffset();
        if (offset <= this.getStartOffset() && offset + documentEvent.getLength() >= this.getEndOffset()) {
            this.setPropertiesFromAttributes();
        }
    }
    
    public float getAlignment(final int n) {
        switch (n) {
            case 0: {
                return 0.0f;
            }
            case 1: {
                final float preferredSpan = this.getPreferredSpan(1);
                final View view = this.getView(0);
                final float preferredSpan2 = view.getPreferredSpan(1);
                return ((int)preferredSpan != 0) ? (preferredSpan2 * view.getAlignment(1) / preferredSpan) : 0.0f;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    public int getResizeWeight(final int n) {
        switch (n) {
            case 0: {
                return 1;
            }
            case 1: {
                return 0;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)this.getDocument()).getStyleSheet();
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Rectangle rectangle = (Rectangle)shape;
        this.painter.paint(graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height, this);
        super.paint(graphics, rectangle);
    }
    
    protected void setPropertiesFromAttributes() {
        this.attr = this.getStyleSheet().getViewAttributes(this);
        if (this.attr != null) {
            this.setInsets((short)this.painter.getInset(1, this), (short)this.painter.getInset(2, this), (short)this.painter.getInset(3, this), (short)this.painter.getInset(4, this));
        }
    }
}
