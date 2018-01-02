// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public final class ComparatorChain implements Comparator
{
    private final Comparator[] a;
    
    public ComparatorChain(final List list) {
        this.a = list.toArray(new Comparator[list.size()]);
    }
    
    public int compare(final Object o, final Object o2) {
        for (int i = 0; i < this.a.length; ++i) {
            final int compare = this.a[i].compare(o, o2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }
    
    public Comparator[] a() {
        return this.a;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && Arrays.equals(this.a, ((ComparatorChain)o).a));
    }
    
    public int hashCode() {
        return 0;
    }
}
