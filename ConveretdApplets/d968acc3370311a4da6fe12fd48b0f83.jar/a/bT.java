// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class bT extends bZ
{
    public ep q;
    public int q;
    int w;
    int e;
    public long q;
    public long w;
    public String q;
    public String w;
    
    public bT(final int n, final String s) {
        super(n, s);
        this.q = 0;
    }
    
    public final void q(final int n) {
        this.w = (n & 0xFFFFFF);
    }
    
    public final int w() {
        return this.w;
    }
    
    public final int e() {
        return this.e;
    }
    
    public final void w(final int n) {
        this.e = (n & 0xFFFFFF);
    }
    
    public final int q(final bT bt) {
        final int q;
        if ((q = super.q(bt)) != 0) {
            return q;
        }
        if (this.q != bt.q) {
            return this.q - bt.q;
        }
        if (this.q != null && this.q.toString().compareTo(bt.q.toString()) != 0) {
            return this.q.toString().compareTo(bt.q.toString());
        }
        if (this.w != bt.w) {
            return this.w - bt.w;
        }
        if (this.e != bt.e) {
            return this.e - bt.e;
        }
        if (this.q != bt.q) {
            return (int)(this.q - bt.q);
        }
        if (this.w != bt.w) {
            return (int)(this.w - bt.w);
        }
        if (this.q != null && this.q.compareTo(bt.q) != 0) {
            return this.q.compareTo(bt.q);
        }
        if (this.w != null && this.w.compareTo(bt.w) != 0) {
            return this.w.compareTo(bt.w);
        }
        return 0;
    }
}
