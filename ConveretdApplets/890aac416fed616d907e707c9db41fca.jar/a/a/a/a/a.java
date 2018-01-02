// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a implements ay
{
    long Q;
    ap N;
    ap R;
    long P;
    int L;
    int S;
    int O;
    double M;
    
    public a() {
        this.Q = -1L;
        this.N = null;
        this.R = null;
        this.P = 0L;
        this.L = 0;
        this.S = 0;
        this.O = 1;
        this.M = 0.0;
    }
    
    public void a(final ap r, final ap n) {
        this.R = r;
        this.N = n;
        this.M = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * this.O / 25;
        if (this.N.s <= 0) {
            ++this.O;
            return;
        }
        if (this.M < 0.5) {
            a3.a(this.R, ap, n, n2 + n5, n3, n4 - n5);
            a3.a(this.N, ap, n, n2, n3, n5);
        }
        else {
            a3.a(this.R, ap, n, n2, n3, n4 - n5);
            a3.a(this.N, ap, n, n2 + n4 - n5, n3, n5);
        }
        ++this.O;
    }
    
    public boolean a(final long n) {
        return this.O < 25;
    }
}
