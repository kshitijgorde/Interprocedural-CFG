// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.ConcurrentModificationException;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Collection;
import java.io.Serializable;
import java.util.List;

public class CopyOnWriteArrayList implements List, Cloneable, Serializable
{
    protected transient Object[] array_;
    
    public CopyOnWriteArrayList() {
        this.array_ = new Object[0];
    }
    
    public CopyOnWriteArrayList(final Collection collection) {
        this.array_ = new Object[collection.size()];
        final Iterator<Object> iterator = collection.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            this.array_[n++] = iterator.next();
        }
    }
    
    public CopyOnWriteArrayList(final Object[] array) {
        this.copyIn(array, 0, array.length);
    }
    
    public synchronized void add(final int n, final Object o) {
        final int length = this.array_.length;
        if (n > length || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + length);
        }
        final Object[] array_ = new Object[length + 1];
        System.arraycopy(this.array_, 0, array_, 0, n);
        array_[n] = o;
        System.arraycopy(this.array_, n, array_, n + 1, length - n);
        this.array_ = array_;
    }
    
    public synchronized boolean add(final Object o) {
        final int length = this.array_.length;
        final Object[] array_ = new Object[length + 1];
        System.arraycopy(this.array_, 0, array_, 0, length);
        array_[length] = o;
        this.array_ = array_;
        return true;
    }
    
    public synchronized boolean addAll(int n, final Collection collection) {
        final int length = this.array_.length;
        if (n > length || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + length);
        }
        final int size = collection.size();
        if (size == 0) {
            return false;
        }
        final Object[] array_ = new Object[length + size];
        System.arraycopy(this.array_, 0, array_, 0, length);
        final int n2 = length - n;
        if (n2 > 0) {
            System.arraycopy(this.array_, n, array_, n + size, n2);
        }
        final Iterator<Object> iterator = collection.iterator();
        for (int i = 0; i < size; ++i) {
            array_[n++] = iterator.next();
        }
        this.array_ = array_;
        return true;
    }
    
    public synchronized boolean addAll(final Collection collection) {
        final int size = collection.size();
        if (size == 0) {
            return false;
        }
        int length = this.array_.length;
        final Object[] array_ = new Object[length + size];
        System.arraycopy(this.array_, 0, array_, 0, length);
        final Iterator<Object> iterator = collection.iterator();
        for (int i = 0; i < size; ++i) {
            array_[length++] = iterator.next();
        }
        this.array_ = array_;
        return true;
    }
    
    public synchronized int addAllAbsent(final Collection collection) {
        final int size = collection.size();
        if (size == 0) {
            return 0;
        }
        final Object[] array_ = this.array_;
        final int length = array_.length;
        final Object[] array = new Object[size];
        int n = 0;
        for (final Object next : collection) {
            if (indexOf(next, array_, length) < 0 && indexOf(next, array, n) < 0) {
                array[n++] = next;
            }
        }
        if (n == 0) {
            return 0;
        }
        final Object[] array_2 = new Object[length + n];
        System.arraycopy(array_, 0, array_2, 0, length);
        System.arraycopy(array, 0, array_2, length, n);
        this.array_ = array_2;
        return n;
    }
    
    public synchronized boolean addIfAbsent(final Object o) {
        final int length = this.array_.length;
        final Object[] array_ = new Object[length + 1];
        for (int i = 0; i < length; ++i) {
            if (o == this.array_[i] || (o != null && o.equals(this.array_[i]))) {
                return false;
            }
            array_[i] = this.array_[i];
        }
        array_[length] = o;
        this.array_ = array_;
        return true;
    }
    
    protected synchronized Object[] array() {
        return this.array_;
    }
    
    public synchronized void clear() {
        this.array_ = new Object[0];
    }
    
    public Object clone() {
        try {
            final Object[] array = this.array();
            final CopyOnWriteArrayList list = (CopyOnWriteArrayList)super.clone();
            System.arraycopy(array, 0, list.array_ = new Object[array.length], 0, array.length);
            return list;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public boolean contains(final Object o) {
        final Object[] array = this.array();
        return indexOf(o, array, array.length) >= 0;
    }
    
    public boolean containsAll(final Collection collection) {
        final Object[] array = this.array();
        final int length = array.length;
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (indexOf(iterator.next(), array, length) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public synchronized void copyIn(final Object[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.array_ = new Object[n2], 0, n2);
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        final List list = (List)o;
        if (this.size() != list.size()) {
            return false;
        }
        final ListIterator listIterator = this.listIterator();
        final ListIterator<Object> listIterator2 = list.listIterator();
        while (listIterator.hasNext()) {
            final Object next = listIterator.next();
            final Object next2 = listIterator2.next();
            boolean equals;
            if (next == null) {
                if (next2 == null) {
                    continue;
                }
                equals = false;
            }
            else {
                equals = next.equals(next2);
            }
            if (!equals) {
                return false;
            }
        }
        return true;
    }
    
    public Object get(final int n) {
        final Object[] array = this.array();
        this.rangeCheck(n, array.length);
        return array[n];
    }
    
    public int hashCode() {
        int n = 1;
        for (final Object next : this) {
            n = 31 * n + ((next == null) ? 0 : next.hashCode());
        }
        return n;
    }
    
    public int indexOf(final Object o) {
        final Object[] array = this.array();
        return indexOf(o, array, array.length);
    }
    
    public int indexOf(final Object o, final int n) {
        final Object[] array = this.array();
        final int length = array.length;
        if (o == null) {
            for (int i = n; i < length; ++i) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n; j < length; ++j) {
                if (o.equals(array[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    protected static int indexOf(final Object o, final Object[] array, final int n) {
        if (o == null) {
            for (int i = 0; i < n; ++i) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                if (o.equals(array[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public Iterator iterator() {
        return new COWIterator(this.array(), 0);
    }
    
    public int lastIndexOf(final Object o) {
        final Object[] array = this.array();
        return lastIndexOf(o, array, array.length);
    }
    
    public int lastIndexOf(final Object o, final int n) {
        final Object[] array = this.array();
        if (o == null) {
            for (int i = n; i >= 0; --i) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n; j >= 0; --j) {
                if (o.equals(array[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    protected static int lastIndexOf(final Object o, final Object[] array, final int n) {
        if (o == null) {
            for (int i = n - 1; i >= 0; --i) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n - 1; j >= 0; --j) {
                if (o.equals(array[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public ListIterator listIterator() {
        return new COWIterator(this.array(), 0);
    }
    
    public ListIterator listIterator(final int n) {
        final int length = this.array().length;
        if (n < 0 || n > length) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
        return new COWIterator(this.array(), n);
    }
    
    protected void rangeCheck(final int n, final int n2) {
        if (n >= n2 || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + n2);
        }
    }
    
    private synchronized void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final Object[] array_ = new Object[objectInputStream.readInt()];
        for (int i = 0; i < array_.length; ++i) {
            array_[i] = objectInputStream.readObject();
        }
        this.array_ = array_;
    }
    
    public synchronized Object remove(final int n) {
        final int length = this.array_.length;
        this.rangeCheck(n, length);
        final Object o = this.array_[n];
        final Object[] array_ = new Object[length - 1];
        System.arraycopy(this.array_, 0, array_, 0, n);
        final int n2 = length - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.array_, n + 1, array_, n, n2);
        }
        this.array_ = array_;
        return o;
    }
    
    public synchronized boolean remove(final Object o) {
        final int length = this.array_.length;
        if (length == 0) {
            return false;
        }
        final int n = length - 1;
        final Object[] array = new Object[n];
        for (int i = 0; i < n; ++i) {
            if (o == this.array_[i] || (o != null && o.equals(this.array_[i]))) {
                for (int j = i + 1; j < length; ++j) {
                    array[j - 1] = this.array_[j];
                }
                this.array_ = array;
                return true;
            }
            array[i] = this.array_[i];
        }
        if (o == this.array_[n] || (o != null && o.equals(this.array_[n]))) {
            this.array_ = array;
            return true;
        }
        return false;
    }
    
    public synchronized boolean removeAll(final Collection collection) {
        final Object[] array_ = this.array_;
        final int length = array_.length;
        if (length == 0) {
            return false;
        }
        final Object[] array = new Object[length];
        int n = 0;
        for (final Object o : array_) {
            if (!collection.contains(o)) {
                array[n++] = o;
            }
        }
        if (n == length) {
            return false;
        }
        final Object[] array_2 = new Object[n];
        System.arraycopy(array, 0, array_2, 0, n);
        this.array_ = array_2;
        return true;
    }
    
    public synchronized void removeRange(final int n, final int n2) {
        final int length = this.array_.length;
        if (n < 0 || n >= length || n2 > length || n2 < n) {
            throw new IndexOutOfBoundsException();
        }
        final int n3 = length - n2;
        final Object[] array_ = new Object[length - (n2 - n)];
        System.arraycopy(this.array_, 0, array_, 0, n);
        System.arraycopy(this.array_, n2, array_, n, n3);
        this.array_ = array_;
    }
    
    public synchronized boolean retainAll(final Collection collection) {
        final Object[] array_ = this.array_;
        final int length = array_.length;
        if (length == 0) {
            return false;
        }
        final Object[] array = new Object[length];
        int n = 0;
        for (final Object o : array_) {
            if (collection.contains(o)) {
                array[n++] = o;
            }
        }
        if (n == length) {
            return false;
        }
        final Object[] array_2 = new Object[n];
        System.arraycopy(array, 0, array_2, 0, n);
        this.array_ = array_2;
        return true;
    }
    
    public synchronized Object set(final int n, final Object o) {
        final int length = this.array_.length;
        this.rangeCheck(n, length);
        final Object o2 = this.array_[n];
        if (o2 != o && (o == null || !o.equals(o2))) {
            final Object[] array_ = new Object[length];
            System.arraycopy(this.array_, 0, array_, 0, length);
            array_[n] = o;
            this.array_ = array_;
        }
        return o2;
    }
    
    public int size() {
        return this.array().length;
    }
    
    public synchronized List subList(final int n, final int n2) {
        final int length = this.array_.length;
        if (n < 0 || n2 > length || n > n2) {
            throw new IndexOutOfBoundsException();
        }
        return new COWSubList(this, n, n2);
    }
    
    public Object[] toArray() {
        final Object[] array = this.array();
        final Object[] array2 = new Object[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public Object[] toArray(Object[] array) {
        final Object[] array2 = this.array();
        if (array.length < array2.length) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), array2.length);
        }
        System.arraycopy(array2, 0, array, 0, array2.length);
        if (array.length > array2.length) {
            array[array2.length] = null;
        }
        return array;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final Iterator iterator = this.iterator();
        sb.append("[");
        for (int n = this.size() - 1, i = 0; i <= n; ++i) {
            sb.append(String.valueOf(iterator.next()));
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final Object[] array = this.array();
        objectOutputStream.writeInt(array.length);
        for (int i = 0; i < array.length; ++i) {
            objectOutputStream.writeObject(array[i]);
        }
    }
    
    protected static class COWIterator implements ListIterator
    {
        protected final Object[] array;
        protected int cursor;
        
        protected COWIterator(final Object[] array, final int cursor) {
            this.array = array;
            this.cursor = cursor;
        }
        
        public void add(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext() {
            return this.cursor < this.array.length;
        }
        
        public boolean hasPrevious() {
            return this.cursor > 0;
        }
        
        public Object next() {
            try {
                return this.array[this.cursor++];
            }
            catch (IndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
        
        public int nextIndex() {
            return this.cursor;
        }
        
        public Object previous() {
            try {
                final Object[] array = this.array;
                final int cursor = this.cursor - 1;
                this.cursor = cursor;
                return array[cursor];
            }
            catch (IndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
        
        public int previousIndex() {
            return this.cursor - 1;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public void set(final Object o) {
            throw new UnsupportedOperationException();
        }
    }
    
    protected static class COWSubList extends AbstractList
    {
        protected final CopyOnWriteArrayList l;
        protected final int offset;
        protected int size;
        protected Object[] expectedArray;
        
        protected COWSubList(final CopyOnWriteArrayList l, final int offset, final int n) {
            this.l = l;
            this.expectedArray = this.l.array();
            this.offset = offset;
            this.size = n - offset;
        }
        
        public void add(final int n, final Object o) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n > this.size) {
                    throw new IndexOutOfBoundsException();
                }
                this.l.add(n + this.offset, o);
                this.expectedArray = this.l.array_;
                ++this.size;
            }
            // monitorexit(this.l)
        }
        
        protected void checkForComodification() {
            if (this.l.array_ != this.expectedArray) {
                throw new ConcurrentModificationException();
            }
        }
        
        public Object get(final int n) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                // monitorexit(this.l)
                return this.l.get(n + this.offset);
            }
        }
        
        public Iterator iterator() {
            synchronized (this.l) {
                this.checkForComodification();
                // monitorexit(this.l)
                return new COWSubListIterator(0);
            }
        }
        
        public ListIterator listIterator(final int n) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n > this.size) {
                    throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
                }
                // monitorexit(this.l)
                return new COWSubListIterator(n);
            }
        }
        
        protected void rangeCheck(final int n) {
            if (n < 0 || n >= this.size) {
                throw new IndexOutOfBoundsException("Index: " + n + ",Size: " + this.size);
            }
        }
        
        public Object remove(final int n) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                final Object remove = this.l.remove(n + this.offset);
                this.expectedArray = this.l.array_;
                --this.size;
                // monitorexit(this.l)
                return remove;
            }
        }
        
        public Object set(final int n, final Object o) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                final Object set = this.l.set(n + this.offset, o);
                this.expectedArray = this.l.array_;
                // monitorexit(this.l)
                return set;
            }
        }
        
        public int size() {
            synchronized (this.l) {
                this.checkForComodification();
                // monitorexit(this.l)
                return this.size;
            }
        }
        
        public List subList(final int n, final int n2) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n2 > this.size) {
                    throw new IndexOutOfBoundsException();
                }
                // monitorexit(this.l)
                return new COWSubList(this.l, n + this.offset, n2 + this.offset);
            }
        }
        
        protected class COWSubListIterator implements ListIterator
        {
            protected final ListIterator i;
            protected final int index;
            
            protected COWSubListIterator(final int index) {
                this.index = index;
                this.i = COWSubList.this.l.listIterator(index + COWSubList.this.offset);
            }
            
            public void add(final Object o) {
                throw new UnsupportedOperationException();
            }
            
            public boolean hasNext() {
                return this.nextIndex() < COWSubList.this.size;
            }
            
            public boolean hasPrevious() {
                return this.previousIndex() >= 0;
            }
            
            public Object next() {
                if (this.hasNext()) {
                    return this.i.next();
                }
                throw new NoSuchElementException();
            }
            
            public int nextIndex() {
                return this.i.nextIndex() - COWSubList.this.offset;
            }
            
            public Object previous() {
                if (this.hasPrevious()) {
                    return this.i.previous();
                }
                throw new NoSuchElementException();
            }
            
            public int previousIndex() {
                return this.i.previousIndex() - COWSubList.this.offset;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            public void set(final Object o) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
