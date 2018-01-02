// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;

public class aA extends aH
{
    protected aa b;
    protected aa c;
    
    public aA() {
        this.b = null;
        this.c = null;
    }
    
    public aA(final aa b, final aa c) {
        this.b = b;
        this.c = c;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.b != null) {
            this.b.paintBorder(component, graphics, n5, n6, n7, n8);
            final Insets borderInsets = this.b.getBorderInsets(component);
            n5 += borderInsets.left;
            n6 += borderInsets.top;
            n7 = n7 - borderInsets.right - borderInsets.left;
            n8 = n8 - borderInsets.bottom - borderInsets.top;
        }
        if (this.c != null) {
            this.c.paintBorder(component, graphics, n5, n6, n7, n8);
        }
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        if (this.b != null) {
            final Insets borderInsets = this.b.getBorderInsets(component);
            insets.top += borderInsets.top;
            insets.left += borderInsets.left;
            insets.right += borderInsets.right;
            insets.bottom += borderInsets.bottom;
        }
        if (this.c != null) {
            final Insets borderInsets2 = this.c.getBorderInsets(component);
            insets.top += borderInsets2.top;
            insets.left += borderInsets2.left;
            insets.right += borderInsets2.right;
            insets.bottom += borderInsets2.bottom;
        }
        return insets;
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets(component, new Insets(0, 0, 0, 0));
    }
}
