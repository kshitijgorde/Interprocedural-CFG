// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.List;
import java.util.Set;
import java.util.Iterator;

public interface MultiMap<K, T>
{
    void clear();
    
    boolean containsKey(final K p0);
    
    boolean containsValue(final T p0);
    
    boolean equals(final Object p0);
    
    T getFirst(final K p0);
    
    Iterator<T> iterator(final K p0);
    
    boolean isEmpty();
    
    Set<K> keySet();
    
    void put(final K p0, final T p1);
    
    void putAll(final MultiMap<K, T> p0);
    
    boolean remove(final K p0, final T p1);
    
    void removeAll(final K p0);
    
    int size(final K p0);
    
    int size();
    
    List<T> values();
}
