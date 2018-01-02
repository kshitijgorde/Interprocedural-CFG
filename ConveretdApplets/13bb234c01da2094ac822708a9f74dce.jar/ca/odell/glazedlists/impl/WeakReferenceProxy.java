// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.EventList;
import java.lang.ref.WeakReference;
import ca.odell.glazedlists.event.ListEventListener;

public final class WeakReferenceProxy implements ListEventListener
{
    private final WeakReference a;
    private EventList b;
    
    public WeakReferenceProxy(final EventList b, final ListEventListener listEventListener) {
        if (b == null) {
            throw new IllegalArgumentException("source may not be null");
        }
        if (listEventListener == null) {
            throw new IllegalArgumentException("proxyTarget may not be null");
        }
        this.b = b;
        this.a = new WeakReference((T)listEventListener);
    }
    
    public void a(final ListEvent listEvent) {
        if (this.b == null) {
            return;
        }
        final ListEventListener a = this.a();
        if (a == null) {
            this.b.b(this);
            this.b();
        }
        else {
            a.a(listEvent);
        }
    }
    
    public ListEventListener a() {
        return (ListEventListener)this.a.get();
    }
    
    public void b() {
        this.b = null;
    }
}
