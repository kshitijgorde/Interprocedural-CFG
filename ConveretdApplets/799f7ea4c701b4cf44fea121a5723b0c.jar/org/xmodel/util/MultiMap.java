// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public interface MultiMap<K, T>
{
    void clear();
    
    boolean containsKey(final Object p0);
    
    boolean containsValue(final Object p0);
    
    boolean equals(final Object p0);
    
    T getFirst(final K p0);
    
    Iterator<T> iterator(final K p0);
    
    boolean isEmpty();
    
    Set<K> keySet();
    
    List<T> get(final K p0);
    
    void put(final K p0, final T p1);
    
    void putAll(final K p0, final Collection<T> p1);
    
    void putAll(final MultiMap<K, T> p0);
    
    boolean remove(final K p0, final T p1);
    
    List<T> removeAll(final K p0);
    
    int size(final K p0);
    
    int size();
    
    List<T> values();
}
