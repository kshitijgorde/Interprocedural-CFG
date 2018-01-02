// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cr extends bZ
{
    public String q;
    public String w;
    private int a;
    public int q;
    public int w;
    public int e;
    private long q;
    public ep q;
    protected int o;
    protected int p;
    
    public cr(final int n, final String s) {
        super(n, s);
        this.a = 0;
        this.q = -999;
        this.w = -999;
        this.e = -999;
        this.q = Long.MAX_VALUE;
    }
    
    public final boolean q() {
        return this.q(61);
    }
    
    public final synchronized int w() {
        return this.a;
    }
    
    public final synchronized void q(final int a) {
        this.a = a;
    }
    
    public final synchronized void q() {
        ++this.a;
    }
    
    public final synchronized void w() {
        --this.a;
        if (this.a < 0) {
            this.a = 0;
        }
    }
    
    public final String toString() {
        return super.toString() + " Capasity=" + this.w();
    }
    
    public final int e() {
        return this.o;
    }
    
    public final void w(final int n) {
        this.o = (n & 0xFFFFFF);
    }
    
    public final int i() {
        return this.p;
    }
    
    public final void p(final int n) {
        this.p = (n & 0xFFFFFF);
    }
    
    public final int q(final cr cr) {
        final int q;
        if ((q = super.q(cr)) != 0) {
            return q;
        }
        if (this.q != null && this.q.compareTo(cr.q) != 0) {
            return this.q.compareTo(cr.q);
        }
        if (this.w != null && this.w.compareTo(cr.w) != 0) {
            return this.w.compareTo(cr.w);
        }
        if (this.a != cr.a) {
            return this.a - cr.a;
        }
        if (this.q != cr.q) {
            return this.q - cr.q;
        }
        if (this.w != cr.w) {
            return this.w - cr.w;
        }
        if (this.e != cr.e) {
            return this.e - cr.e;
        }
        if (this.q != cr.q) {
            return 1;
        }
        if (this.q != null && this.q != cr.q) {
            return 1;
        }
        if (this.o != cr.o) {
            return this.o - cr.o;
        }
        if (this.p != cr.p) {
            return this.p - cr.p;
        }
        return 0;
    }
}
