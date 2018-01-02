import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class a
{
    protected int[] a;
    protected int[] b;
    protected int[] c;
    protected int[] d;
    protected int e;
    protected int f;
    public static int g;
    
    public a(final int n, final int n2, final int n3, final int f, final int e) {
        this.a = new int[e];
        this.b = new int[e];
        this.c = new int[e];
        this.d = new int[e];
        this.a(n, n2, n3, f);
        this.e = e;
        this.f = f;
    }
    
    protected abstract void a(final int p0, final int p1, final int p2, final int p3);
    
    public abstract void draw(final Color p0, final double p1, final Graphics p2);
    
    protected void a(final double n) {
        final boolean h = b.h;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0123: {
                    if (!h) {
                        break Label_0123;
                    }
                    this.c[n2] = (int)((this.a[0] - this.a[n2]) * Math.cos(n) - (this.b[0] - this.b[n2]) * Math.sin(n) + this.a[0]);
                    this.d[n2] = (int)((this.a[0] - this.a[n2]) * Math.sin(n) + (this.b[0] - this.b[n2]) * Math.cos(n) + this.b[0]);
                    ++n2;
                }
                if (n2 < this.e) {
                    continue;
                }
                break;
            }
            if (!h) {
                return;
            }
            continue;
        }
    }
    
    public int getThickness() {
        return this.f;
    }
}
