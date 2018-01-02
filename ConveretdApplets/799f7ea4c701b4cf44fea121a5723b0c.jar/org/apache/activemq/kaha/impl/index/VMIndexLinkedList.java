// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import org.apache.activemq.kaha.StoreEntry;

public final class VMIndexLinkedList implements Cloneable, IndexLinkedList
{
    private transient IndexItem root;
    private transient int size;
    
    public VMIndexLinkedList(final IndexItem header) {
        this.root = header;
        final IndexItem root = this.root;
        final IndexItem root2 = this.root;
        final IndexItem root3 = this.root;
        root2.prev = root3;
        root.next = root3;
    }
    
    @Override
    public void setRoot(final IndexItem newRoot) {
        this.root = newRoot;
    }
    
    @Override
    public synchronized IndexItem getRoot() {
        return this.root;
    }
    
    @Override
    public synchronized IndexItem getFirst() {
        if (this.size == 0) {
            return null;
        }
        return this.root.next;
    }
    
    @Override
    public synchronized IndexItem getLast() {
        if (this.size == 0) {
            return null;
        }
        return this.root.prev;
    }
    
    @Override
    public synchronized StoreEntry removeFirst() {
        if (this.size == 0) {
            return null;
        }
        final StoreEntry result = this.root.next;
        this.remove(this.root.next);
        return result;
    }
    
    @Override
    public synchronized Object removeLast() {
        if (this.size == 0) {
            return null;
        }
        final StoreEntry result = this.root.prev;
        this.remove(this.root.prev);
        return result;
    }
    
    @Override
    public synchronized void addFirst(final IndexItem item) {
        this.addBefore(item, this.root.next);
    }
    
    @Override
    public synchronized void addLast(final IndexItem item) {
        this.addBefore(item, this.root);
    }
    
    @Override
    public synchronized int size() {
        return this.size;
    }
    
    @Override
    public synchronized boolean isEmpty() {
        return this.size == 0;
    }
    
    @Override
    public synchronized boolean add(final IndexItem item) {
        this.addBefore(item, this.root);
        return true;
    }
    
    @Override
    public synchronized void clear() {
        final IndexItem root = this.root;
        final IndexItem root2 = this.root;
        final IndexItem root3 = this.root;
        root2.prev = root3;
        root.next = root3;
        this.size = 0;
    }
    
    @Override
    public synchronized IndexItem get(final int index) {
        return this.entry(index);
    }
    
    @Override
    public synchronized void add(final int index, final IndexItem element) {
        this.addBefore(element, (index == this.size) ? this.root : this.entry(index));
    }
    
    @Override
    public synchronized Object remove(final int index) {
        final IndexItem e = this.entry(index);
        this.remove(e);
        return e;
    }
    
    private IndexItem entry(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        IndexItem e = this.root;
        if (index < this.size / 2) {
            for (int i = 0; i <= index; ++i) {
                e = e.next;
            }
        }
        else {
            for (int i = this.size; i > index; --i) {
                e = e.prev;
            }
        }
        return e;
    }
    
    @Override
    public synchronized int indexOf(final StoreEntry o) {
        int index = 0;
        for (IndexItem e = this.root.next; e != this.root; e = e.next) {
            if (o == e) {
                return index;
            }
            ++index;
        }
        return -1;
    }
    
    @Override
    public synchronized IndexItem getNextEntry(final IndexItem entry) {
        return (entry.next != this.root) ? entry.next : null;
    }
    
    @Override
    public synchronized IndexItem getPrevEntry(final IndexItem entry) {
        return (entry.prev != this.root) ? entry.prev : null;
    }
    
    public synchronized void addBefore(final IndexItem insert, final IndexItem e) {
        insert.next = e;
        insert.prev = e.prev;
        insert.prev.next = insert;
        insert.next.prev = insert;
        ++this.size;
    }
    
    @Override
    public synchronized void remove(final IndexItem e) {
        if (e == this.root || e.equals(this.root)) {
            return;
        }
        e.prev.next = e.next;
        e.next.prev = e.prev;
        --this.size;
    }
    
    public synchronized Object clone() {
        final IndexLinkedList clone = new VMIndexLinkedList(this.root);
        for (IndexItem e = this.root.next; e != this.root; e = e.next) {
            clone.add(e);
        }
        return clone;
    }
    
    @Override
    public synchronized StoreEntry getEntry(final StoreEntry current) {
        return current;
    }
    
    @Override
    public synchronized StoreEntry refreshEntry(final StoreEntry current) {
        return current;
    }
}
