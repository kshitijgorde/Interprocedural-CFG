// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import b.a.a.e;
import b.a.a.c;
import java.util.Vector;
import org.shetline.astronomy.StarCatalog;
import b.a.a.b;

public class o extends b implements a
{
    protected r a;
    protected StarCatalog b;
    
    public o() {
        this(null, null);
    }
    
    public o(final r a, final StarCatalog b) {
        if (a == null) {
            this.a = new r();
        }
        else {
            this.a = a;
        }
        if (b == null) {
            try {
                this.b = new StarCatalog();
                if (!StarCatalog.isProperlyInitialized()) {
                    this.b = null;
                }
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                this.b = null;
            }
        }
        else {
            this.b = b;
        }
    }
    
    public b.a.c.b a(final int n, final int n2, final int n3, final q q) {
        int n4 = 0;
        int b = 1440;
        if (q != null) {
            n4 = q.e() + q.a(n, n2, n3);
            b = q.b(n, n2, n3);
        }
        final double a = k.a(n, n2, n3, 0, -n4, -30.0);
        final double a2 = k.a(n, n2, n3, 0, b - n4, -30.0);
        final double b2 = j.b(a);
        final double b3 = j.b(a2);
        double t = this.a.t(b2);
        double t2 = this.a.t(b3);
        double n5 = 0.0;
        boolean b4 = false;
        int n6 = -1;
        if (t > 315.0) {
            t -= 360.0;
        }
        if (t2 > 315.0) {
            t2 -= 360.0;
        }
        do {
            final double n7 = ++n6 * 90.0;
            if (t <= n7 && n7 < t2) {
                b4 = true;
                n5 = (new s(this, this.a, 1.0E-4, 6, b2, t - n7, b3, t2 - n7, n7).a() - b2) * 24.0;
            }
        } while (n6 < 3 && !b4);
        if (!b4) {
            return null;
        }
        return this.a(n, n2, n3, b, n5, 0 + n6, 0.0, q);
    }
    
    public Vector a(final int n, final int n2, final int n3, final int n4, final q q) {
        return this.a(n, n2, n3, n4, q, false);
    }
    
    public Vector a(final int n, final int n2, final int n3, final int n4, final q q, final boolean b) {
        double n5 = -0.5833;
        if (n == 0 || n == 10) {
            n5 -= 0.25;
        }
        return this.a(n, n2, n3, n4, q, b, n5);
    }
    
    public Vector a(final int n, final int n2, final int n3, final int n4, final q q, final boolean b, final double n5) {
        final boolean b2 = n == 0 && n5 <= -6.0;
        final Vector vector = new Vector<b.a.c.b>();
        double a = k.a(n2, n3, n4, 0, -q.e() - q.a(n2, n3, n4), -30.0);
        final int b3 = q.b(n2, n3, n4);
        final double n6 = b3 / 1440.0;
        int n7 = 6;
        if (n == 10) {
            n7 *= 2;
        }
        if (b.a(q.d().b()) > 60.0) {
            n7 *= 2;
        }
        if (b) {
            a += 6.944444444444445E-4;
        }
        double n8 = a;
        double b4 = this.a(n, n8, q).c().b();
        double n9 = -90.0;
        for (int i = 1; i <= n7; ++i) {
            if (i == n7 / 2) {
                n9 = b4;
            }
            double n10 = a + i / n7 * n6;
            final double b6;
            double b5 = b6 = this.a(n, n10, q).c().b();
            int n11;
            if ((b.a(b4 - n5) < 1.0 || b.a(b5 - n5) < 1.0) && b.a(b4 - b5) < 2.0) {
                n11 = 10;
            }
            else {
                n11 = 1;
            }
            for (int j = 1; j <= n11; ++j) {
                if (n11 > 1) {
                    if (j < n11) {
                        n10 = a + (i - 1.0 + j / n11) / n7 * n6;
                        b5 = this.a(n, n10, q).c().b();
                    }
                    else {
                        n10 = a + i / n7 * n6;
                        b5 = b6;
                    }
                }
                if ((b4 <= n5 && n5 < b5) || (b5 < n5 && n5 <= b4)) {
                    int n12;
                    if (b4 < b5) {
                        n12 = (b2 ? 205 : 200);
                    }
                    else {
                        n12 = (b2 ? 206 : (b ? 207 : 201));
                    }
                    final double a2 = new t(this, q, 0.001, 8, n8, b4 - n5, n10, b5 - n5, n, n5).a();
                    vector.addElement(this.a(n2, n3, n4, b3, (a2 - a) * 24.0, n12, a2, q));
                }
                n8 = n10;
                b4 = b5;
            }
        }
        if (!b2 && vector.size() == 0) {
            if (n9 > n5) {
                vector.addElement(new b.a.c.b(n2, n3, n4, 0, 0, 202, 0.0));
            }
            else {
                vector.addElement(new b.a.c.b(n2, n3, n4, 0, 0, 203, 0.0));
            }
        }
        return vector;
    }
    
    public c a(final int n, final double n2, final q q) {
        if (n >= 0) {
            return this.a.a(n, n2, q);
        }
        if (this.b != null) {
            return this.b.getHorizontalPosition(-(n + 1), n2, q);
        }
        return null;
    }
    
    protected b.a.c.b a(final int n, final int n2, final int n3, final int n4, double n5, final int n6, final double n7, final q q) {
        if (n5 < 0.0) {
            n5 = 0.0;
        }
        else if (n5 >= n4 / 60.0) {
            n5 = n4 / 60.0 - 0.01;
        }
        final int c = e.c(n5);
        return new b.a.c.b(n, n2, n3, c, e.c((n5 - c) * 60.0), n6, n7, q);
    }
}
