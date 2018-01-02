// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;

public final class bk extends EventObject
{
    final String a;
    final int b;
    
    public bk(final Object o, final String a, final int b) {
        super(o);
        this.a = a;
        this.b = b;
    }
}
