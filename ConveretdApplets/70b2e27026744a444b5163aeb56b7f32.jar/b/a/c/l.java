// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import b.a.a.d;
import b.a.a.c;
import b.a.a.e;
import b.a.a.b;

public class l extends b implements a
{
    protected double a;
    protected double b;
    protected b.a.a.a[] c;
    protected static f[] d;
    protected static final double[] e;
    
    public l() {
        this.b = -1.0;
    }
    
    public b.a.a.a[] r(final double n) {
        return this.a(n, 0);
    }
    
    public synchronized b.a.a.a[] a(final double a, final int n) {
        if (this.a == a && this.b == n) {
            return this.c;
        }
        final double n2 = (a - 2451545.0) / 36525.0;
        final b.a.a.a[] c = new b.a.a.a[3];
        if (n == 2) {
            c[0] = new b.a.a.a(0.0);
            c[1] = new b.a.a.a(0.0);
            c[2] = new b.a.a.a(23.43929111, 1);
        }
        else {
            double n3 = n2 / 100.0;
            double n4 = 23.43929111;
            for (int i = 0; i < l.e.length; ++i) {
                n4 += l.e[i] * n3 / 3600.0;
                n3 *= n3;
            }
            c[2] = new b.a.a.a(n4, 1);
            if (n == 1) {
                c[0] = new b.a.a.a(0.0);
                c[1] = new b.a.a.a(0.0);
            }
            else {
                final double n5 = n2 * n2;
                final double n6 = n5 * n2;
                final double n7 = 297.85036 + 445267.11148 * n2 - 0.0019142 * n5 + n6 / 189474.0;
                final double n8 = 357.52772 + 35999.05034 * n2 - 1.603E-4 * n5 - n6 / 300000.0;
                final double n9 = 134.96298 + 477198.867398 * n2 + 0.0086972 * n5 + n6 / 56250.0;
                final double n10 = 93.27191 + 483202.017538 * n2 + 0.0036825 * n5 + n6 / 327270.0;
                final double n11 = 125.04452 - 1934.136261 * n2 + 0.0020708 * n5 + n6 / 450000.0;
                double n12 = 0.0;
                double n13 = 0.0;
                for (int j = 0; j < l.d.length; ++j) {
                    final f f = l.d[j];
                    final double n14 = n7 * f.a + n8 * f.b + n9 * f.c + n10 * f.d + n11 * f.e;
                    n12 += b.a.a.e.g(n14) * (f.f + f.g * n2);
                    n13 += b.a.a.e.b(n14) * (f.h + f.i * n2);
                }
                c[0] = new b.a.a.a(n12 / 10000.0, 3);
                c[1] = new b.a.a.a(n13 / 10000.0, 3);
                c[2] = c[2].a(c[1]);
            }
        }
        this.a = a;
        this.b = n;
        return this.c = c;
    }
    
    public c a(final c c, final double n, final int n2) {
        if (n2 == 2) {
            return c;
        }
        b.a.a.a f = this.a(n, (n2 == 3) ? 0 : n2)[0];
        if (n2 == 3) {
            f = f.f();
        }
        return new c(c.a().b(f), c.d());
    }
    
    public d a(final d d, final double n) {
        return this.a(d, n, 0);
    }
    
    public d a(final d d, final double n, final int n2) {
        if (n2 == 2) {
            return d;
        }
        return new d(this.a((c)d, n, n2), d.f());
    }
    
    public c b(final c c, final double n, final int n2) {
        final b.a.a.a[] a = this.a(n, n2);
        final b.a.a.a b = c.b();
        final b.a.a.a e = c.e();
        final b.a.a.a a2 = a[2];
        return new c(b.a.a.a.b(b.c() * a2.d() - e.e() * a2.c(), b.d()), b.a.a.a.a(b.a.a.b.i(e.c() * a2.d() + e.d() * a2.c() * b.c())));
    }
    
    public d b(final d d, final double n, final int n2) {
        return new d(this.b((c)d, n, n2), d.f());
    }
    
    public c c(final c c, final double n, final int n2) {
        final b.a.a.a[] a = this.a(n, n2);
        final b.a.a.a b = c.b();
        final b.a.a.a e = c.e();
        final b.a.a.a a2 = a[2];
        return new c(b.a.a.a.b(b.c() * a2.d() + e.e() * a2.c(), b.d()), b.a.a.a.a(b.a.a.b.i(e.c() * a2.d() - e.d() * a2.c() * b.c())));
    }
    
