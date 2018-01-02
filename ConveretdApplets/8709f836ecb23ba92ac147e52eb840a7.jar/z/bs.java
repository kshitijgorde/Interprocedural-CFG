// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public class bs extends EventObject
{
    public final String a;
    public final boolean b;
    private static /* synthetic */ boolean c;
    
    public bs(final Object o, final String a, final boolean b) {
        super(o);
        if (!bs.c && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
        this.b = b;
    }
    
    static {
        bs.c = !bs.class.desiredAssertionStatus();
    }
}
