// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dE extends bX implements aF
{
    public final Object q(final String s) {
        if ("time".equals(s)) {
            if (super.q == 0) {
                return "";
            }
            if (super.q == Integer.MAX_VALUE) {
                return eb.q("for ever");
            }
            return String.valueOf(super.q / 60);
        }
        else if ("name".equals(s)) {
            if (ci.q(this.getName())) {
                return ci.w(this.getName());
            }
            return this.getName();
        }
        else {
            if ("ID".equals(s)) {
                return new Integer(super.r);
            }
            return null;
        }
    }
    
    public dE(final int n, final String s) {
        super(-999, s);
    }
    
    public dE(final bX bx) {
        super(bx.r, bx.getName());
        this.q(bx.q());
        this.q = bx.q;
    }
}
