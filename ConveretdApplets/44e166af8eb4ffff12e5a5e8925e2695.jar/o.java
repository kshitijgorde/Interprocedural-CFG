import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    public int j;
    protected l[] k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public a s;
    public String t;
    public String u;
    
    public o() {
        this.s = new a(new Color(255, 255, 255));
        this.t = "";
        this.u = "";
    }
    
    public void O(final int l, final int m) {
        for (int i = 0; i < this.j; ++i) {
            final l j = this.k[i];
            j.d += l - this.l;
            final l k = this.k[i];
            k.e += m - this.m;
        }
        this.l = l;
        this.m = m;
    }
    
    public void J() {
        this.r = 0;
    }
    
    public l N() {
        return this.k[this.r++];
    }
    
    public boolean P() {
        return this.r != this.j;
    }
    
    public void K() {
        ++this.q;
    }
    
    public void S() {
        this.q = 0;
    }
    
    public void Q(final a s) {
        this.s = s;
        for (int i = 0; i < this.j; ++i) {
            this.k[i].f = this.s.A(this.k[i].d, this.k[i].e);
        }
    }
    
    public void L(final String t, final String u) {
        this.t = t;
        this.u = u;
    }
    
    public o M(final int n, final int n2) {
        if (n > this.l - (this.n >> 1) & n < this.l + (this.n >> 1) & n2 > this.m - (this.o >> 1) & n2 < this.m + (this.o >> 1)) {
            return this;
        }
        return null;
    }
    
    protected static Color U(final Color color, final Color color2, final int n, final int n2) {
        return new Color((color.getRed() * (n2 - n) + color2.getRed() * n) / n2, (color.getGreen() * (n2 - n) + color2.getGreen() * n) / n2, (color.getBlue() * (n2 - n) + color2.getBlue() * n) / n2);
    }
    
    protected static double T(final l l, final int n, final int n2) {
        return Math.sqrt((l.d - n) * (l.d - n) + (l.e - n2) * (l.e - n2));
    }
    
    protected static double V(final l l, final int n, final int n2) {
        double asin = 0.0;
        if (T(l, n, n2) != 0.0) {
            asin = Math.asin((l.d - n) / T(l, n, n2));
        }
        if (l.e - n2 > 0) {
            asin = 3.141592653589793 - asin;
        }
        if (asin < 0.0) {
            asin += 6.283185307179586;
        }
        return asin;
    }
    
    protected static l R(final int n, final int n2, final double n3, final double n4, final Color color) {
        return new l(n + (int)(n3 * Math.sin(n4)), n2 - (int)(n3 * Math.cos(n4)), color);
    }
}
