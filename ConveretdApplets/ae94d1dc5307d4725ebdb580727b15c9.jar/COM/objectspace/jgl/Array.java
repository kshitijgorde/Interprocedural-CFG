// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.util.Enumeration;

public class Array implements Sequence
{
    Object[] myStorage;
    int myLength;
    static final int DEFAULT_SIZE = 10;
    static final int THRESHOLD = 2000;
    static final int MULTIPLIER = 2;
    
    public Array() {
        this.myStorage = new Object[10];
    }
    
    public Array(final int myLength) {
        if (myLength < 0) {
            throw new IllegalArgumentException("Attempt to create an Array with a negative size");
        }
        this.myLength = myLength;
        this.myStorage = new Object[this.myLength];
    }
    
    public Array(final int n, final Object o) {
        this(n);
        for (int i = 0; i < this.myLength; ++i) {
            this.myStorage[i] = o;
        }
    }
    
    public Array(final Object[] myStorage) {
        synchronized (myStorage) {
            this.myStorage = myStorage;
            this.myLength = myStorage.length;
        }
    }
    
    public Array(final Array array) {
        synchronized (array) {
            this.myLength = array.myLength;
            this.myStorage = new Object[this.myLength];
            System.arraycopy(array.myStorage, 0, this.myStorage, 0, this.myLength);
        }
    }
    
    public synchronized Object clone() {
        return new Array(this);
    }
    
    public boolean equals(final Object o) {
        return o instanceof Array && this.equals((Array)o);
    }
    
    public synchronized boolean equals(final Array array) {
        synchronized (array) {
            return Comparing.equal(this, array);
        }
    }
    
    public synchronized int hashCode() {
        return Hashing.orderedHash(this);
    }
    
    public synchronized String toString() {
        return Printing.toString(this, "Array");
    }
    
    public synchronized void copy(final Array array) {
        if (this == array) {
            return;
        }
        synchronized (array) {
            if (array.myLength > this.myStorage.length) {
                this.myStorage = new Object[array.myLength];
                System.arraycopy(array.myStorage, 0, this.myStorage, 0, array.myLength);
            }
            else if (this.myLength > array.myLength) {
                System.arraycopy(array.myStorage, 0, this.myStorage, 0, array.myLength);
                for (int i = array.myLength; i < this.myLength; ++i) {
                    this.myStorage[i] = null;
                }
            }
            else {
                System.arraycopy(array.myStorage, 0, this.myStorage, 0, array.myLength);
            }
            this.myLength = array.myLength;
        }
    }
    
    public synchronized void copyTo(final Object[] array) {
        synchronized (array) {
            if (this.myLength < array.length) {
                System.arraycopy(this.myStorage, 0, array, 0, this.myLength);
            }
            else {
                System.arraycopy(this.myStorage, 0, array, 0, array.length);
            }
        }
    }
    
    public boolean isEmpty() {
        return this.myLength == 0;
    }
    
    public int size() {
        return this.myLength;
    }
    
    public int maxSize() {
        return Allocator.maxSize();
    }
    
    public int capacity() {
        return this.myStorage.length;
    }
    
