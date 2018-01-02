// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.impl.GlazedListsImpl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import java.util.Comparator;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTree;
import ca.odell.glazedlists.impl.adt.barcode2.Element;
import ca.odell.glazedlists.impl.adt.barcode2.SimpleTreeIterator;
import java.util.Iterator;

class SortedList$SortedListIterator implements Iterator
{
    private SimpleTreeIterator b;
    final /* synthetic */ SortedList a;
    
    private SortedList$SortedListIterator(final SortedList a) {
        this.a = a;
        this.b = new SimpleTreeIterator(this.a.h);
    }
    
    public boolean hasNext() {
        return this.b.a();
    }
    
    public Object next() {
        this.b.b();
        return this.a.a.get(this.a.g.a((Element)this.b.d(), (byte)1));
    }
    
    public void remove() {
        final int c = this.b.c();
        this.a.a.remove(this.a.a(c));
        this.b = new SimpleTreeIterator(this.a.h, c, (byte)1);
    }
}
