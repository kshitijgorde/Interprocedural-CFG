// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;

public final class H extends V
{
    private bn a;
    private bn b;
    
    public H() {
        this.a = null;
        this.b = null;
    }
    
    public H(final bn a, final bn b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.a != null) {
            this.a.a(component, graphics, n, n2, n3, n4);
            final Insets a = this.a.a(component);
            n5 = n + a.left;
            n6 = n2 + a.top;
            n7 = n3 - a.right - a.left;
            n8 = n4 - a.bottom - a.top;
        }
        if (this.b != null) {
            this.b.a(component, graphics, n5, n6, n7, n8);
        }
    }
    
    public final Insets a(Component component) {
        final H h = this;
        final Component component2 = component;
        final Insets insets = new Insets(0, 0, 0, 0);
        component = component2;
        this = h;
        final Insets insets2 = insets;
        final Insets insets3 = insets;
        final Insets insets4 = insets;
        final Insets insets5 = insets;
        final boolean b = false;
        insets5.bottom = (b ? 1 : 0);
        insets4.right = (b ? 1 : 0);
        insets3.left = (b ? 1 : 0);
        insets2.top = (b ? 1 : 0);
        if (this.a != null) {
            final Insets a = this.a.a(component);
            final Insets insets6 = insets;
            insets6.top += a.top;
            final Insets insets7 = insets;
            insets7.left += a.left;
            final Insets insets8 = insets;
            insets8.right += a.right;
            final Insets insets9 = insets;
            insets9.bottom += a.bottom;
        }
        if (this.b != null) {
            final Insets a2 = this.b.a(component);
            final Insets insets10 = insets;
            insets10.top += a2.top;
            final Insets insets11 = insets;
            insets11.left += a2.left;
            final Insets insets12 = insets;
            insets12.right += a2.right;
            final Insets insets13 = insets;
            insets13.bottom += a2.bottom;
        }
        return insets;
    }
}
