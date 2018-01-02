// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cz extends bp
{
    public static final String q;
    public static final String w;
    public static final String e;
    public String r;
    public int q;
    public int w;
    private boolean q;
    private boolean w;
    
    public cz(final int n, final String s) {
        super(n, s);
        this.q = false;
        this.w = false;
        this.w = -1;
    }
    
    protected final String e() {
        if (dI.q(this.w(), 22)) {
            return cz.w;
        }
        if (dI.q(this.w(), 23)) {
            return cz.e;
        }
        return cz.q;
    }
    
    protected final long q(final String s) {
        if (s.equals(cz.w)) {
            this.q(dI.q(this.w(), 22, true));
        }
        if (s.equals(cz.e)) {
            this.q(dI.q(this.w(), 23, true));
        }
        if (s.equals(cz.q)) {
            this.q(dI.q(this.w(), 22, false));
            this.q(dI.q(this.w(), 23, false));
        }
        return this.w();
    }
    
    public final boolean q() {
        return this.w;
    }
    
    public final void q(final boolean w) {
        this.w = w;
    }
    
    public final boolean w() {
        return this.q(21);
    }
    
    public final void w(final boolean b) {
        this.q(21, b);
    }
    
    public final int q(final cz cz) {
        final int q;
        if ((q = super.q(cz)) != 0) {
            return q;
        }
        if (this.r.compareTo(cz.r) != 0) {
            return this.r.compareTo(cz.r);
        }
        if (this.q != cz.q) {
            return this.q - cz.q;
        }
        if (this.w != cz.w) {
            return this.w - cz.w;
        }
        if (this.w != cz.w) {
            return 1;
        }
        return 0;
    }
    
    static {
        q = ds.q("l<<");
        w = ds.q("x1CD5B");
        e = ds.q("rE5CD");
    }
}
