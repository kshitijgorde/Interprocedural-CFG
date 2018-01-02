// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ba extends rp_dt
{
    private rp_dC[] a;
    
    public rp_ba(final rp_dC[] array, final int n, final int n2) {
        super(rp_au.a("Act_Dupl"));
        this.a = new rp_dC[array.length];
        for (int i = 0; i < array.length; ++i) {
            (this.a[i] = (rp_dC)array[i].clone()).b(-1);
            this.a[i].a(n, n2);
        }
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            if (i == 0) {
                rp_ba.a.a(this.a[i], true);
            }
            else {
                rp_ba.a.b(this.a[i], false);
            }
        }
    }
    
    public final void b() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_ba.a.c(this.a[i]);
        }
    }
}
