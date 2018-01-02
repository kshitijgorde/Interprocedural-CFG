import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class aa extends c
{
    Hashtable a;
    c b;
    av c;
    aq d;
    as e;
    au f;
    ar g;
    b h;
    int i;
    blaze3d j;
    ar k;
    Hashtable l;
    
    aa(final blaze3d j) {
        this.a = new Hashtable(10, 0.75f);
        this.b = new c();
        this.c = new av(false);
        this.d = new aq(0.0f);
        this.e = new as();
        this.f = new au();
        this.g = new ar();
        this.h = new b();
        this.i = 0;
        this.l = new Hashtable(10, 0.75f);
        this.j = j;
        super.f = d.d;
    }
    
    void a() {
        this.a.clear();
        this.k = null;
    }
    
    c a(final aa aa) {
        return null;
    }
    
    c f(final as as, final boolean b) {
        final c a = super.d.a(as, b);
        if (a != null) {
            return a;
        }
        if (as.a.equals("_global")) {
            return this;
        }
        if (as.a.equals("_level0")) {
            return this.j.ao[0];
        }
        final c d = this.d(as.a);
        if (d.a != 0) {
            return d;
        }
        if (as.a.indexOf(".") != -1) {
            return this.g(as, b);
        }
        return d.e;
    }
    
    void b() {
        this.b("_global", null);
        this.b.b("Object", this);
        this.k = (ar)this.b.f("constructor");
        this.b.c(d.d);
        this.c.b("Boolean", this);
        this.d.b("Number", this);
        this.e.b("String", this);
        this.f.b("Array", this);
        this.g.b("Function", this);
        this.h.b("MovieClip", this);
        this.h.c("useHandCursor", d.a);
        this.h.c("enabled", d.a);
        this.d("TextField");
        this.j.aa.b("Mouse", this);
        this.j.w.b("Key", this);
        this.j.as.b("Selection", this);
        this.c("Infinity", new aq(Float.POSITIVE_INFINITY));
    }
    
    public int c() {
        if (this.i == 1) {
            return this.j.ad[0];
        }
        if (this.i == 2) {
            return this.j.ad[1];
        }
        return this.j.ac;
    }
    
    public void a(final String s) {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.equals("best")) {
            this.j.a7 = 1;
            if (!c.l) {
                return;
            }
        }
        if (lowerCase.equals("high")) {
            this.j.a7 = 0;
        }
    }
    
    public String e() {
        if (this.j.a7 == 1) {
            return "BEST";
        }
        return "HIGH";
    }
    
    public c a(final c c) {
        return c;
    }
    
    public String b(final String s) {
        return s;
    }
    
    public float b(final float n) {
        return n;
    }
    
    public boolean a(final boolean b) {
        return b;
    }
    
    public au c(final au au) {
        return new au(au);
    }
}
