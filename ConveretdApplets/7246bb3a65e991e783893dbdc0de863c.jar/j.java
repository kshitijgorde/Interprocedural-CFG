import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class j extends i
{
    protected int d;
    protected int e;
    protected static final int f = 12;
    protected static final int g = 4096;
    protected static final int h = 4095;
    protected Point[] i;
    protected Point[] j;
    protected int k;
    protected int l;
    protected Rectangle[] m;
    protected int n;
    protected int o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    
    j() {
        this.d = 5;
        this.e = 1 << this.d;
        this.n = 0;
        super.a("ptrz", new float[] { 0.0f, 0.0f, 0.0f, 1.0f });
    }
    
    protected static final int a(final int n, final int n2) {
        return n + n2 - 1 & ~(n2 - 1);
    }
    
    protected void a(final Dimension dimension) {
        final int a = a(dimension.width, this.e);
        final int a2 = a(dimension.height, this.e);
        this.k = a >> this.d;
        this.l = a2 >> this.d;
        if (dimension.width == 0 || dimension.height == 0) {
            return;
        }
        this.i = new Point[(this.k + 1) * (this.l + 1)];
        this.j = new Point[this.i.length];
        int n = 0;
        for (int i = 0; i <= a2; i += this.e) {
            for (int j = 0; j <= a; j += this.e, ++n) {
                this.j[n] = new Point(j, i);
                this.i[n] = new Point(0, 0);
            }
        }
        this.m = new Rectangle[this.k * this.l];
        int n2 = 0;
        int n3 = 0;
        for (int k = 0; k < a2; k += this.e, ++n2) {
            for (int l = 0; l < a; l += this.e, ++n3, ++n2) {
                this.m[n3] = new Rectangle();
                this.m[n3].x = l;
                this.m[n3].y = k;
                this.m[n3].width = Math.min(this.e, dimension.width - l);
                this.m[n3].height = Math.min(this.e, dimension.height - k);
            }
        }
    }
    
    protected final void b() {
        for (int i = 0; i < this.i.length; ++i) {
            this.a(this.j[i], this.i[i]);
        }
    }
    
    protected abstract void a(final Point p0, final Point p1);
    
    protected void a(final Point point, final Point point2, final Point point3, final Point point4, final int n, final int n2) {
        this.o = point.x;
        this.p = point.y;
        this.q = point2.x - point.x >> n;
        this.r = point2.y - point.y >> n;
        this.s = point3.x - point.x >> n2;
        this.t = point3.y - point.y >> n2;
        this.u = point4.x - point3.x - point2.x + point.x >> n + n2;
        this.v = point4.y - point3.y - point2.y + point.y >> n + n2;
    }
}
