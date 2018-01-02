// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import b.a.a.c;
import b.a.a.d;
import org.shetline.astronomy.Pluto;
import org.shetline.astronomy.AdditionalOrbitingObjects;
import b.a.a.b;

public class r extends b implements a
{
    protected l a;
    protected m b;
    protected AdditionalOrbitingObjects c;
    protected p d;
    protected Pluto e;
    protected String[] f;
    protected static final double[][][] g;
    
    public r() {
        this.a = new l();
        this.f = k.c("PLANET_NAMES");
        this.d = null;
        this.b = new n();
    }
    
    public d a(final int n, final double n2, final int n3) {
        final int n4 = n3 & 0xFFFFFFFE & 0xFFFFFFFD;
        d d;
        if (1 <= n && n <= 8) {
            if (this.d != null && (n3 & 0x40) == 0x0) {
                d = this.d.a(n, n2, this.e(n, n3));
            }
            else {
                d = this.a(this.c(n, n2));
            }
        }
        else {
            if (n == 0) {
                return new d();
            }
            if (n == 10) {
                d = this.b(10, n2, n4).a(this.b(0, n2, n4));
            }
            else if (n == 9) {
                if (this.e != null && (n3 & 0x40) == 0x0) {
                    d = this.e.getHeliocentricPosition(n2, this.e(9, n3));
                }
                else {
                    d = this.a(this.c(n, n2));
                }
            }
            else {
                if (20000 < n && n <= 39999) {
                    return this.c.getHeliocentricPosition(n, n2);
                }
                return null;
            }
        }
        return d;
    }
    
    protected double e(final int n, final int n2) {
        if ((n2 & 0x2) != 0x0 || n == 9) {
            return 0.0;
        }
        if (n == 10) {
            return ((n2 & 0x1) != 0x0) ? 0.1 : 0.01;
        }
        return 0.0;
    }
    
    public d b(final int n, final double n2, final int n3) {
        return this.a(n, n2, null, n3);
    }
    
    public d a(final int n, final double n2, final q q, final int n3) {
        return this.a(n, n2, n2, q, n3);
    }
    
    protected d a(final int n, final double n2, final double n3, final q q, final int n4) {
        if ((n4 & 0x8) != 0x0 && q != null) {
            return this.a.c(this.b(n, n2, q, n4), n2, ((n4 & 0x4) != 0x0) ? 0 : 2);
        }
        if (n == 3) {
            return new d();
        }
        d d;
        if (n == 10) {
            d = this.b.d(n2, this.e(10, n4));
        }
        else if (n == 0 && (n4 & 0x20) != 0x0) {
            final double n5 = (n2 - 2451545.0) / 36525.0;
            final double n6 = n5 * n5;
            final double n7 = 0.016708634 - 4.2037E-5 * n5 - 1.267E-7 * n6;
            final double n8 = 280.46646 + 36000.76983 * n5 + 3.032E-4 * n6;
            final double n9 = 357.52911 + 35999.05029 * n5 - 1.537E-4 * n6;
            final double n10 = (1.914602 - 0.004817 * n5 - 1.4E-5 * n6) * b.a.a.b.l(n9) + (0.019993 - 1.01E-4 * n5) * b.a.a.b.l(2.0 * n9) + 2.89E-4 * b.a.a.b.l(3.0 * n9);
            d = new d(b.a.a.b.b(n8 + n10, 360.0), 1, 0.0, 0, 1.000001018 * (1.0 - n7 * n7) / (1.0 + n7 * b.a.a.b.f(n9 + n10)));
        }
        else {
            if ((0 > n || n > 9) && (20000 >= n || n > 39999)) {
                return null;
            }
            final d a = this.a(3, n3, n4);
            final d a2 = this.a(n, n2, n4);
            if (a2 == null) {
                return null;
            }
            d = a2.a(a);
        }
        if ((n4 & 0x80) != 0x0 || (n4 & 0x100) != 0x0 || (n4 & 0x400) != 0x0) {
            d d2 = null;
            double n11 = d.f();
            double n12 = n2;
            final int n13 = n4 & 0xFFFFFF7F & 0xFFFFFEFF & 0xFFFFFBFF & 0xFFFFFFFB;
            for (int i = 0; i < ((n == 10) ? 1 : 3); ++i) {
                n12 = n2 - 0.005775518328 * n11;
                if ((n4 & 0x100) != 0x0) {
                    d2 = this.a(n, n12, n3, null, n13);
                }
                else {
                    d2 = this.a(n, n12, n12, null, n13);
                }
                n11 = d2.f();
            }
            if ((n4 & 0x200) != 0x0) {
                d = new d(d2.a(), d2.d(), d.f());
            }
            else if ((n4 & 0x400) != 0x0) {
                d = new d(d2.a(), d2.d(), n12);
            }
            else {
                d = d2;
            }
        }
        if ((n4 & 0x4) != 0x0) {
            d = this.a.a(d, n2);
        }
        return d;
    }
    
