import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends h implements Serializable
{
    static a[] p;
    static p d;
    
    public n() {
    }
    
    public n(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
    }
    
    public final void d(final int p) {
        super.p = p;
    }
    
    public final int d() {
        return super.p;
    }
    
    public final void p(final int d) {
        super.d = d;
    }
    
    public final int p() {
        return super.d;
    }
    
    static {
        n.p = new a[] { new a("_value1", "width"), new a("_value2", "height") };
        n.d = new p(n.p);
    }
}
