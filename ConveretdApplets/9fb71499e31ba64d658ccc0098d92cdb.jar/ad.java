// 
// Decompiled by Procyon v0.5.30
// 

public class ad
{
    public int a;
    public int b;
    public double c;
    public double d;
    public double e;
    public double f;
    public char g;
    public char h;
    public boolean i;
    public double j;
    public boolean k;
    public int l;
    public int m;
    public boolean n;
    public int o;
    public boolean p;
    public double q;
    public boolean r;
    public double s;
    public int t;
    public int u;
    
    public boolean a() {
        return this.t == 2;
    }
    
    public void a(final boolean b) {
        this.u = 0;
        this.t = (b ? 2 : this.t);
    }
    
    public ad() {
        this.j = 1.0;
        this.t = 0;
        this.u = 0;
    }
    
    public void b() {
        this.k = true;
        this.l = 0;
        this.m = 20;
    }
    
    public boolean c() {
        return this.t == 3;
    }
    
    public void b(final boolean b) {
        this.u = 0;
        this.t = (b ? 3 : this.t);
    }
    
    public boolean d() {
        return this.t == 1;
    }
    
    public void c(final boolean b) {
        this.u = 0;
        this.t = (b ? 1 : this.t);
    }
    
    public void e() {
        this.u = 0;
        if (!this.c()) {
            this.u = (this.a() ? 3 : 1);
        }
    }
    
    public boolean f() {
        return this.t == 0;
    }
    
    public boolean g() {
        return this.u > 0;
    }
}
