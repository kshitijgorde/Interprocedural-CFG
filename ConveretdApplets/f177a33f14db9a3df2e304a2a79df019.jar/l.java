import java.awt.Dimension;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class l extends k
{
    protected void a(final Rectangle rectangle, final boolean b) {
        if (this.a.a().size() == 1) {
            super.a(rectangle, b);
            return;
        }
        final Dimension b2 = this.a.b();
        final Dimension b3 = this.b.b();
        final int[][] array = new int[this.a.a().size()][];
        for (int i = 0; i < this.a.a().size(); ++i) {
            array[i] = (int[])this.a.a().elementAt(i);
        }
        final int[] array2 = this.b.a().elementAt(0);
        final int n = (int)(Math.log(((q)this.a).f()) / Math.log(2.0));
        final int n2 = (1 << n) - 1;
        int n3 = rectangle.y * b3.width + rectangle.x;
        if (this.Q == 2 && b) {
            for (int j = 0; j < rectangle.height; ++j) {
                int n4 = n3;
                for (int k = 0; k < rectangle.width; ++k) {
                    array2[n4++] = -16777216;
                }
                n3 += b3.width;
            }
            return;
        }
        int n5 = 0;
        if (b && this.Q == 0) {
            n5 = b2.height / 2;
        }
        for (int l = 0; l < rectangle.height; ++l) {
            int o = this.o;
            int p2 = this.p;
            int n6 = n3;
            for (int n7 = 0; n7 < rectangle.width; ++n7) {
                try {
                    array2[n6] = this.a(array, b2, o, p2, n5, n2, n);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    array2[n6] = -16777216;
                }
                ++n6;
                o += this.q;
                p2 += this.r;
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
            n3 += b3.width;
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        if (this.a.a().size() == 1) {
            super.a(n, n2, b);
            return;
        }
        final Dimension b2 = this.a.b();
        final Dimension b3 = this.b.b();
        final int[][] array = new int[this.a.a().size()][];
        for (int i = 0; i < this.a.a().size(); ++i) {
            array[i] = (int[])this.a.a().elementAt(i);
        }
        final int[] array2 = this.b.a().elementAt(0);
        final int n3 = (int)(Math.log(((q)this.a).f()) / Math.log(2.0));
        final int n4 = (1 << n3) - 1;
        int n5 = 0;
        final boolean b4 = false;
        if (this.Q == 0) {
            n5 = b2.height / 2;
        }
        int n6 = 0;
        int n7 = 0;
        if (this.Q == 2) {
            n6 = (b ? 1 : 0);
            n7 = (b ? 0 : 1);
        }
        final int n8 = b ? n5 : b4;
        final int n9 = b ? b4 : n5;
        final int n10 = this.G ? 1 : b3.width;
        final int n11 = this.G ? b3.width : 1;
        final int n12 = this.G ? b3.height : b3.width;
        final int n13 = n >> this.d;
        this.a(this.A[n13], this.z[n13], this.A[n13 + 1], this.z[n13 + 1], this.d + 1, this.d);
        for (int j = n; j < n2; ++j) {
            final int max = Math.max(this.D[j] - this.e - 1 & ~(this.e - 1), 0);
            final int min = Math.min(this.D[j], n12 - 1);
            int n14 = j * n10 + max * n11;
            if (n6 == 1) {
                for (int k = max; k <= min; ++k) {
                    array2[n14] = -16777216;
                    n14 += n11;
                }
            }
            else {
                final int n15 = this.w - (this.D[j] - max);
                int n16 = this.o + n15 * this.q;
                int n17 = this.p + n15 * this.r;
                for (int l = max; l <= min; ++l) {
                    try {
                        array2[n14] = this.a(array, b2, n16, n17, n8, n4, n3);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        array2[n14] = -16777216;
                    }
                    n14 += n11;
                    n16 += this.q;
                    n17 += this.r;
                }
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
        }
        this.a(this.B[n13], this.z[n13], this.B[n13 + 1], this.z[n13 + 1], this.d + 1, this.d);
        for (int n18 = n; n18 < n2; ++n18) {
            final int n19 = Math.min(this.D[n18] + this.w & ~(this.e - 1), n12) - 1;
            final int max2 = Math.max(this.D[n18] + 1, 0);
            int n20 = n18 * n10 + n19 * n11;
            if (n7 == 1) {
                for (int n21 = n19; n21 >= max2; --n21) {
                    array2[n20] = -16777216;
                    n20 -= n11;
                }
            }
            else {
                final int n22 = this.w + (this.D[n18] - n19);
                int n23 = this.o + n22 * this.q;
                int n24 = this.p + n22 * this.r;
                for (int n25 = n19; n25 >= max2; --n25) {
                    try {
                        array2[n20] = this.a(array, b2, n23, n24, n9, n4, n3);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        array2[n20] = -16777216;
                    }
                    n20 -= n11;
                    n23 += this.q;
                    n24 += this.r;
                }
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
        }
    }
    
    protected int a(final int[][] array, final Dimension dimension, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.n > 20) {
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
        return this.a(n11, n12, array[n14][n13], array[n14][n13 + 1], n, n2);
    }
}
