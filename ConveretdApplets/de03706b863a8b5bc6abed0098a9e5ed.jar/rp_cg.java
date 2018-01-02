// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cg extends rp_dt
{
    private rp_dt[] a;
    
    public rp_cg(final rp_dt rp_dt, final rp_dt rp_dt2) {
        super(rp_au.a("Act_Gr"));
        (this.a = new rp_dt[2])[0] = rp_dt;
        this.a[1] = rp_dt2;
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                this.a[i].a();
            }
        }
    }
    
    public final void b() {
        for (int i = this.a.length - 1; i >= 0; --i) {
            if (this.a[i] != null) {
                this.a[i].b();
            }
        }
    }
}
