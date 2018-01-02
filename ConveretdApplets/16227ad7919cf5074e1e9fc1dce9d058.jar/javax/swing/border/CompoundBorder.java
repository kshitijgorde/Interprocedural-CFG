// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;

public class CompoundBorder extends AbstractBorder
{
    protected Border outsideBorder;
    protected Border insideBorder;
    
    public CompoundBorder() {
        this.outsideBorder = null;
        this.insideBorder = null;
    }
    
    public CompoundBorder(final Border outsideBorder, final Border insideBorder) {
        this.outsideBorder = outsideBorder;
        this.insideBorder = insideBorder;
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets(component, new Insets(0, 0, 0, 0));
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        if (this.outsideBorder != null) {
            final Insets borderInsets = this.outsideBorder.getBorderInsets(component);
            insets.top += borderInsets.top;
            insets.left += borderInsets.left;
            insets.right += borderInsets.right;
            insets.bottom += borderInsets.bottom;
        }
        if (this.insideBorder != null) {
            final Insets borderInsets2 = this.insideBorder.getBorderInsets(component);
            insets.top += borderInsets2.top;
            insets.left += borderInsets2.left;
            insets.right += borderInsets2.right;
            insets.bottom += borderInsets2.bottom;
        }
        return insets;
    }
    
    public Border getInsideBorder() {
        return this.insideBorder;
    }
    
    public Border getOutsideBorder() {
        return this.outsideBorder;
    }
    
    public boolean isBorderOpaque() {
        return this.outsideBorder != null && this.outsideBorder.isBorderOpaque() && this.insideBorder != null && this.insideBorder.isBorderOpaque();
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.outsideBorder != null) {
            this.outsideBorder.paintBorder(component, graphics, n5, n6, n7, n8);
            final Insets borderInsets = this.outsideBorder.getBorderInsets(component);
            n5 += borderInsets.left;
            n6 += borderInsets.top;
            n7 = n7 - borderInsets.right - borderInsets.left;
            n8 = n8 - borderInsets.bottom - borderInsets.top;
        }
        if (this.insideBorder != null) {
            this.insideBorder.paintBorder(component, graphics, n5, n6, n7, n8);
        }
    }
}
