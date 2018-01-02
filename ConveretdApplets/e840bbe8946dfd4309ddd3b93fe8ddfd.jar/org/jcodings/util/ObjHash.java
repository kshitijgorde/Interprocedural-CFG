// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

public final class ObjHash<K, V> extends Hash<V>
{
    protected void init() {
        this.head = (HashEntry<V>)new ObjHashEntry();
    }
    
    public V put(final K key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(key.hashCode());
        final int i = Hash.bucketIndex(hash, this.table.length);
        for (ObjHashEntry<K, V> entry = (ObjHashEntry<K, V>)(ObjHashEntry)this.table[i]; entry != null; entry = (ObjHashEntry<K, V>)(ObjHashEntry)entry.next) {
            final K k;
            if (entry.hash == hash && ((k = entry.key) == key || key.equals(k))) {
                return (V)(entry.value = (V)value);
            }
        }
        this.table[i] = (HashEntry<V>)new ObjHashEntry(hash, (HashEntry<Object>)this.table[i], value, key, (HashEntry<Object>)this.head);
        ++this.size;
        return null;
    }
    
    public void putDirect(final K key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(key.hashCode());
        final int i = Hash.bucketIndex(hash, this.table.length);
        this.table[i] = (HashEntry<V>)new ObjHashEntry(hash, (HashEntry<Object>)this.table[i], value, key, (HashEntry<Object>)this.head);
        ++this.size;
    }
    
    public V get(final K key) {
        final int hash = Hash.hashValue(key.hashCode());
        for (ObjHashEntry<K, V> entry = (ObjHashEntry<K, V>)(ObjHashEntry)this.table[Hash.bucketIndex(hash, this.table.length)]; entry != null; entry = (ObjHashEntry<K, V>)(ObjHashEntry)entry.next) {
            final K k;
            if (entry.hash == hash && ((k = entry.key) == key || key.equals(k))) {
                return (V)entry.value;
            }
        }
        return null;
    }
    
    public V delete(final K key) {
        final int hash = Hash.hashValue(key.hashCode());
        final int i = Hash.bucketIndex(hash, this.table.length);
        ObjHashEntry<K, V> entry = (ObjHashEntry<K, V>)(ObjHashEntry)this.table[i];
        if (entry == null) {
            return null;
        }
        K k;
        if (entry.hash == hash && ((k = entry.key) == key || key.equals(k))) {
            this.table[i] = (HashEntry<V>)entry.next;
            --this.size;
            entry.remove();
            return (V)entry.value;
        }
        while (entry.next != null) {
            final HashEntry<V> tmp = (HashEntry<V>)entry.next;
            if (tmp.hash == hash && ((k = entry.key) == key || key.equals(k))) {
                entry.next = entry.next.next;
                --this.size;
                tmp.remove();
                return tmp.value;
            }
            entry = (ObjHashEntry<K, V>)(ObjHashEntry)entry.next;
        }
        return null;
    }
    
    public static final class ObjHashEntry<K, V> extends HashEntry<V>
    {
        public final K key;
        
        public ObjHashEntry(final int hash, final HashEntry<V> next, final V value, final K key, final HashEntry<V> head) {
            super(hash, next, value, head);
            this.key = key;
        }
        
        public ObjHashEntry() {
            this.key = null;
        }
        
        public boolean equals(final Object key) {
            return this.key == key || this.key.equals(key);
        }
    }
}
