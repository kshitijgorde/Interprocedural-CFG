// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class D extends bp implements dy
{
    public static final String q;
    public static final String w;
    public static final String e;
    public int q;
    public String r;
    public int w;
    public String t;
    public int e;
    public long q;
    
    public D(final int n, final String s) {
        super(n, s);
        this.q = 0L;
    }
    
    public final String q() {
        return be.w("Click here to edit this item.");
    }
    
    public final boolean q(final String s, final Object o) {
        if ("user".equals(s)) {
            if (o instanceof String) {
                this.r = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.a = (String)o;
            return true;
        }
        if ("priority".equals(s)) {
            this.q = Integer.parseInt((String)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("user".equals(s)) {
            return this.r;
        }
        if ("priority".equals(s)) {
            final int q;
            if ((q = this.q) == 1) {
                return D.q;
            }
            if (q == 0) {
                return D.w;
            }
            if (q == 2) {
                return D.e;
            }
            return "None";
        }
        else {
            if ("foruser".equals(s)) {
                return this.t;
            }
            if ("ttl".equals(s)) {
                return P.q(this.e);
            }
            return super.q(s);
        }
    }
    
    public final int q(final dy dy) {
        return this.q - ((D)dy).q;
    }
    
    static {
        q = be.w("Normal");
        w = be.w("High");
        e = be.w("Low");
    }
}
