// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fR extends rp_dt
{
    private int a;
    private int b;
    private int c;
    private int d;
    private rp_dC a;
    
    public rp_fR(final rp_dC a, final int c, final int d) {
        super(rp_au.a("Act_Resize"));
        this.a = a;
        this.a = a.b();
        this.b = a.c();
        this.c = c;
        this.d = d;
    }
    
    public final void a() {
        rp_fR.a.d(this.a);
        this.a.a(this.c, this.d);
        rp_fR.a.d(this.a);
        rp_fR.a.b(this.a);
    }
    
    public final void b() {
        rp_fR.a.d(this.a);
        this.a.a(this.a, this.b);
        rp_fR.a.d(this.a);
        rp_fR.a.b(this.a);
    }
}
