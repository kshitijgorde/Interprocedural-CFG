// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public class n extends EventObject
{
    public final String a;
    public final int b;
    private static /* synthetic */ boolean c;
    
    public n(final Object o, final String a, final int b) {
        super(o);
        if (!n.c && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        if (!n.c && b <= 0) {
            throw new AssertionError();
        }
        this.a = a;
        this.b = b;
    }
    
    static {
        n.c = !n.class.desiredAssertionStatus();
    }
}
