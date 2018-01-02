import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dM extends rp_dt
{
    private boolean a;
    private Point a;
    private rp_c a;
    private int a;
    
    public rp_dM(final rp_c a, final int n) {
        super(rp_au.a("Act_DelW"));
        this.a = true;
        if (n == 1) {
            this.a = a.b();
            this.a = a.f;
            this.a = a;
            return;
        }
        this.a = a.c();
        this.a = a.h;
        this.a = a(a.g);
    }
    
    public rp_dM(final rp_c rp_c, final Point a) {
        super(rp_au.a("Act_InsW"));
        this.a = false;
        this.a = a;
        this.a = rp_c.h;
        this.a = new rp_c(rp_dM.a.a(), rp_dM.a, this.a.x, this.a.y, rp_c.c().x, rp_c.c().y, rp_c.a);
        this.a.f = this.a;
        this.a.g = rp_c.g;
    }
    
    public final void a() {
        if (this.a) {
            this.c();
            return;
        }
        this.d();
    }
    
    public final void b() {
        if (this.a) {
            this.d();
            return;
        }
        this.c();
    }
    
    private void c() {
        final rp_c a;
        if ((a = a(this.a)) != null && this.a != null) {
            a.a(this.a.c());
            a.g = this.a.g;
            rp_dM.a.d(a);
            final rp_c a2;
            if ((a2 = a(this.a.g)) != null) {
                a2.f = this.a;
            }
            rp_dM.a.c(this.a);
            rp_dM.a.d(a2);
            rp_dM.a.b();
        }
    }
    
    private void d() {
        final rp_c a;
        if ((a = a(this.a)) != null) {
            a.g = this.a.h;
            a.a(this.a);
            rp_dM.a.d(a);
            final rp_c a2;
            if ((a2 = a(this.a.g)) != null) {
                a2.f = this.a.h;
            }
            rp_dM.a.a((rp_dC)this.a);
            rp_dM.a.d(this.a);
            rp_dM.a.d(a2);
            rp_dM.a.b();
        }
    }
    
    static rp_c a(final int n) {
        final rp_dC a;
        if ((a = rp_dM.a.a(n)) != null && a.a(4096)) {
            return (rp_c)a;
        }
        return null;
    }
}
