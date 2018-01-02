// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class r extends cz
{
    public final boolean q(final String s, final Object o) {
        if ("type".equals(s)) {
            if (o instanceof String) {
                this.q((String)o);
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
            return this.e();
        }
        if ("view".equals(s)) {
            return new Boolean(!this.q(0));
        }
        return super.q(s);
    }
    
    public r(final int n, final String s) {
        super(-999, s);
        super.w = -1;
    }
    
    public r(final cz cz) {
        super(cz.s, new String(cz.a));
        super.w = cz.w;
        super.q = cz.q;
        super.r = new String(cz.r);
        super.d = cz.w();
        super.i(cz.f);
        super.o(cz.g);
        super.q(cz.q());
        super.q(cz.w());
    }
}