    public d c(final d d, final double n, final int n2) {
        return new d(this.c((c)d, n, n2), d.f());
    }
    
    public static c a(final c c, final double n) {
        return a(c, 2451545.0, n);
    }
    
    public static c a(final c c, final double n, final double n2) {
        final double n3 = (n - 2451545.0) / 36525.0;
        final double n4 = n3 * n3;
        final double n5 = (n2 - n) / 36525.0;
        final double n6 = n5 * n5;
        final double n7 = n6 * n5;
        final double a = c.a().a();
        final double a2 = c.d().a();
        final double n8 = (47.0029 - 0.06603 * n3 + 5.98E-4 * n4) * n5 + (-0.03302 + 5.98E-4 * n3) * n6 + 6.0E-5 * n7;
        final double n9 = 629554.9824 + 3289.4789 * n3 + 0.60622 * n4 - (869.8089 + 0.50491 * n3) * n5 + 0.03536 * n6;
        final double n10 = (5029.0966 + 2.22226 * n3 - 4.2E-5 * n4) * n5 + (1.11113 - 4.2E-5 * n3) * n6 - 6.0E-6 * n7;
        final double n11 = n8 * 4.84813681109536E-6;
        final double n12 = n9 * 4.84813681109536E-6;
        return new c(n10 * 4.84813681109536E-6 + n12 - b.a(b.e(n11) * b.e(a2) * b.k(n12 - a) - b.k(n11) * b.k(a2), b.e(a2) * b.e(n12 - a)), b.b(b.i(b.e(n11) * b.k(a2) + b.k(n11) * b.e(a2) * b.k(n12 - a))));
    }
    
