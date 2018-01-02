// 
// Decompiled by Procyon v0.5.30
// 

abstract class bb
{
    static final int a = 0;
    static final int b = 1;
    static final int c = 2;
    protected bo d;
    
    void a() {
    }
    
    protected float b() {
        return 0.00698f * this.c();
    }
    
    protected float c() {
        return 30.0f / this.d.a();
    }
    
    protected float a(final float n, final boolean b) {
        return (float)Math.exp(Math.log(n) + (b ? 1.0f : -1.0f) * this.b());
    }
    
    abstract boolean a(final float[] p0);
    
    boolean a(final int n) {
        return true;
    }
}