    public d b(final int n, final double n2, final q q, final int n3) {
        if (n == 3) {
            return new d();
        }
        int n4;
        if ((n3 & 0x4) != 0x0) {
            n4 = 0;
        }
        else {
            n4 = 1;
        }
        d d = this.a.b(this.a(n, n2, null, n3 & 0xFFFFFFF7), n2, n4);
        if ((n3 & 0x8) != 0x0 && q != null) {
            d = q.a(d, n2, n3);
        }
        return d;
    }
    
    public static double r(final double n) {
        final double n2 = n - 2451545.0;
        final double n3 = n2 / 36525.0;
        return b.b(280.46061837 + 360.98564736629 * n2 + 3.87933E-4 * n3 * n3 - n3 * n3 * n3 / 3.871E7, 360.0);
    }
    
    public double s(final double n) {
        final double r = r(n);
        final b.a.a.a[] r2 = this.a.r(j.b(n));
        return b.a.a.b.b(r + r2[0].b() * r2[2].d(), 360.0);
    }
    
    public d a(final int n, final double n2, final q q) {
        return this.c(n, n2, q, 129);
    }
    
    public d c(final int n, final double n2, final q q, int n3) {
        if (n < 0 || (n > 10 && n <= 20000) || n > 39999 || q == null) {
            return null;
        }
        n3 &= 0xFFFFFFFB;
        if (n == 10) {
            n3 |= 0x8;
        }
        return q.b(this.b(n, j.b(n2), q, n3), n2, n3);
    }
    
    public double t(final double n) {
        return b.a.a.b.b(this.b(10, n, 129).a().b() - this.b(0, n, 129).a().b(), 360.0);
    }
    
    protected double a(final int n, final double n2) {
        final double f = this.a(n, n2, 1).f();
        final double f2 = this.b(n, n2, 129).f();
        final double f3 = this.a(3, n2, 1).f();
        double n3 = (f * f + f2 * f2 - f3 * f3) / (2.0 * f * f2);
        if (n3 < -1.0) {
            n3 = -1.0;
        }
        else if (n3 > 1.0) {
            n3 = 1.0;
        }
        return n3;
    }
    
    public double b(final int n, final double n2) {
        if (n <= 0 || n == 3 || n > 10) {
            return 0.0;
        }
        return (1.0 + this.a(n, n2)) / 2.0;
    }
    
