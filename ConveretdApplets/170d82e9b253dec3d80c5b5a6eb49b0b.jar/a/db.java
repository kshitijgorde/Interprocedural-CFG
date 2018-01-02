// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class db extends cr implements aF
{
    public boolean w;
    public boolean e;
    
    public Object q(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.w());
        }
        if (!"lock".equals(s)) {
            return super.q(s);
        }
        if (this.w) {
            return a.a.e;
        }
        if (super.q == null || super.q.q()) {
            return null;
        }
        if (a.a.w != null) {
            return a.a.w;
        }
        return "*";
    }
    
    public String q() {
        String s;
        if (this.w) {
            s = ec.q(eb.q("You are in %1."), new String[] { this.getName() });
        }
        else {
            s = ec.q(eb.q("Double-click here to enter %1."), new String[] { this.getName() });
        }
        if (super.q != null) {
            s = s.substring(0, s.length() - 1) + ": " + super.q;
        }
        return s;
    }
    
    public final int q(final aF af, final String s) {
        if (!"users".equals(s) || !(af instanceof cr)) {
            return super.q(af, s);
        }
        final cr cr = (cr)af;
        if (this.q(55) && !cr.q(55)) {
            return -1;
        }
        if (!this.q(55) && cr.q(55)) {
            return 1;
        }
        return cr.w() - super.w();
    }
    
    public db(final int n, final String s) {
        super(n, s);
        this.w = false;
        this.e = true;
    }
}
