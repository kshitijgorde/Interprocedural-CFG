// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bz implements ay
{
    long I;
    ap F;
    ap J;
    long H;
    int D;
    int K;
    int G;
    double E;
    
    public bz() {
        this.I = -1L;
        this.F = null;
        this.J = null;
        this.H = 0L;
        this.D = 0;
        this.K = 0;
        this.G = 1;
        this.E = 0.0;
    }
    
    public void a(final ap j, final ap f) {
        this.J = j;
        this.F = f;
        this.E = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * this.G / 25;
        if (this.E < 0.5) {
            a3.a(this.J, ap, n, n2, n3, n4);
            a3.a(this.F, ap, n, n2 - n4 + n5, n3, n4);
        }
        else {
            a3.a(this.J, ap, n, n2, n3, n4);
            a3.a(this.F, ap, n, n2 + n4 - n5, n3, n4);
        }
        ++this.G;
    }
    
    public boolean a(final long n) {
        return this.G < 25;
    }
}
