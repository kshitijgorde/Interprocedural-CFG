// 
// Decompiled by Procyon v0.5.30
// 

package core;

public final class BI
{
    private final int a;
    private final RevolverEngine b;
    private int c;
    
    public BI(final RevolverEngine b) {
        this.b = b;
        this.a = b.e.a + 4;
        this.a();
    }
    
    public final int a(final E e) {
        e.a(0, this.c += this.a, this.b.a, this.a, 110 - 2 * this.c);
        return this.c + 2;
    }
    
    public final void a() {
        this.c = -this.a;
    }
}
