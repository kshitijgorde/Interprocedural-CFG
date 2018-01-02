// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import java.lang.reflect.Array;
import com.neurotec.lang.NObject;

public abstract class NArrayCollection<E, EBase> extends NCollection<E[]>
{
    private NCollection<EBase> baseCollection;
    private boolean isRemoveRange;
    private boolean isAdd;
    private boolean isAddWithIndex;
    private boolean isRemoveByObject;
    
    protected NArrayCollection(final NObject owner, final NCollection<EBase> baseCollection, final boolean isRemoveRange, final boolean isAdd, final boolean isAddWithIndex, final boolean isRemoveByObject) {
        super(owner);
        this.baseCollection = baseCollection;
        this.isRemoveRange = isRemoveRange;
        this.isAdd = isAdd;
        this.isAddWithIndex = isAddWithIndex;
        this.isRemoveByObject = isRemoveByObject;
    }
    
    protected final int addInternal(final E[] value) {
        throw new UnsupportedOperationException();
    }
    
    protected final void addInternal(final int index, final E[] value) {
        throw new UnsupportedOperationException();
    }
    
    protected final void clearInternal() {
        throw new UnsupportedOperationException();
    }
    
    protected final E[] getInternal(final int baseIndex) {
        return this.toElementArray(baseIndex);
    }
    
    protected final E[] removeInternal(final int index) {
        throw new UnsupportedOperationException();
    }
    
    protected final void removeRangeInternal(final int index, final int count) {
        throw new UnsupportedOperationException();
    }
    
    protected final E[] setInternal(final int index, final E[] value) {
        throw new UnsupportedOperationException();
    }
    
    protected void onSetItem(final int baseIndex, final int index) {
    }
    
    protected void onInsertItem(final int baseIndex, final int index) {
    }
    
    protected void onRemoveItemRange(final int baseIndex, final int index, final int count) {
    }
    
    protected final void touch() {
        this.baseCollection.touch();
    }
    
    public final int addElement(final int baseIndex, final E value) {
        int index;
        if (this.isAdd) {
            index = this.elementSize(baseIndex);
            this.addNative(baseIndex, value);
        }
        else {
            if (!this.isAddWithIndex) {
                throw new UnsupportedOperationException();
            }
            index = this.addWithIndexNative(baseIndex, value);
        }
        if (index != -1) {
            this.onInsertItem(baseIndex, index);
            this.touch();
        }
        return index;
    }
    
    public final void addElement(final int baseIndex, final int index, final E value) {
        this.addNative(baseIndex, index, value);
        this.onInsertItem(baseIndex, index);
        this.touch();
    }
    
    public final void clearElement(final int baseIndex) {
        final int count = this.elementSize(baseIndex);
        this.clearNative(baseIndex);
        this.onRemoveItemRange(baseIndex, 0, count);
        this.touch();
    }
    
    public final E getElement(final int baseIndex, final int index) {
        return this.getNative(baseIndex, index);
    }
    
    public final int indexOf(final Object item) {
        throw new UnsupportedOperationException();
    }
    
    public int indexOfElement(final int baseIndex, final E value) {
        throw new UnsupportedOperationException();
    }
    
    public boolean containsElement(final int baseIndex, final E value) {
        return this.indexOfElement(baseIndex, value) != -1;
    }
    
    public final void removeElement(final int baseIndex, final int index) {
        this.removeNative(baseIndex, index);
        this.onRemoveItemRange(baseIndex, index, 1);
        this.touch();
    }
    
    public int removeElement(final int baseIndex, final E value) {
        int index;
        if (!this.isRemoveByObject) {
            index = this.indexOfElement(baseIndex, value);
            if (index != -1) {
                this.removeElement(baseIndex, index);
            }
        }
        else {
            index = this.removeNative(baseIndex, value);
            if (index != -1) {
                this.onRemoveItemRange(baseIndex, index, 1);
                this.touch();
            }
        }
        return index;
    }
    
    public final void removeElementRange(final int baseIndex, final int index, final int count) {
        if (this.isRemoveRange) {
            this.removeRangeNative(baseIndex, index, count);
            this.onRemoveItemRange(baseIndex, index, count);
            this.touch();
        }
        else if (index == 0 && count == this.elementSize(baseIndex)) {
            this.clearElement(baseIndex);
        }
        else if (count == 1) {
            this.removeElement(baseIndex, index);
        }
        else {
            if (index < 0) {
                throw new IllegalArgumentException("index is less than zero");
            }
            if (count < 0) {
                throw new IllegalArgumentException("count is less than zero");
            }
            if (index + count > this.elementSize(baseIndex)) {
                throw new IllegalArgumentException("index plus count is greater than GetCount(baseIndex)");
            }
            for (int i = count - 1; i >= 0; --i) {
                this.removeNative(baseIndex, index + i);
            }
            this.onRemoveItemRange(baseIndex, index, count);
            this.touch();
        }
    }
    
    public final void setElement(final int baseIndex, final int index, final E value) {
        this.setNative(baseIndex, index, value);
        this.onSetItem(baseIndex, index);
        this.touch();
    }
    
    public final int elementSize(final int baseIndex) {
        return this.sizeNative(baseIndex);
    }
    
    public final int size() {
        return this.baseCollection.size();
    }
    
    public E[] toElementArray(final int baseIndex) {
        return this.getAllNative(baseIndex);
    }
    
    public <T> T[] toElementArray(final int baseIndex, T[] a) {
        final int size = this.elementSize(baseIndex);
        if (a.length < size) {
            a = (T[])Array.newInstance(a.getClass().getComponentType(), size);
        }
        final E[] el = this.toElementArray(baseIndex);
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
    
    protected abstract void addNative(final int p0, final E p1);
    
    protected abstract void addNative(final int p0, final int p1, final E p2);
    
    protected abstract int addWithIndexNative(final int p0, final E p1);
    
    protected abstract void clearNative(final int p0);
    
    protected abstract E[] getAllNative(final int p0);
    
    protected abstract E getNative(final int p0, final int p1);
    
    protected abstract void removeNative(final int p0, final int p1);
    
    protected abstract int removeNative(final int p0, final E p1);
    
    protected abstract void removeRangeNative(final int p0, final int p1, final int p2);
    
    protected abstract void setNative(final int p0, final int p1, final E p2);
    
    protected abstract int sizeNative(final int p0);
}
