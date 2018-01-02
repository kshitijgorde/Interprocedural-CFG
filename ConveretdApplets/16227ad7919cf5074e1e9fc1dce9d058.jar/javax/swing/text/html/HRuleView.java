// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Graphics;
import javax.swing.text.BadLocationException;
import java.awt.Rectangle;
import javax.swing.text.Position;
import java.awt.Shape;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.text.StyleConstants;
import javax.swing.text.Element;
import javax.swing.border.Border;
import javax.swing.text.View;

class HRuleView extends View
{
    private Border bevel;
    private float margin_left;
    private float margin_right;
    private int alignment;
    private String noshade;
    private int size;
    private int hrwidth;
    private static final int SPACE_ABOVE = 3;
    private static final int SPACE_BELOW = 3;
    
    public HRuleView(final Element element) {
        super(element);
        this.margin_left = 0.0f;
        this.margin_right = 0.0f;
        this.alignment = 0;
        this.noshade = null;
        this.size = 0;
        this.hrwidth = 0;
        final AttributeSet attributes = element.getAttributes();
        if (attributes != null) {
            this.margin_left = 0.0f;
            this.margin_right = 0.0f;
            this.alignment = StyleConstants.getAlignment(attributes);
            this.noshade = (String)attributes.getAttribute("noshade");
            final String s = (String)attributes.getAttribute("size");
            if (s != null) {
                this.size = Integer.valueOf(s);
            }
            final String s2 = (String)attributes.getAttribute("width");
            if (s2 != null) {
                this.hrwidth = Integer.valueOf(s2);
            }
        }
        this.bevel = BorderFactory.createLoweredBevelBorder();
    }
    
    public View breakView(final int n, final int n2, final float n3, final float n4) {
        return null;
    }
    
    public int getBreakWeight(final int n, final float n2, final float n3) {
        if (n == 0) {
            return 3000;
        }
        return 0;
    }
    
    public float getPreferredSpan(final int n) {
        final Insets borderInsets = this.bevel.getBorderInsets(this.getContainer());
        switch (n) {
            case 0: {
                return borderInsets.left + borderInsets.right;
            }
            case 1: {
                if (this.size > 0) {
                    return this.size + 3 + 3;
                }
                if (this.noshade == "#DEFAULT") {
                    return 7.0f;
                }
                return borderInsets.top + borderInsets.bottom + 3 + 3;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public int getResizeWeight(final int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 0;
        }
        return 0;
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        final int startOffset = this.getStartOffset();
        final int endOffset = this.getEndOffset();
        if (n >= startOffset && n <= endOffset) {
            final Rectangle bounds = shape.getBounds();
            if (n == endOffset) {
                final Rectangle rectangle = bounds;
                rectangle.x += bounds.width;
            }
            bounds.width = 0;
            return bounds;
        }
        return null;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Rectangle bounds = shape.getBounds();
        final int n = bounds.y + 3;
        int hrwidth = bounds.width - (int)(this.margin_left + this.margin_right);
        if (this.hrwidth > 0) {
            hrwidth = this.hrwidth;
        }
        int size = bounds.height - 6;
        if (this.size > 0) {
            size = this.size;
        }
        int n2 = 0;
        switch (this.alignment) {
            case 1: {
                n2 = bounds.x + bounds.width / 2 - hrwidth / 2;
                break;
            }
            case 2: {
                n2 = bounds.x + bounds.width - this.hrwidth - (int)this.margin_right;
                break;
            }
            default: {
                n2 = bounds.x + (int)this.margin_left;
                break;
            }
        }
        if (this.noshade == "#DEFAULT") {
            graphics.fillRect(n2, n, hrwidth, size);
        }
        else {
            this.bevel.paintBorder(this.getContainer(), graphics, n2, n, hrwidth, size);
        }
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        final Rectangle rectangle = (Rectangle)shape;
        if (n < rectangle.x + rectangle.width / 2) {
            array[0] = Position.Bias.Forward;
            return this.getStartOffset();
        }
        array[0] = Position.Bias.Backward;
        return this.getEndOffset();
    }
}
