import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class o extends l
{
    protected float[] u;
    protected float v;
    protected float w;
    protected float x;
    
    void a(final String s, final Object o) {
        if (s.equals("Viewpoint")) {
            this.u = (float[])o;
        }
        else if (s.equals("Warp")) {
            this.v = (float)o;
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
        this.u = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
        this.v = 1.0f;
        super.a("Viewpoint", this.u);
        super.a("Warp", new Float(this.v));
    }
    
    float a(final float n) {
        return 6.2831855f * super.b.c().height / super.a.c().width / n;
    }
    
    float b(final float n) {
        final float n2 = 2.8f;
        final float n3 = super.b.c().height / (super.a.c().height - 4);
        if (n3 < n2) {
            return bm.b(n, n3, n2);
        }
        return n3;
    }
    
    protected void a(final Point point, final Point point2) {
        final float n = this.w * this.v;
        final float n2 = this.u[3] * this.v;
        final float n3 = point.x - super.b.b()[0];
        final float n4 = point.y - super.b.b()[1];
        final float n5 = n3 / n2;
        final float n6 = n4 / n2;
        final float n7 = (float)Math.atan2(n5, this.w) * n;
        final float n8 = n6 * n / (float)Math.sqrt(this.w * this.w + n5 * n5);
        final float n9 = n7 + this.u[0] * this.x;
        final float n10 = n8 - this.u[1] * this.x;
        final float n11 = n9 + super.b.b()[0];
        float n12 = n10 + super.a.b()[1];
        if (n12 < 1.0f) {
            n12 = 1.0f;
        }
        point2.x = (int)(n11 * 4096.0f);
        point2.y = (int)(n12 * 4096.0f);
    }
    
    void a() {
        this.b();
        int n = 0;
        for (int n2 = 0, i = 0; i < super.b.c().height; i += super.e, ++n2) {
            for (int j = 0; j < super.b.c().width; j += super.e, ++n2, ++n) {
                this.a(super.h[n2], super.h[n2 + 1], super.h[n2 + super.j + 1], super.h[n2 + super.j + 2], super.d, super.d);
                this.a(super.l[n]);
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
    
    void b(final t t) {
        if (super.a == t) {
            return;
        }
        try {
            this.x = t.c().width / 6.2831855f;
            this.w = this.x / 1.5f;
            super.b(t);
        }
        catch (NullPointerException ex) {}
    }
    
    void a(final float[] array) {
        array[3] = this.b(array[3]);
        array[0] = bm.a(array[0], -3.1415927f, 3.1415927f);
        final Dimension c = super.b.c();
        final Dimension c2 = super.a.c();
        final float floatValue = (float)super.a.a("vFOV");
        final float n = floatValue / 2.0f - floatValue / (2.0f * array[3]) * (c.height + 2) / c2.height;
        array[1] = bm.b(array[1], -n, n);
    }
}