    public i c(final int n, final double n2) {
        if (n < 1 || n > 9) {
            return null;
        }
        final int n3 = n - 1;
        final i i = new i();
        final double n4 = (n2 - 2451545.0) / 36525.0;
        double n5 = 1.0;
        final double[] array = new double[6];
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < 6; ++k) {
                final double[] array2 = array;
                final int n6 = k;
                array2[n6] += r.g[n3][k][j] * n5;
            }
            n5 *= n4;
        }
        i.a = b.a.a.b.b(array[0], 360.0);
        i.b = array[1];
        i.c = array[2];
        i.d = array[3];
        i.e = b.a.a.b.b(array[4], 360.0);
        i.f = b.a.a.b.b(array[5], 360.0);
        if (n == 9) {
            final double b = l.a(new c(), n2).a().b();
            i.a = b.a.a.b.b(i.a + b, 360.0);
            i.e = b.a.a.b.b(i.e + b, 360.0);
            i.f = b.a.a.b.b(i.f + b, 360.0);
        }
        i.g = b.a.a.b.b(i.f - i.e, 360.0);
        i.h = b.a.a.b.b(i.a - i.f, 360.0);
        double q;
        double n8;
        final double n7 = n8 = (q = b.a.a.b.q(i.h));
        for (int l = 0; l < 100; ++l) {
            n8 = n7 + i.c * b.a.a.b.k(q);
            if (b.a.a.b.a(b.a.a.b.c(n8 - q, 6.283185307179586)) < 1.0E-6) {
                break;
            }
            q = n8;
        }
        i.j = b.a.a.b.b(2.0 * b.a.a.b.d(b.a.a.b.m((1.0 + i.c) / (1.0 - i.c)) * b.a.a.b.n(n8 / 2.0)), 360.0);
        i.i = b.a.a.b.b(i.j - i.h, 360.0);
        return i;
    }
    
    public d a(final i i) {
        final double f = b.a.a.b.f(i.d);
        final double l = b.a.a.b.l(i.d);
        final double f2 = b.a.a.b.f(i.e);
        final double j = b.a.a.b.l(i.e);
        final double n = i.b * (1.0 - i.c * i.c) / (1.0 + i.c * b.a.a.b.f(i.j));
        final double n2 = i.j + i.f - i.e;
        final double f3 = b.a.a.b.f(n2);
        final double k = b.a.a.b.l(n2);
        final double n3 = n * (f2 * f3 - j * k * f);
        final double n4 = n * (j * f3 + f2 * k * f);
        return new d(b.a.a.a.b(n4, n3), b.a.a.a.a(n * k * l, b.a.a.b.m(n3 * n3 + n4 * n4)), n);
    }
    
    static {
        g = new double[][][] { { { 252.250906, 149474.0722491, 3.035E-4, 1.8E-8 }, { 0.38709831, 0.0, 0.0, 0.0 }, { 0.20563175, 2.0407E-5, -2.83E-8, -1.8E-10 }, { 7.004986, 0.0018215, -1.81E-5, 5.6E-8 }, { 48.330893, 1.1861883, 1.7542E-4, 2.15E-7 }, { 77.456119, 1.5564776, 2.9544E-4, 9.0E-9 } }, { { 181.979801, 58519.2130302, 3.1014E-4, 1.5E-8 }, { 0.72332982, 0.0, 0.0, 0.0 }, { 0.00677192, -4.7765E-5, 9.81E-8, 4.6E-10 }, { 3.394662, 0.0010037, -8.8E-7, -7.0E-9 }, { 76.67992, 0.9011206, 4.0618E-4, -9.3E-8 }, { 131.563703, 1.4022288, -0.00107618, -5.678E-6 } }, { { 100.466457, 36000.7698278, 3.0322E-4, 2.0E-8 }, { 1.000001018, 0.0, 0.0, 0.0 }, { 0.01670863, -4.2037E-5, -1.267E-7, 1.4E-10 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 }, { 102.937348, 1.7195366, 4.5688E-4, -1.8E-8 } }, { { 355.433, 19141.6964471, 3.1052E-4, 1.6E-8 }, { 1.523679342, 0.0, 0.0, 0.0 }, { 0.09340065, 9.0484E-5, -8.06E-8, -2.5E-10 }, { 1.849726, -6.011E-4, 1.276E-5, -7.0E-9 }, { 49.558093, 0.7720959, 1.557E-5, 2.267E-6 }, { 336.060234, 1.8410449, 1.3477E-4, 5.36E-7 } }, { { 34.351519, 3036.3027748, 2.233E-4, 3.7E-8 }, { 5.202603209, 1.913E-7, 0.0, 0.0 }, { 0.04849793, 1.63225E-4, -4.714E-7, -2.01E-9 }, { 1.303267, -0.0054965, 4.66E-6, -2.0E-9 }, { 100.464407, 1.0209774, 4.0315E-4, 4.04E-7 }, { 14.331207, 1.6126352, 0.00103042, -4.464E-6 } }, { { 50.077444, 1223.5110686, 5.1908E-4, -3.0E-8 }, { 9.554909192, -2.139E-6, 4.0E-9, 0.0 }, { 0.05554814, -3.46641E-4, -6.436E-7, 3.4E-9 }, { 2.488879, -0.0037362, -1.519E-5, 8.7E-8 }, { 113.665503, 0.877088, -1.2176E-4, -2.249E-6 }, { 93.057237, 1.9637613, 8.3753E-4, 4.928E-6 } }, { { 314.055005, 429.8640561, 3.039E-4, 2.6E-8 }, { 19.218446062, -3.72E-8, 9.8E-10, 0.0 }, { 0.04638122, -2.7293E-5, 7.89E-8, 2.4E-10 }, { 0.773197, 7.744E-4, 3.749E-5, -9.2E-8 }, { 74.005957, 0.5211278, 0.00133947, 1.8484E-5 }, { 173.005291, 1.486379, 2.1406E-4, 4.34E-7 } }, { { 304.348665, 219.8833092, 3.0882E-4, 1.8E-8 }, { 30.110386869, -1.663E-7, 6.9E-10, 0.0 }, { 0.00945575, 6.033E-6, 0.0, -5.0E-11 }, { 1.769953, -0.0093082, -7.08E-6, 2.7E-8 }, { 131.784057, 1.1022039, 2.5952E-4, -6.37E-7 }, { 48.120276, 1.4262957, 3.8434E-4, 2.0E-8 } }, { { 238.96, 144.96, 0.0, 0.0 }, { 39.543, 0.0, 0.0, 0.0 }, { 0.249, 0.0, 0.0, 0.0 }, { 17.14, 0.0, 0.0, 0.0 }, { 110.307, 0.0, 0.0, 0.0 }, { 224.075, 0.0, 0.0, 0.0 } } };
    }
}
