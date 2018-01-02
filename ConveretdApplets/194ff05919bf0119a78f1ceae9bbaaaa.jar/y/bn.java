// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Container;

final class bn extends cb
{
    private dh a;
    
    bn(final dh a) {
        super(a);
        this.a = a;
    }
    
    public final boolean a() {
        return this.a.isActive();
    }
    
    public final void a(final Throwable t) {
        this.a.a(t);
    }
}
