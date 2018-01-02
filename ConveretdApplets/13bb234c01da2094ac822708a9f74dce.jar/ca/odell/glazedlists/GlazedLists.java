// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.impl.sort.ReverseComparator;
import ca.odell.glazedlists.impl.sort.ComparableComparator;
import ca.odell.glazedlists.impl.sort.ComparatorChain;
import ca.odell.glazedlists.impl.Diff;
import java.util.List;
import java.util.Comparator;

public final class GlazedLists
{
    private static Comparator a;
    private static Comparator b;
    private static Comparator c;
    private static TextFilterator d;
    
    private GlazedLists() {
        throw new UnsupportedOperationException();
    }
    
    public static void a(final EventList list, final List list2, final boolean b) {
        Diff.a(list, list2, b);
    }
    
    public static void a(final EventList list, final List list2, final boolean b, final Comparator comparator) {
        Diff.a(list, list2, b, comparator);
    }
    
    public static Comparator a(final List list) {
        return new ComparatorChain(list);
    }
    
    public static Comparator a() {
        if (GlazedLists.b == null) {
            GlazedLists.b = new ComparableComparator();
        }
        return GlazedLists.b;
    }
    
    public static Comparator a(final Comparator comparator) {
        return new ReverseComparator(comparator);
    }
    
    static {
        GlazedLists.a = null;
        GlazedLists.b = null;
        GlazedLists.c = null;
        GlazedLists.d = null;
    }
}
