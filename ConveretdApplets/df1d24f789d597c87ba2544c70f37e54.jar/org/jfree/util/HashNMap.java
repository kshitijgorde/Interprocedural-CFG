// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;

public class HashNMap implements Serializable, Cloneable
{
    private static final Iterator EMPTY_ITERATOR;
    private HashMap table;
    
    public HashNMap() {
        this.table = null;
        this.table = new HashMap();
    }
    
    public void put(final Object o, final Object o2) {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.add(o2);
        this.table.put(o, list);
    }
    
    public void add(final Object o, final Object o2) {
        final ArrayList<Object> list = this.table.get(o);
        if (list == null) {
            this.put(o, o2);
        }
        else {
            list.add(o2);
        }
    }
    
    public Object getFirst(final Object o) {
        return this.get(o, 0);
    }
    
    public Object get(final Object o, final int n) {
        final ArrayList<Object> list = this.table.get(o);
        if (list == null) {
            return null;
        }
        return list.get(n);
    }
    
    public Iterator getAll(final Object o) {
        final ArrayList list = this.table.get(o);
        if (list == null) {
            return HashNMap.EMPTY_ITERATOR;
        }
        return list.iterator();
    }
    
    public Iterator keys() {
        return this.table.keySet().iterator();
    }
    
    public Set keySet() {
        return this.table.keySet();
    }
    
    public boolean remove(final Object o, final Object o2) {
        final ArrayList list = this.table.get(o);
        if (list == null) {
            return false;
        }
        if (!list.remove(o2)) {
            return false;
        }
        if (list.size() == 0) {
            this.table.remove(o);
        }
        return true;
    }
    
    public void removeAll(final Object o) {
        this.table.remove(o);
    }
    
    public void clear() {
        this.table.clear();
    }
    
    public boolean containsKey(final Object o) {
        return this.table.containsKey(o);
    }
    
    public boolean containsValue(final Object o) {
        Iterator<ArrayList> iterator;
        boolean contains;
        for (iterator = this.table.values().iterator(), contains = false; iterator.hasNext() && !contains; contains = iterator.next().contains(o)) {}
        return contains;
    }
    
    public boolean containsValue(final Object o, final Object o2) {
        final ArrayList list = this.table.get(o);
        return list != null && list.contains(o2);
    }
    
    public boolean contains(final Object o) {
        return this.containsKey(o) || this.containsValue(o);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final HashNMap hashNMap = (HashNMap)super.clone();
        hashNMap.table = new HashMap();
        final Iterator keys = this.keys();
        while (keys.hasNext()) {
            final Object next = keys.next();
            final ArrayList list = hashNMap.table.get(next);
            if (list != null) {
                hashNMap.table.put(next, list.clone());
            }
        }
        return hashNMap;
    }
    
    public Object[] toArray(final Object o, final Object[] array) {
        if (o == null) {
            throw new NullPointerException("Key must not be null.");
        }
        final ArrayList list = this.table.get(o);
        if (list != null) {
            return list.toArray(array);
        }
        return new Object[0];
    }
    
    public Object[] toArray(final Object o) {
        if (o == null) {
            throw new NullPointerException("Key must not be null.");
        }
        final ArrayList list = this.table.get(o);
        if (list != null) {
            return list.toArray();
        }
        return new Object[0];
    }
    
    public int getValueCount(final Object o) {
        if (o == null) {
            throw new NullPointerException("Key must not be null.");
        }
        final ArrayList list = this.table.get(o);
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    
    static {
        EMPTY_ITERATOR = new EmptyIterator();
    }
    
    private static final class EmptyIterator implements Iterator
    {
        public boolean hasNext() {
            return false;
        }
        
        public Object next() {
            throw new NoSuchElementException("This iterator is empty.");
        }
        
        public void remove() {
            throw new UnsupportedOperationException("This iterator is empty, no remove supported.");
        }
    }
}
