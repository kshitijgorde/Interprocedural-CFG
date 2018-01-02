// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cQ extends rp_dt
{
    private double a;
    private int a;
    private int b;
    private rp_dC[] a;
    
    public rp_cQ(final rp_dC[] a, final int a2, final int b, final double a3) {
        super(rp_au.a("Act_Rot"));
        this.a = a;
        this.a = a3;
        this.a = a2;
        this.b = b;
    }
    
    public final void a() {
        this.a(this.a);
    }
    
    public final void b() {
        this.a(-this.a);
    }
    
    private void a(final double n) {
        for (int i = 0; i < this.a.length; ++i) {
            rp_cQ.a.d(this.a[i]);
            this.a[i].a(this.a, this.b, n);
            rp_cQ.a.d(this.a[i]);
        }
        rp_cQ.a.a(this.a);
    }
}
