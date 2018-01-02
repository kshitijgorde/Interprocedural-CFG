// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

public final class IntArrayHash<V> extends Hash<V>
{
    public IntArrayHash() {
    }
    
    public IntArrayHash(final int size) {
        super(size);
    }
    
    protected void init() {
        this.head = (HashEntry<V>)new IntArrayHashEntry<Object>();
    }
    
    private int hashCode(final int[] key) {
        switch (key.length) {
            case 1: {
                return key[0];
            }
            case 2: {
                return key[0] + key[1];
            }
            case 3: {
                return key[0] + key[1] + key[2];
            }
            case 4: {
                return key[0] + key[1] + key[2] + key[3];
            }
            default: {
                int h = 0;
                for (int i = 0; i < key.length; ++i) {
                    h += key[i];
                }
                return h;
            }
        }
    }
    
    public V put(final int[] key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(this.hashCode(key));
        final int i = Hash.bucketIndex(hash, this.table.length);
        for (IntArrayHashEntry<V> entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)this.table[i]; entry != null; entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)entry.next) {
            if (entry.hash == hash && entry.equals(key)) {
                return entry.value = value;
            }
        }
        this.table[i] = (HashEntry<V>)new IntArrayHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, key, (HashEntry<V>)this.head);
        ++this.size;
        return null;
    }
    
    public void putDirect(final int[] key, final V value) {
        this.checkResize();
        final int hash = Hash.hashValue(this.hashCode(key));
        final int i = Hash.bucketIndex(hash, this.table.length);
        this.table[i] = (HashEntry<V>)new IntArrayHashEntry<Object>(hash, (HashEntry<V>)this.table[i], (V)value, key, (HashEntry<V>)this.head);
        ++this.size;
    }
    
    public V get(final int... key) {
        final int hash = Hash.hashValue(this.hashCode(key));
        for (IntArrayHashEntry<V> entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)this.table[Hash.bucketIndex(hash, this.table.length)]; entry != null; entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)entry.next) {
            if (entry.hash == hash && entry.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
    
    public V delete(final int... key) {
        final int hash = Hash.hashValue(this.hashCode(key));
        final int i = Hash.bucketIndex(hash, this.table.length);
        IntArrayHashEntry<V> entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)this.table[i];
        if (entry == null) {
            return null;
        }
        if (entry.hash == hash && entry.equals(key)) {
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
            entry = (IntArrayHashEntry<V>)(IntArrayHashEntry)entry.next;
        }
        return null;
    }
    
    public static final class IntArrayHashEntry<V> extends HashEntry<V>
    {
        public final int[] key;
        
        public IntArrayHashEntry(final int hash, final HashEntry<V> next, final V value, final int[] key, final HashEntry<V> head) {
            super(hash, next, value, head);
            this.key = key;
        }
        
        public IntArrayHashEntry() {
            this.key = null;
        }
        
        public boolean equals(final int[] key) {
            if (this.key == key) {
                return true;
            }
            if (this.key.length != key.length) {
                return false;
            }
            switch (key.length) {
                case 1: {
                    return this.key[0] == key[0];
                }
                case 2: {
                    return this.key[0] == key[0] && this.key[1] == key[1];
                }
                case 3: {
                    return this.key[0] == key[0] && this.key[1] == key[1] && this.key[2] == key[2];
                }
                case 4: {
                    return this.key[0] == key[0] && this.key[1] == key[1] && this.key[2] == key[2] && this.key[3] == key[3];
                }
                default: {
                    for (int i = 0; i < key.length; ++i) {
                        if (this.key[i] != key[i]) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
    }
}
