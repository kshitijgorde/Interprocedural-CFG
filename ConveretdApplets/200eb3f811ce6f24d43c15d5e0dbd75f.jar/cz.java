import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class cz extends bd implements ay
{
    public cz(final az az, final Vector vector, final bc bc, final String s, final long n, final String s2) {
        super(az, vector, bc, s, n, s2);
    }
    
    public boolean a(final bg bg, final int n, final bb bb, final du du) {
        if (ay.a.k()) {
            ay.a.i(super.c.as() + " accessing MDGObjects " + super.a);
        }
        if (du.g) {
            final a1 i = super.c.i();
            ++i.m;
            final a1 j = super.c.i();
            j.p += du.e;
            final a1 k = super.c.i();
            k.q += du.d;
        }
        else {
            final a1 l = super.c.i();
            ++l.n;
            final a1 m = super.c.i();
            m.o += du.d;
        }
        final a1 i2 = super.c.i();
        i2.r += du.f;
        final cw cw = super.a.elementAt(n);
        if (ay.a.k()) {
            ay.a.i(super.c.as() + " try to init " + cw + " with " + bg);
        }
        try {
            cw.a(bg, du.b().length, du.a().i(), du);
        }
        catch (Exception ex) {
            if (ay.a.g()) {
                ay.a.b(super.c.as() + " setting status of " + cw.i() + " to status uninitialized bacause of ", ex);
            }
            cw.q.a(-5);
        }
        return true;
    }
}
