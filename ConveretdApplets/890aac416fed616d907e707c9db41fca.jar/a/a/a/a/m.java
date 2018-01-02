// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class m implements ay
{
    ap new;
    ap byte;
    int try;
    double int;
    
    public m() {
        this.new = null;
        this.byte = null;
        this.try = 1;
        this.int = 0.0;
    }
    
    public void a(final ap byte1, final ap new1) {
        this.byte = byte1;
        this.new = new1;
        this.int = Math.random();
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * (25 - this.try) / 25;
        final int n6 = n3 * (25 - this.try) / 25;
        a3.a(this.new, ap, n, n2, n3, n4);
        if (this.int < 0.25) {
            a3.a(this.byte, ap, n, n2, n6, n5);
        }
        else if (this.int < 0.5) {
            a3.a(this.byte, ap, n, n2 + n4 - n5, n6, n5);
        }
        else if (this.int < 0.75) {
            a3.a(this.byte, ap, n + n3 - n6, n2, n6, n5);
        }
        else {
            a3.a(this.byte, ap, n + n3 - n6, n2 + n4 - n5, n6, n5);
        }
        ++this.try;
    }
    
    public boolean a(final long n) {
        return this.try < 25;
    }
}
