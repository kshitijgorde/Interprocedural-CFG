// 
// Decompiled by Procyon v0.5.30
// 

public class _zq extends _zv
{
    public double p;
    public double d;
    public double a;
    public int[] p;
    public int[] d;
    
    public final synchronized void p(final int[] array, final double n) {
        final int n2 = (int)(n / super.a * super.a * 65536.0);
        final int n3 = super.a - 1 << 16;
        final int n4 = super.a << 16;
        for (int i = 0; i < super.p; ++i) {
            final int n5 = this.d[i * 2];
            final int n6 = this.d[i * 2 + 1];
            final int n7 = this.p[i * 2];
            final int n8 = this.p[i * 2 + 1];
            int n9;
            int n10;
            for (n9 = n2 + n5, n10 = n2 + n7; n9 > n4 || n10 > n4; n9 -= n4, n10 -= n4) {}
            final int n11 = (n10 - n9) / super.d;
            final int n12 = (n8 - n6) / super.d;
            int j = n6 + (n8 - (n6 + n12 * super.d));
            int k = n9 + (n10 - (n9 + n11 * super.d));
            int n13 = i + (super.d - 1) * super.p;
            if (n10 < 0) {
                while (k >= 0) {
                    if (!super.p) {
                        array[n13] = super.p[(j >> 16) * super.v + (k >> 16)];
                    }
                    else {
                        array[n13] = this.p(k, j);
                    }
                    k += n11;
                    j += n12;
                    n13 -= super.p;
                }
                k += n4;
            }
            if (k < 0) {
                int l;
                for (l = k + n4; l < n4 - 1; l += n11, j += n12, n13 -= super.p) {
                    if (!super.p) {
                        array[n13] = super.p[(j >> 16) * super.v + (l >> 16)];
                    }
                    else {
                        array[n13] = this.p(l, j);
                    }
                }
                k = l - n4;
            }
            if (!super.p) {
                while (j > n8) {
                    array[n13] = super.p[(j >> 16) * super.v + (k >> 16)];
                    k += n11;
                    j += n12;
                    n13 -= super.p;
                }
            }
            else {
                while (j > n8) {
                    final int n14 = (j >> 16) * super.v + (k >> 16);
                    final int n15 = (k & 0xFFFF) >> 8;
                    final int n16 = (j & 0xFFFF) >> 8;
                    final int n17 = (255 - n15) * (255 - n16);
                    final int n18 = (255 - n16) * n15;
                    final int n19 = (255 - n15) * n16;
                    final int n20 = n15 * n16;
                    final int n21 = super.p[n14];
                    final int n22 = n21 & 0xFF;
                    final int n23 = (n21 & 0xFF00) >> 8;
                    final int n24 = (n21 & 0xFF0000) >> 16;
                    final int n25 = n21 & 0xFF000000;
                    final int n26 = super.p[n14 + 1];
                    final int n27 = n26 & 0xFF;
                    final int n28 = (n26 & 0xFF00) >> 8;
                    final int n29 = (n26 & 0xFF0000) >> 16;
                    final int n30 = n14 + super.v;
                    final int n31 = super.p[n30];
                    final int n32 = n31 & 0xFF;
                    final int n33 = (n31 & 0xFF00) >> 8;
                    final int n34 = (n31 & 0xFF0000) >> 16;
                    final int n35 = super.p[n30 + 1];
                    array[n13] = ((n17 * n22 + n18 * n27 + n19 * n32 + n20 * (n35 & 0xFF) & 0xFF0000) >> 16 | (n17 * n23 + n18 * n28 + n19 * n33 + n20 * ((n35 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n17 * n24 + n18 * n29 + n19 * n34 + n20 * ((n35 & 0xFF0000) >> 16) & 0xFF0000) | n25);
                    k += n11;
                    j += n12;
                    n13 -= super.p;
                }
            }
        }
    }
    
    public final synchronized int p() {
        if (super.d == 0 || super.p == 0 || super.p <= 0.0) {
            return 1;
        }
        super.d = super.a(super.p);
        if (super.d > super.v) {
            super.d = super.v;
        }
        this.p = super.v - super.d / 2.0;
        return 0;
    }
    
    public final synchronized void p(final double n, final int[] array) {
        final double tan = Math.tan(n * 3.141592653589793 / 180.0);
        final int n2 = super.n << 16;
        final int n3 = n2 / 2;
        final double n4 = this.a / Math.cos(super.d / 2.0 * 3.141592653589793 / 180.0) * Math.cos(n * 3.141592653589793 / 180.0);
        final double n5 = Math.tan(super.p / 2.0 * 3.141592653589793 / 180.0) * this.a * 2.0 / super.p;
        final int n6 = (int)(this.a * 65536.0);
        int n7 = super.p / 2 * 2;
        int n8;
        double n9;
        if ((super.p & 0x1) == 0x0) {
            n8 = super.p / 2;
            n9 = n5 / 2.0;
        }
        else {
            n8 = super.p / 2 + 1;
            n9 = 0.0;
        }
        for (int i = 0; i < n8; ++i) {
            final double atan2 = Math.atan2(n9, n4);
            n9 += n5;
            array[n7++] = (int)(atan2 * n6);
            array[n7++] = n2 - ((int)(n6 * Math.cos(atan2) * tan) + n3);
        }
        int n10 = 0;
        int n11 = (super.p - 1) * 2;
        for (int n12 = super.p / 2, j = 0; j < n12; ++j) {
            array[n10++] = -array[n11];
            array[n10++] = array[n11 + 1];
            n11 -= 2;
        }
    }
    
    public final synchronized int p(final int[] array, double n, double n2, double l) {
        if (super.p == 0 || super.d == 0 || this.p == null || this.d == null) {
            return 3;
        }
        while (n < 0.0) {
            n += super.a;
        }
        while (n > super.a) {
            n -= super.a;
        }
        if (l > super.l) {
            l = super.l;
        }
        if (l < 1.0) {
            l = 1.0;
        }
        if (super.p != l) {
            super.p = l;
            this.p();
        }
        final double p4 = this.p();
        if (n2 > p4) {
            n2 = p4;
        }
        else if (n2 < -p4) {
            n2 = -p4;
        }
        this.p(n2 + super.d / 2.0, this.p);
        this.p(n2 - super.d / 2.0, this.d);
        this.p(array, n);
        return 0;
    }
    
    public final synchronized int p(final int n, final int n2) {
        final int n3 = (n2 >> 16) * super.v + (n >> 16);
        final int n4 = (n & 0xFFFF) >> 8;
        final int n5 = (n2 & 0xFFFF) >> 8;
        final int n6 = (255 - n4) * (255 - n5);
        final int n7 = (255 - n5) * n4;
        final int n8 = (255 - n4) * n5;
        final int n9 = n4 * n5;
        final int n10 = super.p[n3];
        final int n11 = n10 & 0xFF;
        final int n12 = (n10 & 0xFF00) >> 8;
        final int n13 = (n10 & 0xFF0000) >> 16;
        final int n14 = n10 & 0xFF000000;
        final int n15 = super.p[n3 + 1];
        final int n16 = n15 & 0xFF;
        final int n17 = (n15 & 0xFF00) >> 8;
        final int n18 = (n15 & 0xFF0000) >> 16;
        final int n19 = n3 + super.v;
        final int n20 = super.p[n19];
        final int n21 = n20 & 0xFF;
        final int n22 = (n20 & 0xFF00) >> 8;
        final int n23 = (n20 & 0xFF0000) >> 16;
        final int n24 = super.p[n19 + 1];
        return (n6 * n11 + n7 * n16 + n8 * n21 + n9 * (n24 & 0xFF) & 0xFF0000) >> 16 | (n6 * n12 + n7 * n17 + n8 * n22 + n9 * ((n24 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n6 * n13 + n7 * n18 + n8 * n23 + n9 * ((n24 & 0xFF0000) >> 16) & 0xFF0000) | n14;
    }
    
    public final synchronized double d(final double n) {
        return -this.p(n);
    }
    
    public final synchronized int p(final int[] array, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final double n7) {
        super.p(array, n, n2, n3, n4, n5, n6, n7);
        this.d = n5 * 3.141592653589793 / 180.0;
        super.l = 179.0;
        super.f = 179.0;
        super.p = super.l;
        this.a = super.a / this.d;
        super.v = this.n(Math.atan(super.n / 2.0 / this.a));
        super.n = -super.v;
        this.p();
        this.p = new int[super.p * 2];
        if (this.p == null) {
            return 2;
        }
        this.d = new int[super.p * 2];
        if (this.d == null) {
            return 2;
        }
        return 0;
    }
}
