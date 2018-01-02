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
    
    public abstract void a(final Color p0, final double p1, final Graphics p2);
    
    protected void a(final double n) {
        for (int i = 0; i < this.e; ++i) {
            this.c[i] = (int)((this.a[0] - this.a[i]) * Math.cos(n) - (this.b[0] - this.b[i]) * Math.sin(n) + this.a[0]);
            this.d[i] = (int)((this.a[0] - this.a[i]) * Math.sin(n) + (this.b[0] - this.b[i]) * Math.cos(n) + this.b[0]);
        }
    }
    
    public int a() {
        return this.f;
    }
}
