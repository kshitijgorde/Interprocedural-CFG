// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cu extends bp
{
    public dD q;
    public int q;
    public int w;
    public int e;
    public long q;
    public long w;
    public String q;
    public String w;
    
    public cu(final int n, final String s) {
        super(n, s);
        this.q = 0;
    }
    
    public final void q(final int n) {
        this.w = (n & 0xFFFFFF);
    }
    
    public final void w(final int n) {
        this.e = (n & 0xFFFFFF);
    }
    
    public final int q(final cu cu) {
        final int q;
        if ((q = super.q(cu)) != 0) {
            return q;
        }
        if (this.q != cu.q) {
            return this.q - cu.q;
        }
        if (this.q != null && this.q.toString().compareTo(cu.q.toString()) != 0) {
            return this.q.toString().compareTo(cu.q.toString());
        }
        if (this.w != cu.w) {
            return this.w - cu.w;
        }
        if (this.e != cu.e) {
            return this.e - cu.e;
        }
        if (this.q != cu.q) {
            return (int)(this.q - cu.q);
        }
        if (this.w != cu.w) {
            return (int)(this.w - cu.w);
        }
        if (this.q != null && this.q.compareTo(cu.q) != 0) {
            return this.q.compareTo(cu.q);
        }
        if (this.w != null && this.w.compareTo(cu.w) != 0) {
            return this.w.compareTo(cu.w);
        }
        return 0;
    }
}
