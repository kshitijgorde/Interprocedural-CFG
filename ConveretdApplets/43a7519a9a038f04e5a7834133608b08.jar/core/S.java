// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import I.I;
import B.Z;

public final class S
{
    private final RE g;
    public final int a;
    public final int b;
    private final double h;
    private final double i;
    private double j;
    private double k;
    public final byte[] c;
    public final byte[] d;
    public final byte[] e;
    public final byte[] f;
    private final byte[] l;
    private final byte[] m;
    private final byte[] n;
    private final byte[] o;
    private final Z p;
    private final double q;
    private final double r;
    private double s;
    private double t;
    private double u;
    private double v;
    private double w;
    private double x;
    private double y;
    private boolean z;
    
    public final double a() {
        return this.q;
    }
    
    public final double b() {
        return this.r;
    }
    
    public S(final RE g) {
        this.j = -10000.0;
        this.k = -110.0;
        this.p = new Z(400.0, 70.0, 300.0);
        final Z p;
        final double sqrt = Math.sqrt((p = this.p).a());
        final Z z2;
        final Z z = z2 = p;
        z.a /= sqrt;
        final Z z3 = z2;
        z3.b /= sqrt;
        final Z z4 = z2;
        z4.c /= sqrt;
        this.t = 0.0;
        this.u = 0.5;
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.y = -0.3;
        this.z = true;
        this.g = g;
        this.a = g.a / 2;
        this.b = g.b / 2;
        this.h = g.a / 0.022;
        this.i = g.b / 0.022;
        final int n = this.a * this.b;
        this.q = 0.022 / (g.a - 1);
        this.r = 0.022 / (g.b - 1);
        this.c = new byte[n];
        this.d = new byte[n];
        this.e = new byte[n];
        this.f = new byte[n];
        this.l = new byte[n];
        this.m = new byte[n];
        this.n = new byte[n];
        this.o = new byte[n];
        if (!g.a(I.I(620), false)) {
            for (int i = 0; i < n; ++i) {
                if (Math.random() < 0.005) {
                    this.l[i] = (byte)(150.0 * Math.random());
                }
                if (Math.random() < 0.005) {
                    this.m[i] = (byte)(150.0 * Math.random());
                }
                if (Math.random() < 0.005) {
                    this.n[i] = (byte)(150.0 * Math.random());
                }
                if (Math.random() < 0.005) {
                    this.o[i] = (byte)(150.0 * Math.random());
                }
            }
        }
    }
    
    public final synchronized void a(final int n, final int n2, final int n3, final int n4) {
        final Z a;
        if ((a = this.a(n, n2)) == null) {
            return;
        }
        final Z a2;
        if ((a2 = this.a(n3, n4)) == null) {
            return;
        }
        if (a.c == 0.0 || a2.c == 0.0) {
            return;
        }
        if (n != n3) {
            final double cos = Math.cos(this.u);
            final double sin = Math.sin(this.u);
            final Z a3;
            (a3 = a.a(cos, sin)).b = 0.0;
            final Z a4;
            (a4 = a2.a(cos, sin)).b = 0.0;
            double acos = Math.acos(a3.a(a4) / (Math.sqrt(a3.a()) * Math.sqrt(a4.a())));
            if (n - n3 < 0) {
                acos = -acos;
            }
            if (a3.c < 0.0 && a4.c < 0.0) {
                acos = -acos;
            }
            this.w += acos;
        }
        if (n2 != n4) {
            a.a = 0.0;
            a2.a = 0.0;
            double acos2 = Math.acos(a.a(a2) / (Math.sqrt(a.a()) * Math.sqrt(a2.a())));
            if (n4 - n2 < 0) {
                acos2 = -acos2;
            }
            this.a(acos2);
        }
    }
    
    public final synchronized void a(final boolean z) {
        if (!(this.z = z)) {
            this.w = this.v + this.x / 11.0;
        }
    }
    
    public final synchronized void a(final double n) {
        final double u;
        if ((u = this.u + n) >= -1.5707963267948966 && u <= 1.5707963267948966) {
            this.u = u;
        }
    }
    
    public final synchronized void b(final double n) {
        this.e(this.y + n);
        this.x = this.y;
    }
    
    private void e(final double y) {
        if (Math.abs(y) < 1.5707963267948966) {
            this.y = y;
            return;
        }
        if (y < 0.0) {
            this.y = -1.5707963267948966;
            return;
        }
        this.y = 1.5707963267948966;
    }
    
    public final double c() {
        return this.t;
    }
    
    public final double d() {
        return this.v;
    }
    
    public final double e() {
        return this.j;
    }
    
    private static double a(double n, final double n2) {
        if ((n *= n2) < 1.0) {
            return n;
        }
        return 1.0;
    }
    
