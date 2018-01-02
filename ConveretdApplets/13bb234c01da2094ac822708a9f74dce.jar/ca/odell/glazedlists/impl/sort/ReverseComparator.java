// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.sort;

import java.util.Comparator;

public final class ReverseComparator implements Comparator
{
    private Comparator a;
    
    public ReverseComparator(final Comparator a) {
        this.a = a;
    }
    
    public int compare(final Object o, final Object o2) {
        return this.a.compare(o2, o);
    }
    
    public Comparator a() {
        return this.a;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.a.equals(((ReverseComparator)o).a));
    }
    
    public int hashCode() {
        return this.a.hashCode();
    }
}
