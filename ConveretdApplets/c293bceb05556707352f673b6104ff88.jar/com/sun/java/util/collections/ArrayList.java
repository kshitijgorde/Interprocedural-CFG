// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.io.Serializable;

public class ArrayList extends AbstractList implements List, Cloneable, Serializable
{
    private transient Object[] elementData;
    private int size;
    
    public ArrayList(final int n) {
        this.elementData = new Object[n];
    }
    
    public ArrayList() {
        this(10);
    }
    
    public ArrayList(final Collection collection) {
        this(collection.size() * 110 / 100);
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.elementData[this.size++] = iterator.next();
        }
    }
    
    public void trimToSize() {
        ++super.modCount;
        if (this.size < this.elementData.length) {
            System.arraycopy(this.elementData, 0, this.elementData = new Object[this.size], 0, this.size);
        }
    }
    
    public void ensureCapacity(final int n) {
        ++super.modCount;
        final int length = this.elementData.length;
        if (n > length) {
            final Object[] elementData = this.elementData;
            int n2 = length * 3 / 2 + 1;
            if (n2 < n) {
                n2 = n;
            }
            System.arraycopy(elementData, 0, this.elementData = new Object[n2], 0, this.size);
        }
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public boolean contains(final Object o) {
        return this.indexOf(o) >= 0;
    }
    
    public int indexOf(final Object o) {
        if (o == null) {
            for (int i = 0; i < this.size; ++i) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = 0; j < this.size; ++j) {
                if (o.equals(this.elementData[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public int lastIndexOf(final Object o) {
        if (o == null) {
            for (int i = this.size - 1; i >= 0; --i) {
                if (this.elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = this.size - 1; j >= 0; --j) {
                if (o.equals(this.elementData[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public Object clone() {
        try {
            final ArrayList list = (ArrayList)super.clone();
            list.elementData = new Object[this.size];
            System.arraycopy(this.elementData, 0, list.elementData, 0, this.size);
            list.modCount = 0;
            return list;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.size];
        System.arraycopy(this.elementData, 0, array, 0, this.size);
        return array;
    }
    
    public Object[] toArray(Object[] array) {
        if (array.length < this.size) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), this.size);
        }
        System.arraycopy(this.elementData, 0, array, 0, this.size);
        if (array.length > this.size) {
            array[this.size] = null;
        }
        return array;
    }
    
    public Object get(final int n) {
        this.RangeCheck(n);
        return this.elementData[n];
    }
    
    public Object set(final int n, final Object o) {
        this.RangeCheck(n);
        final Object o2 = this.elementData[n];
        this.elementData[n] = o;
        return o2;
    }
    
    public boolean add(final Object o) {
        this.ensureCapacity(this.size + 1);
        this.elementData[this.size++] = o;
        return true;
    }
    
    public void add(final int n, final Object o) {
        if (n > this.size || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
        }
        this.ensureCapacity(this.size + 1);
        System.arraycopy(this.elementData, n, this.elementData, n + 1, this.size - n);
        this.elementData[n] = o;
        ++this.size;
    }
    
    public Object remove(final int n) {
        this.RangeCheck(n);
        ++super.modCount;
        final Object o = this.elementData[n];
        final int n2 = this.size - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.elementData, n + 1, this.elementData, n, n2);
        }
        this.elementData[--this.size] = null;
        return o;
    }
    
    public void clear() {
        ++super.modCount;
        for (int i = 0; i < this.size; ++i) {
            this.elementData[i] = null;
        }
        this.size = 0;
    }
    
    public boolean addAll(final Collection collection) {
        ++super.modCount;
        final int size = collection.size();
        this.ensureCapacity(this.size + size);
        final Iterator iterator = collection.iterator();
        for (int i = 0; i < size; ++i) {
            this.elementData[this.size++] = iterator.next();
        }
        return size != 0;
    }
    
    public boolean addAll(int n, final Collection collection) {
        if (n > this.size || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
        }
        final int size = collection.size();
        this.ensureCapacity(this.size + size);
        final int n2 = this.size - n;
        if (n2 > 0) {
            System.arraycopy(this.elementData, n, this.elementData, n + size, n2);
        }
        final Iterator iterator = collection.iterator();
        for (int i = 0; i < size; ++i) {
            this.elementData[n++] = iterator.next();
        }
        this.size += size;
        return size != 0;
    }
    
    protected void removeRange(final int n, final int n2) {
        ++super.modCount;
        System.arraycopy(this.elementData, n2, this.elementData, n, this.size - n2);
        while (this.size != this.size - (n2 - n)) {
            this.elementData[--this.size] = null;
        }
    }
    
    private void RangeCheck(final int n) {
        if (n >= this.size || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
        }
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.elementData.length);
        for (int i = 0; i < this.size; ++i) {
            objectOutputStream.writeObject(this.elementData[i]);
        }
    }
    
    private synchronized void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.elementData = new Object[objectInputStream.readInt()];
        for (int i = 0; i < this.size; ++i) {
            this.elementData[i] = objectInputStream.readObject();
        }
    }
}
