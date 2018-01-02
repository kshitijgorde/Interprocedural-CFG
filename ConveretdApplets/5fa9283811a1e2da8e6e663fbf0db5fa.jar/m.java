// 
// Decompiled by Procyon v0.5.30
// 

public class m extends r
{
    public l p;
    public l d;
    
    public final void p(final l l, final l i) {
        this.p.d(l);
        this.d.d(i);
    }
    
    public final boolean p(final l l) {
        return this.i(l.p - this.p.p) && this.a(l.p - this.d.p) && this.i(l.d - this.p.d) && this.a(l.d - this.d.d);
    }
    
    public m(final l l, final l i) {
        this.p = new l(l);
        this.d = new l(i);
    }
}
