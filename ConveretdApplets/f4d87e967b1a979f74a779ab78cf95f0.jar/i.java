import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends v implements Serializable
{
    public int p;
    public int d;
    public int[] a;
    
    public i() {
    }
    
    public i(final String s, final y y, final int n, final boolean b) {
        super(s, y, n, b);
    }
    
    public final void p(final int p3, final int d, final int[] a) {
        this.p = p3;
        this.d = d;
        this.a = a;
    }
    
    public final void p(final v v) {
        ((i)v).p = this.p;
        ((i)v).d = this.d;
        ((i)v).a = this.a;
    }
    
    public final boolean p(final v v) {
        final i i = (i)v;
        return i.p == this.p && i.d == this.d && i.a == this.a;
    }
}
