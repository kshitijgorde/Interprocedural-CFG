// 
// Decompiled by Procyon v0.5.30
// 

abstract class r
{
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    protected bb d;
    
    abstract boolean a(final float[] p0);
    
    boolean a(final int n) {
        return true;
    }
    
    void a() {
    }
    
    void a(final a a) {
    }
    
    protected float b() {
        return 30.0f / this.d.d();
    }
    
    protected float c() {
        return 0.00698f * this.b();
    }
    
    protected float a(final float n, final boolean b) {
        return (float)Math.exp(Math.log(n) + (b ? 1.0f : -1.0f) * this.c());
    }
}
