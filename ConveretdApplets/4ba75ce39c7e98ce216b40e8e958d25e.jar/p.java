import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class p extends o
{
    protected int y;
    protected int z;
    protected boolean A;
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.y = (int)(Math.log(super.a.a().elementAt(0).b().height) / Math.log(2.0));
        this.z = (1 << this.y) - 1;
    }
    
    protected void a(final Point point, final Point point2, final Point point3, final Point point4, final int n, final int n2) {
        super.a(point, point2, point3, point4, n, n2);
        final int n3 = this.y + 12;
        final int n4 = point.y >> n3;
        final int n5 = point2.y >> n3;
        final int n6 = point3.y >> n3;
        final int n7 = point4.y >> n3;
        this.A = (n4 != n5 || n4 != n6 || n4 != n7 || n5 != n6 || n5 != n7 || n6 != n7);
    }
    
    protected void a(final Rectangle rectangle) {
        if (super.a.a().size() == 1) {
            super.a(rectangle);
            return;
        }
        if (this.A) {
            this.b(rectangle);
            return;
        }
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(super.n >> 12 + this.y).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        super.n &= (1 + this.z << 12) - 1;
        for (int i = 0; i < rectangle.height; ++i) {
            if (super.m < 0) {
                super.m += c.width << 12;
            }
            int m = super.m;
            int n2 = super.n;
            int n3 = n;
            for (int j = 0; j < rectangle.width; ++j) {
                try {
                    a2[n3] = a[(n2 >> 12) * c.width + (m >> 12)];
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a2[n3] = -16777216;
                }
                ++n3;
                m += super.o;
                n2 += super.p;
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
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
            if (super.m < 0) {
                super.m += n2;
            }
            else if (super.m >= n2) {
                super.m -= n2;
            }
            int m = super.m;
            int n3 = super.n;
            int n4 = n;
            for (int k = 0; k < rectangle.width; ++k) {
                final int n5 = n3 >> 12;
                final int n6 = (n5 & this.z) * c.width + (m >> 12);
                try {
                    a[n4] = array[n5 >> this.y][n6];
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    try {
                        final int n7 = n6 + ((n6 < 0) ? c.width : (-c.width));
                        m += ((n7 < 0) ? n2 : (-n2));
                        a[n4] = array[n5 >> this.y][n7];
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        a[n4] = -16777216;
                    }
                }
                ++n4;
                m += super.o;
                n3 += super.p;
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
            n += c2.width;
        }
    }
}
