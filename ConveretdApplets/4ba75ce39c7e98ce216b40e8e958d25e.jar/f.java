// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    static final int a = -1;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    protected float e;
    protected int f;
    protected Throwable g;
    
    Throwable a() {
        return this.g;
    }
    
    f(final int f) {
        this.e = 0.0f;
        this.f = 0;
        this.g = null;
        this.f = f;
    }
    
    f(final int f, final float e) {
        this.e = 0.0f;
        this.f = 0;
        this.g = null;
        this.f = f;
        this.e = e;
    }
    
    f(final Throwable g) {
        this.e = 0.0f;
        this.f = 0;
        this.g = null;
        this.g = g;
    }
    
    boolean b() {
        return this.g != null || this.f == -1;
    }
    
    boolean c() {
        return this.f == 1;
    }
    
    boolean d() {
        return this.f == 3;
    }
    
    float e() {
        return this.e;
    }
    
    boolean f() {
        return this.f == 2;
    }
}
