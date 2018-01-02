// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class br implements ay
{
    ap A;
    ap C;
    int B;
    double z;
    
    public br() {
        this.A = null;
        this.C = null;
        this.B = 1;
        this.z = 0.0;
    }
    
    public void a(final ap c, final ap a) {
        this.C = c;
        this.A = a;
        this.z = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 * this.B / 25;
        if (this.z < 0.5) {
            a3.a(this.C, ap, n, n2, n3, n4);
            a3.a(this.A, ap, n - n3 + n5, n2, n3, n4);
        }
        else {
            a3.a(this.C, ap, n, n2, n3, n4);
            a3.a(this.A, ap, n + n3 - n5, n2, n3, n4);
        }
        ++this.B;
    }
    
    public boolean a(final long n) {
        return this.B < 25;
    }
}
