import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class o extends l
{
    protected float[] x;
    protected float y;
    protected float z;
    protected float A;
    
    void a(final String s, final Object o) {
        if (s.equals("Warp")) {
            this.y = (float)o;
        }
        super.a(s, o);
    }
    
    void a(final t t) {
        if (super.b == t) {
            return;
        }
        try {
            this.a(t.c());
            super.a(t);
        }
        catch (NullPointerException ex) {}
    }
    
    o() {
        this.x = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
        this.y = 1.0f;
        super.a("Viewpoint", this.x);
        super.a("Warp", new Float(this.y));
    }
    
    float a(final float n) {
        return 6.2831855f * super.b.c().height / super.a.c().width / n;
    }
    
    float b(final float n) {
        final float n2 = 2.8f;
        final float n3 = super.b.c().height / (super.a.c().height - 4);
        if (n3 < n2) {
            return bn.b(n, n3, n2);
        }
        return n3;
    }
    
    protected void a(final Point point, final Point point2) {
        final float n = point.x - super.b.b()[0];
        final float n2 = point.y - super.b.b()[1];
        float n3;
        float n4;
        if (this.y == 0.0f) {
            final int width = super.a.c().width;
            n3 = n / this.x[3] + this.x[0] / 6.2831855f * width;
            n4 = n2 / this.x[3] - this.x[1] / 6.2831855f * width;
        }
        else {
            final float n5 = this.z * this.y;
            final float n6 = this.x[3] * this.y;
            final float n7 = n / n6;
            final float n8 = n2 / n6;
            final float n9 = (float)Math.atan2(n7, this.z) * n5;
            final float n10 = n8 * n5 / (float)Math.sqrt(this.z * this.z + n7 * n7);
            n3 = n9 + this.x[0] * this.A;
            n4 = n10 - this.x[1] * this.A;
        }
        final float n11 = n3 + super.b.b()[0];
        float n12 = n4 + super.a.b()[1];
        if (n12 < 1.0f) {
            n12 = 1.0f;
        }
        point2.x = (int)(n11 * 4096.0f);
        point2.y = (int)(n12 * 4096.0f);
    }
    
    void a() {
        final float[] x = (float[])this.a("Viewpoint");
        try {
            final float[] b = bn.b(x, this.x);
            final float n = (float)Math.sqrt(b[0] * b[0] + b[1] * b[1] + b[3] * b[3]);
            final Dimension c = super.b.c();
            super.o = (int)(n * (6.2831855f * (float)Math.sqrt(c.width * c.width + c.height * c.height) / super.a.c().width / x[3]) * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.x = x;
        this.b();
        int n2 = 0;
        for (int n3 = 0, i = 0; i < super.b.c().height; i += super.f, ++n3) {
            for (int j = 0; j < super.b.c().width; j += super.f, ++n3, ++n2) {
                this.a(super.j[n3], super.j[n3 + 1], super.j[n3 + super.l + 1], super.j[n3 + super.l + 2], super.e, super.e);
                this.a(super.n[n2]);
            }
        }
    }
    
    protected void a(final Rectangle rectangle) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(0).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        for (int i = 0; i < rectangle.height; ++i) {
            int p = super.p;
            int q = super.q;
            int n2 = n;
            for (int j = 0; j < rectangle.width; ++j) {
                try {
                    a2[n2] = this.a(a, c, p, q);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a2[n2] = -16777216;
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
    
    void b(final t t) {
        if (super.a == t) {
            return;
        }
        try {
            this.A = t.c().width / 6.2831855f;
            this.z = this.A / 1.5f;
            super.b(t);
        }
        catch (NullPointerException ex) {}
    }
    
    void a(final float[] array) {
        array[3] = this.b(array[3]);
        array[0] = bn.a(array[0], -3.1415927f, 3.1415927f);
        final Dimension c = super.b.c();
        final Dimension c2 = super.a.c();
        final float floatValue = (float)super.a.a("vFOV");
        final float n = floatValue / 2.0f - floatValue / (2.0f * array[3]) * (c.height + 2) / c2.height;
        array[1] = bn.b(array[1], -n, n);
    }
    
    protected int a(final int[] array, final Dimension dimension, final int n, final int n2) {
        final int n3 = (n2 >> 12) * dimension.width + (n >> 12);
        if (super.o > 30) {
            return array[n3];
        }
        final int n4 = array[n3];
        final int n5 = array[n3 + 1];
        final int n6 = array[n3 + dimension.width];
        final int n7 = array[n3 + dimension.width + 1];
        final int n8 = (n4 & 0xFF0000) >> 16;
        final int n9 = (n4 & 0xFF00) >> 8;
        final int n10 = n4 & 0xFF;
        final int n11 = (n5 & 0xFF0000) >> 16;
        final int n12 = (n5 & 0xFF00) >> 8;
        final int n13 = n5 & 0xFF;
        final int n14 = (n6 & 0xFF0000) >> 16;
        final int n15 = (n6 & 0xFF00) >> 8;
        final int n16 = n6 & 0xFF;
        final int n17 = (n7 & 0xFF0000) >> 16;
        final int n18 = (n7 & 0xFF00) >> 8;
        final int n19 = n7 & 0xFF;
        final int n20 = (n & 0xFFF) >> 4;
        final int n21 = (n2 & 0xFFF) >> 4;
        return 0xFF000000 | (((n17 - n14) * n20 + (n14 << 8)) * n21 + ((n11 - n8) * n20 + (n8 << 8)) * (256 - n21) & 0xFF0000) | (((n18 - n15) * n20 + (n15 << 8)) * n21 + ((n12 - n9) * n20 + (n9 << 8)) * (256 - n21) >> 8 & 0xFF00) | (((n19 - n16) * n20 + (n16 << 8)) * n21 + ((n13 - n10) * n20 + (n10 << 8)) * (256 - n21) >> 16 & 0xFF);
    }
}
