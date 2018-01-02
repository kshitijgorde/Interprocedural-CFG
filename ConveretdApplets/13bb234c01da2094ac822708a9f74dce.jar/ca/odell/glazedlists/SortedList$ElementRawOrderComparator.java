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

class SortedList$ElementRawOrderComparator implements Comparator
{
    final /* synthetic */ SortedList a;
    
    private SortedList$ElementRawOrderComparator(final SortedList a) {
        this.a = a;
    }
    
    public int compare(final Object o, final Object o2) {
        return this.a.g.a((Element)o, (byte)1) - this.a.g.a((Element)o2, (byte)1);
    }
}
