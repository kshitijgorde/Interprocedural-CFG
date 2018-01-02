// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fg extends rp_dt
{
    private double a;
    private int a;
    private int b;
    private int c;
    private int d;
    private rp_dC[] a;
    
    public rp_fg(final rp_dC[] a, final int a2, final int b, final double a3, final int c, final int d) {
        super(rp_au.a("Act_Snap"));
        this.a = a;
        this.a = a3;
        this.a = a2;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_fg.a.d(this.a[i]);
            this.a[i].a(this.a, this.b, this.a);
            this.a[i].a(this.c, this.d);
            rp_fg.a.d(this.a[i]);
        }
        rp_fg.a.a(this.a);
    }
    
    public final void b() {
        for (int i = 0; i < this.a.length; ++i) {
            rp_fg.a.d(this.a[i]);
            this.a[i].a(-this.c, -this.d);
            this.a[i].a(this.a, this.b, -this.a);
            rp_fg.a.d(this.a[i]);
        }
        rp_fg.a.a(this.a);
    }
}
