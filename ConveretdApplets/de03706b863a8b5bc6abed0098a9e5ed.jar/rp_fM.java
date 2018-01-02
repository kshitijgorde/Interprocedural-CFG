// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fM extends rp_dt
{
    private rp_dC[] a;
    
    public rp_fM(final rp_dC[] a) {
        super(rp_au.a("Act_Ins"));
        this.a = a;
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_fM.a.a(this.a[i], this.a.length == 1);
        }
        rp_fM.a.b();
    }
    
    public final void b() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_fM.a.c(this.a[i]);
        }
    }
}
