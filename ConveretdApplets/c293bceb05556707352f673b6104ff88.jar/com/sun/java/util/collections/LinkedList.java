// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.io.Serializable;

public class LinkedList extends AbstractSequentialList implements List, Cloneable, Serializable
{
    private transient Entry header;
    private transient int size;
    
    public LinkedList() {
        this.header = new Entry(null, null, null);
        final Entry header = this.header;
        final Entry header2 = this.header;
        final Entry header3 = this.header;
        header2.previous = header3;
        header.next = header3;
    }
    
    public LinkedList(final Collection collection) {
        this();
        this.addAll(collection);
    }
    
    public Object getFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.header.next.element;
    }
    
    public Object getLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.header.previous.element;
    }
    
    public Object removeFirst() {
        final Object element = this.header.next.element;
        this.remove(this.header.next);
        return element;
    }
    
    public Object removeLast() {
        final Object element = this.header.previous.element;
        this.remove(this.header.previous);
        return element;
    }
    
    public void addFirst(final Object o) {
        this.addBefore(o, this.header.next);
    }
    
    public void addLast(final Object o) {
        this.addBefore(o, this.header);
    }
    
    public boolean contains(final Object o) {
        return this.indexOf(o) != -1;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean add(final Object o) {
        this.addBefore(o, this.header);
        return true;
    }
    
    public boolean remove(final Object o) {
        if (o == null) {
            for (Entry entry = this.header.next; entry != this.header; entry = entry.next) {
                if (entry.element == null) {
                    this.remove(entry);
                    return true;
                }
            }
        }
        else {
            for (Entry entry2 = this.header.next; entry2 != this.header; entry2 = entry2.next) {
                if (o.equals(entry2.element)) {
                    this.remove(entry2);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean addAll(final Collection collection) {
        return this.addAll(this.size, collection);
    }
    
    public boolean addAll(final int n, final Collection collection) {
        final int size = collection.size();
        if (size == 0) {
            return false;
        }
        ++super.modCount;
        final Entry entry = (n == this.size) ? this.header : this.entry(n);
        Entry previous = entry.previous;
        final Iterator iterator = collection.iterator();
        for (int i = 0; i < size; ++i) {
            final Entry next = new Entry(iterator.next(), entry, previous);
            previous.next = next;
            previous = next;
        }
        entry.previous = previous;
        this.size += size;
        return true;
    }
    
    public void clear() {
        ++super.modCount;
        final Entry header = this.header;
        final Entry header2 = this.header;
        final Entry header3 = this.header;
        header2.previous = header3;
        header.next = header3;
        this.size = 0;
    }
    
    public Object get(final int n) {
        return this.entry(n).element;
    }
    
    public Object set(final int n, final Object element) {
        final Entry entry = this.entry(n);
        final Object element2 = entry.element;
        entry.element = element;
        return element2;
    }
    
    public void add(final int n, final Object o) {
        this.addBefore(o, (n == this.size) ? this.header : this.entry(n));
    }
    
    public Object remove(final int n) {
        final Entry entry = this.entry(n);
        this.remove(entry);
        return entry.element;
    }
    
    private Entry entry(final int n) {
        if (n < 0 || n >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.size);
        }
        Entry entry = this.header;
        if (n < this.size / 2) {
            for (int i = 0; i <= n; ++i) {
                entry = entry.next;
            }
        }
        else {
            for (int j = this.size; j > n; --j) {
                entry = entry.previous;
            }
        }
        return entry;
    }
    
    public int indexOf(final Object o) {
        int n = 0;
        if (o == null) {
            for (Entry entry = this.header.next; entry != this.header; entry = entry.next) {
                if (entry.element == null) {
                    return n;
                }
                ++n;
            }
        }
        else {
            for (Entry entry2 = this.header.next; entry2 != this.header; entry2 = entry2.next) {
                if (o.equals(entry2.element)) {
                    return n;
                }
                ++n;
            }
        }
        return -1;
    }
    
    public int lastIndexOf(final Object o) {
        int size = this.size;
        if (o == null) {
            for (Entry entry = this.header.previous; entry != this.header; entry = entry.previous) {
                --size;
                if (entry.element == null) {
                    return size;
                }
            }
        }
        else {
            for (Entry entry2 = this.header.previous; entry2 != this.header; entry2 = entry2.previous) {
                --size;
                if (o.equals(entry2.element)) {
                    return size;
                }
            }
        }
        return -1;
    }
    
    public ListIterator listIterator(final int n) {
        return (ListIterator)new LinkedList.ListItr(this, n);
    }
    
    private Entry addBefore(final Object o, final Entry entry) {
        final Entry entry2 = new Entry(o, entry, entry.previous);
        entry2.previous.next = entry2;
        entry2.next.previous = entry2;
        ++this.size;
        ++super.modCount;
        return entry2;
    }
    
    private void remove(final Entry entry) {
        if (entry == this.header) {
            throw new NoSuchElementException();
        }
        entry.previous.next = entry.next;
        entry.next.previous = entry.previous;
        --this.size;
        ++super.modCount;
    }
    
    public Object clone() {
        return new LinkedList(this);
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.size];
        int n = 0;
        for (Entry entry = this.header.next; entry != this.header; entry = entry.next) {
            array[n++] = entry.element;
        }
        return array;
    }
    
    public Object[] toArray(Object[] array) {
        if (array.length < this.size) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), this.size);
        }
        int n = 0;
        for (Entry entry = this.header.next; entry != this.header; entry = entry.next) {
            array[n++] = entry.element;
        }
        if (array.length > this.size) {
            array[this.size] = null;
        }
        return array;
    }
    
    private synchronized void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        for (Entry entry = this.header.next; entry != this.header; entry = entry.next) {
            objectOutputStream.writeObject(entry.element);
        }
    }
    
    private synchronized void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        this.header = new Entry(null, null, null);
        final Entry header = this.header;
        final Entry header2 = this.header;
        final Entry header3 = this.header;
        header2.previous = header3;
        header.next = header3;
        for (int i = 0; i < int1; ++i) {
            this.add(objectInputStream.readObject());
        }
    }
    
    private static class Entry
    {
        Object element;
        Entry next;
        Entry previous;
        
        Entry(final Object element, final Entry next, final Entry previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
}
