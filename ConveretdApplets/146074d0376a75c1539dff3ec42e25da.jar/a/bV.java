// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class bV extends bZ implements aF
{
    public static final String q;
    public static final String w;
    public static final String e;
    public static final String r;
    
    public bV(final int n, final String s) {
        super(n, s);
    }
    
    public bV(final bV bv) {
        super(bv.r, bv.getName());
    }
    
    public final boolean q(final String s, final Object o) {
        if ("name".equals(s)) {
            super.t = (String)o;
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("name".equals(s)) {
            return this.getName();
        }
        return super.q(s);
    }
    
    public final String q() {
        return eb.q("Click here to edit this item.");
    }
    
    static {
        q = eb.q("Disable Filter");
        w = eb.q("Low Sensetivity");
        e = eb.q("Medium Sensetivity");
        r = eb.q("High Sensetivity");
    }
}
