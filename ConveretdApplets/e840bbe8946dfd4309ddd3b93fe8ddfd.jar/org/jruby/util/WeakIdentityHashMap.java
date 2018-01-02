// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.NoSuchElementException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.lang.ref.ReferenceQueue;
import java.util.Map;

public class WeakIdentityHashMap extends GenericMap implements Map
{
    private static final float DEFAULT_RATIO = 0.75f;
    private static final Object NULL_KEY;
    private final ReferenceQueue queue;
    private Entry[] table;
    private int range;
    private float ratio;
    
    private static Object unmaskKey(final Object key) {
        if (key == WeakIdentityHashMap.NULL_KEY) {
            return null;
        }
        return key;
    }
    
    private Object maskKey(final Object key) {
        if (key == null) {
            return WeakIdentityHashMap.NULL_KEY;
        }
        return key;
    }
    
    private int index(final int hash) {
        return (hash & 0x7FFFFFF) % this.range;
    }
    
    public WeakIdentityHashMap() {
        this.queue = new ReferenceQueue();
        this.clear(3);
    }
    
    public WeakIdentityHashMap(final int size) {
        this.queue = new ReferenceQueue();
        this.clear(Math.max(3, Math.round(size / 0.75f)));
    }
    
    public void clear() {
        this.clear(3);
    }
    
    private void clear(final int size) {
        this.range = size;
        this.size = 0;
        this.ratio = 0.75f;
        this.table = new Entry[this.range];
    }
    
    private void expunge() {
        Entry e;
        while ((e = (Entry)this.queue.poll()) != null) {
            this.removeEntry(e);
        }
    }
    
    public Object get(final Object key) {
        final Object masked_key = this.maskKey(key);
        final int hash = this.keyHash(masked_key);
        return this.get(hash, masked_key);
    }
    
    private Object get(final int hash, final Object masked_key) {
        final int idx = this.index(hash);
        this.expunge();
        for (Entry ent = this.table[idx]; ent != null; ent = ent.next) {
            if (ent.sameKey(hash, masked_key)) {
                return ent.value;
            }
        }
        return null;
    }
    
    public boolean containsKey(final Object key) {
        final Object masked_key = this.maskKey(key);
        final int hash = this.keyHash(masked_key);
        return this.containsKey(hash, masked_key);
    }
    
    private boolean containsKey(final int hash, final Object masked_key) {
        final int idx = this.index(hash);
        this.expunge();
        for (Entry ent = this.table[idx]; ent != null; ent = ent.next) {
            if (ent.sameKey(hash, masked_key)) {
                return true;
            }
        }
        return false;
    }
    
    public Object put(final Object key, final Object value) {
        final Object masked_key = this.maskKey(key);
        final int hash = this.keyHash(masked_key);
        return this.put(hash, masked_key, value);
    }
    
    private Object put(final int hash, final Object masked_key, final Object value) {
        int idx = this.index(hash);
        for (Entry ent = this.table[idx]; ent != null; ent = ent.next) {
            if (ent.sameKey(hash, masked_key)) {
                return ent.setValue(value);
            }
        }
        this.expunge();
        if (1.0f * this.size / this.range > this.ratio) {
            this.grow();
            idx = this.index(hash);
        }
        this.table[idx] = new Entry(hash, masked_key, value, this.table[idx], this.queue);
        ++this.size;
        return null;
    }
    
    public Object remove(Object key) {
        key = this.maskKey(key);
        final int hash = this.keyHash(key);
        return this.remove(hash, key);
    }
    
    public Object remove(final int hash, Object key) {
        key = this.maskKey(key);
        final int idx = this.index(hash);
        Entry entry = this.table[idx];
        if (entry != null) {
            if (entry.sameKey(hash, key)) {
                this.table[idx] = entry.next;
                --this.size;
                return entry.getValue();
            }
            for (Entry ahead = entry.next; ahead != null; ahead = ahead.next) {
                if (ahead.sameKey(hash, key)) {
                    entry.next = ahead.next;
                    --this.size;
                    return ahead.getValue();
                }
                entry = ahead;
            }
        }
        return null;
    }
    
