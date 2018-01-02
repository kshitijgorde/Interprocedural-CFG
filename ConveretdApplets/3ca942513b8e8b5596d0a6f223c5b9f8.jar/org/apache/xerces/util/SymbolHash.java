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
    
    public SymbolHash(final int fTableSize) {
        this.fTableSize = 101;
        this.fNum = 0;
        this.fTableSize = fTableSize;
        this.fBuckets = new Entry[this.fTableSize];
    }
    
    public void put(final Object o, final Object value) {
        final int n = (o.hashCode() & Integer.MAX_VALUE) % this.fTableSize;
        final Entry search = this.search(o, n);
        if (search != null) {
            search.value = value;
        }
        else {
            this.fBuckets[n] = new Entry(o, value, this.fBuckets[n]);
            ++this.fNum;
        }
    }
    
    public Object get(final Object o) {
        final Entry search = this.search(o, (o.hashCode() & Integer.MAX_VALUE) % this.fTableSize);
        if (search != null) {
            return search.value;
        }
        return null;
    }
    
    public int getLength() {
        return this.fNum;
    }
    
    public int getValues(final Object[] array, final int n) {
        for (int n2 = 0, n3 = 0; n2 < this.fTableSize && n3 < this.fNum; ++n2) {
            for (Entry next = this.fBuckets[n2]; next != null; next = next.next) {
                array[n + n3] = next.value;
                ++n3;
            }
        }
        return this.fNum;
    }
    
    public SymbolHash makeClone() {
        final SymbolHash symbolHash = new SymbolHash(this.fTableSize);
        symbolHash.fNum = this.fNum;
        for (int i = 0; i < this.fTableSize; ++i) {
            if (this.fBuckets[i] != null) {
                symbolHash.fBuckets[i] = this.fBuckets[i].makeClone();
            }
        }
        return symbolHash;
    }
    
    public void clear() {
        for (int i = 0; i < this.fTableSize; ++i) {
            this.fBuckets[i] = null;
        }
        this.fNum = 0;
    }
    
    protected Entry search(final Object o, final int n) {
        for (Entry next = this.fBuckets[n]; next != null; next = next.next) {
            if (o.equals(next.key)) {
                return next;
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
