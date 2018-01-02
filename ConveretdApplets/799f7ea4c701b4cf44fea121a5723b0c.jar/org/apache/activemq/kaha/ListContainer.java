// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.util.List;

public interface ListContainer<V> extends List<V>
{
    void load();
    
    void unload();
    
    boolean isLoaded();
    
    void setMarshaller(final Marshaller p0);
    
    Object getId();
    
    int size();
    
    void addFirst(final V p0);
    
    void addLast(final V p0);
    
    V removeFirst();
    
    V removeLast();
    
    boolean doRemove(final int p0);
    
    StoreEntry placeLast(final V p0);
    
    StoreEntry placeFirst(final V p0);
    
    void update(final StoreEntry p0, final V p1);
    
    V get(final StoreEntry p0);
    
    StoreEntry getFirst();
    
    StoreEntry getLast();
    
    StoreEntry getNext(final StoreEntry p0);
    
    StoreEntry getPrevious(final StoreEntry p0);
    
    boolean remove(final StoreEntry p0);
    
    StoreEntry refresh(final StoreEntry p0);
}
