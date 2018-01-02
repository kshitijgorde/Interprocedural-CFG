import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private int Y;
    private int Z;
    private int a;
    private int b;
    private int c;
    private double d;
    private double e;
    private double f;
    private double g;
    private Color h;
    private int[] i;
    private int[] j;
    private int[] k;
    private int[] l;
    private double m;
    private double n;
    private double o;
    
    public b(final int y, final double g, final Color h, final double d, final double n, final double n2, final int c) {
        this.Y = y;
        this.Z = 2 * this.Y;
        this.b = this.Z * this.Z;
        this.g = g;
        this.h = h;
        this.d = d;
        this.e = n * Math.sin(n2);
        this.f = -n * Math.cos(n2);
        this.c = c;
        this.a = this.Z + 2 * this.c;
        this.J();
    }
    
    public void I(final a a, final a a2, final int n, final int n2) {
        int n3 = 0;
        int n4 = n - this.Y - this.c + (n2 - this.Y - this.c) * a.B;
        int n5 = 0;
        for (int i = 0; i < this.c; ++i) {
            System.arraycopy(a.A, n4, a2.A, n5, this.a);
            n4 += a.B;
            n5 += this.a;
        }
        for (int j = 0; j < this.Z; ++j) {
            for (int k = 0; k < this.c; ++k) {
                a2.A[n5++] = a.A[n4++];
            }
            for (int l = 0; l < this.Z; ++l) {
                final int n6 = a.A[n + this.i[n3] + (n2 + this.j[n3]) * a.B];
                if (this.l[n3] == 256) {
                    a2.A[n5++] = n6;
                }
                else {
                    a2.A[n5++] = (0xFF000000 | (((n6 & 0xFF0000) * this.l[n3] >> 8 & 0xFF0000) | ((n6 & 0xFF00) * this.l[n3] >> 8 & 0xFF00) | (n6 & 0xFF) * this.l[n3] >> 8) + this.k[n3]);
                }
                ++n3;
            }
            int n7 = n4 + this.Z;
            for (int n8 = 0; n8 < this.c; ++n8) {
                a2.A[n5++] = a.A[n7++];
            }
            n4 = n7 + (a.B - this.a);
        }
        for (int n9 = 0; n9 < this.c; ++n9) {
            System.arraycopy(a.A, n4, a2.A, n5, this.a);
            n4 += a.B;
            n5 += this.a;
        }
    }
    
    private void J() {
        this.i = new int[this.b];
        this.j = new int[this.b];
        this.k = new int[this.b];
        this.l = new int[this.b];
        int n = 0;
        for (int i = -this.Y; i < this.Y; ++i) {
            for (int j = -this.Y; j < this.Y; ++j) {
                final double n2 = j;
                final double n3 = i;
                final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
                final double m = M(j, i);
                if (sqrt < this.Y) {
                    this.L(sqrt);
                    this.i[n] = (int)(Math.sin(m) * this.m);
                    this.j[n] = (int)(-Math.cos(m) * this.m);
                    final double pow = Math.pow(this.g, this.o);
                    final double max = Math.max(this.K(Math.sin(m) * this.n, Math.cos(m) * this.n), 0.0);
                    this.k[n] = ((int)((1.0 - max) * (1.0 - pow) * this.h.getRed() + max * 255.0) << 16 | (int)((1.0 - max) * (1.0 - pow) * this.h.getGreen() + max * 255.0) << 8 | (int)((1.0 - max) * (1.0 - pow) * this.h.getBlue() + max * 255.0));
                    this.l[n] = (int)((1.0 - max) * pow * 256.0);
                }
                else {
                    this.i[n] = j;
                    this.j[n] = i;
                    this.k[n] = 0;
                    this.l[n] = 256;
                }
                ++n;
            }
        }
    }
    
    private void L(final double n) {
        final double n2 = n / this.Y;
        final double sqrt = Math.sqrt(1.0 - n2 * n2);
        final double n3 = 2.0 * Math.asin(n2);
        final double n4 = Math.asin(n2 / this.d) - Math.asin(n2);
        final double o = sqrt / Math.cos(n4);
        this.m = (n2 + sqrt * Math.tan(n4)) * this.Y;
        this.n = n3;
        this.o = o;
    }
    
    private double K(final double n, final double n2) {
        return 0.5 * Math.cos(Math.min(Math.sqrt(Math.pow(n + this.e, 2.0) + Math.pow(n2 + this.f, 2.0)) / 1.5, 3.141592653589793)) + 0.5 * Math.pow(Math.cos(Math.min(2.0 * Math.sqrt(Math.pow(n + this.e, 2.0) + Math.pow(n2 + this.f, 2.0)), 1.5707963267948966)), 3.0);
    }
    
    private static double H(final double n, final double n2) {
        return Math.sqrt(n * n + n2 * n2);
    }
    
    private static double M(final double n, final double n2) {
        double asin = 0.0;
        if (Math.sqrt(n * n + n2 * n2) != 0.0) {
            asin = Math.asin(n / Math.sqrt(n * n + n2 * n2));
        }
        if (n2 > 0.0) {
            asin = 3.141592653589793 - asin;
        }
        if (asin < 0.0) {
            asin += 6.283185307179586;
        }
        return asin;
    }
}
