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

class ListSelection$DeselectedList extends TransformedList
{
    final /* synthetic */ ListSelection b;
    
    public int size() {
        return this.b.h.a(ListSelection.b);
    }
    
    protected int a(final int n) {
        return this.b.h.a(n, ListSelection.b);
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
