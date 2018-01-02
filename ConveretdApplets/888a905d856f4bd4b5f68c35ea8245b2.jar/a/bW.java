// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class bW extends bp
{
    public int q;
    
    public bW(final int n, final String s) {
        super(n, s);
        this.q(0, l.q(s));
    }
    
    public final int q(final bW bw) {
        final int q;
        if ((q = super.q(bw)) != 0) {
            return q;
        }
        if (this.q != bw.q) {
            return this.q - bw.q;
        }
        return 0;
    }
}
