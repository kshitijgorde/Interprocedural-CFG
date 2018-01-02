// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

public class HashMap extends Map
{
    static final int DEFAULT_SIZE = 203;
    static final float DEFAULT_RATIO = 0.75f;
    BinaryPredicate comparator;
    boolean allowDups;
    boolean expandActive;
    transient int size;
    transient HashMapNode[] buckets;
    int length;
    int limit;
    float ratio;
    
    public HashMap() {
        this(new EqualTo(), false, 203, 0.75f);
    }
    
    public HashMap(final boolean b) {
        this(new EqualTo(), b, 203, 0.75f);
    }
    
    public HashMap(final BinaryPredicate binaryPredicate) {
        this(binaryPredicate, false, 203, 0.75f);
    }
    
    public HashMap(final BinaryPredicate binaryPredicate, final boolean b) {
        this(binaryPredicate, b, 203, 0.75f);
    }
    
    public HashMap(final BinaryPredicate binaryPredicate, final int n, final float n2) {
        this(binaryPredicate, false, n, n2);
    }
    
    public HashMap(final BinaryPredicate comparator, final boolean allowDups, final int length, final float ratio) {
        this.expandActive = true;
        this.comparator = comparator;
        this.ratio = ratio;
        this.length = length;
        this.limit = (int)(this.length * this.ratio);
        this.buckets = new HashMapNode[this.length];
        this.allowDups = allowDups;
    }
    
    public HashMap(final HashMap hashMap) {
        this.expandActive = true;
        this.copy(hashMap);
    }
    
    public boolean allowsDuplicates() {
        return this.allowDups;
    }
    
    public BinaryPredicate getComparator() {
        return this.comparator;
    }
    
    public float getLoadRatio() {
        return this.ratio;
    }
    
    public synchronized Object clone() {
        return new HashMap(this);
    }
    
    public synchronized void copy(final HashMap hashMap) {
        synchronized (hashMap) {
            this.comparator = hashMap.comparator;
            this.length = hashMap.length;
            this.ratio = hashMap.ratio;
            this.limit = hashMap.limit;
            this.size = hashMap.size();
            this.buckets = new HashMapNode[this.length];
            this.allowDups = hashMap.allowDups;
            for (int i = 0; i < this.length; ++i) {
                HashMapNode hashMapNode = null;
                for (HashMapNode next = hashMap.buckets[i]; next != null; next = next.next) {
                    final HashMapNode next2 = new HashMapNode();
                    next2.key = next.key;
                    next2.value = next.value;
                    next2.hash = next.hash;
                    if (this.buckets[i] == null) {
                        this.buckets[i] = next2;
                    }
                    else {
                        hashMapNode.next = next2;
                    }
                    hashMapNode = next2;
                }
            }
        }
    }
    
    public synchronized String toString() {
        return Printing.toString(this, "HashMap");
    }
    
    public synchronized Enumeration elements() {
        return new HashMapIterator(this.first(), this, 3);
    }
    
    public ForwardIterator start() {
        return this.begin();
    }
    
    public ForwardIterator finish() {
        return this.end();
    }
    
    public synchronized HashMapIterator begin() {
        return new HashMapIterator(this.first(), this, 1);
    }
    
