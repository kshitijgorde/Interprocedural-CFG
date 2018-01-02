// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ao implements ay
{
    ap o;
    ap q;
    int p;
    double n;
    
    public ao() {
        this.o = null;
        this.q = null;
        this.p = 1;
        this.n = 0.0;
    }
    
    public void a(final ap q, final ap o) {
        this.q = q;
        this.o = o;
        this.n = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * this.p / 25;
        final int n6 = n3 * this.p / 25;
        a3.a(this.q, ap, n, n2, n3, n4);
        if (this.n < 0.25) {
            a3.a(this.o, ap, n, n2, n6, n5);
        }
        else if (this.n < 0.5) {
            a3.a(this.o, ap, n, n2 + n4 - n5, n6, n5);
        }
        else if (this.n < 0.75) {
            a3.a(this.o, ap, n + n3 - n6, n2, n6, n5);
        }
        else {
            a3.a(this.o, ap, n + n3 - n6, n2 + n4 - n5, n6, n5);
        }
        ++this.p;
    }
    
    public boolean a(final long n) {
        return this.p < 25;
    }
}
