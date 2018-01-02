// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HashMultiMap<K, T> implements MultiMap<K, T>
{
    Map<K, List<T>> B;
    int A;
    
    public HashMultiMap() {
        this.B = new LinkedHashMap<K, List<T>>();
    }
    
    public HashMultiMap(final int n) {
        this.B = new LinkedHashMap<K, List<T>>(n);
    }
    
    public HashMultiMap(final int n, final float n2) {
        this.B = new LinkedHashMap<K, List<T>>(n, n2);
    }
    
    @Override
    public void clear() {
        this.B.clear();
        this.A = 0;
    }
    
    @Override
    public boolean containsKey(final Object o) {
        return this.B.containsKey(o);
    }
    
    @Override
    public boolean containsValue(final Object o) {
        final Iterator<List<T>> iterator = this.B.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().contains(o)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<T> get(final K k) {
        final List<T> list = this.B.get(k);
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList((List<? extends T>)list);
    }
    
    @Override
    public T getFirst(final K k) {
        final List<T> list = this.B.get(k);
        return (list == null) ? null : list.get(0);
    }
    
    @Override
    public Iterator<T> iterator(final K k) {
        return this.A(k).listIterator();
    }
    
    @Override
    public boolean isEmpty() {
        return this.B.isEmpty();
    }
    
    @Override
    public Set<K> keySet() {
        return this.B.keySet();
    }
    
    @Override
    public void put(final K k, final T t) {
        this.A(k).add(t);
        ++this.A;
    }
    
    @Override
    public void putAll(final K k, final Collection<T> collection) {
        this.A(k).addAll((Collection<? extends T>)collection);
        this.A += collection.size();
    }
    
    @Override
    public void putAll(final MultiMap<K, T> multiMap) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean remove(final K k, final T t) {
        final List<T> list = this.B.get(k);
        if (list == null) {
            return false;
        }
        if (list.remove(t)) {
            if (list.size() == 0) {
                this.B.remove(k);
            }
            --this.A;
            return true;
        }
        return false;
    }
    
    @Override
    public List<T> removeAll(final K k) {
        final List<T> list = this.B.remove(k);
        if (list != null) {
            this.A -= list.size();
        }
        return list;
    }
    
    @Override
    public int size(final K k) {
        final List<T> list = this.B.get(k);
        return (list == null) ? 0 : list.size();
    }
    
    @Override
    public int size() {
        return this.A;
    }
    
    @Override
    public List<T> values() {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<T>(1);
        final Iterator<List<T>> iterator = this.B.values().iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next());
        }
        return (List<T>)list;
    }
    
    private List<T> A(final K k) {
        List<T> list = this.B.get(k);
        if (list == null) {
            list = new ArrayList<T>(1);
            this.B.put(k, list);
        }
        return list;
    }
}
