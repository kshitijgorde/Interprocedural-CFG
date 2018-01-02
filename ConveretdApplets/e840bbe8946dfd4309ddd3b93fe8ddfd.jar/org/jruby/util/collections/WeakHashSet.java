// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.Set;

public class WeakHashSet<T> implements Set<T>
{
    private WeakHashMap<T, Object> map;
    
    public WeakHashSet() {
        this.map = new WeakHashMap<T, Object>();
    }
    
    public WeakHashSet(final int size) {
        this.map = new WeakHashMap<T, Object>(size);
    }
    
    public boolean add(final T o) {
        final Object previousValue = this.map.put(o, null);
        return previousValue == null;
    }
    
    public Iterator<T> iterator() {
        return this.map.keySet().iterator();
    }
    
    public int size() {
        return this.map.size();
    }
    
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    public boolean contains(final Object o) {
        return this.map.containsKey(o);
    }
    
    public boolean remove(final Object o) {
        final boolean contains = this.contains(o);
        this.map.remove(o);
        return contains;
    }
    
    public boolean removeAll(final Collection collection) {
        return this.map.keySet().removeAll(collection);
    }
    
    public boolean retainAll(final Collection collection) {
        return this.map.keySet().retainAll(collection);
    }
    
    public void clear() {
        this.map.clear();
    }
    
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }
    
    public boolean containsAll(final Collection arg0) {
        return this.map.keySet().containsAll(arg0);
    }
    
    public boolean addAll(final Collection<? extends T> arg0) {
        boolean added = false;
        for (final T i : arg0) {
            this.add(i);
            added = true;
        }
        return added;
    }
    
    public <T> T[] toArray(final T[] arg0) {
        return this.map.keySet().toArray(arg0);
    }
}
