// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ag implements ay
{
    ap h;
    ap j;
    int i;
    
    public ag() {
        this.h = null;
        this.j = null;
        this.i = 1;
    }
    
    public void a(final ap j, final ap h) {
        this.j = j;
        this.h = h;
    }
    
    public void a(final ap ap, int n, int n2, final int n3, final int n4) {
        a3.a(this.h, ap, n, n2, n3, n4);
        final int n5 = n3 * (25 - this.i) / 25;
        final int n6 = n4 * (25 - this.i) / 25;
        n += n3 - n5 >> 1;
        n2 += n4 - n6 >> 1;
        a3.a(this.j, ap, n, n2, n5, n6);
        ++this.i;
    }
    
    public boolean a(final long n) {
        return this.i < 25;
    }
}
