// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class ba extends EventObject
{
    public final String a;
    public final int b;
    private static /* synthetic */ boolean c;
    
    public ba(final Object o, final String a, final int b) {
        super(o);
        if (!ba.c && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
        this.b = b;
    }
    
    static {
        ba.c = !w.class.desiredAssertionStatus();
    }
}
