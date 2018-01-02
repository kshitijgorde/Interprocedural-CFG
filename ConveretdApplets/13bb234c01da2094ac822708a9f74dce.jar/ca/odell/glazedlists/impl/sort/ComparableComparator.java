// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.sort;

import java.util.Comparator;

public final class ComparableComparator implements Comparator
{
    public int a(final Comparable comparable, final Comparable comparable2) {
        if (comparable != null && comparable2 != null) {
            return comparable.compareTo(comparable2);
        }
        if (comparable != null) {
            return 1;
        }
        if (comparable2 == null) {
            return 0;
        }
        return -1;
    }
    
    public boolean equals(final Object o) {
        return o instanceof ComparableComparator;
    }
}
