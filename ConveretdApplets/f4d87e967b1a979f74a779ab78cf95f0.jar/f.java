import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends l implements Serializable
{
    static a[] p;
    static p d;
    
    public f(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
    }
    
    public final void p(final float n) {
        super.p.p(n);
    }
    
    public final float p() {
        return super.p.p();
    }
    
    public final void d(final float n) {
        super.d.p(n);
    }
    
    public final float d() {
        return super.d.p();
    }
    
    static {
        f.p = new a[] { new a("_value1", "x"), new a("_value2", "y") };
        f.d = new p(f.p);
    }
}
