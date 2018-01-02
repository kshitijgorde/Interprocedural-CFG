// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Iterator;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.impl.adt.BarcodeIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import ca.odell.glazedlists.impl.adt.Barcode;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.event.ListEventAssembler;
import ca.odell.glazedlists.event.ListEvent;

class ListSelection$SelectedList extends TransformedList
{
    final /* synthetic */ ListSelection f;
    
    ListSelection$SelectedList(final ListSelection f, final EventList list) {
        this.f = f;
        super(list);
    }
    
    public int size() {
        return this.f.h.a(ListSelection.a);
    }
    
    protected int a(final int n) {
        return this.f.h.a(n, ListSelection.a);
    }
    
    public void a(final ListEvent listEvent) {
    }
    
    public ListEventAssembler d() {
        return this.c;
    }
    
    protected boolean a() {
        return true;
    }
}
