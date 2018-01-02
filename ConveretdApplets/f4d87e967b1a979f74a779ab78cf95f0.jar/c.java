import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends v implements Serializable
{
    public df p;
    
    public c() {
        this.p = new df();
    }
    
    public c(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
        this.p = new df();
    }
    
    public final void p(final boolean p) {
        this.p.p = p;
    }
    
    public final boolean p() {
        return this.p.p;
    }
    
    public final void p(final v v) {
        ((c)v).p.p(this.p);
    }
    
    public final boolean p(final v v) {
        return ((c)v).p.p(this.p);
    }
}