    private void removeEntry(final Entry ent) {
        final int idx = this.index(ent.key_hash);
        Entry entry = this.table[idx];
        if (entry != null) {
            if (entry == ent) {
                this.table[idx] = entry.next;
                --this.size;
                return;
            }
            for (Entry ahead = entry.next; ahead != null; ahead = ahead.next) {
                if (ahead == ent) {
                    entry.next = ahead.next;
                    --this.size;
                    return;
                }
                entry = ahead;
            }
        }
        this.valueRemoved(ent.value);
    }
    
    protected void valueRemoved(final Object value) {
    }
    
    private void grow() {
        final int old_range = this.range;
        final Entry[] old_table = this.table;
        this.range = old_range * 2 + 1;
        this.table = new Entry[this.range];
        for (Entry entry : old_table) {
            while (entry != null) {
                final Entry ahead = entry.next;
                final int idx = this.index(entry.key_hash);
                entry.next = this.table[idx];
                this.table[idx] = entry;
                entry = ahead;
            }
        }
    }
    
    protected Iterator entryIterator() {
        return new EntryIterator();
    }
    
    protected final int keyHash(final Object key) {
        return System.identityHashCode(key);
    }
    
    protected final boolean keyEquals(final Object key1, final Object key2) {
        return key1 == key2;
    }
    
    public int size() {
        this.expunge();
        return super.size();
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    static {
        NULL_KEY = new Object();
    }
    
    class Entry extends WeakReference implements Map.Entry
    {
        private final int key_hash;
        private Entry next;
        private Object value;
        
        public int hashCode() {
            return this.key_hash ^ WeakIdentityHashMap.this.valueHash(this.getValue());
        }
        
        public boolean equals(final Object other) {
            if (other instanceof Map.Entry) {
                final Map.Entry ent = (Map.Entry)other;
                return this.getKey() == ent.getKey() && WeakIdentityHashMap.this.valueEquals(this.getValue(), ent.getValue());
            }
            return false;
        }
        
        Entry(final int key_hash, final Object masked_key, final Object value, final Entry next, final ReferenceQueue q) {
            super(masked_key, q);
            this.key_hash = key_hash;
            this.value = value;
            this.next = next;
        }
        
        Object getMaskedKey() {
            return super.get();
        }
        
        public Object getKey() {
            return unmaskKey(this.getMaskedKey());
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Object setValue(final Object value) {
            final Object result = this.value;
            this.value = value;
            return result;
        }
        
        boolean sameKey(final int hash, final Object masked_key) {
            return this.getMaskedKey() == masked_key;
        }
    }
    
    final class EntryIterator implements Iterator
    {
        private int idx;
        private Entry entry;
        
        EntryIterator() {
            this.idx = 0;
            WeakIdentityHashMap.this.expunge();
            this.entry = WeakIdentityHashMap.this.table[0];
            this.locateNext();
        }
        
        private void locateNext() {
            while (this.entry == null) {
                ++this.idx;
                if (this.idx == WeakIdentityHashMap.this.range) {
                    return;
                }
                this.entry = WeakIdentityHashMap.this.table[this.idx];
            }
        }
        
        public boolean hasNext() {
            return this.entry != null;
        }
        
        public Object next() {
            final Object result = this.entry;
            if (result == null) {
                throw new NoSuchElementException();
            }
            this.entry = this.entry.next;
            this.locateNext();
            return result;
        }
        
        public void remove() {
            final Entry remove = this.entry;
            WeakIdentityHashMap.this.expunge();
            this.entry = this.entry.next;
            this.locateNext();
            WeakIdentityHashMap.this.removeEntry(remove);
        }
    }
}
