import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class g
{
    protected Rectangle E;
    protected int ryb;
    protected int F;
    protected int syb;
    
    public g(final int n, final int n2, final int n3, final int n4) {
        this.E = new Rectangle(n, n2, n3, n4);
    }
    
    public void h() {
        final Rectangle e = this.E;
        e.x += this.ryb;
        final Rectangle e2 = this.E;
        e2.y += this.F;
    }
    
    public void b(final Point point) {
        if (this.ryb < 0) {
            point.x *= -1;
        }
        if (this.F < 0) {
            point.y *= -1;
        }
        this.ryb = point.x;
        this.F = point.y;
    }
    
    public void e() {
        this.ryb *= -1;
        this.i();
    }
    
    public void f() {
        this.F *= -1;
        this.j();
    }
    
    public void g() {
        this.e();
        this.f();
    }
    
    private void i() {
        if (this.ryb > 0) {
            final Rectangle e = this.E;
            e.x += this.ryb + this.syb++ % 4;
            return;
        }
        final Rectangle e2 = this.E;
        e2.x += this.ryb - this.syb++ % 4;
    }
    
    private void j() {
        if (this.F > 0) {
            final Rectangle e = this.E;
            e.y += this.F + this.syb++ % 4;
            return;
        }
        final Rectangle e2 = this.E;
        e2.y += this.F - this.syb++ % 4;
    }
}
