// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class bc extends EventObject
{
    public String a;
    public Exception b;
    
    public bc(final Object o, final String a, final Exception b) {
        super(o);
        this.a = a;
        this.b = b;
    }
}
