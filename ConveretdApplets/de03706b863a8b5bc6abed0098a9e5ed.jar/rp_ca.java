// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ca extends rp_dt
{
    private int a;
    private int b;
    private rp_dC[] a;
    
    public rp_ca(final rp_dC[] a, final int a2, final int b) {
        super(rp_au.a("Act_Move"));
        this.a = a;
        this.a = a2;
        this.b = b;
    }
    
    public final void a() {
        this.a(this.a, this.b);
    }
    
    public final void b() {
        this.a(-this.a, -this.b);
    }
    
    private void a(final int n, final int n2) {
        for (int i = 0; i < this.a.length; ++i) {
            rp_ca.a.d(this.a[i]);
            this.a[i].a(n, n2);
            rp_ca.a.d(this.a[i]);
        }
        rp_ca.a.a(this.a);
    }
}