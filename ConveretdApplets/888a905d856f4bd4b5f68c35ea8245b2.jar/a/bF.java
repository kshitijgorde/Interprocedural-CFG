// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bF extends bW implements bJ
{
    public final Object q(final String s) {
        if ("time".equals(s)) {
            if (super.q == 0) {
                return "";
            }
            if (super.q == Integer.MAX_VALUE) {
                return be.w("for ever");
            }
            return String.valueOf(super.q / 60);
        }
        else if ("name".equals(s)) {
            if (l.q(super.a)) {
                return l.w(super.a);
            }
            return super.a;
        }
        else {
            if ("ID".equals(s)) {
                return new Integer(super.s);
            }
            return null;
        }
    }
    
    public bF(final int n, final String s) {
        super(-999, s);
    }
    
    public bF(final bW bw) {
        super(bw.s, bw.a);
        this.q(bw.w());
        this.q = bw.q;
    }
}
