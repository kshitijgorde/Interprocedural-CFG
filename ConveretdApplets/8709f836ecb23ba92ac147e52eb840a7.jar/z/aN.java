// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class aN extends EventObject
{
    public final String a;
    public final boolean b;
    private static /* synthetic */ boolean c;
    
    public aN(final Object o, final String a, final boolean b) {
        super(o);
        if (!aN.c && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
        this.b = b;
    }
    
    static {
        aN.c = !w.class.desiredAssertionStatus();
    }
}
