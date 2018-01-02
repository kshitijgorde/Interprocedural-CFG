// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cc extends cb
{
    public final boolean q(final String s, final Object o) {
        if ("type".equals(s)) {
            if (o instanceof String) {
                final String s2 = (String)o;
                if (s2.equals(cb.w)) {
                    this.q(es.q(this.q(), 22, true));
                }
                if (s2.equals(cb.e)) {
                    this.q(es.q(this.q(), 23, true));
                }
                if (s2.equals(cb.q)) {
                    this.q(es.q(this.q(), 22, false));
                    this.q(es.q(this.q(), 23, false));
                }
                this.q();
            }
            return true;
        }
        if ("view".equals(s)) {
            this.q(0, !(boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("type".equals(s)) {
            if (es.q(this.q(), 22)) {
                return cb.w;
            }
            if (es.q(this.q(), 23)) {
                return cb.e;
            }
            return cb.q;
        }
        else {
            if ("view".equals(s)) {
                return new Boolean(!this.q(0));
            }
            return super.q(s);
        }
    }
    
    public cc(final int n, final String s) {
        super(-999, s);
        super.w = -1;
    }
    
    public cc(final cb cb) {
        super(cb.r, new String(cb.getName()));
        super.w = cb.w;
        super.q = cb.q;
        super.r = new String(cb.r);
        super.t = cb.y();
        super.r(cb.y);
        super.t(cb.u);
        super.w = cb.w;
        super.q(cb.q());
    }
}
