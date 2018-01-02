// 
// Decompiled by Procyon v0.5.30
// 

public class _zg extends _zv
{
    public int p;
    public int d;
    public int a;
    public int n;
    public double p;
    
    public final synchronized double p(final double n) {
        final double n2 = super.d * (n / super.a * super.a / super.p);
        double p;
        if (n2 > super.n) {
            p = 0.0;
        }
        else {
            p = (super.n - n2) / (super.n * 0.05);
        }
        return this.p = p;
    }
    
    private final synchronized void p(final int[] array, final int n, final int n2, final double n3, final double n4, final double n5, final double n6, final int n7, final int n8, final int n9, final int n10) {
        if (array == null || n8 >= n10 || n7 >= n9 || n4 >= n6 || n3 >= n5) {
            return;
        }
        final double n11 = (n5 - n3) / (n9 - n7);
        final double n12 = (n6 - n4) / (n10 - n8);
        final int n13 = (int)(n11 * 1024.0);
        final int n14 = (int)(n12 * 1024.0);
        final int n15 = (int)(n3 * 1024.0);
        int n16 = (int)(n4 * 1024.0);
        for (int i = n8; i < n10; ++i) {
            int n17 = i * n + n7;
            int n18 = (int)(n3 * 1024.0);
            if (!super.p) {
                for (int j = n7; j < n9; ++j) {
                    array[n17++] = super.p[(n18 >> 10) + (n16 >> 10) * super.v];
                    n18 += n13;
                }
            }
            else {
                for (int k = n7; k < n9; ++k) {
                    final int n19 = (n16 >> 10) * super.v + (n18 >> 10);
                    final int n20 = (n18 & 0x3FF) >> 2;
                    final int n21 = (n16 & 0x3FF) >> 2;
                    final int n22 = (255 - n20) * (255 - n21);
                    final int n23 = (255 - n21) * n20;
                    final int n24 = (255 - n20) * n21;
                    final int n25 = n20 * n21;
                    final int n26 = super.p[n19];
                    final int n27 = n26 & 0xFF;
                    final int n28 = (n26 & 0xFF00) >> 8;
                    final int n29 = (n26 & 0xFF0000) >> 16;
                    final int n30 = n26 & 0xFF000000;
                    final int n31 = super.p[n19 + 1];
                    final int n32 = n31 & 0xFF;
                    final int n33 = (n31 & 0xFF00) >> 8;
                    final int n34 = (n31 & 0xFF0000) >> 16;
                    final int n35 = n19 + super.v;
                    final int n36 = super.p[n35];
                    final int n37 = n36 & 0xFF;
                    final int n38 = (n36 & 0xFF00) >> 8;
                    final int n39 = (n36 & 0xFF0000) >> 16;
                    final int n40 = super.p[n35 + 1];
                    array[n17++] = ((n22 * n27 + n23 * n32 + n24 * n37 + n25 * (n40 & 0xFF) & 0xFF0000) >> 16 | (n22 * n28 + n23 * n33 + n24 * n38 + n25 * ((n40 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n22 * n29 + n23 * n34 + n24 * n39 + n25 * ((n40 & 0xFF0000) >> 16) & 0xFF0000) | n30);
                    n18 += n13;
                }
            }
            n16 += n14;
        }
    }
    
    public final synchronized int p(final int[] array, final double n, final double n2, final double n3) {
        if (super.p == 0 || super.d == 0 || super.p == null || array == null) {
            return 3;
        }
        final double n4 = (n - n3 / 2.0) / super.a * (super.a - 1);
        final double n5 = n3 / super.a * super.a;
        final double n6 = super.d * (n5 / super.p);
        final double n7 = (super.n - n6) / 2.0;
        double n8;
        if (n7 <= 0.0) {
            n8 = 0.0;
        }
        else if (Math.abs(this.p) < 1.0E-4) {
            n8 = 0.0;
        }
        else {
            n8 = n7 - n2 / this.p * n7;
        }
        this.p = (int)n4;
        this.d = (int)n8;
        this.a = (int)(n5 + 0.5);
        this.n = (int)(n6 + 0.5);
        if (n4 >= 0.0 && n4 + n5 <= super.a) {
            this.p(array, super.p, super.d, n4, n8, n4 + n5, n8 + n6, 0, 0, super.p, super.d);
        }
        else {
            if (n4 <= 0.0) {
                final double n9 = n4 + super.a;
                final double n10 = -n4;
                final int n11 = (int)(n10 / n5 * super.p);
                this.p(array, super.p, super.d, n9, n8, n9 + n10, n8 + n6, 0, 0, n11, super.d);
                final double n12 = 0.0;
                final double n13 = n5 - n10;
                final int n14 = n11;
                this.p(array, super.p, super.d, n12, n8, n12 + n13, n8 + n6, n14, 0, n14 + (super.p - n11), super.d);
            }
            if (n4 + n5 > super.a) {
                final double n15 = n4;
                final double n16 = super.a - n4;
                final int n17 = (int)(n16 / n5 * super.p);
                this.p(array, super.p, super.d, n15, n8, n15 + n16, n8 + n6, 0, 0, n17, super.d);
                final double n18 = 0.0;
                final double n19 = n5 - n16;
                final int n20 = n17;
                this.p(array, super.p, super.d, n18, n8, n18 + n19, n8 + n6, n20, 0, n20 + (super.p - n17), super.d);
            }
        }
        return 0;
    }
    
    public final synchronized double p(final int p2, final int d) {
        final double n = super.a / super.n;
        final double n2 = p2 / d;
        super.p = p2;
        super.d = d;
        if (n < n2) {
            super.l = super.a;
        }
        else {
            super.l = super.n / d * p2 / super.a * super.a;
        }
        return super.l;
    }
    
    public final synchronized double d(final double n) {
        return -this.p(n);
    }
    
    public final synchronized double p(final double n, final double n2) {
        return n2 / 2.0;
    }
}
