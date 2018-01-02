// 
// Decompiled by Procyon v0.5.30
// 

public class _zj
{
    public int p;
    public int d;
    public int a;
    public double p;
    public double d;
    private int n;
    private int v;
    private int i;
    private int l;
    private double a;
    public int b;
    public int c;
    public int e;
    public int f;
    public int g;
    public int h;
    public int j;
    public int k;
    public int m;
    public int o;
    public boolean p;
    public int q;
    public boolean d;
    public int r;
    public int s;
    public int t;
    public int u;
    public int[] p;
    
    private final synchronized void p(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11) {
        double n12 = 1.0;
        double n13 = 0.0;
        final int n14 = n5 - n3;
        final int q = this.q;
        int n15 = 0;
        int n16;
        int n17;
        if (this.a == 1.0) {
            n15 = (n5 - n3 << 16) / (n9 - n7);
            n16 = (n6 - n4 << 16) / (n10 - n8);
            n17 = n4 << 16;
        }
        else {
            final int n18 = n9 - n7;
            final int n19 = n10 - n8;
            if (n14 != n18) {
                n12 = n14 / n18;
            }
            else {
                n12 = 1.0;
            }
            double n20;
            if (q != n19) {
                n20 = q / n19;
            }
            else {
                n20 = 1.0;
            }
            final double n21 = 2.0 * this.a;
            n13 = this.b - n18 / n21;
            final double n22 = n20 * n8 / this.a - (this.c - n19 / n21);
            n16 = (int)(n20 / this.a * 65536.0);
            n17 = n16 * n8 - (int)(n22 * 65536.0) + (n4 << 16);
        }
        for (int i = n8; i < n10; ++i) {
            if (this.a == 1.0) {
                int n23 = n3 << 16;
                int n24 = i * n + n7;
                for (int j = n7; j < n9; ++j) {
                    array[n24++] = this.p[(n17 >> 16) * n14 + (n23 >> 16)];
                    n23 += n15;
                }
            }
            else {
                final int n25 = n17 >> 16;
                if (n25 <= 0 || n25 >= this.s - 1) {
                    continue;
                }
                final int n26 = n25 * n14;
                final double n27 = n12 * n7 / this.a - n13;
                n15 = (int)(n12 / this.a * 65536.0);
                int n28 = n15 * n7 - (int)(n27 * 65536.0);
                int n29 = i * n + n7;
                if (!this.d) {
                    for (int k = n7; k < n9; ++k) {
                        final int n30 = n28 + 32768 >> 16;
                        if (n30 > 0.0 && n30 < n14 - 1) {
                            array[n29++] = this.p[n26 + n30];
                        }
                        n28 += n15;
                    }
                }
                else {
                    for (int l = n7; l < n9; ++l) {
                        final int n31 = n28 + 32768 >> 16;
                        if (n31 > 0.0 && n31 < n14 - 1) {
                            final int n32 = (n17 >> 16) * n14 + (n28 >> 16);
                            final int n33 = (n28 & 0xFFFF) >> 8;
                            final int n34 = (n17 & 0xFFFF) >> 8;
                            final int n35 = (255 - n33) * (255 - n34);
                            final int n36 = (255 - n34) * n33;
                            final int n37 = (255 - n33) * n34;
                            final int n38 = n33 * n34;
                            final int n39 = this.p[n32];
                            final int n40 = n39 & 0xFF;
                            final int n41 = (n39 & 0xFF00) >> 8;
                            final int n42 = (n39 & 0xFF0000) >> 16;
                            final int n43 = n39 & 0xFF000000;
                            final int n44 = this.p[n32 + 1];
                            final int n45 = n44 & 0xFF;
                            final int n46 = (n44 & 0xFF00) >> 8;
                            final int n47 = (n44 & 0xFF0000) >> 16;
                            final int n48 = n32 + n14;
                            final int n49 = this.p[n48];
                            final int n50 = n49 & 0xFF;
                            final int n51 = (n49 & 0xFF00) >> 8;
                            final int n52 = (n49 & 0xFF0000) >> 16;
                            final int n53 = this.p[n48 + 1];
                            array[n29++] = ((n35 * n40 + n36 * n45 + n37 * n50 + n38 * (n53 & 0xFF) & 0xFF0000) >> 16 | (n35 * n41 + n36 * n46 + n37 * n51 + n38 * ((n53 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n35 * n42 + n36 * n47 + n37 * n52 + n38 * ((n53 & 0xFF0000) >> 16) & 0xFF0000) | n43);
                        }
                        n28 += n15;
                    }
                }
            }
            n17 += n16;
        }
    }
    
    public final synchronized void p() {
        this.p = null;
    }
    
    public _zj() {
        this.p = 1;
        this.d = 0;
        this.p = false;
    }
    
    public final synchronized void p(final int e, final int f, final int g, final int h) {
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.j = f - e;
        this.k = h - g;
        this.m = this.j / 2;
        this.o = this.k / 2;
        if (this.b < 0 || this.a == 1.0) {
            this.p = false;
            this.b = this.m;
            this.c = this.o;
            return;
        }
        if (this.p) {}
    }
    
    public final synchronized boolean p() {
        return this.d < 360.0;
    }
    
    public final synchronized void p(final int[] array, final double a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (this.p == 0) {
            return;
        }
        final double n = this.s / this.p;
        final int q = (int)(n + 0.5);
        this.q = q;
        final int r = this.r;
        if (r <= this.t && q <= this.u) {
            this.n = (this.t - r) / 2;
            this.v = (this.u - q) / 2;
            this.i = this.n + r;
            this.l = this.v + q;
        }
        else if (r / q < this.t / this.u) {
            int n2 = (int)(this.u / q * r + 0.5);
            if (n2 == 0) {
                n2 = 1;
            }
            this.v = 0;
            this.l = this.u;
            this.n = (this.t - n2) / 2;
            this.i = this.n + n2;
        }
        else {
            int n3 = (int)(this.t / r * q + 0.5);
            if (n3 == 0) {
                n3 = 1;
            }
            this.n = 0;
            this.i = this.t;
            this.v = (this.u - n3) / 2;
            this.l = this.v + n3;
        }
        final int n4 = this.i - this.n;
        final int n5 = this.l - this.v;
        if (this.b < 0 || this.c < 0 || this.a == 1.0) {
            this.b = n4 / 2;
            this.c = n5 / 2;
        }
        this.p(this.n, this.i, this.v, this.l);
        this.p(array, this.t, this.u, 0, (int)(n * this.d + 0.5), this.r, (int)(n * (this.d + 1) + 0.5), this.n, this.v, this.i, this.l, 0);
    }
    
    public final synchronized boolean p(final int n) {
        if (n == 1) {
            ++this.d;
            if (this.p()) {
                if (this.d >= this.p) {
                    this.d = this.p - 1;
                    return true;
                }
            }
            else if (this.d >= this.p) {
                this.d = 0;
            }
        }
        else {
            --this.d;
            if (this.p()) {
                if (this.d < 0) {
                    this.d = 0;
                    return true;
                }
            }
            else if (this.d < 0) {
                this.d = this.p - 1;
            }
        }
        return false;
    }
}
