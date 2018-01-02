import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cH extends rp_dt
{
    private int a;
    private int b;
    private int c;
    private int d;
    private String a;
    private String b;
    private int e;
    private int f;
    private String c;
    private String d;
    private Color a;
    private Color b;
    private Color c;
    private Color d;
    private boolean a;
    private rp_dC a;
    
    public rp_cH(final rp_dC rp_dC, final int c, final int d, final String s, final int f, final String d2, final Color color, final Color color2) {
        this(rp_dC, s, color, color2);
        this.a = true;
        this.a = rp_dC.b();
        this.b = rp_dC.c();
        this.c = c;
        this.d = d;
        this.e = rp_dC.a();
        this.f = f;
        this.c = rp_dC.b();
        this.d = d2;
    }
    
    public rp_cH(final rp_dC a, final String b, final Color b2, final Color d) {
        super(rp_au.a("Act_Prop"));
        this.a = false;
        this.a = a;
        this.a = a.a();
        this.b = b;
        this.a = a.a();
        this.b = b2;
        this.c = a.b();
        this.d = d;
    }
    
    public final void a() {
        rp_cH.a.d(this.a);
        if (this.b != null) {
            this.a.a(this.b);
        }
        this.a.a = this.b;
        this.a.b = this.d;
        if (this.a) {
            this.a.a(this.c, this.d);
            rp_cH.a.b(this.a);
            this.a.a(this.f);
            if (this.d != null) {
                this.a.b(this.d);
            }
        }
        rp_cH.a.d(this.a);
    }
    
    public final void b() {
        rp_cH.a.d(this.a);
        if (this.a != null) {
            this.a.a(this.a);
        }
        this.a.a = this.a;
        this.a.b = this.c;
        if (this.a) {
            this.a.a(this.a, this.b);
            this.a.a(this.e);
            if (this.c != null) {
                this.a.b(this.c);
            }
        }
        rp_cH.a.d(this.a);
    }
}
