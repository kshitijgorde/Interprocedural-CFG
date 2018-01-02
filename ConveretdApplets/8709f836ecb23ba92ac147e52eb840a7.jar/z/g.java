// 
// Decompiled by Procyon v0.5.30
// 

package z;

final class g
{
    private Thread a;
    
    g(final Thread a) {
        this.a = a;
    }
    
    final synchronized Thread a() {
        return this.a;
    }
    
    final synchronized void b() {
        this.a = null;
    }
}
