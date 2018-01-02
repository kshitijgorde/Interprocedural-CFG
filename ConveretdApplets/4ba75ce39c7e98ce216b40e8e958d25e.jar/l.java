import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class l extends k
{
    protected int d;
    protected int e;
    protected static final int f = 12;
    protected static final int g = 4096;
    protected Point[] h;
    protected Point[] i;
    protected int j;
    protected int k;
    protected Rectangle[] l;
    protected int m;
    protected int n;
    protected int o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    
    Object a(final String s) {
        if (s.equals("Viewpoint")) {
            final float[] array = (float[])super.a(s);
            final float[] array2 = new float[array.length];
            System.arraycopy(array, 0, array2, 0, array.length);
            return array2;
        }
        return super.a(s);
    }
    
    l() {
        this.d = 5;
        this.e = 1 << this.d;
        super.a("Viewpoint", new float[] { 0.0f, 0.0f, 0.0f, 1.0f });
        super.a("Warp", new Float(1.0f));
    }
    
    protected void a(final Dimension dimension) {
        final int a = a(dimension.width, this.e);
        final int a2 = a(dimension.height, this.e);
        this.j = a >> this.d;
        this.k = a2 >> this.d;
        if (dimension.width == 0 || dimension.height == 0) {
            return;
        }
        this.h = new Point[(this.j + 1) * (this.k + 1)];
        this.i = new Point[this.h.length];
        int n = 0;
        for (int i = 0; i <= a2; i += this.e) {
            for (int j = 0; j <= a; j += this.e, ++n) {
                this.i[n] = new Point(j, i);
                this.h[n] = new Point(0, 0);
            }
        }
        this.l = new Rectangle[this.j * this.k];
        int n2 = 0;
        int n3 = 0;
        for (int k = 0; k < a2; k += this.e, ++n2) {
            for (int l = 0; l < a; l += this.e, ++n3, ++n2) {
                this.l[n3] = new Rectangle();
                this.l[n3].x = l;
                this.l[n3].y = k;
                this.l[n3].width = Math.min(this.e, dimension.width - l);
                this.l[n3].height = Math.min(this.e, dimension.height - k);
            }
        }
    }
    
    protected final void b() {
        for (int i = 0; i < this.h.length; ++i) {
            this.a(this.i[i], this.h[i]);
        }
    }
    
    protected void a(final Point point, final Point point2, final Point point3, final Point point4, final int n, final int n2) {
        this.m = point.x;
        this.n = point.y;
        this.o = point2.x - point.x >> n;
        this.p = point2.y - point.y >> n;
        this.q = point3.x - point.x >> n2;
        this.r = point3.y - point.y >> n2;
        this.s = point4.x - point3.x - point2.x + point.x >> n + n2;
        this.t = point4.y - point3.y - point2.y + point.y >> n + n2;
    }
    
    protected abstract void a(final Point p0, final Point p1);
    
    protected static final int a(final int n, final int n2) {
        return n + n2 - 1 & ~(n2 - 1);
    }
}
