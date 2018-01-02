// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.event.ListEventPublisher;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;
import ca.odell.glazedlists.event.ListEventListener;
import java.util.List;

public interface EventList extends List
{
    void a(final ListEventListener p0);
    
    void b(final ListEventListener p0);
    
    ReadWriteLock b();
    
    ListEventPublisher c();
}
