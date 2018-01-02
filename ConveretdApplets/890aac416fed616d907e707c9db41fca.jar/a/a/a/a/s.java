// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class s implements ay
{
    long b;
    ap long;
    ap c;
    long void;
    
    public s() {
        this.b = -1L;
        this.long = null;
        this.c = null;
        this.void = 0L;
    }
    
    public void a(final ap c, final ap long1) {
        this.c = c;
        this.long = long1;
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        try {
            int n5 = 0;
            int n6 = (int)((this.b - this.void) * 255L) >> 11;
            if (n6 > 255) {
                n6 = 255;
            }
            if (n6 < 0) {
                n6 = 0;
            }
            final int n7 = n6 << 24;
            while (true) {
                this.long.x[n5] = ((this.long.x[n5] & 0xFFFFFF) | n7);
                ++n5;
            }
        }
        catch (Exception ex) {
            this.long.i = true;
            this.long.v = true;
            a3.a(this.c, ap, n, n2, n3, n4);
            a3.a(this.long, ap, n, n2, n3, n4);
        }
    }
    
    public boolean a(final long n) {
        if (this.void == 0L) {
            this.void = n;
        }
        this.b = n;
        return this.void + 2048L >= n;
    }
}
