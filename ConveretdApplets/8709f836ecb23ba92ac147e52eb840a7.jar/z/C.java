// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public class C extends EventObject
{
    private static /* synthetic */ boolean a;
    
    public C(final Object o, final String s, final String s2) {
        super(o);
        if (!C.a && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!C.a && s.length() <= 0) {
            throw new AssertionError();
        }
    }
    
    static {
        C.a = !C.class.desiredAssertionStatus();
    }
}
