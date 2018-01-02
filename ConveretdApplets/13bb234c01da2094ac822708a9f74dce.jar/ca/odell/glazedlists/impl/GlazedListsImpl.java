// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.Comparator;

public final class GlazedListsImpl
{
    private GlazedListsImpl() {
        throw new UnsupportedOperationException();
    }
    
    public static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o2 != null && o.equals(o2));
    }
    
    public static Comparator a() {
        return new GlazedListsImpl$EqualsComparator(null);
    }
}
