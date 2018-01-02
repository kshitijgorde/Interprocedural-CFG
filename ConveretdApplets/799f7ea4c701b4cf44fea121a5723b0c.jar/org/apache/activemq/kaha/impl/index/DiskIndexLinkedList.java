// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.IOException;
import org.apache.activemq.kaha.StoreEntry;

public class DiskIndexLinkedList implements IndexLinkedList
{
    protected IndexManager indexManager;
    protected transient IndexItem root;
    protected transient IndexItem last;
    protected transient int size;
    
    public DiskIndexLinkedList(final IndexManager im, final IndexItem header) {
        this.indexManager = im;
        this.root = header;
    }
    
    @Override
    public synchronized IndexItem getRoot() {
        return this.root;
    }
    
    @Override
    public void setRoot(final IndexItem e) {
        this.root = e;
    }
    
    @Override
    public synchronized IndexItem getFirst() {
        if (this.size == 0) {
            return null;
        }
        return this.getNextEntry(this.root);
    }
    
    @Override
    public synchronized IndexItem getLast() {
        if (this.size == 0) {
            return null;
        }
        if (this.last != null) {
            this.last.next = null;
            this.last.setNextItem(-1L);
        }
        return this.last;
    }
    
    @Override
    public synchronized StoreEntry removeFirst() {
        if (this.size == 0) {
            return null;
        }
        final IndexItem result = this.getNextEntry(this.root);
        this.remove(result);
        return result;
    }
    
    @Override
    public synchronized Object removeLast() {
        if (this.size == 0) {
            return null;
        }
        final StoreEntry result = this.last;
        this.remove(this.last);
        return result;
    }
    
    @Override
    public synchronized void addFirst(final IndexItem item) {
        if (this.size == 0) {
            this.last = item;
        }
        ++this.size;
    }
    
    @Override
    public synchronized void addLast(final IndexItem item) {
        ++this.size;
        this.last = item;
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
        this.addLast(item);
        return true;
    }
    
    @Override
    public synchronized void clear() {
        this.last = null;
        this.size = 0;
    }
    
    @Override
    public synchronized IndexItem get(final int index) {
        return this.entry(index);
    }
    
    @Override
    public synchronized void add(final int index, final IndexItem element) {
        if (index == this.size) {
            this.last = element;
        }
        ++this.size;
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
        for (int i = 0; i <= index; ++i) {
            e = this.getNextEntry(e);
        }
        if (e != null && this.last != null && this.last.equals(e)) {
            this.last = e;
        }
        return e;
    }
    
    @Override
    public synchronized int indexOf(final StoreEntry o) {
        int index = 0;
        if (this.size > 0) {
            for (IndexItem e = this.getNextEntry(this.root); e != null; e = this.getNextEntry(e)) {
                if (o.equals(e)) {
                    return index;
                }
                ++index;
            }
        }
        return -1;
    }
    
    @Override
    public synchronized IndexItem getNextEntry(IndexItem current) {
        IndexItem result = null;
        if (current != null) {
            current = (IndexItem)this.refreshEntry(current);
            if (current.getNextItem() >= 0L) {
                try {
                    result = this.indexManager.getIndex(current.getNextItem());
                }
                catch (IOException e) {
                    throw new RuntimeException("Failed to get next index from " + this.indexManager + " for " + current, e);
                }
            }
        }
        if (result != null && this.last != null && this.last.equals(result)) {
            this.last = result;
        }
        return result;
    }
    
    @Override
    public synchronized IndexItem getPrevEntry(IndexItem current) {
        IndexItem result = null;
        if (current != null && current.getPreviousItem() >= 0L) {
            current = (IndexItem)this.refreshEntry(current);
            try {
                result = this.indexManager.getIndex(current.getPreviousItem());
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to  get current index for " + current, e);
            }
        }
        if (result != null && this.root != null && this.root.equals(result)) {
            return null;
        }
        return result;
    }
    
    @Override
    public synchronized StoreEntry getEntry(final StoreEntry current) {
        StoreEntry result = null;
        if (current != null && current.getOffset() >= 0L) {
            try {
                result = this.indexManager.getIndex(current.getOffset());
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to index", e);
            }
        }
        if (result != null && this.root != null && this.root.equals(result)) {
            return this.root;
        }
        return result;
    }
    
    @Override
    public synchronized StoreEntry refreshEntry(final StoreEntry current) {
        StoreEntry result = null;
        if (current != null && current.getOffset() >= 0L) {
            try {
                result = this.indexManager.refreshIndex((IndexItem)current);
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to index", e);
            }
        }
        if (result != null && this.root != null && this.root.equals(result)) {
            return this.root;
        }
        return result;
    }
    
    @Override
    public synchronized void remove(final IndexItem e) {
        if (e == null || e == this.root || e.equals(this.root)) {
            return;
        }
        if (e == this.last || e.equals(this.last)) {
            if (this.size > 1) {
                this.last = (IndexItem)this.refreshEntry(this.last);
                this.last = this.getPrevEntry(this.last);
            }
            else {
                this.last = null;
            }
        }
        --this.size;
    }
}
