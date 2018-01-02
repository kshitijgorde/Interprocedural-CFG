// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import org.apache.activemq.kaha.StoreEntry;

public interface IndexLinkedList
{
    void setRoot(final IndexItem p0);
    
    IndexItem getRoot();
    
    IndexItem getFirst();
    
    IndexItem getLast();
    
    StoreEntry removeFirst();
    
    Object removeLast();
    
    void addFirst(final IndexItem p0);
    
    void addLast(final IndexItem p0);
    
    int size();
    
    boolean isEmpty();
    
    boolean add(final IndexItem p0);
    
    void clear();
    
    IndexItem get(final int p0);
    
    void add(final int p0, final IndexItem p1);
    
    Object remove(final int p0);
    
    int indexOf(final StoreEntry p0);
    
    IndexItem getNextEntry(final IndexItem p0);
    
    IndexItem getPrevEntry(final IndexItem p0);
    
    void remove(final IndexItem p0);
    
    StoreEntry getEntry(final StoreEntry p0);
    
    StoreEntry refreshEntry(final StoreEntry p0);
}
