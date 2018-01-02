// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import java.util.concurrent.locks.Lock;
import com.neurotec.lang.NObject;
import java.util.concurrent.locks.ReentrantLock;
import java.util.AbstractList;

public abstract class NReadOnlyCollection<E> extends AbstractList<E>
{
    private ReentrantLock lock;
    private NObject owner;
    
    protected NReadOnlyCollection(final NObject owner) {
        this.lock = new ReentrantLock();
        this.owner = owner;
    }
    
    void touch() {
        ++this.modCount;
    }
    
    protected final NObject getOwner() {
        return this.owner;
    }
    
    public final Lock getLock() {
        return this.lock;
    }
    
    public final boolean add(final E e) {
        throw new UnsupportedOperationException();
    }
    
    public final void add(final int index, final E element) {
        throw new UnsupportedOperationException();
    }
    
    public final void clear() {
        throw new UnsupportedOperationException();
    }
    
    public final E remove(final int index) {
        throw new UnsupportedOperationException();
    }
    
    protected final void removeRange(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException();
    }
    
    public final E get(final int index) {
        return this.getInternal(index);
    }
    
    public final E set(final int index, final E element) {
        throw new UnsupportedOperationException();
    }
    
    protected abstract E getInternal(final int p0);
}
