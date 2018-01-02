// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public float a;
    public float k;
    public float c;
    public float m;
    public float p;
    public float b;
    public float h;
    public boolean a;
    public float d;
    
    public boolean a() {
        if (this.a) {
            this.m += this.h * (this.d - this.a);
        }
        this.c = this.m / this.p;
        this.k = (this.k + this.c) * this.b;
        this.a += this.k;
        this.m = 0.0f;
        boolean b = false;
        if (this.k > 1.0E-4f) {
            b = true;
        }
        return b;
    }
    
    public final void a(final float d) {
        this.a = true;
        this.d = d;
    }
    
    private final void c() {
        this.a = 0.0f;
        this.k = 0.0f;
        this.c = 0.0f;
        this.m = 0.0f;
        this.p = 1.0f;
        this.b = 0.5f;
        this.h = 0.2f;
        this.a = false;
        this.d = 0.0f;
    }
    
    public f() {
        this.c();
    }
    
    public f(final float a) {
        this.c();
        this.a = a;
    }
}
