import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    public static final int tyb = -1;
    public static final int uyb = 10;
    public static final int vyb = 11;
    public static final int wyb = 12;
    public static final int xyb = 13;
    public static final int yyb = 14;
    protected boolean ib;
    protected boolean D;
    protected boolean zyb;
    protected Rectangle E;
    protected int Ayb;
    private Rectangle Byb;
    
    public f(final int n, final int n2, final int n3, final int n4, final int ayb) {
        this.Ayb = ayb;
        this.ib = true;
        this.E = new Rectangle(n, n2, n3, n4);
        this.Byb = new Rectangle();
    }
    
    public boolean _(final g g) {
        if (this.E.intersects(g.E)) {
            this.ib = false;
            this.zyb = true;
            this._(g);
            return true;
        }
        return false;
    }
    
    protected void _(final g g) {
        final Rectangle e = g.E;
        this.Byb.x = ((this.E.x > e.x) ? this.E.x : e.x);
        this.Byb.y = ((this.E.y > e.y) ? this.E.y : e.y);
        this.Byb.width = Math.min(this.E.x + this.E.width, e.x + e.width) - this.Byb.x;
        this.Byb.height = Math.min(this.E.y + this.E.height, e.y + e.height) - this.Byb.y;
        if (this.Byb.width == this.Byb.height) {
            if (this.Byb.x == this.E.x && this.Byb.y == this.E.y) {
                this._(-1, -1, g);
                return;
            }
            final int n = this.E.x + this.E.width - this.Byb.width;
            final int n2 = this.E.y + this.E.height - this.Byb.height;
            if (this.Byb.x == n && this.Byb.y == this.E.y) {
                this._(1, -1, g);
                return;
            }
            if (this.Byb.x == n && this.Byb.y == n2) {
                this._(1, 1, g);
                return;
            }
            if (this.Byb.x == this.E.x && this.Byb.y == n2) {
                this._(-1, 1, g);
            }
        }
        else if (this.Byb.width > this.Byb.height) {
            if (g.E.y < this.E.y) {
                this._(1, g.F, false, g);
                return;
            }
            this._(-1, g.F, false, g);
        }
        else {
            if (g.E.x < this.E.x) {
                this._(1, g.ryb, true, g);
                return;
            }
            this._(-1, g.ryb, true, g);
        }
    }
    
    private void _(final int n, final int n2, final g g) {
        if ((n < 0 && g.ryb > 0) || (n > 0 && g.ryb < 0)) {
            g.e();
        }
        if ((n2 < 0 && g.F > 0) || (n2 > 0 && g.F < 0)) {
            g.f();
        }
    }
    
    private void _(final int n, final int n2, final boolean b, final g g) {
        if (b) {
            if ((n < 0 && n2 < 0) || (n > 0 && n2 > 0)) {
                g.e();
                return;
            }
            g.f();
        }
        else {
            if ((n < 0 && n2 > 0) || (n > 0 && n2 < 0)) {
                g.e();
                return;
            }
            g.f();
        }
    }
}
