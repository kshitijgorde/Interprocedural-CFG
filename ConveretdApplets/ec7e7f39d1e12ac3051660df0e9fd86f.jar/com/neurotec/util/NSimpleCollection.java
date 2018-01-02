// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import java.lang.reflect.Array;
import com.neurotec.lang.NObject;

public abstract class NSimpleCollection<E> extends NCollection<E>
{
    private boolean isRemoveRange;
    private boolean isAddWithIndex;
    private boolean isRemoveByObject;
    
    protected NSimpleCollection(final NObject owner, final boolean isRemoveRange, final boolean isAddWithIndex, final boolean isRemoveByObject) {
        super(owner);
        this.isRemoveRange = isRemoveRange;
        this.isAddWithIndex = isAddWithIndex;
        this.isRemoveByObject = isRemoveByObject;
    }
    
    protected final int addInternal(final E value) {
        int index;
        if (this.isAddWithIndex) {
            index = this.addWithIndexNative(value);
        }
        else {
            index = this.size();
            this.addNative(value);
        }
        this.onInsertItem(index);
        return index;
    }
    
    protected final void addInternal(final int index, final E value) {
        if (this.isAddWithIndex) {
            throw new UnsupportedOperationException();
        }
        this.addNative(index, value);
        if (index != -1) {
            this.onInsertItem(index);
        }
    }
    
    protected final void clearInternal() {
        final int count = this.size();
        this.clearNative();
        this.onRemoveItemRange(0, count);
    }
    
    protected final E getInternal(final int index) {
        return this.getNative(index);
    }
    
    protected final E removeInternal(final int index) {
        final E value = this.getNative(index);
        this.removeNative(index);
        this.onRemoveItemRange(index, 1);
        return value;
    }
    
    protected final int removeInternal(final E value) {
        if (!this.isRemoveByObject) {
            return super.removeInternal(value);
        }
        final int index = this.removeNative(value);
        if (index != -1) {
            this.onRemoveItemRange(index, 1);
        }
        return index;
    }
    
    protected final void removeRangeInternal(final int fromIndex, final int toIndex) {
        final int count = toIndex - fromIndex;
        if (this.isRemoveRange) {
            this.removeRangeNative(fromIndex, count);
            this.onRemoveItemRange(fromIndex, count);
        }
        else if (fromIndex == 0 && count == this.size()) {
            this.clear();
        }
        else if (count == 1) {
            this.remove(fromIndex);
        }
        else {
            for (int i = count - 1; i >= 0; --i) {
                this.removeNative(fromIndex + i);
            }
            this.onRemoveItemRange(fromIndex, count);
        }
    }
    
    protected final E setInternal(final int index, final E value) {
        final E element = this.getNative(index);
        this.setNative(index, value);
        this.onSetItem(index);
        return element;
    }
    
    protected void onSetItem(final int index) {
    }
    
    protected void onInsertItem(final int index) {
    }
    
    protected void onRemoveItemRange(final int index, final int count) {
    }
    
    public final int indexOf(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public final int size() {
        return this.sizeNative();
    }
    
    public final void ensureCapacity(final int minCapacity) {
        final int value = this.getCapacityNative();
        if (value < minCapacity) {
            this.setCapacityNative(minCapacity);
        }
    }
    
    public final void trimToSize() {
        final int s = this.size();
        if (this.getCapacityNative() > s) {
            this.setCapacityNative(s);
        }
    }
    
    public Object[] toArray() {
        return this.getAllNative();
    }
    
    public <T> T[] toArray(T[] a) {
        final int size = this.size();
        if (a.length < size) {
            a = (T[])Array.newInstance(a.getClass().getComponentType(), size);
        }
        final E[] el = this.getAllNative();
        for (int i = 0; i < a.length; ++i) {
            if (i < size) {
                a[i] = (T)el[i];
            }
            else {
                a[i] = null;
            }
        }
        return a;
    }
    
    protected abstract void addNative(final E p0);
    
    protected abstract void addNative(final int p0, final E p1);
    
    protected abstract int addWithIndexNative(final E p0);
    
    protected abstract void clearNative();
    
    protected abstract int getCapacityNative();
    
    protected abstract E getNative(final int p0);
    
    protected abstract E[] getAllNative();
    
    protected abstract void removeNative(final int p0);
    
    protected abstract int removeNative(final E p0);
    
    protected abstract void removeRangeNative(final int p0, final int p1);
    
    protected abstract void setCapacityNative(final int p0);
    
    protected abstract void setNative(final int p0, final E p1);
    
    protected abstract int sizeNative();
}
