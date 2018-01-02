// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.util.Iterator;
import java.io.Serializable;
import java.util.Set;
import java.util.AbstractSet;

public class LRUSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable
{
    private static final Object IGNORE;
    private final LRUCache cache;
    
    public LRUSet() {
        this(0, 10000, 0.75f, true);
    }
    
    public LRUSet(final int maximumCacheSize) {
        this(0, maximumCacheSize, 0.75f, true);
    }
    
    public LRUSet(final int initialCapacity, final int maximumCacheSize, final float loadFactor, final boolean accessOrder) {
        this.cache = new LRUCache(initialCapacity, maximumCacheSize, loadFactor, accessOrder);
    }
    
    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>)this.cache.keySet().iterator();
    }
    
    @Override
    public int size() {
        return this.cache.size();
    }
    
    @Override
    public boolean isEmpty() {
        return this.cache.isEmpty();
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.cache.containsKey(o);
    }
    
    @Override
    public boolean add(final E o) {
        return this.cache.put(o, LRUSet.IGNORE) == null;
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.cache.remove(o) == LRUSet.IGNORE;
    }
    
    @Override
    public void clear() {
        this.cache.clear();
    }
    
    static {
        IGNORE = new Object();
    }
}
