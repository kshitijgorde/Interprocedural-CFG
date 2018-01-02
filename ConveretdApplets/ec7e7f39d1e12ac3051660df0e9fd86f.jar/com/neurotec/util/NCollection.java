// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.lang.NObject;
import java.util.AbstractList;

public abstract class NCollection<E> extends AbstractList<E>
{
    private NObject owner;
    
    protected NCollection(final NObject owner) {
        this.owner = owner;
    }
    
    void touch() {
        ++this.modCount;
    }
    
    protected final NObject getOwner() {
        return this.owner;
    }
    
    protected abstract E getInternal(final int p0);
    
    protected abstract int addInternal(final E p0);
    
    protected abstract void addInternal(final int p0, final E p1);
    
    protected abstract E setInternal(final int p0, final E p1);
    
    protected abstract E removeInternal(final int p0);
    
    protected int removeInternal(final E value) {
        final int index = this.indexOf(value);
        if (index != -1) {
            this.removeInternal(index);
        }
        return index;
    }
    
    protected abstract void removeRangeInternal(final int p0, final int p1);
    
    protected abstract void clearInternal();
    
    public final int addEx(final E e) {
        final int result = this.addInternal(e);
        if (result != -1) {
            this.touch();
        }
        return result;
    }
    
    public final int remove2(final Object e) {
        final int index = this.removeInternal(e);
        if (index != -1) {
            this.touch();
        }
        return index;
    }
    
    public final boolean add(final E e) {
        return this.addEx(e) != -1;
    }
    
    public final void add(final int index, final E element) {
        this.addInternal(index, element);
        this.touch();
    }
    
    public final void clear() {
        this.clearInternal();
        this.touch();
    }
    
    public final E get(final int index) {
        return this.getInternal(index);
    }
    
    public final E remove(final int index) {
        final E item = this.removeInternal(index);
        this.touch();
        return item;
    }
    
    public final boolean remove(final Object e) {
        return this.remove2(e) != -1;
    }
    
    protected final void removeRange(final int fromIndex, final int toIndex) {
        this.removeRangeInternal(fromIndex, toIndex);
        this.touch();
    }
    
    public final E set(final int index, final E element) {
        final E item = this.setInternal(index, element);
        this.touch();
        return item;
    }
}
