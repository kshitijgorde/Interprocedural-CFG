// 
// Decompiled by Procyon v0.5.30
// 

package core;

public final class Z
{
    private boolean a;
    private boolean b;
    private final CI c;
    
    public Z(final CI c) {
        this.a = false;
        this.b = false;
        this.c = c;
    }
    
    public final void a() {
        if (this.a) {
            this.c.a();
            return;
        }
        if (this.b) {
            this.c.b();
        }
    }
    
    public final F b() {
        return new F(this);
    }
    
    public final X c() {
        return new X(this);
    }
    
    static final void a(final Z z, final boolean a) {
        z.a = a;
    }
    
    static final void b(final Z z, final boolean b) {
        z.b = b;
    }
}