    public synchronized Object back() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("Array is empty");
        }
        return this.myStorage[this.myLength - 1];
    }
    
    public synchronized Object front() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("Array is empty");
        }
        return this.myStorage[0];
    }
    
    public synchronized Object at(final int n) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        return this.myStorage[n];
    }
    
    public synchronized void put(final int n, final Object o) {
        if (n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        this.myStorage[n] = o;
    }
    
    public synchronized void clear() {
        this.myStorage = new Object[10];
        this.myLength = 0;
    }
    
    public Object remove(final Enumeration enumeration) {
        if (!(enumeration instanceof ArrayIterator)) {
            throw new IllegalArgumentException("Enumeration not an ArrayIterator");
        }
        if (((ArrayIterator)enumeration).myArray != this) {
            throw new IllegalArgumentException("Enumeration not for this Array ");
        }
        final Object value = ((ArrayIterator)enumeration).get();
        this.remove(((ArrayIterator)enumeration).myIndex);
        return value;
    }
    
    public synchronized Object remove(final int n) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        final Object o = this.myStorage[n];
        System.arraycopy(this.myStorage, n + 1, this.myStorage, n, this.myLength - n - 1);
        this.myStorage[--this.myLength] = null;
        return o;
    }
    
    public int remove(final Enumeration enumeration, final Enumeration enumeration2) {
        if (!(enumeration instanceof ArrayIterator) || !(enumeration2 instanceof ArrayIterator)) {
            throw new IllegalArgumentException("Enumeration not an ArrayIterator");
        }
        if (((ArrayIterator)enumeration).myArray != this || ((ArrayIterator)enumeration2).myArray != this) {
            throw new IllegalArgumentException("Enumeration not for this Array ");
        }
        return this.remove(((ArrayIterator)enumeration).myIndex, ((ArrayIterator)enumeration2).myIndex - 1);
    }
    
    public synchronized int remove(final int n, final int n2) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        final int n3 = n2 - n + 1;
        System.arraycopy(this.myStorage, n2 + 1, this.myStorage, n, this.myLength - n2 - 1);
        for (int i = this.myLength - n3; i < this.myLength; ++i) {
            this.myStorage[i] = null;
        }
        this.myLength -= n3;
        return n3;
    }
    
    public synchronized Object popBack() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("Array is empty");
        }
        final Object[] myStorage = this.myStorage;
        final int myLength = this.myLength - 1;
        this.myLength = myLength;
        final Object o = myStorage[myLength];
        this.myStorage[this.myLength] = null;
        return o;
    }
    
    public synchronized Object add(final Object o) {
        if (this.myLength == this.myStorage.length) {
            final Object[] nextStorage = this.getNextStorage();
            System.arraycopy(this.myStorage, 0, nextStorage, 0, this.myLength);
            this.myStorage = nextStorage;
        }
        this.myStorage[this.myLength++] = o;
        return null;
    }
    
    public void pushBack(final Object o) {
        this.add(o);
    }
    
    public ArrayIterator insert(final ArrayIterator arrayIterator, final Object o) {
        this.insert(arrayIterator.myIndex, o);
        return new ArrayIterator(this, arrayIterator.myIndex);
    }
    
    public synchronized void insert(final int n, final Object o) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        if (this.myLength != this.myStorage.length) {
            if (n != this.myLength) {
                System.arraycopy(this.myStorage, n, this.myStorage, n + 1, this.myLength - n);
            }
        }
        else {
            final Object[] nextStorage = this.getNextStorage();
            System.arraycopy(this.myStorage, 0, nextStorage, 0, n);
            System.arraycopy(this.myStorage, n, nextStorage, n + 1, this.myLength - n);
            this.myStorage = nextStorage;
        }
        this.myStorage[n] = o;
        ++this.myLength;
    }
    
    public void insert(final ArrayIterator arrayIterator, final int n, final Object o) {
        this.insert(arrayIterator.myIndex, n, o);
    }
    
    public synchronized void insert(final int n, final int n2, final Object o) {
        if (n < 0 || n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("Attempt to insert a negative number of objects.");
        }
        if (n2 == 0) {
            return;
        }
        if (this.myStorage.length - this.myLength >= n2) {
            System.arraycopy(this.myStorage, n, this.myStorage, n + n2, this.myLength - n);
        }
        else {
            final Object[] nextStorage = this.getNextStorage(n2);
            System.arraycopy(this.myStorage, 0, nextStorage, 0, n);
            System.arraycopy(this.myStorage, n, nextStorage, n + n2, this.myLength - n);
            this.myStorage = nextStorage;
        }
        for (int i = n; i < n + n2; ++i) {
            this.myStorage[i] = o;
        }
        this.myLength += n2;
    }
    
    public void insert(final ArrayIterator arrayIterator, final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2) {
        this.insert(arrayIterator.myIndex, forwardIterator, forwardIterator2);
    }
    
    public synchronized void insert(final int n, final ForwardIterator forwardIterator, final ForwardIterator forwardIterator2) {
        final int distance = forwardIterator.distance(forwardIterator2);
        if (distance == 0) {
            return;
        }
        final ForwardIterator forwardIterator3 = (ForwardIterator)forwardIterator.clone();
        if (this.myStorage.length - this.myLength >= distance) {
            System.arraycopy(this.myStorage, n, this.myStorage, n + distance, this.myLength - n);
        }
        else {
            final Object[] nextStorage = this.getNextStorage(distance);
            System.arraycopy(this.myStorage, 0, nextStorage, 0, n);
            System.arraycopy(this.myStorage, n, nextStorage, n + distance, this.myLength - n);
            this.myStorage = nextStorage;
        }
        for (int i = n; i < n + distance; ++i) {
            this.myStorage[i] = forwardIterator3.nextElement();
        }
        this.myLength += distance;
    }
    
    public synchronized void swap(final Array array) {
        synchronized (array) {
            final int myLength = this.myLength;
            final Object[] myStorage = this.myStorage;
            this.myLength = array.myLength;
            this.myStorage = array.myStorage;
            array.myLength = myLength;
            array.myStorage = myStorage;
        }
    }
    
    public synchronized Enumeration elements() {
        return new ArrayIterator(this, 0);
    }
    
    public ForwardIterator start() {
        return this.begin();
    }
    
    public ForwardIterator finish() {
        return this.end();
    }
    
    public synchronized ArrayIterator begin() {
        return new ArrayIterator(this, 0);
    }
    
    public synchronized ArrayIterator end() {
        return new ArrayIterator(this, this.myLength);
    }
    
    public synchronized void trimToSize() {
        if (this.myLength < this.myStorage.length) {
            System.arraycopy(this.myStorage, 0, this.myStorage = new Object[this.myLength], 0, this.myLength);
        }
    }
    
    public synchronized void ensureCapacity(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Attempt to reserve a negative size.");
        }
        if (this.myStorage.length < n) {
            final Object[] myStorage = new Object[n];
            if (this.myLength > 0) {
                System.arraycopy(this.myStorage, 0, myStorage, 0, this.myLength);
            }
            this.myStorage = myStorage;
        }
    }
    
    public synchronized Object popFront() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("Array is empty");
        }
        final Object o = this.myStorage[0];
        this.remove(0);
        return o;
    }
    
    public void pushFront(final Object o) {
        this.insert(0, o);
    }
    
    public int remove(final Object o) {
        return this.remove(0, this.myLength - 1, o);
    }
    
    public synchronized int remove(final Object o, int i) {
        int n = 0;
        while (i > 0) {
            final int index = this.indexOf(o);
            if (index >= 0) {
                --i;
                ++n;
                this.remove(index);
            }
        }
        return n;
    }
    
    public synchronized int remove(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return this.remove(((ArrayIterator)Removing.remove(new ArrayIterator(this, n), new ArrayIterator(this, n2 + 1), o)).myIndex, n2);
    }
    
    public synchronized int replace(final Object o, final Object o2) {
        return Replacing.replace(this, o, o2);
    }
    
    public synchronized int replace(final int n, final int n2, final Object o, final Object o2) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return Replacing.replace(new ArrayIterator(this, n), new ArrayIterator(this, n2 + 1), o, o2);
    }
    
    public synchronized int count(final Object o) {
        return Counting.count(this, o);
    }
    
    public synchronized int count(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return Counting.count(new ArrayIterator(this, n), new ArrayIterator(this, n2 + 1), o);
    }
    
    public int indexOf(final Object o) {
        return this.indexOf(0, this.myLength - 1, o);
    }
    
    public synchronized int indexOf(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return -1;
        }
        this.checkRange(n, n2);
        final int myIndex = ((ArrayIterator)Finding.find(new ArrayIterator(this, n), new ArrayIterator(this, n2 + 1), o)).myIndex;
        return (myIndex == n2 + 1) ? -1 : myIndex;
    }
    
    public synchronized void setSize(final int n) {
        if (this.myLength > n) {
            this.remove(n, this.myLength - 1);
        }
        else if (this.myLength < n) {
            this.insert(this.myLength, n - this.myLength, null);
        }
    }
    
    public boolean contains(final Object o) {
        return this.indexOf(o) != -1;
    }
    
    private void checkRange(final int n, final int n2) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        if (n2 < 0 || n2 >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n2))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
    }
    
    private int getNextSize() {
        return Math.max(1, (this.myLength > 2000) ? (this.myLength + 2000) : (this.myLength * 2));
    }
    
    private Object[] getNextStorage() {
        return new Object[this.getNextSize()];
    }
    
    private Object[] getNextStorage(final int n) {
        return new Object[Math.max(this.getNextSize(), this.myLength + n)];
    }
}
