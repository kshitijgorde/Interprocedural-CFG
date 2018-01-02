// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bO extends bk implements aq
{
    public boolean q;
    public boolean w;
    
    public final Object q(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.y());
        }
        if (!"lock".equals(s)) {
            return super.q(s);
        }
        if (this.q) {
            return a.e;
        }
        if (super.q == null || super.q.q()) {
            return null;
        }
        if (a.w != null) {
            return a.w;
        }
        return "*";
    }
    
    public final String q() {
        String s;
        if (this.q) {
            s = cv.q(cv.q("You are in %1."), new String[] { this.getName() });
        }
        else {
            s = cv.q(cv.q("Double-click here to enter %1."), new String[] { this.getName() });
        }
        if (super.q != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.q;
        }
        return s;
    }
    
    public final int q(final aq aq, final String s) {
        if (!"users".equals(s) || !(aq instanceof bk)) {
            return super.q(aq, s);
        }
        final bk bk = (bk)aq;
        if (this.q(55) && !bk.q(55)) {
            return -1;
        }
        if (!this.q(55) && bk.q(55)) {
            return 1;
        }
        return bk.y() - super.y();
    }
    
    public bO(final int n, final String s) {
        super(n, s);
        this.q = false;
        this.w = true;
    }
}
