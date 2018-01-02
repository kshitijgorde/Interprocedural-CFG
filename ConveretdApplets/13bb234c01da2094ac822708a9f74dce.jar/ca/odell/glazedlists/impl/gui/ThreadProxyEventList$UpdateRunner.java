// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import java.util.Collection;
import java.util.ArrayList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEventAssembler;
import java.util.List;
import java.util.RandomAccess;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

class ThreadProxyEventList$UpdateRunner implements ListEventListener, Runnable
{
    final /* synthetic */ ThreadProxyEventList a;
    
    private ThreadProxyEventList$UpdateRunner(final ThreadProxyEventList a) {
        this.a = a;
    }
    
    public void run() {
        this.a.b().a().a();
        try {
            this.a.g.c();
            this.a.c.c();
        }
        finally {
            this.a.h = false;
            this.a.b().a().b();
        }
    }
    
    public void a(final ListEvent listEvent) {
        this.a.b = this.a.a(this.a.a, listEvent, this.a.b);
    }
}
