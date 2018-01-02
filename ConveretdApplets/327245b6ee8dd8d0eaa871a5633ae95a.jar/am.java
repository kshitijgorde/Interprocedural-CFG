// 
// Decompiled by Procyon v0.5.30
// 

public abstract class am
{
    protected byte[] a;
    protected int b;
    protected int c;
    protected byte[] d;
    protected int e;
    protected int f;
    protected boolean g;
    
    final void a(final byte[] a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    final void a(final byte[] d, final int e, final int f, final boolean g) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    void a() {
        this.a = null;
        this.d = null;
    }
    
    abstract void a(final l p0);
}
