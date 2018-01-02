// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;

public class aP extends aY
{
    protected O d;
    protected O e;
    
    public aP() {
        this.d = null;
        this.e = null;
    }
    
    public aP(final O d, final O e) {
        this.d = d;
        this.e = e;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.d != null) {
            this.d.paintBorder(component, graphics, n5, n6, n7, n8);
            final Insets borderInsets = this.d.getBorderInsets(component);
            n5 += borderInsets.left;
            n6 += borderInsets.top;
            n7 = n7 - borderInsets.right - borderInsets.left;
            n8 = n8 - borderInsets.bottom - borderInsets.top;
        }
        if (this.e != null) {
            this.e.paintBorder(component, graphics, n5, n6, n7, n8);
        }
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        if (this.d != null) {
            final Insets borderInsets = this.d.getBorderInsets(component);
            insets.top += borderInsets.top;
            insets.left += borderInsets.left;
            insets.right += borderInsets.right;
            insets.bottom += borderInsets.bottom;
        }
        if (this.e != null) {
            final Insets borderInsets2 = this.e.getBorderInsets(component);
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
