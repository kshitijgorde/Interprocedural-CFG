// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;

public class m extends R
{
    protected H a;
    protected H b;
    
    public m() {
        this.a = null;
        this.b = null;
    }
    
    public m(final H a, final H b) {
        this.a = a;
        this.b = b;
    }
    
    public void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.a != null) {
            this.a.a(component, graphics, n5, n6, n7, n8);
            final Insets a = this.a.a(component);
            n5 += a.left;
            n6 += a.top;
            n7 = n7 - a.right - a.left;
            n8 = n8 - a.bottom - a.top;
        }
        if (this.b != null) {
            this.b.a(component, graphics, n5, n6, n7, n8);
        }
    }
    
    public Insets a(final Component component, final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        if (this.a != null) {
            final Insets a = this.a.a(component);
            insets.top += a.top;
            insets.left += a.left;
            insets.right += a.right;
            insets.bottom += a.bottom;
        }
        if (this.b != null) {
            final Insets a2 = this.b.a(component);
            insets.top += a2.top;
            insets.left += a2.left;
            insets.right += a2.right;
            insets.bottom += a2.bottom;
        }
        return insets;
    }
    
    public Insets a(final Component component) {
        return this.a(component, new Insets(0, 0, 0, 0));
    }
}
