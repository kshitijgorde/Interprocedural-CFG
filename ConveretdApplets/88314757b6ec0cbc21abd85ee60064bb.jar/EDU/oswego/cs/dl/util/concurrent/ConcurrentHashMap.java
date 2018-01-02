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

public class ConcurrentHashMap extends AbstractMap implements Map, Cloneable, Serializable
{
    protected transient Entry[] table;
    protected static final int CONCURRENCY_LEVEL = 32;
    protected static final int SEGMENT_MASK = 31;
    protected final Segment[] segments;
    public static int DEFAULT_INITIAL_CAPACITY;
    private static final int MINIMUM_CAPACITY = 32;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected final float loadFactor;
    protected int threshold;
    protected transient volatile int votesForResize;
    protected transient Set keySet;
    protected transient Set entrySet;
    protected transient Collection values;
    
    static {
        ConcurrentHashMap.DEFAULT_INITIAL_CAPACITY = 32;
    }
    
    public ConcurrentHashMap() {
        this(ConcurrentHashMap.DEFAULT_INITIAL_CAPACITY, 0.75f);
    }
    
    public ConcurrentHashMap(final int n) {
        this(n, 0.75f);
    }
    
    public ConcurrentHashMap(final int n, final float loadFactor) {
        this.segments = new Segment[32];
        this.keySet = null;
        this.entrySet = null;
        this.values = null;
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException("Illegal Load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = new Segment();
        }
        this.table = this.newTable(this.p2capacity(n));
    }
    
    public ConcurrentHashMap(final Map map) {
        this(Math.max((int)(map.size() / 0.75f) + 1, 32), 0.75f);
        this.putAll(map);
    }
    
    protected static int bitcount(int n) {
        n -= (0xAAAAAAAA & n) >>> 1;
        n = (n & 0x33333333) + (n >>> 2 & 0x33333333);
        n = (n + (n >>> 4) & 0xF0F0F0F);
        n += n >>> 8;
        n += n >>> 16;
        return n & 0xFF;
    }
    
    public void clear() {
        for (int i = 0; i < this.segments.length; ++i) {
            final Segment segment = this.segments[i];
            synchronized (segment) {
                final Entry[] table = this.table;
                for (int j = i; j < table.length; j += this.segments.length) {
                    for (Entry next = table[j]; next != null; next = next.next) {
                        next.value = null;
                    }
                    table[j] = null;
                    segment.count = 0;
                }
            }
            // monitorexit(segment)
        }
    }
    
