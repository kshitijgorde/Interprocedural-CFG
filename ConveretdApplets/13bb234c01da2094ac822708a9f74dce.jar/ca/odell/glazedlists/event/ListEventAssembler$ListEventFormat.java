// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

import java.util.ConcurrentModificationException;
import ca.odell.glazedlists.impl.event.Tree4Deltas;
import ca.odell.glazedlists.impl.event.BlockSequence;
import ca.odell.glazedlists.impl.WeakReferenceProxy;
import ca.odell.glazedlists.EventList;

class ListEventAssembler$ListEventFormat implements SequenceDependenciesEventPublisher$EventFormat
{
    final /* synthetic */ ListEventAssembler a;
    
    private ListEventAssembler$ListEventFormat(final ListEventAssembler a) {
        this.a = a;
    }
    
    public void a(final EventList list, final ListEvent listEvent, final ListEventListener listEventListener) {
        listEvent.a();
        listEventListener.a(listEvent);
    }
    
    public void a(final EventList list) {
        this.a.i();
        this.a.l = false;
    }
    
    public boolean a(final EventList list, final ListEventListener listEventListener) {
        if (listEventListener instanceof WeakReferenceProxy && ((WeakReferenceProxy)listEventListener).a() == null) {
            ((WeakReferenceProxy)listEventListener).b();
            return true;
        }
        return false;
    }
}
