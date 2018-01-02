// 
// Decompiled by Procyon v0.5.30
// 

public class eo
{
    public String a;
    public String b;
    public cr c;
    public String d;
    public String e;
    public int f;
    
    public eo(final String s, final String s2, final cr cr, final String s3, final int n) {
        this(s, s2, cr, s3, n, null);
    }
    
    public eo(final String s, final String s2, final cr cr, final String e, final int f, final String s3) {
        this(s, s2, cr, s3);
        this.e = e;
        this.f = f;
    }
    
    public eo(final String a, final String b, final cr c, final String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final String a() {
        return this.b;
    }
    
    public Object a(final String s, final u u) throws ao {
        return cr.a(s, this.c, u);
    }
}
