// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

public final class X extends s
{
    private int g;
    private int h;
    
    X(final s s, final int g, final int h) {
        super(s);
        this.g = g;
        this.h = h;
    }
    
    final int h() {
        return this.g * this.d;
    }
    
    final int i() {
        return this.h * this.e;
    }
    
    final int a() {
        return this.a + this.g * this.c();
    }
    
    final int b() {
        return this.b - this.h * this.d();
    }
}
