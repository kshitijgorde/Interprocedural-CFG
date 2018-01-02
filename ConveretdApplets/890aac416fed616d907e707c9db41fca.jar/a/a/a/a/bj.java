// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bj implements ay
{
    ap w;
    ap y;
    int x;
    double v;
    
    public bj() {
        this.w = null;
        this.y = null;
        this.x = 1;
        this.v = 0.0;
    }
    
    public void a(final ap y, final ap w) {
        this.y = y;
        this.w = w;
        this.v = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 * this.x / 25;
        if (this.v < 0.5) {
            a3.a(this.y, ap, n + n5, n2, n3 - n5, n4);
            a3.a(this.w, ap, n, n2, n5, n4);
        }
        else {
            a3.a(this.y, ap, n, n2, n3 - n5, n4);
            a3.a(this.w, ap, n + n3 - n5, n2, n5, n4);
        }
        ++this.x;
    }
    
    public boolean a(final long n) {
        return this.x < 25;
    }
}
