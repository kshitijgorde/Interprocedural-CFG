import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends v implements Serializable
{
    public int p;
    public int d;
    public byte[] a;
    
    public g() {
    }
    
    public g(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
    }
    
    public final void p(final int p3, final int d, final byte[] a) {
        this.p = p3;
        this.d = d;
        this.a = a;
    }
    
    public final void p(final v v) {
        ((g)v).p = this.p;
        ((g)v).d = this.d;
        ((g)v).a = this.a;
    }
    
    public final boolean p(final v v) {
        final g g = (g)v;
        return g.p == this.p && g.d == this.d && g.a == this.a;
    }
}
