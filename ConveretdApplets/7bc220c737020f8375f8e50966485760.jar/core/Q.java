// 
// Decompiled by Procyon v0.5.30
// 

package core;

final class Q implements CI
{
    private RevolverEngine a;
    
    Q(final RevolverEngine a) {
        this.a = a;
    }
    
    public final void a() {
        this.a.c.a(-this.a.d.b());
    }
    
    public final void b() {
        this.a.c.a(this.a.d.b());
    }
}
