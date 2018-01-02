// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.util;

import java.util.Iterator;
import org.jcodings.exception.InternalException;

public abstract class Hash<V> implements Iterable<V>
{
    protected HashEntry<V>[] table;
    protected int size;
    private static final int[] PRIMES;
    private static final int INITIAL_CAPACITY;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    protected HashEntry<V> head;
    private static final int MIN_CAPA = 8;
    private static final int HASH_SIGN_BIT_MASK = Integer.MAX_VALUE;
    
    public Hash() {
        this.table = (HashEntry<V>[])new HashEntry[Hash.INITIAL_CAPACITY];
        this.init();
    }
    
    protected abstract void init();
    
    public Hash(final int size) {
        for (int i = 0, n = 8; i < Hash.PRIMES.length; ++i, n <<= 1) {
            if (n > size) {
                this.table = (HashEntry<V>[])new HashEntry[Hash.PRIMES[i]];
                this.init();
                return;
            }
        }
        throw new InternalException("run out of polynomials");
    }
    
    public final int size() {
        return this.size;
    }
    
    protected final void checkResize() {
        if (this.size == this.table.length) {
            final int forSize = this.table.length + 1;
            for (int i = 0, newCapacity = 8; i < Hash.PRIMES.length; ++i, newCapacity <<= 1) {
                if (newCapacity > forSize) {
                    this.resize(Hash.PRIMES[i]);
                    return;
                }
            }
        }
    }
    
    protected final void resize(final int newCapacity) {
        final HashEntry<V>[] oldTable = this.table;
        final HashEntry<V>[] newTable = (HashEntry<V>[])new HashEntry[newCapacity];
        for (int j = 0; j < oldTable.length; ++j) {
            HashEntry<V> entry = oldTable[j];
            oldTable[j] = null;
            while (entry != null) {
                final HashEntry<V> next = entry.next;
                final int i = bucketIndex(entry.hash, newCapacity);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
        this.table = newTable;
    }
    
    protected static int bucketIndex(final int h, final int length) {
        return h % length;
    }
    
    protected static int hashValue(final int h) {
        return h & Integer.MAX_VALUE;
    }
    
    public Iterator<V> iterator() {
        return new HashIterator();
    }
    
    public HashEntryIterator entryIterator() {
        return new HashEntryIterator();
    }
    
    static {
        PRIMES = new int[] { 11, 19, 37, 67, 131, 283, 521, 1033, 2053, 4099, 8219, 16427, 32771, 65581, 131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259, 33554467, 67108879, 134217757, 268435459, 536870923, 1073741909, 0 };
        INITIAL_CAPACITY = Hash.PRIMES[0];
    }
    
    static class HashEntry<V>
    {
        final int hash;
        HashEntry<V> next;
        HashEntry<V> before;
        HashEntry<V> after;
        public V value;
        
        HashEntry(final int hash, final HashEntry<V> next, final V value, final HashEntry<V> head) {
            this.hash = hash;
            this.next = next;
            this.value = value;
            this.after = head;
            this.before = head.before;
            this.before.after = this;
            this.after.before = this;
        }
        
        void remove() {
            this.before.after = this.after;
            this.after.before = this.before;
        }
        
        HashEntry() {
            this.hash = 0;
            this.after = this;
            this.before = this;
        }
        
        public int getHash() {
            return this.hash;
        }
    }
    
    public class HashIterator implements Iterator<V>
    {
        HashEntry<V> next;
        
        public HashIterator() {
            this.next = Hash.this.head.after;
        }
        
        public boolean hasNext() {
            return this.next != Hash.this.head;
        }
        
        public V next() {
            final HashEntry<V> e = this.next;
            this.next = e.after;
            return e.value;
        }
        
        public void remove() {
            throw new InternalException("not supported operation exception");
        }
    }
    
    public class HashEntryIterator implements Iterator<HashEntry<V>>
    {
        HashEntry<V> next;
        
        public HashEntryIterator() {
            this.next = Hash.this.head.after;
        }
        
        public boolean hasNext() {
            return this.next != Hash.this.head;
        }
        
        public HashEntry<V> next() {
            final HashEntry<V> e = this.next;
            this.next = e.after;
            return e;
        }
        
        public void remove() {
            throw new InternalException("not supported operation exception");
        }
    }
}
