// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Iterator;
import ca.odell.glazedlists.impl.GlazedListsImpl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTreeIterator;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTree;
import ca.odell.glazedlists.impl.adt.barcode2.Element;
import java.util.Comparator;

class SortedList$ElementComparator implements Comparator
{
    private Comparator b;
    final /* synthetic */ SortedList a;
    
    public SortedList$ElementComparator(final SortedList a, final Comparator b) {
        this.a = a;
        this.b = b;
    }
    
    public int compare(final Object o, final Object o2) {
        Object value = o;
        Object value2 = o2;
        int a = -1;
        int a2 = -1;
        if (o instanceof Element) {
            a = this.a.g.a((Element)o, (byte)1);
            value = this.a.a.get(a);
        }
        if (o2 instanceof Element) {
            a2 = this.a.g.a((Element)o2, (byte)1);
            value2 = this.a.a.get(a2);
        }
        final int compare = this.b.compare(value, value2);
        if (compare != 0) {
            return compare;
        }
        if (a != -1 && a2 != -1) {
            return a - a2;
        }
        return 0;
    }
}
