// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public class ad extends EventObject
{
    public final String a;
    public final String b;
    private static /* synthetic */ boolean c;
    
    public ad(final Object o, final String a, final String b) {
        super(o);
        if (!ad.c && (a == null || a.length() <= 0)) {
            throw new AssertionError();
        }
        if (!ad.c && (b == null || b.length() <= 0)) {
            throw new AssertionError();
        }
        this.a = a;
        this.b = b;
    }
    
    static {
        ad.c = !ad.class.desiredAssertionStatus();
    }
}
