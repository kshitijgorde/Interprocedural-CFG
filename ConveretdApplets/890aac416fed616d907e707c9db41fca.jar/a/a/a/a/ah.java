// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ah implements ay
{
    ap k;
    ap m;
    int l;
    
    public ah() {
        this.k = null;
        this.m = null;
        this.l = 1;
    }
    
    public void a(final ap m, final ap k) {
        this.m = m;
        this.k = k;
    }
    
    public void a(final ap ap, int n, int n2, final int n3, final int n4) {
        a3.a(this.m, ap, n, n2, n3, n4);
        final int n5 = n3 * this.l / 25;
        final int n6 = n4 * this.l / 25;
        n += n3 - n5 >> 1;
        n2 += n4 - n6 >> 1;
        a3.a(this.k, ap, n, n2, n5, n6);
        ++this.l;
    }
    
    public boolean a(final long n) {
        return this.l < 25;
    }
}
