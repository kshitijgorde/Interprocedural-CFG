// 
// Decompiled by Procyon v0.5.30
// 

package core;

final class P implements CI
{
    private RevolverEngine a;
    
    P(final RevolverEngine a) {
        this.a = a;
    }
    
    public final void a() {
        this.a.c.d(30.0 * this.a.d.b());
    }
    
    public final void b() {
        this.a.c.d(-30.0 * this.a.d.b());
    }
}
