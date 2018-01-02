// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class bX extends bZ
{
    public int q;
    
    public bX(final int n, final String s) {
        super(n, s);
        this.q(0, ci.q(s));
    }
    
    public final boolean q() {
        return this.q(0);
    }
    
    public final void q(final boolean b) {
        this.q(0, b);
    }
    
    public final int q(final bX bx) {
        final int q;
        if ((q = super.q(bx)) != 0) {
            return q;
        }
        if (this.q != bx.q) {
            return this.q - bx.q;
        }
        return 0;
    }
}
