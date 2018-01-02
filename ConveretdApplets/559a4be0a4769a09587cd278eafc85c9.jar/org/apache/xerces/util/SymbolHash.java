// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

public class SymbolHash
{
    protected int fTableSize;
    protected Entry[] fBuckets;
    protected int fNum;
    
    public SymbolHash() {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fBuckets = new Entry[this.fTableSize];
    }
    
    public SymbolHash(final int size) {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fTableSize = size;
        this.fBuckets = new Entry[this.fTableSize];
    }
    
    public void put(final Object key, final Object value) {
        final int bucket = (key.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
        Entry entry = this.search(key, bucket);
        if (entry != null) {
            entry.value = value;
        }
        else {
            entry = new Entry(key, value, this.fBuckets[bucket]);
            this.fBuckets[bucket] = entry;
            ++this.fNum;
        }
    }
    
    public Object get(final Object key) {
        final int bucket = (key.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
        final Entry entry = this.search(key, bucket);
        if (entry != null) {
            return entry.value;
        }
        return null;
    }
    
    public int getLength() {
        return this.fNum;
    }
    
    public int getValues(final Object[] elements, final int from) {
        for (int i = 0, j = 0; i < this.fTableSize && j < this.fNum; ++i) {
            for (Entry entry = this.fBuckets[i]; entry != null; entry = entry.next) {
                elements[from + j] = entry.value;
                ++j;
            }
        }
        return this.fNum;
    }
    
    public SymbolHash makeClone() {
        final SymbolHash newTable = new SymbolHash(this.fTableSize);
        newTable.fNum = this.fNum;
        for (int i = 0; i < this.fTableSize; ++i) {
            if (this.fBuckets[i] != null) {
                newTable.fBuckets[i] = this.fBuckets[i].makeClone();
            }
        }
        return newTable;
    }
    
    public void clear() {
        for (int i = 0; i < this.fTableSize; ++i) {
            this.fBuckets[i] = null;
        }
        this.fNum = 0;
    }
    
    protected Entry search(final Object key, final int bucket) {
        for (Entry entry = this.fBuckets[bucket]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }
    
    protected static final class Entry
    {
        public Object key;
        public Object value;
        public Entry next;
        
        public Entry() {
            this.key = null;
            this.value = null;
            this.next = null;
        }
        
        public Entry(final Object key, final Object value, final Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        public Entry makeClone() {
            final Entry entry = new Entry();
            entry.key = this.key;
            entry.value = this.value;
            if (this.next != null) {
                entry.next = this.next.makeClone();
            }
            return entry;
        }
    }
}
