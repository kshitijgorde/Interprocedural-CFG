import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class h
{
    protected Rectangle E;
    private Rectangle p;
    private Rectangle q;
    private Rectangle r;
    boolean n;
    
    public h(final int n, final int n2, final int n3, final int n4) {
        this.E = new Rectangle(n, n2, n3, n4);
        final int n5 = n + 2;
        final int n6 = n + n3 - 2;
        this.p = new Rectangle(n, n2, 2, n4);
        this.q = new Rectangle(n5, n2, n3 - 4, n4);
        this.r = new Rectangle(n6, n2, 2, n4);
        this.n = true;
    }
    
    public void n(final int n) {
        this.E.x = n - this.E.width / 2;
        this.p.x = this.E.x;
        this.q.x = this.E.x + 2;
        this.r.x = this.E.x + this.E.width - 2;
    }
    
    public boolean _(final g g) {
        if (g.F < 0) {
            return false;
        }
        if (this.p.intersects(g.E)) {
            if (g.ryb > 0) {
                g.g();
            }
            else {
                g.f();
            }
            this.n = false;
            return true;
        }
        if (this.r.intersects(g.E)) {
            if (g.ryb < 0) {
                g.g();
            }
            else {
                g.f();
            }
            this.n = false;
            return true;
        }
        if (this.q.intersects(g.E)) {
            g.f();
            return this.n = true;
        }
        return false;
    }
}
