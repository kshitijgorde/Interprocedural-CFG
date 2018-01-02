// 
// Decompiled by Procyon v0.5.30
// 

package kiang.util;

import java.util.Comparator;

public class ComparableComparator implements Comparator
{
    public int compare(final Object o1, final Object o2) {
        final Comparable c1 = (Comparable)o1;
        return c1.compareTo(o2);
    }
}
