// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cj extends cB implements bJ
{
    public boolean q;
    public boolean w;
    
    public Object q(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.r());
        }
        if (!"lock".equals(s)) {
            return super.q(s);
        }
        if (this.q) {
            return dN.e;
        }
        if (super.q == null || super.q.q()) {
            return null;
        }
        if (dN.w != null) {
            return dN.w;
        }
        return "*";
    }
    
    public String q() {
        String s;
        if (this.q) {
            s = B.q(be.w("You are in %1."), new String[] { super.a });
        }
        else {
            s = B.q(be.w("Double-click here to enter %1."), new String[] { super.a });
        }
        if (super.q != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.q;
        }
        return s;
    }
    
    public final int q(final bJ bj, final String s) {
        if (!"users".equals(s) || !(bj instanceof cB)) {
            return super.q(bj, s);
        }
        final cB cb = (cB)bj;
        if (this.q(55) && !cb.q(55)) {
            return -1;
        }
        if (!this.q(55) && cb.q(55)) {
            return 1;
        }
        return cb.r() - super.r();
    }
    
    public cj(final int n, final String s) {
        super(n, s);
        this.q = false;
        this.w = true;
    }
}
