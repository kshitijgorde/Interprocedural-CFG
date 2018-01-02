// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cb extends bZ
{
    public static final String q;
    public static final String w;
    public static final String e;
    public String r;
    public int q;
    public int w;
    private boolean e;
    boolean w;
    
    public cb(final int n, final String s) {
        super(n, s);
        this.e = false;
        this.w = false;
        this.w = -1;
    }
    
    public final boolean q() {
        return this.q(21);
    }
    
    public final void q(final boolean b) {
        this.q(21, b);
    }
    
    public final int q(final cb cb) {
        final int q;
        if ((q = super.q(cb)) != 0) {
            return q;
        }
        if (this.r.compareTo(cb.r) != 0) {
            return this.r.compareTo(cb.r);
        }
        if (this.q != cb.q) {
            return this.q - cb.q;
        }
        if (this.w != cb.w) {
            return this.w - cb.w;
        }
        if (this.w != cb.w) {
            return 1;
        }
        return 0;
    }
    
    static {
        q = dV.q("l<<");
        w = dV.q("x1CD5B");
        e = dV.q("rE5CD");
    }
}
