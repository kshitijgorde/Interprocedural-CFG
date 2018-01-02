import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class p extends o
{
    protected int B;
    protected int C;
    protected boolean D;
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.B = (int)(Math.log(super.a.a().elementAt(0).b().height) / Math.log(2.0));
        this.C = (1 << this.B) - 1;
    }
    
    protected void a(final Point point, final Point point2, final Point point3, final Point point4, final int n, final int n2) {
        super.a(point, point2, point3, point4, n, n2);
        final int n3 = this.B + 12;
        final int n4 = point.y >> n3;
        final int n5 = point2.y >> n3;
        final int n6 = point3.y >> n3;
        final int n7 = point4.y >> n3;
        this.D = (n4 != n5 || n4 != n6 || n4 != n7 || n5 != n6 || n5 != n7 || n6 != n7);
    }
    
    protected void a(final Rectangle rectangle) {
        if (super.a.a().size() == 1) {
            super.a(rectangle);
            return;
        }
        if (this.D) {
            this.b(rectangle);
            return;
        }
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[][] array = new int[super.a.a().size()][];
        for (int i = 0; i < super.a.a().size(); ++i) {
            array[i] = ((z)super.a.a().elementAt(i)).a();
        }
        final int[] a = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        for (int j = 0; j < rectangle.height; ++j) {
            if (super.p < 0) {
                super.p += c.width << 12;
            }
            int p = super.p;
            int q = super.q;
            int n2 = n;
            for (int k = 0; k < rectangle.width; ++k) {
                try {
                    a[n2] = this.a(array, c, p, q, this.C, this.B);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a[n2] = -16777216;
                }
                ++n2;
                p += super.r;
                q += super.s;
            }
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            super.s += super.w;
            n += c2.width;
        }
    }
    
    protected void b(final Rectangle rectangle) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[][] array = new int[super.a.a().size()][];
        for (int i = 0; i < super.a.a().size(); ++i) {
            array[i] = ((z)super.a.a().elementAt(i)).a();
        }
        final int[] a = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        final int n2 = c.width << 12;
        for (int j = 0; j < rectangle.height; ++j) {
            if (super.p < 0) {
                super.p += n2;
            }
            else if (super.p >= n2) {
                super.p -= n2;
            }
            int p = super.p;
            int q = super.q;
            int n3 = n;
            for (int k = 0; k < rectangle.width; ++k) {
                try {
                    a[n3] = this.a(array, c, p, q, this.C, this.B);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    try {
                        if (p < 0) {
                            p += n2;
                            q -= 1 << this.B;
                        }
                        else {
                            p -= n2;
                            q += 1 << this.B;
                        }
                        a[n3] = this.a(array, c, p, q, this.C, this.B);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        a[n3] = -16777216;
                    }
                }
                ++n3;
                p += super.r;
                q += super.s;
            }
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            super.s += super.w;
            n += c2.width;
        }
    }
    
    protected int a(final int[][] array, final Dimension dimension, final int n, final int n2, final int n3, final int n4) {
        if (super.o > 30) {
            final int n5 = n2 >> 12;
            return array[n5 >> n4][(n5 & n3) * dimension.width + (n >> 12)];
        }
        int n6 = n2 >> 12;
        final int n7 = n >> 12;
        final int n8 = (n6 & n3) * dimension.width + n7;
        final int n9 = n6 >> n4;
        final int n10 = array[n9][n8];
        final int n11 = array[n9][n8 + 1];
        final int n12 = (++n6 & n3) * dimension.width + n7;
        final int n13 = n6 >> n4;
        final int n14 = array[n13][n12];
        final int n15 = array[n13][n12 + 1];
        final int n16 = (n10 & 0xFF0000) >> 16;
        final int n17 = (n10 & 0xFF00) >> 8;
        final int n18 = n10 & 0xFF;
        final int n19 = (n11 & 0xFF0000) >> 16;
        final int n20 = (n11 & 0xFF00) >> 8;
        final int n21 = n11 & 0xFF;
        final int n22 = (n14 & 0xFF0000) >> 16;
        final int n23 = (n14 & 0xFF00) >> 8;
        final int n24 = n14 & 0xFF;
        final int n25 = (n15 & 0xFF0000) >> 16;
        final int n26 = (n15 & 0xFF00) >> 8;
        final int n27 = n15 & 0xFF;
        final int n28 = (n & 0xFFF) >> 4;
        final int n29 = (n2 & 0xFFF) >> 4;
        return 0xFF000000 | (((n25 - n22) * n28 + (n22 << 8)) * n29 + ((n19 - n16) * n28 + (n16 << 8)) * (256 - n29) & 0xFF0000) | (((n26 - n23) * n28 + (n23 << 8)) * n29 + ((n20 - n17) * n28 + (n17 << 8)) * (256 - n29) >> 8 & 0xFF00) | (((n27 - n24) * n28 + (n24 << 8)) * n29 + ((n21 - n18) * n28 + (n18 << 8)) * (256 - n29) >> 16 & 0xFF);
    }
}
