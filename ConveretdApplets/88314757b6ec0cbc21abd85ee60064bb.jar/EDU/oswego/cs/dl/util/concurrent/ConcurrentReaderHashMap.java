// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.NoSuchElementException;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Collection;
import java.util.Set;
import java.io.Serializable;
import java.util.Map;
import java.util.AbstractMap;

public class ConcurrentReaderHashMap extends AbstractMap implements Map, Cloneable, Serializable
{
    protected final BarrierLock barrierLock;
    protected transient Object lastWrite;
    public static int DEFAULT_INITIAL_CAPACITY;
    private static final int MINIMUM_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected transient Entry[] table;
    protected transient int count;
    protected int threshold;
    protected float loadFactor;
    protected transient Set keySet;
    protected transient Set entrySet;
    protected transient Collection values;
    
    static {
        ConcurrentReaderHashMap.DEFAULT_INITIAL_CAPACITY = 32;
    }
    
    public ConcurrentReaderHashMap() {
        this(ConcurrentReaderHashMap.DEFAULT_INITIAL_CAPACITY, 0.75f);
    }
    
    public ConcurrentReaderHashMap(final int n) {
        this(n, 0.75f);
    }
    
    public ConcurrentReaderHashMap(final int n, final float loadFactor) {
        this.barrierLock = new BarrierLock();
        this.keySet = null;
        this.entrySet = null;
        this.values = null;
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        final int p2capacity = this.p2capacity(n);
        this.table = new Entry[p2capacity];
        this.threshold = (int)(p2capacity * loadFactor);
    }
    
    public ConcurrentReaderHashMap(final Map map) {
        this(Math.max((int)(map.size() / 0.75f) + 1, 16), 0.75f);
        this.putAll(map);
    }
    
    public synchronized int capacity() {
        return this.table.length;
    }
    
    public synchronized void clear() {
        final Entry[] table = this.table;
        for (int i = 0; i < table.length; ++i) {
            for (Entry next = table[i]; next != null; next = next.next) {
                next.value = null;
            }
            table[i] = null;
        }
        this.count = 0;
        this.recordModification(table);
    }
    
