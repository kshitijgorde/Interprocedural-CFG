// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.util.Hashtable;

final class V extends Hashtable
{
    public final Object a(final String s) {
        return super.get(new W(s));
    }
    
    public final Object a(final String s, final Object o) {
        return super.put(new W(s), o);
    }
    
    public final Object b(final String s) {
        return super.remove(new W(s));
    }
    
    public final Enumeration keys() {
        return new aA(super.keys());
    }
}
