// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

public class IntHash<V> extends Hash<V>
{
    public IntHash() {
    }
    
    public IntHash(final int size) {
        super(size);
    }
    
    protected void init() {
        this.head = (HashEntry<V>)new IntHashEntry<Object>();
    }
    
    public V put(final int key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(key);
        final int i = Hash.bucketIndex(hash, this.table.length);
        for (IntHashEntry<V> entry = (IntHashEntry<V>)(IntHashEntry)this.table[i]; entry != null; entry = (IntHashEntry<V>)(IntHashEntry)entry.next) {
            if (entry.hash == hash) {
                return entry.value = value;
            }
        }
        this.table[i] = (HashEntry<V>)new IntHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, (HashEntry<V>)this.head);
        ++this.size;
        return null;
    }
    
    public void putDirect(final int key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(key);
        final int i = Hash.bucketIndex(hash, this.table.length);
        this.table[i] = (HashEntry<V>)new IntHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, (HashEntry<V>)this.head);
        ++this.size;
    }
    
    public V get(final int key) {
        final int hash = Hash.hashValue(key);
        for (IntHashEntry<V> entry = (IntHashEntry<V>)(IntHashEntry)this.table[Hash.bucketIndex(hash, this.table.length)]; entry != null; entry = (IntHashEntry<V>)(IntHashEntry)entry.next) {
            if (entry.hash == hash) {
                return entry.value;
            }
        }
        return null;
    }
    
    public V delete(final int key) {
        final int hash = Hash.hashValue(key);
        final int i = Hash.bucketIndex(hash, this.table.length);
        IntHashEntry<V> entry = (IntHashEntry<V>)(IntHashEntry)this.table[i];
        if (entry == null) {
            return null;
        }
        if (entry.hash == hash) {
            this.table[i] = entry.next;
            --this.size;
            entry.remove();
            return entry.value;
        }
        while (entry.next != null) {
            final HashEntry<V> tmp = entry.next;
            if (tmp.hash == hash && entry.equals(key)) {
                entry.next = entry.next.next;
                --this.size;
                tmp.remove();
                return tmp.value;
            }
            entry = (IntHashEntry<V>)(IntHashEntry)entry.next;
        }
        return null;
    }
    
    public static final class IntHashEntry<V> extends HashEntry<V>
    {
        public IntHashEntry(final int hash, final HashEntry<V> next, final V value, final HashEntry<V> head) {
            super(hash, next, value, head);
        }
        
        public IntHashEntry() {
        }
    }
}
