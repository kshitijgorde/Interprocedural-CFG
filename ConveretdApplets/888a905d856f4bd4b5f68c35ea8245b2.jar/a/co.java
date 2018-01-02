// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class co extends cj
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
        return B.q(be.w("Double-click here to edit %1: %2."), new String[] { super.a, super.q });
    }
    
    public co(final cB cb) {
        this(cb.s, cb.a);
        this.q(cb.w());
        super.q = cb.q;
        super.q = cb.q;
        super.w = cb.w;
        super.e = cb.e;
        super.d = cb.w();
        super.o(cb.g);
        super.q = cb.q;
        super.r = cb.t();
        super.t = cb.y();
    }
    
    public co(final int n, final String s) {
        super(n, s);
    }
}
