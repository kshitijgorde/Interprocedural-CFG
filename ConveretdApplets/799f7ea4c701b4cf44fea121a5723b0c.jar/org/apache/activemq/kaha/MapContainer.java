// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import java.util.Collection;
import java.util.Set;
import java.util.Map;

public interface MapContainer<K, V> extends Map<K, V>
{
    void load();
    
    void unload();
    
    boolean isLoaded();
    
    void setKeyMarshaller(final Marshaller<K> p0);
    
    void setValueMarshaller(final Marshaller<V> p0);
    
    Object getId();
    
    int size();
    
    boolean isEmpty();
    
    boolean containsKey(final K p0);
    
    V get(final K p0);
    
    boolean containsValue(final K p0);
    
    void putAll(final Map<K, V> p0);
    
    Set<K> keySet();
    
    Collection<V> values();
    
    Set<Entry<K, V>> entrySet();
    
    V put(final K p0, final V p1);
    
    V remove(final K p0);
    
    void clear();
    
    StoreEntry place(final K p0, final V p1);
    
    void remove(final StoreEntry p0);
    
    K getKey(final StoreEntry p0);
    
    V getValue(final StoreEntry p0);
    
    StoreEntry getFirst();
    
    StoreEntry getLast();
    
    StoreEntry getNext(final StoreEntry p0);
    
    StoreEntry getPrevious(final StoreEntry p0);
    
    StoreEntry refresh(final StoreEntry p0);
    
    StoreEntry getEntry(final K p0);
    
    void setIndexBinSize(final int p0);
    
    int getIndexBinSize();
    
    void setIndexKeySize(final int p0);
    
    int getIndexKeySize();
    
    void setIndexPageSize(final int p0);
    
    int getIndexPageSize();
    
    void setIndexMaxBinSize(final int p0);
    
    int getIndexMaxBinSize();
    
    int getIndexLoadFactor();
    
    void setIndexLoadFactor(final int p0);
    
    IndexMBean getIndexMBean();
    
    void delete();
}
