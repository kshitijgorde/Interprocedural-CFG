// 
// Decompiled by Procyon v0.5.30
// 

public class c1 implements cx, ay
{
    private static int a;
    public String b;
    public c2 c;
    public cx d;
    public int e;
    public String f;
    
    public c1(final String s, final cx cx) {
        this(s, cx, null);
    }
    
    public c1(final String s, final c2 c2) {
        this(s, null, c2);
    }
    
    public c1(final String b, final cx d, final c2 c) {
        this.e = c1.a++;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = "subs:" + this.e + ":" + b + ";" + ((c != null) ? c.toString() : "");
    }
    
    public void a(final cw cw, final c1 c1) {
        if (this.d != null) {
            if (ay.a.l()) {
                ay.a.j(cw.i.as() + " invoking callback " + this.d + " of subs" + this.f);
            }
            this.d.a(cw, c1);
        }
        else if (ay.a.g()) {
            ay.a.d(cw.i.as() + " no callback found for subscription " + this.f);
        }
    }
    
    public final String a() {
        return this.b;
    }
    
    public final c2 b() {
        return this.c;
    }
    
    public String toString() {
        return this.f;
    }
}
