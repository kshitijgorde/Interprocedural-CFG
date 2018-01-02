// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class co extends bZ implements ch
{
    public static final String q;
    public static final String w;
    public static final String e;
    public int q;
    public String r;
    public int w;
    public String y;
    public int e;
    public long q;
    
    public co(final int n, final String s) {
        super(n, s);
        this.q = 0L;
    }
    
    public final String q() {
        return eb.q("Click here to edit this item.");
    }
    
    public final boolean q(final String s, final Object o) {
        if ("user".equals(s)) {
            if (o instanceof String) {
                this.r = (String)o;
            }
            return true;
        }
        if ("name".equals(s)) {
            super.t = (String)o;
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
                return co.q;
            }
            if (q == 0) {
                return co.w;
            }
            if (q == 2) {
                return co.e;
            }
            return "None";
        }
        else {
            if ("foruser".equals(s)) {
                return this.y;
            }
            if ("ttl".equals(s)) {
                return S.q(this.e);
            }
            return super.q(s);
        }
    }
    
    public final int q(final ch ch) {
        return this.q - ((co)ch).q;
    }
    
    static {
        q = eb.q("Normal");
        w = eb.q("High");
        e = eb.q("Low");
    }
}
