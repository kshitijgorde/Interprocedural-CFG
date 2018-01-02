// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ay extends rp_dt
{
    private boolean a;
    private rp_dC[] a;
    private rp_aN a;
    
    public rp_ay(final rp_dC[] array) {
        super(rp_au.a("Act_AddDim"));
        this.a = null;
        this.a = null;
        this.a = true;
        this.a = new rp_dC[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.a[i] = array[i];
        }
        final rp_N a = rp_ay.a;
        final rp_an rp_an = new rp_an(a.a.getGraphics());
        for (int j = 0; j < array.length; ++j) {
            array[j].a(rp_an, a.a);
        }
        this.a = new rp_aN(rp_ay.a.a(), rp_an.a);
    }
    
    public rp_ay(final rp_aN a, final rp_dC[] array, final int n) {
        super(rp_au.a("Act_RemDim"));
        this.a = null;
        this.a = null;
        this.a = false;
        this.a = a;
        this.a = new rp_dC[n];
        for (int i = 0; i < n; ++i) {
            this.a[i] = array[i];
        }
    }
    
    public final void a() {
        if (this.a) {
            this.c();
            return;
        }
        this.d();
    }
    
    public final void b() {
        if (this.a) {
            this.d();
            return;
        }
        this.c();
    }
    
    private void c() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].b(this.a.h);
        }
        rp_ay.a.a(this.a, false);
    }
    
    private void d() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].b(-1);
        }
        rp_ay.a.c(this.a);
    }
}
