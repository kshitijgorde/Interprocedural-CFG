// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public final class aP extends V
{
    private Color a;
    private int a;
    private int b;
    private int c;
    private int d;
    
    public aP(final Color a) {
        this.c = 1;
        this.b = 1;
        this.d = 1;
        this.a = 1;
        this.a = a;
    }
    
    public final void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets a = this.a(component);
        final Color color = graphics.getColor();
        graphics.translate(n, n2);
        if (this.a != null) {
            graphics.setColor(this.a);
            graphics.fillRect(0, 0, n3 - a.right, a.top);
            graphics.fillRect(0, a.top, a.left, n4 - a.top);
            graphics.fillRect(a.left, n4 - a.bottom, n3 - a.left, a.bottom);
            graphics.fillRect(n3 - a.right, 0, a.right, n4 - a.bottom);
        }
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
    
    public final Insets a(final Component component) {
        final aP ap = this;
        final Insets insets = new Insets(0, 0, 0, 0);
        this = ap;
        insets.left = this.a;
        insets.top = this.c;
        insets.right = this.b;
        insets.bottom = this.d;
        return insets;
    }
}
