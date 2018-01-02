// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public class j extends R
{
    protected Color a;
    protected int f;
    protected int a;
    protected int b;
    protected int c;
    
    public j(final int b, final int f, final int c, final int a, final Color a2) {
        this.b = b;
        this.a = a;
        this.c = c;
        this.f = f;
        this.a = a2;
    }
    
    public void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
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
    
    public Insets a(final Component component) {
        return this.a();
    }
    
    public Insets a() {
        return this.a(new Insets(0, 0, 0, 0));
    }
    
    private Insets a(final Insets insets) {
        insets.left = this.f;
        insets.top = this.b;
        insets.right = this.a;
        insets.bottom = this.c;
        return insets;
    }
}
