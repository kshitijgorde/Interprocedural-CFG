// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cg extends cf implements aF
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
    
    public cg(final cf cf) {
        this(cf.r, cf.getName());
        this.q(cf);
    }
    
    public cg(final int n, final String s) {
        super(n, s);
    }
}