    public synchronized HashMapIterator end() {
        return new HashMapIterator(null, this, 1);
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int size() {
        return this.size;
    }
    
    public int maxSize() {
        return Allocator.maxSize();
    }
    
    public boolean equals(final Object o) {
        return o instanceof HashMap && this.equals((HashMap)o);
    }
    
    public synchronized boolean equals(final HashMap hashMap) {
        synchronized (hashMap) {
            if (this.size() != hashMap.size()) {
                // monitorexit(hashMap)
                return false;
            }
            if (this.allowDups) {
                Object o = null;
                final HashMapIterator begin = this.begin();
                while (begin.hasMoreElements()) {
                    final Object key = begin.key();
                    if (o == null || !key.equals(o)) {
                        o = key;
                        if (!this.same(this.values(key), hashMap.values(key))) {
                            // monitorexit(hashMap)
                            return false;
                        }
                    }
                    begin.advance();
                }
            }
            else {
                final HashMapIterator begin2 = this.begin();
                while (begin2.hasMoreElements()) {
                    final Object value = hashMap.get(begin2.key());
                    if (value == null || !value.equals(begin2.value())) {
                        // monitorexit(hashMap)
                        return false;
                    }
                    begin2.advance();
                }
            }
        }
        return true;
    }
    
    public synchronized int hashCode() {
        return Hashing.unorderedHash(new HashMapIterator(this.first(), this, 2), new HashMapIterator(null, this, 2));
    }
    
    public synchronized void swap(final HashMap hashMap) {
        synchronized (hashMap) {
            final int size = this.size;
            this.size = hashMap.size();
            hashMap.size(size);
            final HashMapNode[] buckets = this.buckets;
            this.buckets = hashMap.buckets;
            hashMap.buckets = buckets;
            final int length = this.length;
            this.length = hashMap.length;
            hashMap.length = length;
            final int limit = this.limit;
            this.limit = hashMap.limit;
            hashMap.limit = limit;
            final float ratio = this.ratio;
            this.ratio = hashMap.ratio;
            hashMap.ratio = ratio;
            final boolean allowDups = this.allowDups;
            this.allowDups = hashMap.allowDups;
            hashMap.allowDups = allowDups;
        }
    }
    
    public synchronized void clear() {
        this.buckets = new HashMapNode[this.length];
        this.size = 0;
    }
    
    public Object remove(final Object o) {
        return this.removeAux(o, this.size).first;
    }
    
    public int remove(final Object o, final int n) {
        return ((Number)this.removeAux(o, n).second).intValue();
    }
    
    synchronized Pair removeAux(final Object o, int n) {
        if (n > 0) {
            final int n2 = o.hashCode() & Integer.MAX_VALUE;
            final int n3 = n2 % this.length;
            HashMapNode next = this.buckets[n3];
            HashMapNode hashMapNode = null;
            while (next != null) {
                if (next.hash == n2 && this.comparator.execute(next.key, o)) {
                    int n4 = 1;
                    --n;
                    HashMapNode next2 = next.next;
                    final Object value = next.value;
                    if (this.allowDups) {
                        while (n > 0 && next2 != null && next2.hash == n2 && this.comparator.execute(next2.key, o)) {
                            ++n4;
                            --n;
                            next2 = next2.next;
                        }
                    }
                    if (hashMapNode == null) {
                        this.buckets[n3] = next2;
                    }
                    else {
                        hashMapNode.next = next2;
                    }
                    this.size -= n4;
                    return new Pair(value, new Integer(n4));
                }
                hashMapNode = next;
                next = next.next;
            }
        }
        return new Pair(null, new Integer(0));
    }
    
    public synchronized Object remove(final Enumeration enumeration) {
        if (!(enumeration instanceof HashMapIterator)) {
            throw new IllegalArgumentException("Enumeration not a HashMapIterator");
        }
        if (((HashMapIterator)enumeration).myHashMap != this) {
            throw new IllegalArgumentException("Enumeration not for this HashMap");
        }
        final HashMapNode myNode = ((HashMapIterator)enumeration).myNode;
        final int n = myNode.hash % this.length;
        HashMapNode next = this.buckets[n];
        if (myNode == next) {
            this.buckets[n] = myNode.next;
        }
        else {
            while (next.next != myNode) {
                next = next.next;
            }
            next.next = myNode.next;
        }
        --this.size;
        return (myNode == null) ? null : myNode.value;
    }
    
    public synchronized int remove(final Enumeration enumeration, final Enumeration enumeration2) {
        if (!(enumeration instanceof HashMapIterator) || !(enumeration2 instanceof HashMapIterator)) {
            throw new IllegalArgumentException("Enumeration not a HashMapIterator");
        }
        if (((HashMapIterator)enumeration).myHashMap != this || ((HashMapIterator)enumeration2).myHashMap != this) {
            throw new IllegalArgumentException("Enumeration not for this HashMap");
        }
        HashMapIterator hashMapIterator;
        HashMapIterator hashMapIterator2;
        int n;
        HashMapIterator hashMapIterator3;
        for (hashMapIterator = (HashMapIterator)enumeration, hashMapIterator2 = (HashMapIterator)enumeration2, n = 0; !hashMapIterator.equals(hashMapIterator2); hashMapIterator = hashMapIterator3, ++n) {
            hashMapIterator3 = new HashMapIterator(hashMapIterator);
            hashMapIterator3.advance();
            this.remove(hashMapIterator);
        }
        return n;
    }
    
    public synchronized HashMapIterator find(final Object o) {
        final int n = o.hashCode() & Integer.MAX_VALUE;
        for (HashMapNode next = this.buckets[n % this.length]; next != null; next = next.next) {
            if (n == next.hash && this.comparator.execute(next.key, o)) {
                return new HashMapIterator(next, this, 1);
            }
        }
        return new HashMapIterator(null, this, 1);
    }
    
    public synchronized int count(final Object o) {
        final int n = o.hashCode() & Integer.MAX_VALUE;
        HashMapNode next = this.buckets[n % this.length];
        while (next != null) {
            if (n == next.hash && this.comparator.execute(next.key, o)) {
                if (this.allowDups) {
                    int n2 = 1;
                    for (HashMapNode hashMapNode = next.next; hashMapNode != null && n == hashMapNode.hash && this.comparator.execute(hashMapNode.key, o); hashMapNode = hashMapNode.next) {
                        ++n2;
                    }
                    return n2;
                }
                return 1;
            }
            else {
                next = next.next;
            }
        }
        return 0;
    }
    
    public synchronized int countValues(final Object o) {
        return Counting.count(new HashMapIterator(this.first(), this, 3), new HashMapIterator(null, this, 3), o);
    }
    
    public synchronized Object get(final Object o) {
        final int n = o.hashCode() & Integer.MAX_VALUE;
        for (HashMapNode next = this.buckets[n % this.length]; next != null; next = next.next) {
            if (n == next.hash && this.comparator.execute(next.key, o)) {
                return next.value;
            }
        }
        return null;
    }
    
    public synchronized Object put(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            throw new NullPointerException();
        }
        final int hash = o.hashCode() & Integer.MAX_VALUE;
        final int n = hash % this.length;
        for (HashMapNode next = this.buckets[n]; next != null; next = next.next) {
            if (hash == next.hash && this.comparator.execute(next.key, o)) {
                next.key = o;
                final Object value = next.value;
                next.value = o2;
                return value;
            }
        }
        final HashMapNode hashMapNode = new HashMapNode();
        hashMapNode.key = o;
        hashMapNode.value = o2;
        hashMapNode.hash = hash;
        hashMapNode.next = this.buckets[n];
        this.buckets[n] = hashMapNode;
        if (++this.size > this.limit) {
            this.expand();
        }
        return null;
    }
    
