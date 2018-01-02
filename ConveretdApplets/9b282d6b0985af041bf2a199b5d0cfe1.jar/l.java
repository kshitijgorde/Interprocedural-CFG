import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class l extends k
{
    protected int e;
    protected int f;
    protected static final int g = 12;
    protected static final int h = 4096;
    protected static final int i = 4095;
    protected Point[] j;
    protected Point[] k;
    protected int l;
    protected int m;
    protected Rectangle[] n;
    protected int o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    
    l() {
        this.e = 5;
        this.f = 1 << this.e;
        this.o = 0;
        super.a("Viewpoint", new float[] { 0.0f, 0.0f, 0.0f, 1.0f });
        super.a("Warp", new Float(1.0f));
    }
    
    protected void a(final Dimension dimension) {
        final int a = a(dimension.width, this.f);
        final int a2 = a(dimension.height, this.f);
        this.l = a >> this.e;
        this.m = a2 >> this.e;
        if (dimension.width == 0 || dimension.height == 0) {
            return;
        }
        this.j = new Point[(this.l + 1) * (this.m + 1)];
        this.k = new Point[this.j.length];
        int n = 0;
        for (int i = 0; i <= a2; i += this.f) {
            for (int j = 0; j <= a; j += this.f, ++n) {
                this.k[n] = new Point(j, i);
                this.j[n] = new Point(0, 0);
            }
        }
        this.n = new Rectangle[this.l * this.m];
        int n2 = 0;
        int n3 = 0;
        for (int k = 0; k < a2; k += this.f, ++n2) {
            for (int l = 0; l < a; l += this.f, ++n3, ++n2) {
                this.n[n3] = new Rectangle();
                this.n[n3].x = l;
                this.n[n3].y = k;
                this.n[n3].width = Math.min(this.f, dimension.width - l);
                this.n[n3].height = Math.min(this.f, dimension.height - k);
            }
        }
    }
    
    protected final void b() {
        for (int i = 0; i < this.j.length; ++i) {
            this.a(this.k[i], this.j[i]);
        }
    }
    
    protected void a(final Point point, final Point point2, final Point point3, final Point point4, final int n, final int n2) {
        this.p = point.x;
        this.q = point.y;
        this.r = point2.x - point.x >> n;
        this.s = point2.y - point.y >> n;
        this.t = point3.x - point.x >> n2;
        this.u = point3.y - point.y >> n2;
        this.v = point4.x - point3.x - point2.x + point.x >> n + n2;
        this.w = point4.y - point3.y - point2.y + point.y >> n + n2;
    }
    
    protected abstract void a(final Point p0, final Point p1);
    
    protected static final int a(final int n, final int n2) {
        return n + n2 - 1 & ~(n2 - 1);
    }
}
