// 
// Decompiled by Procyon v0.5.30
// 

public class _zz extends _zv
{
    public final synchronized int p(final int[] array, final double n, final double n2, final double p4) {
        if (super.p == 0 || super.d == 0 || super.n == 0 || array == null || super.n >= super.v) {
            return 3;
        }
        if (super.p != p4) {
            super.p = p4;
            super.d = this.a(p4);
        }
        final double n3 = n / super.a * (super.a - 1);
        final double n4 = super.n / (super.v - super.n) * (super.n - 1);
        final int n5 = super.a << 16;
        final double n6 = super.n - 1;
        final double tan = Math.tan(this.v(super.p / 2.0));
        final double n7 = tan / (super.p / 2);
        final double tan2 = Math.tan(this.v(super.d / 2.0));
        final double n8 = tan2 / (super.d / 2);
        final double n9 = (super.a - 1) / this.v(super.a);
        final double n10 = (super.n - 1) / this.v(super.v - super.n);
        double n11 = tan2;
        final double sin = Math.sin(this.v(n2));
        final double cos = Math.cos(this.v(n2));
        for (int i = 0; i < super.d; ++i) {
            final double n12 = cos - n11 * sin;
            final double n13 = sin + n11 * cos;
            double n14 = -tan;
            final double p5 = this.p(Math.asin(Math.abs(n13) / Math.sqrt(n13 * n13 + n12 * n12)));
            int n15;
            if (p5 < 80.0) {
                n15 = 32;
            }
            else if (p5 < 89.0) {
                n15 = 8;
            }
            else {
                n15 = 1;
            }
            int n16 = i * super.p;
            double n17 = 0.0;
            int n18 = super.p / n15;
            final int n19 = super.p - n18 * n15;
            if (n19 > 0) {
                ++n18;
            }
            double n20 = Math.atan2(n14, n12) * n9 + n3;
            double n21 = n6 - (Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) * n10 - n4);
            while (n18-- > 0) {
                int n22;
                if (n18 == 0 && n19 > 0) {
                    n22 = n19;
                }
                else {
                    n22 = n15;
                }
                n14 += n7 * n22;
                double n23 = Math.atan2(n14, n12) * n9 + n3;
                final double n24 = n6 - (Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) * n10 - n4);
                if (n17 == 0.0) {
                    n17 = n23 - n20;
                }
                else if ((n23 - n20) * n17 < 0.0) {
                    if (n23 < 0.0) {
                        n23 += super.a;
                    }
                    else {
                        n23 -= super.a;
                    }
                }
                int n25 = (int)(n20 * 65536.0);
                int n26 = (int)(n21 * 65536.0);
                final int n27 = (int)((n23 - n20) / n22 * 65536.0);
                final int n28 = (int)((n24 - n21) / n22 * 65536.0);
                if (super.p) {
                    while (n22-- > 0) {
                        if (n25 < 0) {
                            n25 += n5;
                        }
                        else if (n25 >= n5) {
                            n25 -= n5;
                        }
                        final int n29 = (n26 >> 16) * super.v + (n25 >> 16);
                        final int n30 = (n25 & 0xFFFF) >> 8;
                        final int n31 = (n26 & 0xFFFF) >> 8;
                        final int n32 = (255 - n30) * (255 - n31);
                        final int n33 = (255 - n31) * n30;
                        final int n34 = (255 - n30) * n31;
                        final int n35 = n30 * n31;
                        final int n36 = super.p[n29];
                        final int n37 = n36 & 0xFF;
                        final int n38 = (n36 & 0xFF00) >> 8;
                        final int n39 = (n36 & 0xFF0000) >> 16;
                        final int n40 = n36 & 0xFF000000;
                        final int n41 = super.p[n29 + 1];
                        final int n42 = n41 & 0xFF;
                        final int n43 = (n41 & 0xFF00) >> 8;
                        final int n44 = (n41 & 0xFF0000) >> 16;
                        final int n45 = n29 + super.v;
                        final int n46 = super.p[n45];
                        final int n47 = n46 & 0xFF;
                        final int n48 = (n46 & 0xFF00) >> 8;
                        final int n49 = (n46 & 0xFF0000) >> 16;
                        final int n50 = super.p[n45 + 1];
                        array[n16++] = ((n32 * n37 + n33 * n42 + n34 * n47 + n35 * (n50 & 0xFF) & 0xFF0000) >> 16 | (n32 * n38 + n33 * n43 + n34 * n48 + n35 * ((n50 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n32 * n39 + n33 * n44 + n34 * n49 + n35 * ((n50 & 0xFF0000) >> 16) & 0xFF0000) | n40);
                        n25 += n27;
                        n26 += n28;
                    }
                    n20 = n23;
                    n21 = n24;
                }
                else {
                    while (n22-- > 0) {
                        if (n25 < 0) {
                            n25 += n5;
                        }
                        else if (n25 >= n5) {
                            n25 -= n5;
                        }
                        array[n16++] = super.p[(n26 >> 16) * super.v + (n25 >> 16)];
                        n25 += n27;
                        n26 += n28;
                    }
                    n20 = n23;
                    n21 = n24;
                }
            }
            n11 -= n8;
        }
        return 0;
    }
}
