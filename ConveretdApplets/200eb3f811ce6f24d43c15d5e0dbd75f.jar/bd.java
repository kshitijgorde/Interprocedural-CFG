import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bd extends be
{
    public Vector a;
    public bc b;
    public az c;
    private int d;
    private int e;
    private int f;
    
    public bd(final az az, final Vector vector, final bc bc, final long n, final String s) {
        this(az, vector, bc, null, n, s);
    }
    
    public bd(final az c, final Vector a, final bc b, final String s, long n, final String s2) {
        this.d = 0;
        this.e = 0;
        this.f = 1;
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = 1;
        final int n2 = 64000;
        final Integer d = c.aj().d("MAX_NESTED_POST_REQUEST_LENGTH");
        final int n3 = (d != null) ? d : n2;
        if (n3 < n2 && ak.a.i()) {
            ak.a.g("maxPOSTRequestLength (" + n3 + ") is less than recommended " + n2 + " bytes");
        }
        final Vector vector = new Vector(a.size());
        boolean b2 = !c.aj().c("ENABLE_POST");
        n = 0L;
        for (int i = 0; i < a.size(); ++i) {
            final a4 a2 = a.elementAt(i);
            final a5 j = a2.j();
            if (j.e() >= n3) {
                if (ak.a.k()) {
                    ak.a.i(c.as() + " enabling single mode for " + j.toString());
                }
                b2 = true;
            }
            vector.addElement(j);
            final int a3 = c.a(a2);
            if (ak.a.k()) {
                ak.a.i(c.as() + "RequestCollector got uri " + j.toString() + " and timeout " + a3);
            }
            if (a3 > n) {
                n = a3;
            }
        }
        if (ak.a.k()) {
            ak.a.i(c.as() + " try to call reqHandler.get()");
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (b2) {
            this.f = 2;
            if (ak.a.k()) {
                ak.a.i(c.as() + " switching to single mode because of at least one oversized request");
            }
            for (int k = 0; k < vector.size(); ++k) {
                b.a(vector.elementAt(k), this, k, s, c.a(a.elementAt(k)), s2);
            }
        }
        else {
            b.a(vector, this, s, n, s2);
        }
        final a1 l = c.i();
        l.t += (int)(System.currentTimeMillis() - currentTimeMillis);
    }
    
    public boolean a(final bg bg, final int n, final bb bb, final du du) {
        if (ak.a.k()) {
            ak.a.i(this.c.as() + " accessing MDGObjects " + this.a + " at " + n);
        }
        if (du.g) {
            final a1 i = this.c.i();
            ++i.m;
            final a1 j = this.c.i();
            j.p += du.e;
            final a1 k = this.c.i();
            k.q += du.d;
        }
        else {
            final a1 l = this.c.i();
            ++l.n;
            final a1 m = this.c.i();
            m.o += du.d;
        }
        final a1 i2 = this.c.i();
        i2.r += du.f;
        final a4 a4 = this.a.elementAt(n);
        if (a4.r() == -1) {
            if (ak.a.k()) {
                ak.a.i(this.c.as() + " try to init " + a4 + " with " + bg);
            }
            try {
                a4.a(bg, du.b().length, du.a().i());
                if (!a4.f()) {
                    ++this.e;
                }
            }
            catch (Exception ex) {
                if (ak.a.g()) {
                    ak.a.b(this.c.as() + " setting status of " + a4.i() + " to status uninitialized bacause of ", ex);
                }
                a4.q.a(-5);
                ++this.e;
            }
        }
        return true;
    }
    
    public void a(final Exception ex, int n, final String s) {
        if (this.c.al()) {
            return;
        }
        am am;
        if (ex instanceof am) {
            am = (am)ex;
        }
        else if (s != null) {
            am = new am(s, ex);
        }
        else {
            am = new am(ex);
        }
        if (n < 0 || this.f == 2) {
            if (n < 0) {
                n += Integer.MAX_VALUE;
            }
            final a4 a4 = this.a.elementAt(n);
            if (a4.r() == -1) {
                if (ak.a.k()) {
                    ak.a.i(this.c.as() + " initing " + a4.i() + " with " + ex);
                }
                a4.a(am);
                ++this.d;
            }
        }
        else {
            for (int i = n; i < this.a.size(); ++i) {
                final a4 a5 = this.a.elementAt(i);
                if (a5.r() == -1) {
                    if (ak.a.k()) {
                        ak.a.i(this.c.as() + " initing " + a5.i() + " with " + ex);
                    }
                    a5.a(am);
                    ++this.d;
                }
            }
        }
    }
    
    public int a() {
        return this.d;
    }
    
    public int b() {
        return this.e;
    }
    
    public boolean c() {
        return true;
    }
    
    public final void b(final Exception ex, final int n, final String s) {
    }
}
