// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class dQ extends aZ implements bJ
{
    public final boolean q(final String s, final Object o) {
        if ("default".equals(s) && !this.q(63)) {
            this.q(62, (boolean)o);
            return true;
        }
        if ("restricted".equals(s)) {
            this.q(36, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return this.q;
        }
        if ("formaster".equals(s)) {
            return this.q;
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.q(36));
        }
        if ("default".equals(s)) {
            return new Boolean(this.q(62));
        }
        return super.q(s);
    }
    
    public final String q() {
        return null;
    }
    
    public dQ(final int n, final String s) {
        super(-999, s);
    }
    
    public dQ(final aZ az) {
        super(az.s, az.a);
        super.q = az.q;
        this.q(az.w());
        super.q(az.q);
        super.q = az.q;
    }
}
