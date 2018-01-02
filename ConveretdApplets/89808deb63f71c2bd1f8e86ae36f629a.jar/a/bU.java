// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bU extends aJ
{
    private String q;
    
    public final boolean q(final String s, final Object o) {
        if ("formasters".equals(s)) {
            this.q(0, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("image".equals(s)) {
            return null;
        }
        if ("formasters".equals(s)) {
            return new Boolean(this.q(0));
        }
        if ("arrangenumber".equals(s)) {
            return "" + 0;
        }
        if ("description".equals(s)) {
            return this.q;
        }
        return super.q(s);
    }
    
    public static int q(final A a, final int n) {
        if (a != null && n > 0) {
            for (int i = 0; i < a.q; ++i) {
                if (n == ((bU)a.q(i)).s) {
                    return 0;
                }
            }
        }
        return -1;
    }
    
    static {
        ak.q("No group");
    }
}