    public Object clone() {
        return new ConcurrentHashMap(this);
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
        for (int i = 0; i < this.segments.length; ++i) {
            final Segment segment = this.segments[i];
            final Entry[] table;
            synchronized (segment) {
                table = this.table;
            }
            // monitorexit(segment)
            for (int j = i; j < table.length; j += this.segments.length) {
                for (Entry next = table[j]; next != null; next = next.next) {
                    if (o.equals(next.value)) {
                        return true;
                    }
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
    
    public Object get(final Object o) {
        final int hash = hash(o);
        final Entry[] table = this.table;
        Entry next;
        final Entry entry = next = table[hash & table.length - 1];
        while (next != null) {
            if (next.hash == hash && this.eq(o, next.key)) {
                final Object value = next.value;
                if (value != null) {
                    return value;
                }
                break;
            }
            else {
                next = next.next;
            }
        }
        final Segment segment = this.segments[hash & 0x1F];
        synchronized (segment) {
            final Entry[] table2 = this.table;
            final Entry entry2 = table2[hash & table2.length - 1];
            if (next != null || entry != entry2) {
                for (Entry next2 = entry2; next2 != null; next2 = next2.next) {
                    if (next2.hash == hash && this.eq(o, next2.key)) {
                        // monitorexit(segment)
                        return next2.value;
                    }
                }
            }
            // monitorexit(segment)
            return null;
        }
    }
    
    protected static int hash(final Object o) {
        final int hashCode = o.hashCode();
        return (hashCode << 7) - hashCode + (hashCode >>> 9) + (hashCode >>> 17);
    }
    
    public boolean isEmpty() {
        for (int i = 0; i < this.segments.length; ++i) {
            if (this.segments[i].getCount() != 0) {
                return false;
            }
        }
        return true;
    }
    
    public Set keySet() {
        final Set keySet = this.keySet;
        return (keySet != null) ? keySet : (this.keySet = new KeySet());
    }
    
    public Enumeration keys() {
        return new KeyIterator();
    }
    
    protected Entry[] newTable(final int n) {
        this.threshold = (int)(n * this.loadFactor / 32.0f) + 1;
        return new Entry[n];
    }
    
    private int p2capacity(final int n) {
        int i;
        if (n > 1073741824 || n < 0) {
            i = 1073741824;
        }
        else {
            for (i = 32; i < n; i <<= 1) {}
        }
        return i;
    }
    
    public Object put(final Object o, final Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        final int hash = hash(o);
        final Segment segment = this.segments[hash & 0x1F];
        final Entry[] table;
        final int n2;
        int votesForResize;
        synchronized (segment) {
            table = this.table;
            final int n = hash & table.length - 1;
            Entry next;
            Entry entry;
            for (entry = (next = table[n]); next != null; next = next.next) {
                if (next.hash == hash && this.eq(o, next.key)) {
                    final Object value2 = next.value;
                    next.value = value;
                    // monitorexit(segment)
                    return value2;
                }
            }
            table[n] = new Entry(hash, o, value, entry);
            if ((n2 = ++segment.count) < this.threshold) {
                // monitorexit(segment)
                return null;
            }
            final int n3 = 1 << (hash & 0x1F);
            votesForResize = this.votesForResize;
            if ((votesForResize & n3) == 0x0) {
                final int votesForResize2 = this.votesForResize | n3;
                this.votesForResize = votesForResize2;
                votesForResize = votesForResize2;
            }
        }
        // monitorexit(segment)
        if (bitcount(votesForResize) >= 8 || n2 > this.threshold * 32) {
            this.resize(0, table);
        }
        return null;
    }
    
    public void putAll(final Map map) {
        final int size = map.size();
        if (size == 0) {
            return;
        }
        while (true) {
            final Entry[] table;
            final int n;
            synchronized (this.segments[0]) {
                table = this.table;
                n = this.threshold * 32;
            }
            // monitorexit(this.segments[0])
            if (size < n) {
                break;
            }
            this.resize(0, table);
        }
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = this.newTable(objectInputStream.readInt());
        for (int i = 0; i < this.segments.length; ++i) {
            this.segments[i] = new Segment();
        }
        while (true) {
            final Object object = objectInputStream.readObject();
            final Object object2 = objectInputStream.readObject();
            if (object == null) {
                break;
            }
            this.put(object, object2);
        }
    }
    
    protected void rehash() {
        this.votesForResize = 0;
        final Entry[] table = this.table;
        final int length = table.length;
        if (length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        final int n = length << 1;
        final Entry[] table2 = this.newTable(n);
        final int n2 = n - 1;
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
        this.table = table2;
    }
    
    public Object remove(final Object o) {
        return this.remove(o, null);
    }
    
    protected Object remove(final Object o, final Object o2) {
        final int hash = hash(o);
        final Segment segment = this.segments[hash & 0x1F];
        synchronized (segment) {
            final Entry[] table = this.table;
            final int n = hash & table.length - 1;
            Entry next;
            final Entry entry = next = table[n];
            while (next != null) {
                if (next.hash != hash || !this.eq(o, next.key)) {
                    next = next.next;
                }
                else {
                    final Object value = next.value;
                    if (o2 != null && !o2.equals(value)) {
                        // monitorexit(segment)
                        return null;
                    }
                    next.value = null;
                    Entry next2 = next.next;
                    for (Entry next3 = entry; next3 != next; next3 = next3.next) {
                        next2 = new Entry(next3.hash, next3.key, next3.value, next2);
                    }
                    table[n] = next2;
                    final Segment segment2 = segment;
                    --segment2.count;
                    // monitorexit(segment)
                    return value;
                }
            }
            // monitorexit(segment)
            return null;
        }
    }
    
    protected void resize(final int n, final Entry[] array) {
        final Segment segment = this.segments[n];
        synchronized (segment) {
            if (array == this.table) {
                final int n2 = n + 1;
                if (n2 < this.segments.length) {
                    this.resize(n2, array);
                }
                else {
                    this.rehash();
                }
            }
        }
        // monitorexit(segment)
    }
    
    public int size() {
        int n = 0;
        for (int i = 0; i < this.segments.length; ++i) {
            n += this.segments[i].getCount();
        }
        return n;
    }
    
    public Collection values() {
        final Collection values = this.values;
        return (values != null) ? values : (this.values = new Values());
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final int length;
        synchronized (this.segments[0]) {
            length = this.table.length;
        }
        // monitorexit(this.segments[0])
        objectOutputStream.writeInt(length);
        for (int i = 0; i < this.segments.length; ++i) {
            final Segment segment = this.segments[i];
            final Entry[] table;
            synchronized (segment) {
                table = this.table;
            }
            // monitorexit(segment)
            for (int j = i; j < table.length; j += this.segments.length) {
                for (Entry next = table[j]; next != null; next = next.next) {
                    objectOutputStream.writeObject(next.key);
                    objectOutputStream.writeObject(next.value);
                }
            }
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.writeObject(null);
    }
    
    protected static final class Segment implements Serializable
    {
        protected int count;
        
        protected synchronized int getCount() {
            return this.count;
        }
        
        protected synchronized void synch() {
        }
    }
    
    private class KeySet extends AbstractSet
    {
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            return ConcurrentHashMap.this.containsKey(o);
        }
        
        public Iterator iterator() {
            return new KeyIterator();
        }
        
        public boolean remove(final Object o) {
            return ConcurrentHashMap.this.remove(o) != null;
        }
        
        public int size() {
            return ConcurrentHashMap.this.size();
        }
    }
    
    private class Values extends AbstractCollection
    {
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            return ConcurrentHashMap.this.containsValue(o);
        }
        
        public Iterator iterator() {
            return new ValueIterator();
        }
        
        public int size() {
            return ConcurrentHashMap.this.size();
        }
    }
    
    private class EntrySet extends AbstractSet
    {
        public void clear() {
            ConcurrentHashMap.this.clear();
        }
        
        public boolean contains(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry entry = (Map.Entry)o;
            final Object value = ConcurrentHashMap.this.get(entry.getKey());
            return value != null && value.equals(entry.getValue());
        }
        
        public Iterator iterator() {
            return new HashIterator();
        }
        
        public boolean remove(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            final Map.Entry entry = (Map.Entry)o;
            return ConcurrentHashMap.this.remove(entry.getKey(), entry.getValue()) != null;
        }
        
        public int size() {
            return ConcurrentHashMap.this.size();
        }
    }
    
    protected static class Entry implements Map.Entry
    {
        protected final Object key;
        protected volatile Object value;
        protected final int hash;
        protected final Entry next;
        
        Entry(final int hash, final Object key, final Object value, final Entry next) {
            this.value = value;
            this.hash = hash;
            this.key = key;
            this.next = next;
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
            synchronized (ConcurrentHashMap.this.segments[0]) {
                this.tab = ConcurrentHashMap.this.table;
            }
            // monitorexit(this$0.segments[0])
            for (int i = 1; i < ConcurrentHashMap.this.segments.length; ++i) {
                ConcurrentHashMap.this.segments[i].synch();
            }
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
            ConcurrentHashMap.this.remove(this.lastReturned.key);
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
