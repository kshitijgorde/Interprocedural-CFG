// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bl extends bw implements aW
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
            s = a.s.q(ak.q("You are in %1."), new String[] { super.o });
        }
        else {
            s = a.s.q(ak.q("Double-click here to enter %1."), new String[] { super.o });
        }
        if (super.q != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.q;
        }
        return s;
    }
    
    public final int q(final aW aw, final String s) {
        if (!"users".equals(s) || !(aw instanceof bw)) {
            return super.q(aw, s);
        }
        final bw bw = (bw)aw;
        if (this.q(55) && !bw.q(55)) {
            return -1;
        }
        if (!this.q(55) && bw.q(55)) {
            return 1;
        }
        return bw.r() - super.r();
    }
    
    public bl(final int n, final String s) {
        super(n, s);
        this.q = false;
        this.w = true;
    }
}
