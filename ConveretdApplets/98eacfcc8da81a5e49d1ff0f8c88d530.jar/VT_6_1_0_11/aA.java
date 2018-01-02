// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;

final class aA implements Enumeration
{
    private Enumeration a;
    
    public aA(final Enumeration a) {
        this.a = a;
    }
    
    public final boolean hasMoreElements() {
        return this.a.hasMoreElements();
    }
    
    public final Object nextElement() {
        final W nextElement;
        if ((nextElement = this.a.nextElement()) instanceof W) {
            return nextElement.a();
        }
        return nextElement;
    }
}
