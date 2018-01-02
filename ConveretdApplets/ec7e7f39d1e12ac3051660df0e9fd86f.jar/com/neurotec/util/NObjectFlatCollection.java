// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.jna.ptr.HNObject;
import java.util.ArrayList;
import com.neurotec.lang.NObject;

public abstract class NObjectFlatCollection<E extends NObject> extends NCollection<E>
{
    private boolean isRemoveRange;
    private boolean isAdd;
    private boolean isFromHandle;
    private ArrayList<E> items;
    
    protected NObjectFlatCollection(final NObject owner, final boolean isRemoveRange, final boolean isAdd, final boolean isFromHandle) {
        super(owner);
        this.isRemoveRange = isRemoveRange;
        this.isAdd = isAdd;
        if (!(this.isFromHandle = isFromHandle)) {
            final int count = this.sizeNative();
            this.items = new ArrayList<E>(count);
        }
        else {
            this.refreshItems();
        }
    }
    
    private void disposeItems(final int index, final int count) {
        this.items.subList(index, index + count).clear();
    }
    
    private void disposeItem(final int index) {
        this.disposeItems(index, 1);
    }
    
    protected final int addInternal(final E value) {
        if (!this.isAdd) {
            throw new UnsupportedOperationException();
        }
        if (value == null) {
            throw new NullPointerException();
        }
        final int index = this.items.size();
        this.addNative(value);
        final E newItem = this.fromHandleNative(value.getHandle());
        this.onInsertItem(index, newItem);
        return index;
    }
    
    protected final void addInternal(final int index, final E value) {
        if (!this.isAdd) {
            throw new UnsupportedOperationException();
        }
        if (value == null) {
            throw new NullPointerException();
        }
        this.addNative(index, value);
        final E newItem = this.fromHandleNative(value.getHandle());
        this.onInsertItem(index, newItem);
    }
    
    protected final void clearInternal() {
        final int count = this.items.size();
        this.clearNative();
        this.onRemoveItemRange(0, count);
    }
    
    protected final E getInternal(final int index) {
        return this.items.get(index);
    }
    
    protected final E removeInternal(final int index) {
        final E value = this.fromHandleNative(this.getNative(index));
        this.removeNative(index);
        this.onRemoveItemRange(index, 1);
        return value;
    }
    
    protected final void removeRangeInternal(final int index, final int count) {
        if (this.isRemoveRange) {
            this.removeRangeNative(index, count);
            this.onRemoveItemRange(index, count);
        }
        else if (index == 0 && count == this.items.size()) {
            this.clear();
        }
        else if (count == 1) {
            this.remove(index);
        }
        else {
            if (index < 0) {
                throw new IllegalArgumentException("index is less than zero");
            }
            if (count < 0) {
                throw new IllegalArgumentException("count is less than zero");
            }
            if (index + count > this.items.size()) {
                throw new IllegalArgumentException("index plus count is greater than Count");
            }
            for (int i = count - 1; i >= 0; --i) {
                this.removeNative(index + i);
            }
            this.onRemoveItemRange(index, count);
        }
    }
    
    protected final E setInternal(final int index, final E value) {
        throw new UnsupportedOperationException();
    }
    
    protected void onInsertItem(final int index, final E item) {
        this.items.add(index, item);
    }
    
    protected void onRemoveItemRange(final int index, final int count) {
        this.disposeItems(index, count);
    }
    
    protected final void onRefreshItem(final E item) {
    }
    
    protected final void refreshItems() {
        if (!this.isFromHandle) {
            throw new UnsupportedOperationException();
        }
        final int oldCount = (this.items == null) ? 0 : this.items.size();
        final E[] oldItems = null;
        if (oldCount != 0) {
            this.items.toArray(oldItems);
            this.items.clear();
        }
        try {
            final int count = this.sizeNative();
            if (this.items == null) {
                this.items = new ArrayList<E>(count);
            }
            else {
                this.items.ensureCapacity(count);
            }
            try {
                for (int i = 0; i != count; ++i) {
                    boolean isOldItem = false;
                    final HNObject hItem = this.getNative(i);
                    for (int j = 0; j != oldCount; ++j) {
                        final E oldItem = oldItems[j];
                        if (oldItem != null && oldItem.getHandle().equals(hItem)) {
                            this.items.add(oldItem);
                            oldItems[j] = null;
                            isOldItem = true;
                            this.onRefreshItem(oldItem);
                            break;
                        }
                    }
                    if (!isOldItem) {
                        final E item = this.fromHandleNative(hItem);
                        this.items.add(item);
                    }
                }
            }
            catch (RuntimeException e) {
                this.disposeItems();
                throw e;
            }
        }
        finally {
            for (int k = 0; k != oldCount; ++k) {
                final E oldItem2 = oldItems[k];
                if (oldItem2 != null) {
                    oldItem2.setOwner(null);
                    oldItem2.dispose();
                }
            }
        }
    }
    
    protected final void addItem(final E item) {
        this.insertItem(this.items.size(), item);
    }
    
    protected final void insertItem(final int index, final E item) {
        if (item == null) {
            throw new NullPointerException("item");
        }
        this.onInsertItem(index, item);
        this.touch();
    }
    
    protected final void removeItem(final int index) {
        this.disposeItem(index);
        this.touch();
    }
    
    protected final void removeItemRange(final int index, final int count) {
        this.disposeItems(index, count);
        this.touch();
    }
    
    protected final void clearItems() {
        this.disposeItems();
        this.touch();
    }
    
    protected void disposeItems() {
        this.disposeItems(0, this.items.size());
    }
    
    public final boolean contains(final Object o) {
        return this.indexOf(o) != -1;
    }
    
    public final int indexOf(final Object obj) {
        return this.items.indexOf(obj);
    }
    
    public final int size() {
        return this.items.size();
    }
    
    protected abstract void addNative(final E p0);
    
    protected abstract void addNative(final int p0, final E p1);
    
    protected abstract void clearNative();
    
    protected abstract HNObject getNative(final int p0);
    
    protected abstract E fromHandleNative(final HNObject p0);
    
    protected abstract void removeNative(final int p0);
    
    protected abstract void removeRangeNative(final int p0, final int p1);
    
    protected abstract int sizeNative();
}
