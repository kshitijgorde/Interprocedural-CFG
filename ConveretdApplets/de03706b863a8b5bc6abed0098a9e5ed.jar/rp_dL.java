import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dL extends rp_dt
{
    private rp_c a;
    private rp_c b;
    private int a;
    private int b;
    
    public rp_dL(final rp_c rp_c, final int n, final int a, final int b) {
        super(rp_au.a("Act_MoveW"));
        if (n == 1) {
            this.a = rp_c;
            this.b = rp_dM.a(rp_c.f);
        }
        else {
            this.a = rp_dM.a(rp_c.g);
            this.b = rp_c;
        }
        this.a = a;
        this.b = b;
    }
    
    public final void a() {
        this.a(true);
    }
    
    public final void b() {
        this.a(false);
    }
    
    private void a(final boolean b) {
        final int n = b ? 1 : -1;
        final Point b2;
        final Point point = b2 = this.a.b();
        point.x += n * this.a;
        final Point point2 = b2;
        point2.y += n * this.b;
        final rp_c a = this.a;
        final Point point3 = b2;
        final rp_c rp_c = a;
        a.b = point3.x;
        rp_c.c = point3.y;
        rp_c.b();
        final Point c;
        final Point point4 = c = this.b.c();
        point4.x += n * this.a;
        final Point point5 = c;
        point5.y += n * this.b;
        this.b.a(c);
        rp_dL.a.d(this.a);
        rp_dL.a.d(this.b);
    }
}
