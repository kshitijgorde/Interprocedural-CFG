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
            final double n15 = this.n(Math.asin(Math.abs(n13) / Math.sqrt(n13 * n13 + n12 * n12)));
            int n16;
            if (n15 < 80.0) {
                n16 = 32;
            }
            else if (n15 < 89.0) {
                n16 = 8;
            }
            else {
                n16 = 1;
            }
            int n17 = i * super.p;
            double n18 = 0.0;
            int n19 = super.p / n16;
            final int n20 = super.p - n19 * n16;
            if (n20 > 0) {
                ++n19;
            }
            double n21 = Math.atan2(n14, n12) * n9 + n3;
            double n22 = n6 - (Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) * n10 - n4);
            while (n19-- > 0) {
                int n23;
                if (n19 == 0 && n20 > 0) {
                    n23 = n20;
                }
                else {
                    n23 = n16;
                }
                n14 += n7 * n23;
                double n24 = Math.atan2(n14, n12) * n9 + n3;
                final double n25 = n6 - (Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) * n10 - n4);
                if (n18 == 0.0) {
                    n18 = n24 - n21;
                }
                else if ((n24 - n21) * n18 < 0.0) {
                    if (n24 < 0.0) {
                        n24 += super.a;
                    }
                    else {
                        n24 -= super.a;
                    }
                }
                int n26 = (int)(n21 * 65536.0);
                int n27 = (int)(n22 * 65536.0);
                final int n28 = (int)((n24 - n21) / n23 * 65536.0);
                final int n29 = (int)((n25 - n22) / n23 * 65536.0);
                if (super.p) {
                    while (n23-- > 0) {
                        if (n26 < 0) {
                            n26 += n5;
                        }
                        else if (n26 >= n5) {
                            n26 -= n5;
                        }
                        final int n30 = (n27 >> 16) * super.v + (n26 >> 16);
                        final int n31 = (n26 & 0xFFFF) >> 8;
                        final int n32 = (n27 & 0xFFFF) >> 8;
                        final int n33 = (255 - n31) * (255 - n32);
                        final int n34 = (255 - n32) * n31;
                        final int n35 = (255 - n31) * n32;
                        final int n36 = n31 * n32;
                        final int n37 = super.p[n30];
                        final int n38 = n37 & 0xFF;
                        final int n39 = (n37 & 0xFF00) >> 8;
                        final int n40 = (n37 & 0xFF0000) >> 16;
                        final int n41 = n37 & 0xFF000000;
                        final int n42 = super.p[n30 + 1];
                        final int n43 = n42 & 0xFF;
                        final int n44 = (n42 & 0xFF00) >> 8;
                        final int n45 = (n42 & 0xFF0000) >> 16;
                        final int n46 = n30 + super.v;
                        final int n47 = super.p[n46];
                        final int n48 = n47 & 0xFF;
                        final int n49 = (n47 & 0xFF00) >> 8;
                        final int n50 = (n47 & 0xFF0000) >> 16;
                        final int n51 = super.p[n46 + 1];
                        array[n17++] = ((n33 * n38 + n34 * n43 + n35 * n48 + n36 * (n51 & 0xFF) & 0xFF0000) >> 16 | (n33 * n39 + n34 * n44 + n35 * n49 + n36 * ((n51 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n33 * n40 + n34 * n45 + n35 * n50 + n36 * ((n51 & 0xFF0000) >> 16) & 0xFF0000) | n41);
                        n26 += n28;
                        n27 += n29;
                    }
                    n21 = n24;
                    n22 = n25;
                }
                else {
                    while (n23-- > 0) {
                        if (n26 < 0) {
                            n26 += n5;
                        }
                        else if (n26 >= n5) {
                            n26 -= n5;
                        }
                        array[n17++] = super.p[(n27 >> 16) * super.v + (n26 >> 16)];
                        n26 += n28;
                        n27 += n29;
                    }
                    n21 = n24;
                    n22 = n25;
                }
            }
            n11 -= n8;
        }
        return 0;
    }
}
