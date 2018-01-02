// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Component;
import java.io.Serializable;

public abstract class AbstractBorder implements Border, Serializable
{
    public Insets getBorderInsets(final Component component) {
        return new Insets(0, 0, 0, 0);
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        return insets;
    }
    
    public Rectangle getInteriorRectangle(final Component component, final int n, final int n2, final int n3, final int n4) {
        return getInteriorRectangle(component, this, n, n2, n3, n4);
    }
    
    public static Rectangle getInteriorRectangle(final Component component, final Border border, final int n, final int n2, final int n3, final int n4) {
        Insets borderInsets;
        if (border != null) {
            borderInsets = border.getBorderInsets(component);
        }
        else {
            borderInsets = new Insets(0, 0, 0, 0);
        }
        return new Rectangle(n + borderInsets.left, n2 + borderInsets.top, n3 - borderInsets.right - borderInsets.left, n4 - borderInsets.top - borderInsets.bottom);
    }
    
    public boolean isBorderOpaque() {
        return false;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
}
