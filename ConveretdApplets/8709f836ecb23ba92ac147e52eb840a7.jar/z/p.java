// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class p extends EventObject
{
    public final String a;
    public final boolean b;
    
    public p(final Object o, final String a, final boolean b) {
        super(o);
        this.a = a;
        this.b = b;
    }
}
