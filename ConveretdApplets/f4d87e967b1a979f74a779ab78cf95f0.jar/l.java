import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends v implements Serializable
{
    public dc p;
    public dc d;
    
    public l(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
        this.p = new dc();
        this.d = new dc();
    }
    
    public final void p(final v v) {
        ((l)v).p.p(this.p);
        ((l)v).d.p(this.d);
    }
    
    public final boolean p(final v v) {
        return ((l)v).p.p(this.p) && ((l)v).d.p(this.d);
    }
}
