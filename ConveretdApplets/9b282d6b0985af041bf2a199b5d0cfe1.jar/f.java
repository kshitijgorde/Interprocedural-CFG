// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    static final int a = -1;
    static final int b = 1;
    static final int c = 2;
    static final int d = 3;
    static final int e = 4;
    protected float f;
    protected int g;
    protected Throwable h;
    
    Throwable a() {
        return this.h;
    }
    
    f(final int g) {
        this.f = 0.0f;
        this.g = 0;
        this.h = null;
        this.g = g;
    }
    
    f(final int g, final float f) {
        this.f = 0.0f;
        this.g = 0;
        this.h = null;
        this.g = g;
        this.f = f;
    }
    
    f(final Throwable h) {
        this.f = 0.0f;
        this.g = 0;
        this.h = null;
        this.h = h;
    }
    
    boolean b() {
        return this.h != null || this.g == -1;
    }
    
    boolean c() {
        return this.g == 1;
    }
    
    boolean d() {
        return this.g == 3;
    }
    
    float e() {
        return this.f;
    }
    
    boolean f() {
        return this.g == 2;
    }
    
    boolean g() {
        return this.g == 4;
    }
}
