// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bm extends bx implements aX
{
    public boolean q;
    public boolean w;
    
    public final Object q(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.r());
        }
        if (!"lock".equals(s)) {
            return super.q(s);
        }
        if (this.q) {
            return cs.e;
        }
        if (super.q == null || super.q.q()) {
            return null;
        }
        if (cs.w != null) {
            return cs.w;
        }
        return "*";
    }
    
    public final String q() {
        String s;
        if (this.q) {
            s = t.q(al.q("You are in %1."), new String[] { super.o });
        }
        else {
            s = t.q(al.q("Double-click here to enter %1."), new String[] { super.o });
        }
        if (super.q != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.q;
        }
        return s;
    }
    
    public final int q(final aX ax, final String s) {
        if (!"users".equals(s) || !(ax instanceof bx)) {
            return super.q(ax, s);
        }
        final bx bx = (bx)ax;
        if (this.q(55) && !bx.q(55)) {
            return -1;
        }
        if (!this.q(55) && bx.q(55)) {
            return 1;
        }
        return bx.r() - super.r();
    }
    
    public bm(final int n, final String s) {
        super(n, s);
        this.q = false;
        this.w = true;
    }
}
