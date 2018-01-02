// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class bk extends ba
{
    public String q;
    private int t;
    public cH q;
    private int y;
    private int u;
    
    public bk(final int n, final String s) {
        super(n, s);
        this.t = 0;
    }
    
    public final boolean q() {
        return this.q(61);
    }
    
    public final synchronized int y() {
        return this.t;
    }
    
    public final synchronized void y(final int t) {
        this.t = t;
    }
    
    public final synchronized void q() {
        ++this.t;
    }
    
    public final synchronized void w() {
        --this.t;
        if (this.t < 0) {
            this.t = 0;
        }
    }
    
    public final String toString() {
        return super.toString() + " Capasity=" + this.y();
    }
    
    public final int u() {
        return this.y;
    }
    
    public final void u(final int n) {
        this.y = (n & 0xFFFFFF);
    }
    
    public final int i() {
        return this.u;
    }
    
    public final void i(final int n) {
        this.u = (n & 0xFFFFFF);
    }
}
