import java.awt.Dimension;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class n extends m
{
    protected void a(final Rectangle rectangle, final boolean b) {
        if (super.a.a().size() == 1) {
            super.a(rectangle, b);
            return;
        }
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[][] array = new int[super.a.a().size()][];
        for (int i = 0; i < super.a.a().size(); ++i) {
            array[i] = ((z)super.a.a().elementAt(i)).a();
        }
        final int[] a = super.b.a().elementAt(0).a();
        final int n = (int)(Math.log(super.a.a().elementAt(0).b().height) / Math.log(2.0));
        final int n2 = (1 << n) - 1;
        int n3 = rectangle.y * c2.width + rectangle.x;
        if (super.N == 2 && b) {
            for (int j = 0; j < rectangle.height; ++j) {
                int n4 = n3;
                for (int k = 0; k < rectangle.width; ++k) {
                    a[n4++] = -16777216;
                }
                n3 += c2.width;
            }
            return;
        }
        int n5 = 0;
        if (b && super.N == 0) {
            n5 = c.height / 2;
        }
        for (int l = 0; l < rectangle.height; ++l) {
            int m = super.m;
            int n6 = super.n;
            int n7 = n3;
            for (int n8 = 0; n8 < rectangle.width; ++n8) {
                try {
                    a[n7] = this.a(array, c, m, n6, n5, n2, n);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a[n7] = -16777216;
                }
                ++n7;
                m += super.o;
                n6 += super.p;
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
            n3 += c2.width;
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        if (super.a.a().size() == 1) {
            super.a(n, n2, b);
            return;
        }
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[][] array = new int[super.a.a().size()][];
        for (int i = 0; i < super.a.a().size(); ++i) {
            array[i] = ((z)super.a.a().elementAt(i)).a();
        }
        final int[] a = super.b.a().elementAt(0).a();
        final int n3 = (int)(Math.log(super.a.a().elementAt(0).b().height) / Math.log(2.0));
        final int n4 = (1 << n3) - 1;
        int n5 = 0;
        final boolean b2 = false;
        if (super.N == 0) {
            n5 = c.height / 2;
        }
        int n6 = 0;
        int n7 = 0;
        if (super.N == 2) {
            n6 = (b ? 1 : 0);
            n7 = (b ? 0 : 1);
        }
        final int n8 = b ? n5 : b2;
        final int n9 = b ? b2 : n5;
        final int n10 = super.G ? 1 : c2.width;
        final int n11 = super.G ? c2.width : 1;
        final int n12 = super.G ? c2.height : c2.width;
        final int n13 = n >> super.d;
        this.a(super.A[n13], super.z[n13], super.A[n13 + 1], super.z[n13 + 1], super.d + 1, super.d);
        for (int j = n; j < n2; ++j) {
            final int max = Math.max(super.D[j] - super.e - 1 & ~(super.e - 1), 0);
            final int min = Math.min(super.D[j], n12 - 1);
            int n14 = j * n10 + max * n11;
            if (n6 == 1) {
                for (int k = max; k <= min; ++k) {
                    a[n14] = -16777216;
                    n14 += n11;
                }
            }
            else {
                final int n15 = super.u - (super.D[j] - max);
                int n16 = super.m + n15 * super.o;
                int n17 = super.n + n15 * super.p;
                for (int l = max; l <= min; ++l) {
                    try {
                        a[n14] = this.a(array, c, n16, n17, n8, n4, n3);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        a[n14] = -16777216;
                    }
                    n14 += n11;
                    n16 += super.o;
                    n17 += super.p;
                }
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
        }
        this.a(super.B[n13], super.z[n13], super.B[n13 + 1], super.z[n13 + 1], super.d + 1, super.d);
        for (int n18 = n; n18 < n2; ++n18) {
            final int n19 = Math.min(super.D[n18] + super.u & ~(super.e - 1), n12) - 1;
            final int max2 = Math.max(super.D[n18] + 1, 0);
            int n20 = n18 * n10 + n19 * n11;
            if (n7 == 1) {
                for (int n21 = n19; n21 >= max2; --n21) {
                    a[n20] = -16777216;
                    n20 -= n11;
                }
            }
            else {
                final int n22 = super.u + (super.D[n18] - n19);
                int n23 = super.m + n22 * super.o;
                int n24 = super.n + n22 * super.p;
                for (int n25 = n19; n25 >= max2; --n25) {
                    try {
                        a[n20] = this.a(array, c, n23, n24, n9, n4, n3);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        a[n20] = -16777216;
                    }
                    n20 -= n11;
                    n23 += super.o;
                    n24 += super.p;
                }
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
        }
    }
    
    protected int a(final int[][] array, final Dimension dimension, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (super.v > 20) {
            final int n6 = n3 + (n2 >>> 12);
            return array[n6 >> n5][(n6 & n4) * dimension.width + (n >>> 12)];
        }
        int n7 = n3 + (n2 >>> 12);
        final int n8 = n >>> 12;
        final int n9 = (n7 & n4) * dimension.width + n8;
        final int n10 = n7 >> n5;
        final int n11 = array[n10][n9];
        final int n12 = array[n10][n9 + 1];
        final int n13 = (++n7 & n4) * dimension.width + n8;
        final int n14 = n7 >> n5;
        final int n15 = array[n14][n13];
        final int n16 = array[n14][n13 + 1];
        final int n17 = (n11 & 0xFF0000) >> 16;
        final int n18 = (n11 & 0xFF00) >> 8;
        final int n19 = n11 & 0xFF;
        final int n20 = (n12 & 0xFF0000) >> 16;
        final int n21 = (n12 & 0xFF00) >> 8;
        final int n22 = n12 & 0xFF;
        final int n23 = (n15 & 0xFF0000) >> 16;
        final int n24 = (n15 & 0xFF00) >> 8;
        final int n25 = n15 & 0xFF;
        final int n26 = (n16 & 0xFF0000) >> 16;
        final int n27 = (n16 & 0xFF00) >> 8;
        final int n28 = n16 & 0xFF;
        final int n29 = (n & 0xFFF) >> 4;
        final int n30 = (n2 & 0xFFF) >> 4;
        return 0xFF000000 | (((n26 - n23) * n29 + (n23 << 8)) * n30 + ((n20 - n17) * n29 + (n17 << 8)) * (256 - n30) & 0xFF0000) | (((n27 - n24) * n29 + (n24 << 8)) * n30 + ((n21 - n18) * n29 + (n18 << 8)) * (256 - n30) >> 8 & 0xFF00) | (((n28 - n25) * n29 + (n25 << 8)) * n30 + ((n22 - n19) * n29 + (n19 << 8)) * (256 - n30) >> 16 & 0xFF);
    }
}
