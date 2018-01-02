// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dN extends rp_dt
{
    private rp_cw a;
    private int a;
    private int b;
    private int c;
    
    public rp_dN(final rp_cw a, final int a2, final int b, final int c) {
        super(rp_au.a("Act_MoveTD"));
        this.a = a;
        this.a = a2;
        this.b = b;
        this.c = c;
    }
    
    public final void a() {
        this.a.a(this.a, this.b, this.c);
    }
    
    public final void b() {
        this.a.a(this.a, -this.b, -this.c);
    }
}
