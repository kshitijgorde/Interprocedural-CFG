// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class an extends EventObject
{
    public final String a;
    private static /* synthetic */ boolean b;
    
    public an(final Object o, final String a) {
        super(o);
        if (!an.b && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
    }
    
    static {
        an.b = !w.class.desiredAssertionStatus();
    }
}