    public synchronized Object clone() {
        try {
            final ConcurrentReaderHashMap concurrentReaderHashMap = (ConcurrentReaderHashMap)super.clone();
            concurrentReaderHashMap.keySet = null;
            concurrentReaderHashMap.entrySet = null;
            concurrentReaderHashMap.values = null;
            final Entry[] table = this.table;
            concurrentReaderHashMap.table = new Entry[table.length];
            final Entry[] table2 = concurrentReaderHashMap.table;
            for (int i = 0; i < table.length; ++i) {
                Entry entry = null;
                for (Entry next = table[i]; next != null; next = next.next) {
                    entry = new Entry(next.hash, next.key, next.value, entry);
                }
                table2[i] = entry;
            }
            return concurrentReaderHashMap;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public boolean contains(final Object o) {
        return this.containsValue(o);
    }
    
    public boolean containsKey(final Object o) {
        return this.get(o) != null;
    }
    
    public boolean containsValue(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        final Entry[] tableForReading = this.getTableForReading();
        for (int i = 0; i < tableForReading.length; ++i) {
            for (Entry next = tableForReading[i]; next != null; next = next.next) {
                if (o.equals(next.value)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Enumeration elements() {
        return new ValueIterator();
    }
    
    public Set entrySet() {
        final Set entrySet = this.entrySet;
        return (entrySet != null) ? entrySet : (this.entrySet = new EntrySet());
    }
    
    protected boolean eq(final Object o, final Object o2) {
        return o == o2 || o.equals(o2);
    }
    
    protected synchronized boolean findAndRemoveEntry(final Map.Entry entry) {
        final Object key = entry.getKey();
        final Object value = this.get(key);
        if (value != null && value.equals(entry.getValue())) {
            this.remove(key);
            return true;
        }
        return false;
    }
    
    public Object get(final Object o) {
        final int hash = hash(o);
        Entry[] array = this.table;
        int n = hash & array.length - 1;
        Entry next;
        Entry entry = next = array[n];
        while (true) {
            if (next == null) {
                final Entry[] tableForReading = this.getTableForReading();
                if (array == tableForReading && entry == array[n]) {
                    return null;
                }
                array = tableForReading;
                entry = (next = array[n = (hash & array.length - 1)]);
            }
            else if (next.hash == hash && this.eq(o, next.key)) {
                final Object value = next.value;
                if (value != null) {
                    return value;
                }
                synchronized (this) {
                    array = this.table;
                }
                entry = (next = array[n = (hash & array.length - 1)]);
            }
            else {
                next = next.next;
            }
        }
    }
    
    protected final Entry[] getTableForReading() {
        synchronized (this.barrierLock) {
            // monitorexit(this.barrierLock)
            return this.table;
        }
    }
    
    private static int hash(final Object o) {
        final int hashCode = o.hashCode();
        return (hashCode << 7) - hashCode + (hashCode >>> 9) + (hashCode >>> 17);
    }
    
    public synchronized boolean isEmpty() {
        return this.count == 0;
    }
    
    public Set keySet() {
        final Set keySet = this.keySet;
        return (keySet != null) ? keySet : (this.keySet = new KeySet());
    }
    
    public Enumeration keys() {
        return new KeyIterator();
    }
    
    public float loadFactor() {
        return this.loadFactor;
    }
    
    private int p2capacity(final int n) {
        int i;
        if (n > 1073741824 || n < 0) {
            i = 1073741824;
        }
        else {
            for (i = 4; i < n; i <<= 1) {}
        }
        return i;
    }
    
    public Object put(final Object o, final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final int hash = hash(o);
        final Entry[] table = this.table;
        final int n = hash & table.length - 1;
        Entry next;
        Entry entry;
        for (entry = (next = table[n]); next != null && (next.hash != hash || !this.eq(o, next.key)); next = next.next) {}
        synchronized (this) {
            if (table == this.table) {
                if (next == null) {
                    if (entry == table[n]) {
                        final Entry entry2 = new Entry(hash, o, value, entry);
                        table[n] = entry2;
                        if (++this.count >= this.threshold) {
                            this.rehash();
                        }
                        else {
                            this.recordModification(entry2);
                        }
                        // monitorexit(this)
                        return null;
                    }
                }
                else {
                    final Object value2 = next.value;
                    if (entry == table[n] && value2 != null) {
                        next.value = value;
                        // monitorexit(this)
                        return value2;
                    }
                }
            }
            return this.sput(o, value, hash);
        }
    }
    
    public synchronized void putAll(final Map map) {
        final int i = map.size();
        if (i == 0) {
            return;
        }
        while (i >= this.threshold) {
            this.rehash();
        }
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    private synchronized void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        for (int int1 = objectInputStream.readInt(), i = 0; i < int1; ++i) {
            this.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }
    
    protected final void recordModification(final Object lastWrite) {
        synchronized (this.barrierLock) {
            this.lastWrite = lastWrite;
        }
        // monitorexit(this.barrierLock)
    }
    
    protected void rehash() {
        final Entry[] table = this.table;
        final int length = table.length;
        if (length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        final int n = length << 1;
        final int n2 = n - 1;
        this.threshold = (int)(n * this.loadFactor);
        final Entry[] table2 = new Entry[n];
        for (final Entry entry : table) {
            if (entry != null) {
                final int n3 = entry.hash & n2;
                final Entry next = entry.next;
                if (next == null) {
                    table2[n3] = entry;
                }
                else {
                    Entry entry2 = entry;
                    int n4 = n3;
                    for (Entry next2 = next; next2 != null; next2 = next2.next) {
                        final int n5 = next2.hash & n2;
                        if (n5 != n4) {
                            n4 = n5;
                            entry2 = next2;
                        }
                    }
                    table2[n4] = entry2;
                    for (Entry next3 = entry; next3 != entry2; next3 = next3.next) {
                        final int n6 = next3.hash & n2;
                        table2[n6] = new Entry(next3.hash, next3.key, next3.value, table2[n6]);
                    }
                }
            }
        }
        this.recordModification(this.table = table2);
    }
    
    public Object remove(final Object o) {
        final int hash = hash(o);
        final Entry[] table = this.table;
        final int n = hash & table.length - 1;
        Entry next;
        Entry entry;
        for (entry = (next = table[n]); next != null && (next.hash != hash || !this.eq(o, next.key)); next = next.next) {}
        synchronized (this) {
            if (table == this.table) {
                if (next == null) {
                    if (entry == table[n]) {
                        // monitorexit(this)
                        return null;
                    }
                }
                else {
                    final Object value = next.value;
                    if (entry == table[n] && value != null) {
                        next.value = null;
                        --this.count;
                        Entry next2 = next.next;
                        for (Entry next3 = entry; next3 != next; next3 = next3.next) {
                            next2 = new Entry(next3.hash, next3.key, next3.value, next2);
                        }
                        this.recordModification(table[n] = next2);
                        // monitorexit(this)
                        return value;
                    }
                }
            }
            return this.sremove(o, hash);
        }
    }
    
    public synchronized int size() {
        return this.count;
    }
    
    protected Object sput(final Object o, final Object value, final int n) {
        final Entry[] table = this.table;
        final int n2 = n & table.length - 1;
        Entry next;
        Entry entry;
        for (entry = (next = table[n2]); next != null; next = next.next) {
            if (next.hash == n && this.eq(o, next.key)) {
                final Object value2 = next.value;
                next.value = value;
                return value2;
            }
        }
        final Entry entry2 = new Entry(n, o, value, entry);
        table[n2] = entry2;
        if (++this.count >= this.threshold) {
            this.rehash();
        }
        else {
            this.recordModification(entry2);
        }
        return null;
    }
    
    protected Object sremove(final Object o, final int n) {
        final Entry[] table = this.table;
        final int n2 = n & table.length - 1;
        Entry next;
        for (Entry entry = next = table[n2]; next != null; next = next.next) {
            if (next.hash == n && this.eq(o, next.key)) {
                final Object value = next.value;
                next.value = null;
                --this.count;
                Entry next2 = next.next;
                for (Entry next3 = entry; next3 != next; next3 = next3.next) {
                    next2 = new Entry(next3.hash, next3.key, next3.value, next2);
                }
                this.recordModification(table[n2] = next2);
                return value;
            }
        }
        return null;
    }
    
    public Collection values() {
        final Collection values = this.values;
        return (values != null) ? values : (this.values = new Values());
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.count);
        for (int i = this.table.length - 1; i >= 0; --i) {
            for (Entry next = this.table[i]; next != null; next = next.next) {
                objectOutputStream.writeObject(next.key);
                objectOutputStream.writeObject(next.value);
            }
        }
    }
    
    protected static class BarrierLock implements Serializable
    {
    }
    
    private class KeySet extends AbstractSet
    {
        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            return ConcurrentReaderHashMap.this.containsKey(o);
        }
        
        public Iterator iterator() {
            return new KeyIterator();
        }
        
        public boolean remove(final Object o) {
            return ConcurrentReaderHashMap.this.remove(o) != null;
        }
        
        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }
    }
    
    private class Values extends AbstractCollection
    {
        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            return ConcurrentReaderHashMap.this.containsValue(o);
        }
        
        public Iterator iterator() {
            return new ValueIterator();
        }
        
        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }
    }
    
    private class EntrySet extends AbstractSet
    {
        public void clear() {
            ConcurrentReaderHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry entry = (Map.Entry)o;
            final Object value = ConcurrentReaderHashMap.this.get(entry.getKey());
            return value != null && value.equals(entry.getValue());
        }
        
        public Iterator iterator() {
            return new HashIterator();
        }
        
        public boolean remove(final Object o) {
            return o instanceof Map.Entry && ConcurrentReaderHashMap.this.findAndRemoveEntry((Map.Entry)o);
        }
        
        public int size() {
            return ConcurrentReaderHashMap.this.size();
        }
    }
    
    protected static class Entry implements Map.Entry
    {
        protected final int hash;
        protected final Object key;
        protected final Entry next;
        protected volatile Object value;
        
        Entry(final int hash, final Object key, final Object value, final Entry next) {
            this.hash = hash;
            this.key = key;
            this.next = next;
            this.value = value;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry entry = (Map.Entry)o;
            return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
        }
        
        public Object getKey() {
            return this.key;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }
        
        public Object setValue(final Object value) {
            if (value == null) {
                throw new NullPointerException();
            }
            final Object value2 = this.value;
            this.value = value;
            return value2;
        }
        
        public String toString() {
            return String.valueOf(String.valueOf(this.key)) + "=" + this.value;
        }
    }
    
    protected class HashIterator implements Iterator, Enumeration
    {
        protected final Entry[] tab;
        protected int index;
        protected Entry entry;
        protected Object currentKey;
        protected Object currentValue;
        protected Entry lastReturned;
        
        protected HashIterator() {
            this.entry = null;
            this.lastReturned = null;
            this.tab = ConcurrentReaderHashMap.this.getTableForReading();
            this.index = this.tab.length - 1;
        }
        
        public boolean hasMoreElements() {
            return this.hasNext();
        }
        
        public boolean hasNext() {
            do {
                if (this.entry != null) {
                    final Object value = this.entry.value;
                    if (value != null) {
                        this.currentKey = this.entry.key;
                        this.currentValue = value;
                        return true;
                    }
                    this.entry = this.entry.next;
                }
                while (this.entry == null && this.index >= 0) {
                    this.entry = this.tab[this.index--];
                }
            } while (this.entry != null);
            final Object o = null;
            this.currentValue = o;
            this.currentKey = o;
            return false;
        }
        
        public Object next() {
            if (this.currentKey == null && !this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Object returnValueOfNext = this.returnValueOfNext();
            this.lastReturned = this.entry;
            final Object o = null;
            this.currentValue = o;
            this.currentKey = o;
            this.entry = this.entry.next;
            return returnValueOfNext;
        }
        
        public Object nextElement() {
            return this.next();
        }
        
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            ConcurrentReaderHashMap.this.remove(this.lastReturned.key);
            this.lastReturned = null;
        }
        
        protected Object returnValueOfNext() {
            return this.entry;
        }
    }
    
    protected class KeyIterator extends HashIterator
    {
        protected Object returnValueOfNext() {
            return super.currentKey;
        }
    }
    
    protected class ValueIterator extends HashIterator
    {
        protected Object returnValueOfNext() {
            return super.currentValue;
        }
    }
}
