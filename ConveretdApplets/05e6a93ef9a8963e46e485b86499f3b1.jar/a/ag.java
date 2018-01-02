// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ag extends aN implements aX
{
    public ag(final int n, final String s) {
        super(n, s);
    }
    
    public final boolean q(final String s, final Object o) {
        if ("replace".equals(s)) {
            if (o instanceof String) {
                super.q = (String)o;
            }
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("replace".equals(s)) {
            return super.q;
        }
        return super.q(s);
    }
}