    static {
        e = new double[] { -4680.93, -1.55, 1999.25, -51.38, -249.67, -39.05, 7.12, 27.87, 5.79, 2.45 };
        final Vector vector = new Vector<f>();
        int n = 0;
        int index;
        while ((index = "0 0 0 0 1 -171996 -174.2T 92025 8.9T\n-2 0 0 2 2 -13187 -1.6T 5736 -3.1T\n0 0 0 2 2 -2274 -0.2T 977 -0.5T\n0 0 0 0 2 2062 0.2T -895 0.5T\n0 1 0 0 0 1426 -3.4T 54 -0.1T\n0 0 1 0 0 712 0.1T -7\n-2 1 0 2 2 -517 1.2T 224 -0.6T\n0 0 0 2 1 -386 -0.4T 200\n0 0 1 2 2 -301 129 -0.1T\n-2 -1 0 2 2 217 -0.5T -95 0.3T\n-2 0 1 0 0 -158\n-2 0 0 2 1 129 0.1T -70\n0 0 -1 2 2 123 -53\n2 0 0 0 0 63\n0 0 1 0 1 63 0.1T -33\n2 0 -1 2 2 -59 26\n0 0 -1 0 1 -58 -0.1T 32\n0 0 1 2 1 -51 27\n-2 0 2 0 0 48\n0 0 -2 2 1 46 -24\n2 0 0 2 2 -38 16\n0 0 2 2 2 -31 13\n0 0 2 0 0 29\n-2 0 1 2 2 29 -12\n0 0 0 2 0 26\n-2 0 0 2 0 -22\n0 0 -1 2 1 21 -10\n0 2 0 0 0 17 -0.1T\n2 0 -1 0 1 16 -8\n-2 2 0 2 2 -16 0.1T 7\n0 1 0 0 1 -15 9\n-2 0 1 0 1 -13 7\n0 -1 0 0 1 -12 6\n0 0 2 -2 0 11\n2 0 -1 2 1 -10 5\n2 0 1 2 2 -8 3\n0 1 0 2 2 7 -3\n-2 1 1 0 0 -7\n0 -1 0 2 2 -7 3\n2 0 0 2 1 -7 3\n2 0 1 0 0 6\n-2 0 2 2 2 6 -3\n-2 0 1 2 1 6 -3\n2 0 -2 0 1 -6 3\n2 0 0 0 1 -6 3\n0 -1 1 0 0 5\n-2 -1 0 2 1 -5 3\n-2 0 0 0 1 -5 3\n0 0 2 2 1 -5 3\n-2 0 2 0 1 4\n-2 1 0 2 1 4\n0 0 1 -2 0 4\n-1 0 1 0 0 -4\n-2 1 0 0 0 -4\n1 0 0 0 0 -4\n0 0 1 2 0 3\n0 0 -2 2 2 -3\n-1 -1 1 0 0 -3\n0 1 1 0 0 -3\n0 -1 1 2 2 -3\n2 -1 -1 2 2 -3\n0 0 3 2 2 -3\n2 -1 0 2 2 -3\n".indexOf(10, n)) >= 0) {
            final String substring = "0 0 0 0 1 -171996 -174.2T 92025 8.9T\n-2 0 0 2 2 -13187 -1.6T 5736 -3.1T\n0 0 0 2 2 -2274 -0.2T 977 -0.5T\n0 0 0 0 2 2062 0.2T -895 0.5T\n0 1 0 0 0 1426 -3.4T 54 -0.1T\n0 0 1 0 0 712 0.1T -7\n-2 1 0 2 2 -517 1.2T 224 -0.6T\n0 0 0 2 1 -386 -0.4T 200\n0 0 1 2 2 -301 129 -0.1T\n-2 -1 0 2 2 217 -0.5T -95 0.3T\n-2 0 1 0 0 -158\n-2 0 0 2 1 129 0.1T -70\n0 0 -1 2 2 123 -53\n2 0 0 0 0 63\n0 0 1 0 1 63 0.1T -33\n2 0 -1 2 2 -59 26\n0 0 -1 0 1 -58 -0.1T 32\n0 0 1 2 1 -51 27\n-2 0 2 0 0 48\n0 0 -2 2 1 46 -24\n2 0 0 2 2 -38 16\n0 0 2 2 2 -31 13\n0 0 2 0 0 29\n-2 0 1 2 2 29 -12\n0 0 0 2 0 26\n-2 0 0 2 0 -22\n0 0 -1 2 1 21 -10\n0 2 0 0 0 17 -0.1T\n2 0 -1 0 1 16 -8\n-2 2 0 2 2 -16 0.1T 7\n0 1 0 0 1 -15 9\n-2 0 1 0 1 -13 7\n0 -1 0 0 1 -12 6\n0 0 2 -2 0 11\n2 0 -1 2 1 -10 5\n2 0 1 2 2 -8 3\n0 1 0 2 2 7 -3\n-2 1 1 0 0 -7\n0 -1 0 2 2 -7 3\n2 0 0 2 1 -7 3\n2 0 1 0 0 6\n-2 0 2 2 2 6 -3\n-2 0 1 2 1 6 -3\n2 0 -2 0 1 -6 3\n2 0 0 0 1 -6 3\n0 -1 1 0 0 5\n-2 -1 0 2 1 -5 3\n-2 0 0 0 1 -5 3\n0 0 2 2 1 -5 3\n-2 0 2 0 1 4\n-2 1 0 2 1 4\n0 0 1 -2 0 4\n-1 0 1 0 0 -4\n-2 1 0 0 0 -4\n1 0 0 0 0 -4\n0 0 1 2 0 3\n0 0 -2 2 2 -3\n-1 -1 1 0 0 -3\n0 1 1 0 0 -3\n0 -1 1 2 2 -3\n2 -1 -1 2 2 -3\n0 0 3 2 2 -3\n2 -1 0 2 2 -3\n".substring(n, index);
            n = index + 1;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(substring);
                final double[] array = new double[9];
                int n2 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    String s = stringTokenizer.nextToken();
                    boolean b;
                    if (s.endsWith("T")) {
                        b = true;
                        s = s.substring(0, s.length() - 1);
                    }
                    else {
                        b = false;
                    }
                    if (n2 == 6 && !b) {
                        ++n2;
                    }
                    array[n2] = Double.parseDouble(s);
                    ++n2;
                }
                final f f = new f();
                f.a = array[0];
                f.b = array[1];
                f.c = array[2];
                f.d = array[3];
                f.e = array[4];
                f.f = array[5];
                f.g = array[6];
                f.h = array[7];
                f.i = array[8];
                vector.addElement(f);
            }
            catch (NoSuchElementException ex) {}
            catch (NumberFormatException ex2) {}
        }
        l.d = new f[vector.size()];
        for (int i = 0; i < l.d.length; ++i) {
            l.d[i] = vector.elementAt(i);
        }
    }
}
