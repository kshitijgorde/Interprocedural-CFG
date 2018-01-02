// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class g implements ay
{
    ap if;
    ap for;
    int do;
    double a;
    
    public g() {
        this.if = null;
        this.for = null;
        this.do = 1;
        this.a = 0.0;
    }
    
    public void a(final ap for1, final ap if1) {
        this.for = for1;
        this.if = if1;
        this.a = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * (25 - this.do) / 25;
        final int n6 = n3 * (25 - this.do) / 25;
        a3.a(this.if, ap, n, n2, n3, n4);
        if (this.a < 0.25) {
            a3.a(this.for, ap, n - n3 + n6, n2 - n4 + n5, n3, n4);
        }
        else if (this.a < 0.5) {
            a3.a(this.for, ap, n - n3 + n6, n2 + n4 - n5, n3, n4);
        }
        else if (this.a < 0.75) {
            a3.a(this.for, ap, n + n3 - n6, n2 - n4 + n5, n3, n4);
        }
        else {
            a3.a(this.for, ap, n + n3 - n6, n2 + n4 - n5, n3, n4);
        }
        ++this.do;
    }
    
    public boolean a(final long n) {
        return this.do < 25;
    }
}
