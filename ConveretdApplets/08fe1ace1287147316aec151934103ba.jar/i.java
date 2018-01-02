import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class i
{
    protected Rectangle E;
    
    public i(final int n, final int n2, final int n3, final int n4) {
        this.E = new Rectangle(n, n2, n3, n4);
    }
    
    public boolean _(final g g) {
        boolean b = false;
        final boolean b2 = g.E.y < this.E.y;
        final boolean b3 = g.E.x < this.E.x;
        boolean b4 = false;
        if (!b3) {
            b4 = (g.E.x + g.E.width > this.E.x + this.E.width);
        }
        if (b3 || b4) {
            g.e();
            b = true;
        }
        if (b2) {
            g.f();
            b = true;
        }
        return b;
    }
}
