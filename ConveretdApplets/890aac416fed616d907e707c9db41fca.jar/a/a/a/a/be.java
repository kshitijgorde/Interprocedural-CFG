// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class be implements ay
{
    ap s;
    ap u;
    int t;
    double r;
    
    public be() {
        this.s = null;
        this.u = null;
        this.t = 1;
        this.r = 0.0;
    }
    
    public void a(final ap u, final ap s) {
        this.u = u;
        this.s = s;
        this.r = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * this.t / 25;
        final int n6 = n3 * this.t / 25;
        a3.a(this.u, ap, n, n2, n3, n4);
        if (this.r < 0.25) {
            a3.a(this.s, ap, n - n3 + n6, n2 - n4 + n5, n3, n4);
        }
        else if (this.r < 0.5) {
            a3.a(this.s, ap, n - n3 + n6, n2 + n4 - n5, n3, n4);
        }
        else if (this.r < 0.75) {
            a3.a(this.s, ap, n + n3 - n6, n2 - n4 + n5, n3, n4);
        }
        else {
            a3.a(this.s, ap, n + n3 - n6, n2 + n4 - n5, n3, n4);
        }
        ++this.t;
    }
    
    public boolean a(final long n) {
        return this.t < 25;
    }
}
