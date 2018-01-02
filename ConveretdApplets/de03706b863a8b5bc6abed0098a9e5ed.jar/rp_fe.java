// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fe extends rp_dt
{
    private rp_dC[] a;
    
    public rp_fe(final rp_dC[] a) {
        super(rp_au.a("Act_Del"));
        this.a = a;
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                rp_fe.a.c(this.a[i]);
            }
        }
        rp_fe.a.a(this.a);
    }
    
    public final void b() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_fe.a.a(this.a[i], true);
        }
        rp_fe.a.a(this.a);
    }
}
