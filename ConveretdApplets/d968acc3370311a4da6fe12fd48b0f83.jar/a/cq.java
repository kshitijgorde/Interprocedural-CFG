// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cq extends db
{
    public final Object q(final String s) {
        if ("default".equals(s)) {
            return new Boolean(this.q(62));
        }
        return super.q(s);
    }
    
    public final boolean q(final String s, final Object o) {
        if ("default".equals(s) && !this.q(63)) {
            this.q(62, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final String q() {
        return ec.q(eb.q("Double-click here to edit %1: %2."), new String[] { this.getName(), super.q });
    }
    
    public cq(final cr cr) {
        this(cr.r, cr.getName());
        this.q(cr.q());
        super.q = cr.q;
        super.q = cr.q;
        super.w = cr.w;
        super.e = cr.e;
        super.t = cr.y();
        super.t(cr.u);
        super.q = cr.q;
        super.o = cr.e();
        super.p = cr.i();
    }
    
    public cq(final int n, final String s) {
        super(n, s);
    }
}
