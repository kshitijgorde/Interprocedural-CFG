import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ac
{
    public WordFallApplet a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public String l;
    public int m;
    public String n;
    public int o;
    
    public void a(final aq aq) {
        ++this.c;
        this.b += aq.b;
        this.d += aq.a.length();
        this.e += aq.c;
        this.g += aq.f;
        this.h += aq.g;
        this.f += aq.e;
        if (aq.a.length() > this.n.length()) {
            this.n = aq.a;
            this.o = aq.b;
        }
        if (aq.b > this.m) {
            this.l = aq.a;
            this.m = aq.b;
        }
    }
    
    public ac(final WordFallApplet a) {
        this.l = "";
        this.n = "";
        this.a = a;
    }
    
    public void a(final int n) {
        this.b += n;
        ++this.k;
    }
    
    public void a() {
        ++this.i;
    }
    
    public void b() {
        ++this.j;
    }
}
