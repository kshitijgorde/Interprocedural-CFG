import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class d extends v implements Serializable
{
    public dc p;
    
    protected d() {
        this.p = new dc();
    }
    
    public d(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
        this.p = new dc();
    }
    
    public final void p(final float p) {
        this.p.p = p;
    }
    
    public final float p() {
        return this.p.p;
    }
    
    public final void p(final v v) {
        ((d)v).p.p(this.p);
    }
    
    public final boolean p(final v v) {
        return ((d)v).p.p(this.p);
    }
}
