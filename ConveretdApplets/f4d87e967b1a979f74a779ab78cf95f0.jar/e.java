import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends v implements Serializable
{
    public db p;
    
    public e() {
        this.p = new db();
    }
    
    public e(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
        this.p = new db();
    }
    
    public final void p(final int p) {
        this.p.p = p;
    }
    
    public final int p() {
        return this.p.p;
    }
    
    public final void p(final v v) {
        ((e)v).p.p(this.p);
    }
    
    public final boolean p(final v v) {
        return ((e)v).p.p(this.p);
    }
}
