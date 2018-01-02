// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

import org.jcodings.ascii.AsciiTables;

public final class CaseInsensitiveBytesHash<V> extends Hash<V>
{
    public CaseInsensitiveBytesHash() {
    }
    
    public CaseInsensitiveBytesHash(final int size) {
        super(size);
    }
    
    protected void init() {
        this.head = (HashEntry<V>)new CaseInsensitiveBytesHashEntry<Object>();
    }
    
    public static int hashCode(final byte[] bytes, int p, final int end) {
        int key;
        for (key = 0; p < end; key = (key << 16) + (key << 6) - key + AsciiTables.ToLowerCaseTable[bytes[p++] & 0xFF]) {}
        key += key >> 5;
        return key;
    }
    
    public V put(final byte[] bytes, final V value) {
        return this.put(bytes, 0, bytes.length, value);
    }
    
    public V put(final byte[] bytes, final int p, final int end, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(hashCode(bytes, p, end));
        final int i = Hash.bucketIndex(hash, this.table.length);
        for (CaseInsensitiveBytesHashEntry<V> entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)this.table[i]; entry != null; entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)entry.next) {
            if (entry.hash == hash && entry.equals(bytes, p, end)) {
                return entry.value = value;
            }
        }
        this.table[i] = (HashEntry<V>)new CaseInsensitiveBytesHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, bytes, p, end, (HashEntry<V>)this.head);
        ++this.size;
        return null;
    }
    
    public void putDirect(final byte[] bytes, final V value) {
        this.putDirect(bytes, 0, bytes.length, value);
    }
    
    public void putDirect(final byte[] bytes, final int p, final int end, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(hashCode(bytes, p, end));
        final int i = Hash.bucketIndex(hash, this.table.length);
        this.table[i] = (HashEntry<V>)new CaseInsensitiveBytesHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, bytes, p, end, (HashEntry<V>)this.head);
        ++this.size;
    }
    
    public V get(final byte[] bytes) {
        return this.get(bytes, 0, bytes.length);
    }
    
    public V get(final byte[] bytes, final int p, final int end) {
        final int hash = Hash.hashValue(hashCode(bytes, p, end));
        for (CaseInsensitiveBytesHashEntry<V> entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)this.table[Hash.bucketIndex(hash, this.table.length)]; entry != null; entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)entry.next) {
            if (entry.hash == hash && entry.equals(bytes, p, end)) {
                return entry.value;
            }
        }
        return null;
    }
    
    public V delete(final byte[] bytes) {
        return this.delete(bytes, 0, bytes.length);
    }
    
    public V delete(final byte[] bytes, final int p, final int end) {
        final int hash = Hash.hashValue(hashCode(bytes, p, end));
        final int i = Hash.bucketIndex(hash, this.table.length);
        CaseInsensitiveBytesHashEntry<V> entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)this.table[i];
        if (entry == null) {
            return null;
        }
        if (entry.hash == hash && entry.equals(bytes, p, end)) {
            this.table[i] = entry.next;
            --this.size;
            entry.remove();
            return entry.value;
        }
        while (entry.next != null) {
            final HashEntry<V> tmp = entry.next;
            if (tmp.hash == hash && entry.equals(bytes, p, end)) {
                entry.next = entry.next.next;
                --this.size;
                tmp.remove();
                return tmp.value;
            }
            entry = (CaseInsensitiveBytesHashEntry<V>)(CaseInsensitiveBytesHashEntry)entry.next;
        }
        return null;
    }
    
    public static final class CaseInsensitiveBytesHashEntry<V> extends HashEntry<V>
    {
        public final byte[] bytes;
        public final int p;
        public final int end;
        
        public CaseInsensitiveBytesHashEntry(final int hash, final HashEntry<V> next, final V value, final byte[] bytes, final int p, final int end, final HashEntry<V> head) {
            super(hash, next, value, head);
            this.bytes = bytes;
            this.p = p;
            this.end = end;
        }
        
        public CaseInsensitiveBytesHashEntry() {
            this.bytes = null;
            final boolean b = false;
            this.end = (b ? 1 : 0);
            this.p = (b ? 1 : 0);
        }
        
        public boolean equals(final byte[] bytes, int p, final int end) {
            if (this.end - this.p != end - p) {
                return false;
            }
            if (this.bytes == bytes) {
                return true;
            }
            int q = this.p;
            while (q < this.end) {
                if (AsciiTables.ToLowerCaseTable[this.bytes[q++] & 0xFF] != AsciiTables.ToLowerCaseTable[bytes[p++] & 0xFF]) {
                    return false;
                }
            }
            return true;
        }
    }
}
