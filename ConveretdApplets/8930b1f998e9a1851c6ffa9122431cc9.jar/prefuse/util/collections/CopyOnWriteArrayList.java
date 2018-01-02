// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.ConcurrentModificationException;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.ListIterator;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Collection;
import java.io.Serializable;
import java.util.RandomAccess;
import java.util.List;

public class CopyOnWriteArrayList implements List, RandomAccess, Cloneable, Serializable
{
    private static final long serialVersionUID = 8673264195747942595L;
    private transient volatile Object[] array;
    
    public Object[] getArray() {
        return this.array;
    }
    
    void setArray(final Object[] array) {
        this.array = array;
    }
    
    public CopyOnWriteArrayList() {
        this.setArray(new Object[0]);
    }
    
    public CopyOnWriteArrayList(final Collection collection) {
        final Object[] array = new Object[collection.size()];
        int n = 0;
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            array[n++] = iterator.next();
        }
        this.setArray(array);
    }
    
    public CopyOnWriteArrayList(final Object[] array) {
        this.copyIn(array, 0, array.length);
    }
    
    private void copyIn(final Object[] array, final int n, final int n2) {
        final int n3 = n + n2;
        if (n3 > array.length) {
            throw new IndexOutOfBoundsException();
        }
        final Object[] copyOfRange = copyOfRange(array, n, n3, Object[].class);
        synchronized (this) {
            this.setArray(copyOfRange);
        }
    }
    
    public int size() {
        return this.getArray().length;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    private static boolean eq(final Object o, final Object o2) {
        return (o == null) ? (o2 == null) : o.equals(o2);
    }
    
    private static int indexOf(final Object o, final Object[] array, final int n, final int n2) {
        if (o == null) {
            for (int i = n; i < n2; ++i) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = n; j < n2; ++j) {
                if (o.equals(array[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    private static int lastIndexOf(final Object o, final Object[] array, final int n) {
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
    
    public boolean contains(final Object o) {
        final Object[] array = this.getArray();
        return indexOf(o, array, 0, array.length) >= 0;
    }
    
    public int indexOf(final Object o) {
        final Object[] array = this.getArray();
        return indexOf(o, array, 0, array.length);
    }
    
    public int indexOf(final Object o, final int n) {
        final Object[] array = this.getArray();
        return indexOf(o, array, n, array.length);
    }
    
    public int lastIndexOf(final Object o) {
        final Object[] array = this.getArray();
        return lastIndexOf(o, array, array.length - 1);
    }
    
    public int lastIndexOf(final Object o, final int n) {
        return lastIndexOf(o, this.getArray(), n);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public Object[] toArray() {
        final Object[] array = this.getArray();
        return copyOf(array, array.length);
    }
    
    public Object[] toArray(final Object[] array) {
        final Object[] array2 = this.getArray();
        final int length = array2.length;
        if (array.length < length) {
            return copyOf(array2, length, array.getClass());
        }
        System.arraycopy(array2, 0, array, 0, length);
        if (array.length > length) {
            array[length] = null;
        }
        return array;
    }
    
    public Object get(final int n) {
        return this.getArray()[n];
    }
    
    public synchronized Object set(final int n, final Object o) {
        final Object[] array = this.getArray();
        final int length = array.length;
        final Object o2 = array[n];
        if (o2 != o) {
            final Object[] copy = copyOf(array, length);
            copy[n] = o;
            this.setArray(copy);
        }
        return o2;
    }
    
    public boolean add(final Object o) {
        synchronized (this) {
            final Object[] array = this.getArray();
            final int length = array.length;
            final Object[] copy = copyOf(array, length + 1);
            copy[length] = o;
            this.setArray(copy);
        }
        return true;
    }
    
    public synchronized void add(final int n, final Object o) {
        final Object[] array = this.getArray();
        final int length = array.length;
        if (n > length || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + length);
        }
        final int n2 = length - n;
        Object[] copy;
        if (n2 == 0) {
            copy = copyOf(array, length + 1);
        }
        else {
            copy = new Object[length + 1];
            System.arraycopy(array, 0, copy, 0, n);
            System.arraycopy(array, n, copy, n + 1, n2);
        }
        copy[n] = o;
        this.setArray(copy);
    }
    
    public synchronized Object remove(final int n) {
        final Object[] array = this.getArray();
        final int length = array.length;
        final Object o = array[n];
        final int n2 = length - n - 1;
        if (n2 == 0) {
            this.setArray(copyOf(array, length - 1));
        }
        else {
            final Object[] array2 = new Object[length - 1];
            System.arraycopy(array, 0, array2, 0, n);
            System.arraycopy(array, n + 1, array2, n, n2);
            this.setArray(array2);
        }
        return o;
    }
    
    public synchronized boolean remove(final Object o) {
        final Object[] array = this.getArray();
        final int length = array.length;
        if (length != 0) {
            final int n = length - 1;
            final Object[] array2 = new Object[n];
            for (int i = 0; i < n; ++i) {
                if (eq(o, array[i])) {
                    for (int j = i + 1; j < length; ++j) {
                        array2[j - 1] = array[j];
                    }
                    this.setArray(array2);
                    return true;
                }
                array2[i] = array[i];
            }
            if (eq(o, array[n])) {
                this.setArray(array2);
                return true;
            }
        }
        return false;
    }
    
    private synchronized void removeRange(final int n, final int n2) {
        final Object[] array = this.getArray();
        final int length = array.length;
        if (n < 0 || n >= length || n2 > length || n2 < n) {
            throw new IndexOutOfBoundsException();
        }
        final int n3 = length - (n2 - n);
        final int n4 = length - n2;
        if (n4 == 0) {
            this.setArray(copyOf(array, n3));
        }
        else {
            final Object[] array2 = new Object[n3];
            System.arraycopy(array, 0, array2, 0, n);
            System.arraycopy(array, n2, array2, n, n4);
            this.setArray(array2);
        }
    }
    
    public synchronized boolean addIfAbsent(final Object o) {
        final Object[] array = this.getArray();
        final int length = array.length;
        final Object[] array2 = new Object[length + 1];
        for (int i = 0; i < length; ++i) {
            if (eq(o, array[i])) {
                return false;
            }
            array2[i] = array[i];
        }
        array2[length] = o;
        this.setArray(array2);
        return true;
    }
    
    public boolean containsAll(final Collection collection) {
        final Object[] array = this.getArray();
        final int length = array.length;
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (indexOf(iterator.next(), array, 0, length) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public synchronized boolean removeAll(final Collection collection) {
        final Object[] array = this.getArray();
        final int length = array.length;
        if (length != 0) {
            int n = 0;
            final Object[] array2 = new Object[length];
            for (final Object o : array) {
                if (!collection.contains(o)) {
                    array2[n++] = o;
                }
            }
            if (n != length) {
                this.setArray(copyOfRange(array2, 0, n, Object[].class));
                return true;
            }
        }
        return false;
    }
    
    public synchronized boolean retainAll(final Collection collection) {
        final Object[] array = this.getArray();
        final int length = array.length;
        if (length != 0) {
            int n = 0;
            final Object[] array2 = new Object[length];
            for (final Object o : array) {
                if (collection.contains(o)) {
                    array2[n++] = o;
                }
            }
            if (n != length) {
                this.setArray(copyOfRange(array2, 0, n, Object[].class));
                return true;
            }
        }
        return false;
    }
    
    public int addAllAbsent(final Collection collection) {
        final int size = collection.size();
        if (size == 0) {
            return 0;
        }
        synchronized (this) {
            final Object[] array = this.getArray();
            final int length = array.length;
            final Object[] array2 = new Object[size];
            int n = 0;
            for (final Object next : collection) {
                if (indexOf(next, array, 0, length) < 0 && indexOf(next, array2, 0, n) < 0) {
                    array2[n++] = next;
                }
            }
            if (n != 0) {
                final Object[] array3 = new Object[length + n];
                System.arraycopy(array, 0, array3, 0, length);
                System.arraycopy(array2, 0, array3, length, n);
                this.setArray(array3);
            }
            return n;
        }
    }
    
    public synchronized void clear() {
        this.setArray(new Object[0]);
    }
    
    public boolean addAll(final Collection collection) {
        final int size = collection.size();
        if (size == 0) {
            return false;
        }
        synchronized (this) {
            final Object[] array = this.getArray();
            int length = array.length;
            final Object[] array2 = new Object[length + size];
            System.arraycopy(array, 0, array2, 0, length);
            final Iterator<Object> iterator = collection.iterator();
            while (iterator.hasNext()) {
                array2[length++] = iterator.next();
            }
            this.setArray(array2);
            return true;
        }
    }
    
    public boolean addAll(int n, final Collection collection) {
        final int size = collection.size();
        synchronized (this) {
            final Object[] array = this.getArray();
            final int length = array.length;
            if (n > length || n < 0) {
                throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + length);
            }
            if (size == 0) {
                return false;
            }
            final int n2 = length - n;
            Object[] copy;
            if (n2 == 0) {
                copy = copyOf(array, length + size);
            }
            else {
                copy = new Object[length + size];
                System.arraycopy(array, 0, copy, 0, n);
                System.arraycopy(array, n, copy, n + size, n2);
            }
            final Iterator<Object> iterator = collection.iterator();
            while (iterator.hasNext()) {
                copy[n++] = iterator.next();
            }
            this.setArray(copy);
            return true;
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final Object[] array = this.getArray();
        final int length = array.length;
        objectOutputStream.writeInt(length);
        for (int i = 0; i < length; ++i) {
            objectOutputStream.writeObject(array[i]);
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        final Object[] array = new Object[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = objectInputStream.readObject();
        }
        this.setArray(array);
    }
    
    public String toString() {
        final Object[] array = this.getArray();
        final int n = array.length - 1;
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i <= n; ++i) {
            sb.append(String.valueOf(array[i]));
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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
            if (!eq(listIterator.next(), listIterator2.next())) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int n = 1;
        for (final Object o : this.getArray()) {
            n = 31 * n + ((o == null) ? 0 : o.hashCode());
        }
        return n;
    }
    
    public Iterator iterator() {
        return new COWIterator(this.getArray(), 0);
    }
    
    public ListIterator listIterator() {
        return new COWIterator(this.getArray(), 0);
    }
    
    public ListIterator listIterator(final int n) {
        final int length = this.getArray().length;
        if (n < 0 || n > length) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
        return new COWIterator(this.getArray(), n);
    }
    
    public synchronized List subList(final int n, final int n2) {
        final int length = this.getArray().length;
        if (n < 0 || n2 > length || n > n2) {
            throw new IndexOutOfBoundsException();
        }
        return new COWSubList(this, n, n2);
    }
    
    private static Object[] copyOfRange(final Object[] array, final int n, final int n2, final Class clazz) {
        final int n3 = n2 - n;
        if (n3 < 0) {
            throw new IllegalArgumentException(n + " > " + n2);
        }
        final Object[] array2 = (Object[])Array.newInstance(clazz.getComponentType(), n3);
        System.arraycopy(array, n, array2, 0, Math.min(array.length - n, n3));
        return array2;
    }
    
    private static Object[] copyOf(final Object[] array, final int n, final Class clazz) {
        final Object[] array2 = (Object[])Array.newInstance(clazz.getComponentType(), n);
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    private static Object[] copyOf(final Object[] array, final int n) {
        return copyOf(array, n, array.getClass());
    }
    
    private static class COWSubListIterator implements ListIterator
    {
        private final ListIterator i;
        private final int offset;
        private final int size;
        
        private COWSubListIterator(final List list, final int n, final int offset, final int size) {
            this.offset = offset;
            this.size = size;
            this.i = list.listIterator(n + offset);
        }
        
        public boolean hasNext() {
            return this.nextIndex() < this.size;
        }
        
        public Object next() {
            if (this.hasNext()) {
                return this.i.next();
            }
            throw new NoSuchElementException();
        }
        
        public boolean hasPrevious() {
            return this.previousIndex() >= 0;
        }
        
        public Object previous() {
            if (this.hasPrevious()) {
                return this.i.previous();
            }
            throw new NoSuchElementException();
        }
        
        public int nextIndex() {
            return this.i.nextIndex() - this.offset;
        }
        
        public int previousIndex() {
            return this.i.previousIndex() - this.offset;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public void set(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        public void add(final Object o) {
            throw new UnsupportedOperationException();
        }
    }
    
    private static class COWSubList extends AbstractList
    {
        private final CopyOnWriteArrayList l;
        private final int offset;
        private int size;
        private Object[] expectedArray;
        
        private COWSubList(final CopyOnWriteArrayList l, final int offset, final int n) {
            this.l = l;
            this.expectedArray = this.l.getArray();
            this.offset = offset;
            this.size = n - offset;
        }
        
        private void checkForComodification() {
            if (this.l.getArray() != this.expectedArray) {
                throw new ConcurrentModificationException();
            }
        }
        
        private void rangeCheck(final int n) {
            if (n < 0 || n >= this.size) {
                throw new IndexOutOfBoundsException("Index: " + n + ",Size: " + this.size);
            }
        }
        
        public Object set(final int n, final Object o) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                final Object set = this.l.set(n + this.offset, o);
                this.expectedArray = this.l.getArray();
                return set;
            }
        }
        
        public Object get(final int n) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                return this.l.get(n + this.offset);
            }
        }
        
        public int size() {
            synchronized (this.l) {
                this.checkForComodification();
                return this.size;
            }
        }
        
        public void add(final int n, final Object o) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n > this.size) {
                    throw new IndexOutOfBoundsException();
                }
                this.l.add(n + this.offset, o);
                this.expectedArray = this.l.getArray();
                ++this.size;
            }
        }
        
        public void clear() {
            synchronized (this.l) {
                this.checkForComodification();
                this.l.removeRange(this.offset, this.offset + this.size);
                this.expectedArray = this.l.getArray();
                this.size = 0;
            }
        }
        
        public Object remove(final int n) {
            synchronized (this.l) {
                this.rangeCheck(n);
                this.checkForComodification();
                final Object remove = this.l.remove(n + this.offset);
                this.expectedArray = this.l.getArray();
                --this.size;
                return remove;
            }
        }
        
        public Iterator iterator() {
            synchronized (this.l) {
                this.checkForComodification();
                return new COWSubListIterator((List)this.l, 0, this.offset, this.size);
            }
        }
        
        public ListIterator listIterator(final int n) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n > this.size) {
                    throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
                }
                return new COWSubListIterator((List)this.l, n, this.offset, this.size);
            }
        }
        
        public List subList(final int n, final int n2) {
            synchronized (this.l) {
                this.checkForComodification();
                if (n < 0 || n2 > this.size) {
                    throw new IndexOutOfBoundsException();
                }
                return new COWSubList(this.l, n + this.offset, n2 + this.offset);
            }
        }
    }
    
    private static class COWIterator implements ListIterator
    {
        private final Object[] snapshot;
        private int cursor;
        
        private COWIterator(final Object[] snapshot, final int cursor) {
            this.cursor = cursor;
            this.snapshot = snapshot;
        }
        
        public boolean hasNext() {
            return this.cursor < this.snapshot.length;
        }
        
        public boolean hasPrevious() {
            return this.cursor > 0;
        }
        
        public Object next() {
            try {
                return this.snapshot[this.cursor++];
            }
            catch (IndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
        
        public Object previous() {
            try {
                final Object[] snapshot = this.snapshot;
                final int cursor = this.cursor - 1;
                this.cursor = cursor;
                return snapshot[cursor];
            }
            catch (IndexOutOfBoundsException ex) {
                throw new NoSuchElementException();
            }
        }
        
        public int nextIndex() {
            return this.cursor;
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
        
        public void add(final Object o) {
            throw new UnsupportedOperationException();
        }
    }
}
