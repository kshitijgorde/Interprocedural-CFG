// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.AbstractSet;
import java.util.Set;
import java.util.AbstractCollection;
import java.util.Collection;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

public abstract class GenericMap implements Map
{
    protected int size;
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    protected int keyHash(final Object key) {
        return (key == null) ? 0 : key.hashCode();
    }
    
    protected boolean keyEquals(final Object containedKey, final Object givenKey) {
        return (containedKey == null) ? (givenKey == null) : containedKey.equals(givenKey);
    }
    
    protected int valueHash(final Object value) {
        return (value == null) ? 0 : value.hashCode();
    }
    
    protected boolean valueEquals(final Object value1, final Object value2) {
        return (value1 == null) ? (value2 == null) : value1.equals(value2);
    }
    
    public void putAll(final Map other) {
        if (other == this) {
            return;
        }
        for (final Map.Entry entry : other.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    protected abstract Iterator entryIterator();
    
    protected Iterator keyIterator() {
        return new KeyIterator();
    }
    
    protected Iterator valueIterator() {
        return new ValueIterator();
    }
    
    private static Object[] toArray(final Object[] arr, final int size, final Iterator it) {
        Object[] out;
        if (arr != null && arr.length >= size) {
            out = arr;
        }
        else if (arr == null) {
            out = new Object[size];
        }
        else {
            out = (Object[])Array.newInstance(arr.getClass().getComponentType(), size);
        }
        for (int i = 0; i < size; ++i) {
            out[i] = it.next();
        }
        if (out.length > size) {
            out[size] = null;
        }
        return out;
    }
    
    public Collection values() {
        return new AbstractCollection() {
            public Iterator iterator() {
                return GenericMap.this.valueIterator();
            }
            
            public int size() {
                return GenericMap.this.size();
            }
            
            public Object[] toArray(final Object[] arr) {
                return toArray(arr, this.size(), this.iterator());
            }
        };
    }
    
    public Set keySet() {
        return new AbstractSet() {
            public Iterator iterator() {
                return GenericMap.this.keyIterator();
            }
            
            public int size() {
                return GenericMap.this.size();
            }
            
            public Object[] toArray(final Object[] arr) {
                return toArray(arr, this.size(), this.iterator());
            }
        };
    }
    
    public int hashCode() {
        int code = 0;
        final Iterator it = this.entryIterator();
        while (it.hasNext()) {
            code += it.next().hashCode();
        }
        return code;
    }
    
    public boolean equals(final Object other) {
        if (!(other instanceof Map)) {
            return false;
        }
        final Map map = (Map)other;
        if (map.size() != this.size()) {
            return false;
        }
        final Iterator it = this.entryIterator();
        while (it.hasNext()) {
            final Entry ent = it.next();
            final Object key = ent.getKey();
            final Object val = ent.getValue();
            if (map.containsKey(key)) {
                final Object otherVal = map.get(key);
                if (!this.valueEquals(val, otherVal)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    public Set entrySet() {
        return new AbstractSet() {
            public Iterator iterator() {
                return GenericMap.this.entryIterator();
            }
            
            public int size() {
                return GenericMap.this.size;
            }
            
            public Object[] toArray(final Object[] arr) {
                return toArray(arr, this.size(), this.iterator());
            }
        };
    }
    
    public boolean containsValue(final Object value) {
        final Iterator it = this.valueIterator();
        while (it.hasNext()) {
            if (this.valueEquals(value, it.next())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsKey(final Object key) {
        return this.get(key) != null;
    }
    
    abstract class Entry implements Map.Entry
    {
        public int hashCode() {
            return GenericMap.this.keyHash(this.getKey()) ^ GenericMap.this.valueHash(this.getValue());
        }
        
        public boolean equals(final Object other) {
            if (other instanceof Map.Entry) {
                final Map.Entry ent = (Map.Entry)other;
                return GenericMap.this.keyEquals(this.getKey(), ent.getKey()) && GenericMap.this.valueEquals(this.getValue(), ent.getValue());
            }
            return false;
        }
    }
    
    abstract class KeyOrValueIterator implements Iterator
    {
        Iterator iter;
        
        KeyOrValueIterator() {
            this.iter = GenericMap.this.entryIterator();
        }
        
        public boolean hasNext() {
            return this.iter.hasNext();
        }
        
        protected Map.Entry nextEntry() {
            return this.iter.next();
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    class KeyIterator extends KeyOrValueIterator
    {
        public Object next() {
            return this.nextEntry().getKey();
        }
    }
    
    class ValueIterator extends KeyOrValueIterator
    {
        public Object next() {
            return this.nextEntry().getValue();
        }
    }
}
