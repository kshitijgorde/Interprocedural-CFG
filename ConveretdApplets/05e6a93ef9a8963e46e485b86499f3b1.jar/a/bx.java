// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class bx extends aK
{
    public String q;
    private int q;
    public ck q;
    private int w;
    private int e;
    
    public bx(final int n, final String s) {
        super(n, s);
        this.q = 0;
    }
    
    public final synchronized int r() {
        return this.q;
    }
    
    public final synchronized void q(final int q) {
        this.q = q;
    }
    
    public final synchronized void q() {
        ++this.q;
    }
    
    public final synchronized void w() {
        --this.q;
        if (this.q < 0) {
            this.q = 0;
        }
    }
    
    public final String toString() {
        return super.toString() + " Capasity=" + this.r();
    }
    
    public final int t() {
        return this.w;
    }
    
    public final void w(final int n) {
        this.w = (n & 0xFFFFFF);
    }
    
    public final int y() {
        return this.e;
    }
    
    public final void e(final int n) {
        this.e = (n & 0xFFFFFF);
    }
}
