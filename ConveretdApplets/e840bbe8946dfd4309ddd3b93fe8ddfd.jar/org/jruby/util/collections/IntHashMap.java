// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.collections;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;

public class IntHashMap<V>
{
    private transient Entry<V>[] table;
    private transient int count;
    transient volatile Set keySet;
    transient volatile Collection values;
    private int threshold;
    private final float loadFactor;
    private transient Set entrySet;
    
    public IntHashMap() {
        this(20, 0.75f);
    }
    
    public IntHashMap(final int initialCapacity) {
        this(initialCapacity, 0.75f);
    }
    
    public IntHashMap(int initialCapacity, final float loadFactor) {
        this.keySet = null;
        this.values = null;
        this.entrySet = null;
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);
        }
        if (initialCapacity == 0) {
            initialCapacity = 1;
        }
        this.loadFactor = loadFactor;
        this.table = (Entry<V>[])new Entry[initialCapacity];
        this.threshold = (int)(initialCapacity * loadFactor);
    }
    
    public int size() {
        return this.count;
    }
    
    public boolean isEmpty() {
        return this.count == 0;
    }
    
    public boolean contains(final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final Entry[] tab = this.table;
        int i = tab.length;
        while (i-- > 0) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean containsValue(final Object value) {
        return this.contains(value);
    }
    
    public boolean containsKey(final int key) {
        final Entry[] tab = this.table;
        final int index = (key & Integer.MAX_VALUE) % tab.length;
        for (Entry e = tab[index]; e != null; e = e.next) {
            if (e.hash == key) {
                return true;
            }
        }
        return false;
    }
    
    public V get(final int key) {
        final Entry<V>[] tab = this.table;
        final int index = (key & Integer.MAX_VALUE) % tab.length;
        for (Entry<V> e = tab[index]; e != null; e = e.next) {
            if (e.hash == key) {
                return e.value;
            }
        }
        return null;
    }
    
    protected void rehash() {
        final int oldCapacity = this.table.length;
        final Entry[] oldMap = this.table;
        final int newCapacity = oldCapacity * 2 + 1;
        final Entry[] newMap = new Entry[newCapacity];
        this.threshold = (int)(newCapacity * this.loadFactor);
        this.table = (Entry<V>[])newMap;
        int i = oldCapacity;
        while (i-- > 0) {
            Entry e;
            int index;
            for (Entry old = oldMap[i]; old != null; old = old.next, index = (e.hash & Integer.MAX_VALUE) % newCapacity, e.next = newMap[index], newMap[index] = e) {
                e = old;
            }
        }
    }
    
    Entry<V> getEntry(final int key) {
        final Entry<V>[] tab = this.table;
        final int index = (key & Integer.MAX_VALUE) % tab.length;
        for (Entry<V> e = tab[index]; e != null; e = e.next) {
            if (e.hash == key) {
                return e;
            }
        }
        return null;
    }
    
    public V put(final int key, final V value) {
        Entry<V>[] tab = this.table;
        int index = (key & Integer.MAX_VALUE) % tab.length;
        for (Entry<V> e = tab[index]; e != null; e = e.next) {
            if (e.hash == key) {
                final V old = e.value;
                e.value = value;
                return old;
            }
        }
        if (this.count >= this.threshold) {
            this.rehash();
            tab = this.table;
            index = (key & Integer.MAX_VALUE) % tab.length;
        }
        final Entry e2 = new Entry(key, key, (V)value, tab[index]);
        tab[index] = (Entry<V>)e2;
        ++this.count;
        return null;
    }
    
    public V remove(final int key) {
        final Entry<V>[] tab = this.table;
        final int index = (key & Integer.MAX_VALUE) % tab.length;
        Entry<V> e = tab[index];
        Entry<V> prev = null;
        while (e != null) {
            if (e.hash == key) {
                if (prev != null) {
                    prev.next = e.next;
                }
                else {
                    tab[index] = e.next;
                }
                --this.count;
                final V oldValue = e.value;
                e.value = null;
                return oldValue;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }
    
    public synchronized void clear() {
        final Entry[] tab = this.table;
        int index = tab.length;
        while (--index >= 0) {
            tab[index] = null;
        }
        this.count = 0;
    }
    
    Iterator newKeyIterator() {
        return new KeyIterator();
    }
    
    Iterator newValueIterator() {
        return new ValueIterator();
    }
    
    Iterator newEntryIterator() {
        return new EntryIterator();
    }
    
    public Set<Integer> keySet() {
        final Set<Integer> ks = (Set<Integer>)this.keySet;
        return (ks != null) ? ks : (this.keySet = new KeySet());
    }
    
    public Collection<V> values() {
        final Collection<V> vs = (Collection<V>)this.values;
        return (vs != null) ? vs : (this.values = new Values());
    }
    
    public Set<Entry> entrySet() {
        final Set es = this.entrySet;
        return (Set<Entry>)((es != null) ? es : (this.entrySet = new EntrySet()));
    }
    
    public static class Entry<V>
    {
        final int hash;
        final int key;
        V value;
        Entry next;
        
        protected Entry(final int hash, final int key, final V value, final Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        public int getKey() {
            return this.key;
        }
        
        public V getValue() {
            return this.value;
        }
    }
    
    private abstract class HashIterator<V> implements Iterator<V>
    {
        Entry<V> next;
        int index;
        
        HashIterator() {
            final Entry[] t = IntHashMap.this.table;
            int i = t.length;
            Entry<V> n = null;
            if (IntHashMap.this.count != 0) {
                while (i > 0 && (n = (Entry<V>)t[--i]) == null) {}
            }
            this.next = n;
            this.index = i;
        }
        
        public boolean hasNext() {
            return this.next != null;
        }
        
        Entry<V> nextEntry() {
            final Entry<V> e = this.next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            Entry<V> n;
            Entry[] t;
            int i;
            for (n = e.next, t = IntHashMap.this.table, i = this.index; n == null && i > 0; n = (Entry<V>)t[--i]) {}
            this.index = i;
            this.next = n;
            return e;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private class ValueIterator extends HashIterator
    {
        public Object next() {
            return this.nextEntry().value;
        }
    }
    
    private class KeyIterator extends HashIterator
    {
        public Object next() {
            return this.nextEntry().key;
        }
    }
    
    private class EntryIterator extends HashIterator
    {
        public Object next() {
            return this.nextEntry();
        }
    }
    
    private class KeySet extends AbstractSet<Integer>
    {
        public Iterator iterator() {
            return IntHashMap.this.newKeyIterator();
        }
        
        public int size() {
            return IntHashMap.this.count;
        }
        
        public boolean contains(final Object o) {
            return o instanceof Number && IntHashMap.this.containsKey(((Number)o).intValue());
        }
        
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        public void clear() {
            IntHashMap.this.clear();
        }
    }
    
    private class Values extends AbstractCollection<V>
    {
        public Iterator iterator() {
            return IntHashMap.this.newValueIterator();
        }
        
        public int size() {
            return IntHashMap.this.count;
        }
        
        public boolean contains(final Object o) {
            return IntHashMap.this.containsValue(o);
        }
        
        public void clear() {
            IntHashMap.this.clear();
        }
    }
    
    private class EntrySet extends AbstractSet<Entry>
    {
        public Iterator<Entry> iterator() {
            return (Iterator<Entry>)IntHashMap.this.newEntryIterator();
        }
        
        public boolean contains(final Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry e = (Entry)o;
            final Entry candidate = IntHashMap.this.getEntry(e.key);
            return candidate != null && candidate.equals(e);
        }
        
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        public int size() {
            return IntHashMap.this.count;
        }
        
        public void clear() {
            IntHashMap.this.clear();
        }
    }
}
