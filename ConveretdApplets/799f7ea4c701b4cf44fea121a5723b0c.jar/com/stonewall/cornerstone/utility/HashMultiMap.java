// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HashMultiMap<K, T> implements MultiMap<K, T>
{
    Map<K, List<T>> map;
    
    public HashMultiMap() {
        this.map = new LinkedHashMap<K, List<T>>();
    }
    
    @Override
    public void clear() {
        this.map.clear();
    }
    
    @Override
    public boolean containsKey(final K key) {
        return this.map.containsKey(key);
    }
    
    @Override
    public boolean containsValue(final T value) {
        for (final List list : this.map.values()) {
            if (list.contains(value)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public T getFirst(final K key) {
        final List<T> list = this.getList(key);
        return (list == null) ? null : list.get(0);
    }
    
    @Override
    public Iterator<T> iterator(final K key) {
        return this.getOrCreateList(key).listIterator();
    }
    
    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }
    
    @Override
    public void put(final K key, final T value) {
        final List list = this.getOrCreateList(key);
        list.add(value);
    }
    
    @Override
    public void putAll(final MultiMap<K, T> map) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean remove(final K key, final T value) {
        final List list = this.getList(key);
        return list != null && list.remove(value);
    }
    
    @Override
    public void removeAll(final K key) {
        this.map.remove(key);
    }
    
    @Override
    public int size(final K key) {
        final List list = this.getList(key);
        return (list == null) ? 0 : list.size();
    }
    
    @Override
    public int size() {
        int n = 0;
        for (final List list : this.map.values()) {
            n += list.size();
        }
        return n;
    }
    
    @Override
    public List<T> values() {
        final List list = new ArrayList();
        final Iterator iter = this.map.values().iterator();
        while (iter.hasNext()) {
            list.addAll(iter.next());
        }
        return (List<T>)list;
    }
    
    public List<T> getList(final K key) {
        return this.map.get(key);
    }
    
    public List<T> getOrCreateList(final K key) {
        List<T> list = this.map.get(key);
        if (list == null) {
            list = new ArrayList<T>();
            this.map.put(key, list);
        }
        return list;
    }
    
    public boolean moveKey(final K oldKey, final K newKey) {
        final List<T> list = this.getList(oldKey);
        if (list == null) {
            return false;
        }
        this.map.remove(oldKey);
        this.map.put(newKey, list);
        return true;
    }
}