    public final synchronized void c(final double n) {
        if (Math.abs(this.k - this.j) > 0.001) {
            this.j += a(n, 6.6) * (this.k - this.j);
            final double s = this.j * this.j - 1.0;
            this.s = s;
            final double n2 = s;
            int n3 = 0;
            for (int i = 0; i < this.b; ++i) {
                final double n5;
                final double n4 = n5 = this.r * i - 0.011;
                final double n6 = n4 * n4 + 1.0;
                for (int j = 0; j < this.a; ++j) {
                    final double n7 = this.q * j - 0.011;
                    final double n8 = 1.0 / (n7 * n7 + n6);
                    final double n10;
                    final double n9 = n10 = this.j * n8;
                    final double n11 = n9 * n9 - n2 * n8;
                    double n12 = 0.0;
                    double n13 = 0.0;
                    double n14 = 0.0;
                    double n15 = 0.0;
                    if (n11 > 0.0) {
                        final double n17;
                        final double n16 = (n17 = -n10 - Math.sqrt(n11)) * n7;
                        final double n18 = n17 * n5;
                        final double n19 = -n17 - this.j;
                        final double n20 = n16 * n16 + n18 * n18;
                        final double n21 = 1.96 * (n19 * n17 - n20) / Math.sqrt(n20 + n17 * n17);
                        final double n22 = n16 * 0.792273397;
                        final double n23 = n18 * 0.138647844;
                        final double n24 = n19 * 0.594205048;
                        final double n25;
                        if ((n25 = n22 + n23 + n24) > 0.0) {
                            final double n26 = n25 * 109.65;
                            final double n27 = n25 * n21;
                            n12 = n26 + n27 * n27 * n27 * n27 * n27 * n27 * n27 * n27 * n27 * n27 * n27;
                        }
                        final double n28;
                        if ((n28 = n23 + n24 - n22) > 0.0) {
                            final double n29 = n28 * 109.65;
                            final double n30 = n28 * n21;
                            n13 = n29 + n30 * n30 * n30 * n30 * n30 * n30 * n30 * n30 * n30 * n30 * n30;
                        }
                        final double n31;
                        if ((n31 = n22 - n23 + n24) > 0.0) {
                            final double n32 = n31 * 109.65;
                            final double n33 = n31 * n21;
                            n14 = n32 + n33 * n33 * n33 * n33 * n33 * n33 * n33 * n33 * n33 * n33 * n33;
                        }
                        final double n34;
                        if ((n34 = n24 - n22 - n23) > 0.0) {
                            final double n35 = n34 * 109.65;
                            final double n36 = n34 * n21;
                            n15 = n35 + n36 * n36 * n36 * n36 * n36 * n36 * n36 * n36 * n36 * n36 * n36;
                        }
                    }
                    else {
                        n12 = this.l[n3];
                        n13 = this.m[n3];
                        n14 = this.n[n3];
                        n15 = this.o[n3];
                    }
                    final double n37 = -n7 * n10;
                    final double n38 = -n5 * n10;
                    final double n39 = n10 - this.j;
                    double n40;
                    if ((n40 = n37 * n37 + n38 * n38 + n39 * n39) < 1.0) {
                        n40 = 2.0 - n40;
                    }
                    final double n41 = n40 * n40;
                    final double n42 = n41 * n41;
                    final double n43 = 120.0 / (n42 * n42);
                    double n44 = n12 + n43;
                    double n45 = n13 + n43;
                    double n46 = n14 + n43;
                    double n47 = n15 + n43;
                    if (n44 > 255.0) {
                        n44 = 255.0;
                    }
                    if (n45 > 255.0) {
                        n45 = 255.0;
                    }
                    if (n46 > 255.0) {
                        n46 = 255.0;
                    }
                    if (n47 > 255.0) {
                        n47 = 255.0;
                    }
                    this.c[n3] = (byte)n44;
                    this.d[n3] = (byte)n45;
                    this.e[n3] = (byte)n46;
                    this.f[n3] = (byte)n47;
                    ++n3;
                }
            }
        }
        this.t += a(n, 11.0) * (this.u - this.t);
        if (this.z) {
            this.x += a(n, 1.1) * (this.y - this.x);
        }
        else {
            this.e(this.x = ((n * 11.0 < 1.0) ? (11.0 * (this.w - this.v)) : ((this.w - this.v) / n)));
        }
        this.v += this.x * n;
    }
    
    public final synchronized void d(final double n) {
        double k;
        if ((k = this.k + n) > -35.0) {
            k = -35.0;
        }
        if (k != this.k) {
            this.k = k;
        }
    }
    
    public final O a(Z a) {
        final Z z = a = this.g.g.a(a);
        z.c += this.j;
        final Z z2 = a;
        if (z2.a() > this.s) {
            return new O();
        }
        return new O((int)((-z2.a / z2.c + 0.011) * this.h), (int)((-z2.b / z2.c + 0.011) * this.i));
    }
    
    private final Z a(final int n, final int n2) {
        if (n < 0 || n >= this.g.a || n2 < 0 || n2 >= this.g.b) {
            return null;
        }
        final double n3 = n;
        final double n4 = n2;
        final double n5 = n3;
        final double n6 = this.j * this.j - 1.0;
        final double n8;
        final double n7 = n8 = this.r * n4 - 0.011;
        final double n9 = n7 * n7 + 1.0;
        final double n11;
        final double n10 = n11 = this.q * n5 - 0.011;
        final double n12 = n10 * n10 + n9;
        final double n14;
        final double n13 = n14 = this.j / n12;
        final double n15;
        if ((n15 = n13 * n13 - n6 / n12) > 0.0) {
            final double n16;
            return new Z((n16 = -Math.sqrt(n15) - n14) * n11, n16 * n8, -n16 - this.j);
        }
        return null;
    }
    
    public S() {
    }
    
    public static final int[] a(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return array;
    }
}
