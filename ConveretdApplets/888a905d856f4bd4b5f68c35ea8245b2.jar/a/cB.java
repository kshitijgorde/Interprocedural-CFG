// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cB extends bp
{
    public String q;
    public String w;
    private int y;
    public int q;
    public int w;
    public int e;
    private long q;
    public dD q;
    public int r;
    public int t;
    
    public cB(final int n, final String s) {
        super(n, s);
        this.y = 0;
        this.q = -999;
        this.w = -999;
        this.e = -999;
        this.q = Long.MAX_VALUE;
    }
    
    public final synchronized int r() {
        return this.y;
    }
    
    public final synchronized void q(final int y) {
        this.y = y;
    }
    
    public final synchronized void q() {
        ++this.y;
    }
    
    public final synchronized void w() {
        --this.y;
        if (this.y < 0) {
            this.y = 0;
        }
    }
    
    public final String toString() {
        return super.toString() + " Capasity=" + this.r();
    }
    
    public final int t() {
        return this.r;
    }
    
    public final void w(final int n) {
        this.r = (n & 0xFFFFFF);
    }
    
    public final int y() {
        return this.t;
    }
    
    public final void e(final int n) {
        this.t = (n & 0xFFFFFF);
    }
    
    public final int q(final cB cb) {
        final int q;
        if ((q = super.q(cb)) != 0) {
            return q;
        }
        if (this.q != null && this.q.compareTo(cb.q) != 0) {
            return this.q.compareTo(cb.q);
        }
        if (this.w != null && this.w.compareTo(cb.w) != 0) {
            return this.w.compareTo(cb.w);
        }
        if (this.y != cb.y) {
            return this.y - cb.y;
        }
        if (this.q != cb.q) {
            return this.q - cb.q;
        }
        if (this.w != cb.w) {
            return this.w - cb.w;
        }
        if (this.e != cb.e) {
            return this.e - cb.e;
        }
        if (this.q != cb.q) {
            return 1;
        }
        if (this.q != null && this.q != cb.q) {
            return 1;
        }
        if (this.r != cb.r) {
            return this.r - cb.r;
        }
        if (this.t != cb.t) {
            return this.t - cb.t;
        }
        return 0;
    }
}
