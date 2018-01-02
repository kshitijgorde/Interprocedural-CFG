// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bS extends aK
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
    
    public static int q(final B b, final int n) {
        if (b != null && n > 0) {
            for (int i = 0; i < b.q; ++i) {
                if (n == ((bS)b.q(i)).a) {
                    return 0;
                }
            }
        }
        return -1;
    }
    
    static {
        al.q("No group");
    }
}
