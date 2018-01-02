// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class ListToByteCoder
{
    private final List a;
    private final int b;
    
    public ListToByteCoder(final List list) {
        if (list.size() > 7) {
            throw new IllegalArgumentException("Max 7 colors!");
        }
        this.a = Collections.unmodifiableList((List<?>)new ArrayList<Object>(list));
        this.b = this.a.size();
    }
    
    public List a() {
        return this.a;
    }
    
    public byte a(final List list) {
        int n = 0;
        for (int i = 0; i < list.size(); ++i) {
            n |= 1 << this.a.indexOf(list.get(i));
        }
        return (byte)n;
    }
    
    public byte a(final Object o) {
        return (byte)(1 << this.a.indexOf(o));
    }
}
