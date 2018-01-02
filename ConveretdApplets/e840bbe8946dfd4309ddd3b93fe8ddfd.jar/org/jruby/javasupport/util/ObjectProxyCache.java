// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.util;

import java.util.concurrent.locks.ReentrantLock;
import java.lang.ref.SoftReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class ObjectProxyCache<T, A>
{
    private static final int DEFAULT_SEGMENTS = 16;
    private static final int DEFAULT_SEGMENT_SIZE = 8;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAX_CAPACITY = 1073741824;
    private static final int MAX_SEGMENTS = 65536;
    private static final int VULTURE_RUN_FREQ_SECONDS = 5;
    private static int _nextId;
    private final ReferenceType referenceType;
    private final Segment<T, A>[] segments;
    private final int segmentShift;
    private final int segmentMask;
    private Thread vulture;
    private final int id;
    
    private static synchronized int nextId() {
        return ++ObjectProxyCache._nextId;
    }
    
    public ObjectProxyCache() {
        this(16, 8, ReferenceType.WEAK);
    }
    
    public ObjectProxyCache(final ReferenceType refType) {
        this(16, 8, refType);
    }
    
    public ObjectProxyCache(int numSegments, int initialSegCapacity, final ReferenceType refType) {
        if (numSegments <= 0 || initialSegCapacity <= 0 || refType == null) {
            throw new IllegalArgumentException();
        }
        this.id = nextId();
        this.referenceType = refType;
        if (numSegments > 65536) {
            numSegments = 65536;
        }
        int sshift = 0;
        int ssize;
        for (ssize = 1; ssize < numSegments; ssize <<= 1) {
            ++sshift;
        }
        this.segmentShift = 24 - sshift;
        this.segmentMask = ssize - 1;
        this.segments = Segment.newArray(ssize);
        if (initialSegCapacity > 1073741824) {
            initialSegCapacity = 1073741824;
        }
        int cap;
        for (cap = 1; cap < initialSegCapacity; cap <<= 1) {}
        int i = ssize;
        while (--i >= 0) {
            this.segments[i] = new Segment<T, A>(cap, this);
        }
        try {
            (this.vulture = new Thread("ObjectProxyCache " + this.id + " vulture") {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(5000L);
                        }
                        catch (InterruptedException ex) {}
                        final boolean dump = ObjectProxyCache.this.size() > 200;
                        if (dump) {
                            System.err.println("***Vulture " + ObjectProxyCache.this.id + " waking, stats:");
                            System.err.println(ObjectProxyCache.this.stats());
                        }
                        int i = ObjectProxyCache.this.segments.length;
                        while (--i >= 0) {
                            final Segment<T, A> seg = ObjectProxyCache.this.segments[i];
                            seg.lock();
                            try {
                                ((Segment<Object, Object>)seg).expunge();
                            }
                            finally {
                                seg.unlock();
                            }
                            yield();
                        }
                        if (dump) {
                            System.err.println("***Vulture " + ObjectProxyCache.this.id + " sleeping, stats:");
                            System.err.println(ObjectProxyCache.this.stats());
                        }
                    }
                }
            }).setDaemon(true);
        }
        catch (SecurityException e) {
            this.vulture = null;
        }
    }
    
    public abstract T allocateProxy(final Object p0, final A p1);
    
    public T get(final Object javaObject) {
        if (javaObject == null) {
            return null;
        }
        final int hash = hash(javaObject);
        return this.segmentFor(hash).get(javaObject, hash);
    }
    
    public T getOrCreate(final Object javaObject, final A allocator) {
        if (javaObject == null || allocator == null) {
            return null;
        }
        final int hash = hash(javaObject);
        return this.segmentFor(hash).getOrCreate(javaObject, hash, allocator);
    }
    
    public void put(final Object javaObject, final T proxy) {
        if (javaObject == null || proxy == null) {
            return;
        }
        final int hash = hash(javaObject);
        this.segmentFor(hash).put(javaObject, hash, proxy);
    }
    
    private static int hash(final Object javaObject) {
        int h = System.identityHashCode(javaObject);
        h ^= (h >>> 20 ^ h >>> 12);
        return h ^ h >>> 7 ^ h >>> 4;
    }
    
    private Segment<T, A> segmentFor(final int hash) {
        return this.segments[hash >>> this.segmentShift & this.segmentMask];
    }
    
    public int size() {
        int size = 0;
        for (final Segment<T, A> seg : this.segments) {
            size += seg.tableSize;
        }
        return size;
    }
    
    public String stats() {
        final StringBuilder b = new StringBuilder();
        int n = 0;
        int size = 0;
        int alloc = 0;
        b.append("Segments: ").append(this.segments.length).append("\n");
        for (final Segment<T, A> seg : this.segments) {
            int ssize = 0;
            int salloc = 0;
            seg.lock();
            try {
                ssize = ((Segment<Object, Object>)seg).count();
                salloc = seg.entryTable.length;
            }
            finally {
                seg.unlock();
            }
            size += ssize;
            alloc += salloc;
            b.append("seg[").append(n++).append("]:  size: ").append(ssize).append("  alloc: ").append(salloc).append("\n");
        }
        b.append("Total: size: ").append(size).append("  alloc: ").append(alloc).append("\n");
        return b.toString();
    }
    
    static {
        ObjectProxyCache._nextId = 0;
    }
    
    public enum ReferenceType
    {
        WEAK, 
        SOFT;
    }
    
    private static final class WeakEntryRef<T> extends WeakReference<T> implements EntryRef<T>
    {
        final int hash;
        
        WeakEntryRef(final int hash, final T rawObject, final ReferenceQueue<Object> queue) {
            super(rawObject, queue);
            this.hash = hash;
        }
        
        public int hash() {
            return this.hash;
        }
    }
    
    private static final class SoftEntryRef<T> extends SoftReference<T> implements EntryRef<T>
    {
        final int hash;
        
        SoftEntryRef(final int hash, final T rawObject, final ReferenceQueue<Object> queue) {
            super(rawObject, queue);
            this.hash = hash;
        }
        
        public int hash() {
            return this.hash;
        }
    }
    
    static class Entry<T>
    {
        final EntryRef<Object> objectRef;
        final int hash;
        final EntryRef<T> proxyRef;
        final Entry<T> next;
        
        Entry(final Object object, final int hash, final T proxy, final ReferenceType type, final Entry<T> next, final ReferenceQueue<Object> queue) {
            this.hash = hash;
            this.next = next;
            if (type == ReferenceType.WEAK) {
                this.objectRef = new WeakEntryRef<Object>(hash, object, queue);
                this.proxyRef = new WeakEntryRef<T>(hash, proxy, queue);
            }
            else {
                this.objectRef = new SoftEntryRef<Object>(hash, object, queue);
                this.proxyRef = new SoftEntryRef<T>(hash, proxy, queue);
            }
        }
        
        Entry(final EntryRef<Object> objectRef, final int hash, final EntryRef<T> proxyRef, final Entry<T> next) {
            this.objectRef = objectRef;
            this.hash = hash;
            this.proxyRef = proxyRef;
            this.next = next;
        }
        
        static final <T> Entry<T>[] newArray(final int size) {
            return (Entry<T>[])new Entry[size];
        }
    }
    
    static class Segment<T, A> extends ReentrantLock
    {
        final ObjectProxyCache<T, A> cache;
        final ReferenceQueue<Object> referenceQueue;
        volatile Entry<T>[] entryTable;
        int tableSize;
        int threshold;
        
        Segment(final int capacity, final ObjectProxyCache<T, A> cache) {
            this.referenceQueue = new ReferenceQueue<Object>();
            this.threshold = (int)(capacity * 0.75f);
            this.entryTable = Entry.newArray(capacity);
            this.cache = cache;
        }
        
        private void expunge() {
            final Entry<T>[] table = this.entryTable;
            final ReferenceQueue<Object> queue = this.referenceQueue;
            EntryRef ref;
            while ((ref = (EntryRef)queue.poll()) != null) {
                int hash;
                for (Entry<T> e = table[(hash = ref.hash()) & table.length - 1]; e != null; e = e.next) {
                    if (hash == e.hash && (ref == e.objectRef || ref == e.proxyRef)) {
                        this.remove(table, hash, e);
                        break;
                    }
                }
            }
        }
        
        private void remove(final Entry<T>[] table, final int hash, final Entry<T> e) {
            final int index = hash & table.length - 1;
            Entry<T> n;
            for (Entry<T> first = n = table[index]; n != null; n = n.next) {
                if (n == e) {
                    Entry<T> newFirst = n.next;
                    for (Entry<T> p = first; p != n; p = p.next) {
                        newFirst = new Entry<T>(p.objectRef, p.hash, p.proxyRef, newFirst);
                    }
                    table[index] = newFirst;
                    --this.tableSize;
                    this.entryTable = table;
                    return;
                }
            }
        }
        
        private int count() {
            int count = 0;
            for (Entry<T> e : this.entryTable) {
                while (e != null) {
                    ++count;
                    e = e.next;
                }
            }
            return count;
        }
        
        private Entry<T>[] rehash() {
            assert this.tableSize == this.count() : "tableSize " + this.tableSize + " != count() " + this.count();
            final Entry<T>[] oldTable = this.entryTable;
            final int oldCapacity;
            if ((oldCapacity = oldTable.length) >= 1073741824) {
                return oldTable;
            }
            final int newCapacity = oldCapacity << 1;
            final int sizeMask = newCapacity - 1;
            this.threshold = (int)(newCapacity * 0.75f);
            final Entry<T>[] newTable = Entry.newArray(newCapacity);
            int i = oldCapacity;
            while (--i >= 0) {
                final Entry<T> e;
                if ((e = oldTable[i]) != null) {
                    final int idx = e.hash & sizeMask;
                    final Entry<T> next;
                    if ((next = e.next) == null) {
                        newTable[idx] = e;
                    }
                    else {
                        int lastIdx = idx;
                        Entry<T> lastRun = e;
                        for (Entry<T> last = next; last != null; last = last.next) {
                            final int k;
                            if ((k = (last.hash & sizeMask)) != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }
                        newTable[lastIdx] = lastRun;
                        for (Entry<T> p = e; p != lastRun; p = p.next) {
                            final int k = p.hash & sizeMask;
                            final Entry<T> m = new Entry<T>(p.objectRef, p.hash, p.proxyRef, newTable[k]);
                            newTable[k] = m;
                        }
                    }
                }
            }
            return this.entryTable = newTable;
        }
        
        void put(final Object object, final int hash, final T proxy) {
            this.lock();
            try {
                this.expunge();
                int potentialNewSize;
                Entry<T>[] table;
                if ((potentialNewSize = this.tableSize + 1) > this.threshold) {
                    table = this.rehash();
                }
                else {
                    table = this.entryTable;
                }
                final int index;
                Entry<T> e = table[index = (hash & table.length - 1)];
                while (e != null) {
                    if (hash == e.hash && object == e.objectRef.get()) {
                        if (proxy == e.proxyRef.get()) {
                            return;
                        }
                        this.remove(table, hash, e);
                        --potentialNewSize;
                        break;
                    }
                    else {
                        e = e.next;
                    }
                }
                e = new Entry<T>(object, hash, proxy, ((ObjectProxyCache<Object, Object>)this.cache).referenceType, table[index], this.referenceQueue);
                table[index] = e;
                this.tableSize = potentialNewSize;
                this.entryTable = table;
            }
            finally {
                this.unlock();
            }
        }
        
        T getOrCreate(final Object object, final int hash, final A allocator) {
            Entry<T>[] table;
            Entry<T> e = (table = this.entryTable)[hash & table.length - 1];
            while (e != null) {
                if (hash == e.hash && object == e.objectRef.get()) {
                    final T proxy;
                    if ((proxy = e.proxyRef.get()) != null) {
                        return proxy;
                    }
                    break;
                }
                else {
                    e = e.next;
                }
            }
            this.lock();
            try {
                this.expunge();
                int potentialNewSize;
                if ((potentialNewSize = this.tableSize + 1) > this.threshold) {
                    table = this.rehash();
                }
                else {
                    table = this.entryTable;
                }
                final int index;
                Entry<T> e2 = table[index = (hash & table.length - 1)];
                while (e2 != null) {
                    if (hash == e2.hash && object == e2.objectRef.get()) {
                        final T proxy;
                        if ((proxy = e2.proxyRef.get()) != null) {
                            return proxy;
                        }
                        this.remove(table, hash, e2);
                        --potentialNewSize;
                        break;
                    }
                    else {
                        e2 = e2.next;
                    }
                }
                T proxy = this.cache.allocateProxy(object, allocator);
                e2 = new Entry<T>(object, hash, proxy, ((ObjectProxyCache<Object, Object>)this.cache).referenceType, table[index], this.referenceQueue);
                table[index] = e2;
                this.tableSize = potentialNewSize;
                this.entryTable = table;
                return proxy;
            }
            finally {
                this.unlock();
            }
        }
        
        T get(final Object object, final int hash) {
            Entry<T>[] table;
            for (Entry<T> e = (table = this.entryTable)[hash & table.length - 1]; e != null; e = e.next) {
                if (hash == e.hash && object == e.objectRef.get()) {
                    return e.proxyRef.get();
                }
            }
            return null;
        }
        
        static final <T, A> Segment<T, A>[] newArray(final int size) {
            return (Segment<T, A>[])new Segment[size];
        }
    }
    
    private interface EntryRef<T>
    {
        T get();
        
        int hash();
    }
}