    public Object add(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Pair)) {
            throw new IllegalArgumentException("object is not pair");
        }
        if (((Pair)o).first == null || ((Pair)o).second == null) {
            throw new NullPointerException();
        }
        final Pair pair = (Pair)o;
        return this.add(pair.first, pair.second);
    }
    
    public synchronized Object add(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            throw new NullPointerException();
        }
        final int n = o.hashCode() & Integer.MAX_VALUE;
        final int n2 = n % this.length;
        HashMapNode next = this.buckets[n2];
        while (next != null) {
            if (n == next.hash && this.comparator.execute(next.key, o)) {
                if (this.allowDups) {
                    final HashMapNode next2 = new HashMapNode();
                    next2.key = o;
                    next2.value = o2;
                    next2.hash = n;
                    next2.next = next.next;
                    next.next = next2;
                    if (++this.size > this.limit) {
                        this.expand();
                    }
                    return null;
                }
                return next.value;
            }
            else {
                next = next.next;
            }
        }
        final HashMapNode hashMapNode = new HashMapNode();
        hashMapNode.key = o;
        hashMapNode.value = o2;
        hashMapNode.hash = n;
        hashMapNode.next = this.buckets[n2];
        this.buckets[n2] = hashMapNode;
        if (++this.size > this.limit) {
            this.expand();
        }
        return null;
    }
    
    public synchronized Enumeration keys() {
        return new HashMapIterator(this.first(), this, 2);
    }
    
    public synchronized Enumeration keys(final Object o) {
        final Array array = new Array();
        final HashMapIterator begin = this.begin();
        while (begin.hasMoreElements()) {
            if (begin.value().equals(o)) {
                array.pushBack(begin.key());
            }
            begin.advance();
        }
        return array.elements();
    }
    
    public synchronized Enumeration values(final Object o) {
        final Array array = new Array();
        final HashMapIterator begin = this.begin();
        while (begin.hasMoreElements()) {
            if (this.comparator.execute(begin.key(), o)) {
                array.pushBack(begin.value());
            }
            begin.advance();
        }
        return array.elements();
    }
    
    public synchronized HashMapIterator lowerBound(final Object o) {
        return (HashMapIterator)this.equalRange(o).begin;
    }
    
    public synchronized HashMapIterator upperBound(final Object o) {
        return (HashMapIterator)this.equalRange(o).end;
    }
    
    public synchronized Range equalRange(final Object o) {
        final int n = o.hashCode() & Integer.MAX_VALUE;
        for (HashMapNode next = this.buckets[n % this.length]; next != null; next = next.next) {
            if (n == next.hash && this.comparator.execute(next.key, o)) {
                final HashMapNode hashMapNode = next;
                HashMapNode hashMapNode2;
                for (hashMapNode2 = ((next.next == null) ? this.next(next) : next.next); hashMapNode2 != null && n == hashMapNode2.hash && this.comparator.execute(hashMapNode2.key, o); hashMapNode2 = ((hashMapNode2.next == null) ? this.next(hashMapNode2) : hashMapNode2.next)) {}
                return new Range(new HashMapIterator(hashMapNode, this, 1), new HashMapIterator(hashMapNode2, this, 1));
            }
        }
        return new Range(this.end(), this.end());
    }
    
    private HashMapNode first() {
        if (this.size > 0) {
            for (int i = 0; i < this.length; ++i) {
                if (this.buckets[i] != null) {
                    return this.buckets[i];
                }
            }
        }
        return null;
    }
    
    private HashMapNode next(final HashMapNode hashMapNode) {
        for (int i = hashMapNode.hash % this.length + 1; i < this.length; ++i) {
            if (this.buckets[i] != null) {
                return this.buckets[i];
            }
        }
        return null;
    }
    
    public boolean expansionAllowed() {
        return this.expandActive;
    }
    
    public synchronized void allowExpansion(final boolean expandActive) {
        this.expandActive = expandActive;
    }
    
    private void expand() {
        if (!this.expansionAllowed()) {
            return;
        }
        final int length = this.length * 2 + 1;
        final HashMapNode[] buckets = new HashMapNode[length];
        for (int i = 0; i < this.length; ++i) {
            HashMapNode hashMapNode;
            int n;
            for (HashMapNode next = this.buckets[i]; next != null; next = next.next, n = hashMapNode.hash % length, hashMapNode.next = buckets[n], buckets[n] = hashMapNode) {
                hashMapNode = next;
            }
        }
        this.buckets = buckets;
        this.length = length;
        this.limit = (int)(this.length * this.ratio);
    }
    
    private boolean same(final Enumeration enumeration, final Enumeration enumeration2) {
        final Array array = new Array();
        final Array array2 = new Array();
        while (enumeration.hasMoreElements()) {
            array.add(enumeration.nextElement());
        }
        while (enumeration2.hasMoreElements()) {
            array2.add(enumeration2.nextElement());
        }
        if (array.size() != array2.size()) {
            return false;
        }
        for (int i = 0; i < array.size(); ++i) {
            final Object at = array.at(i);
            int n = 0;
            int n2 = 0;
            for (int j = 0; j < array2.size(); ++j) {
                if (array.at(j).equals(at)) {
                    ++n;
                }
                if (array2.at(j).equals(at)) {
                    ++n2;
                }
            }
            if (n != n2) {
                return false;
            }
        }
        return true;
    }
    
    private void size(final int size) {
        this.size = size;
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        Copying.copy(this.begin(), this.end(), new ObjectOutputStreamIterator(objectOutputStream));
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.buckets = new HashMapNode[this.length];
        int int1 = objectInputStream.readInt();
        while (int1-- > 0) {
            this.add(objectInputStream.readObject());
        }
    }
    
    final class HashMapNode
    {
        Object key;
        Object value;
        int hash;
        HashMapNode next;
    }
}
