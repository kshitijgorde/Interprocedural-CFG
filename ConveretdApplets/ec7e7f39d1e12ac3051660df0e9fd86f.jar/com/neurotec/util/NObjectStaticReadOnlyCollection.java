// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.jna.ptr.HNObject;
import java.util.ArrayList;
import com.neurotec.lang.NObject;

public abstract class NObjectStaticReadOnlyCollection<E extends NObject> extends NReadOnlyCollection<E>
{
    private ArrayList<E> items;
    
    protected NObjectStaticReadOnlyCollection() {
        this(null);
    }
    
    protected NObjectStaticReadOnlyCollection(final NObject owner) {
        super(owner);
        this.refreshItems();
    }
    
    private void disposeItems(final int index, final int size) {
        if (this.getOwner() != null) {
            for (int i = 0; i < size; ++i) {
                final E item = this.items.get(index + i);
                item.setOwner(null);
                item.dispose();
            }
        }
        this.items.subList(index, index + size).clear();
    }
    
    protected void disposeItem(final int index) {
        this.disposeItems(index, 1);
    }
    
    protected void disposeItems() {
        this.disposeItems(0, this.items.size());
    }
    
    protected final void refreshItems() {
        final int oldCount = (this.items == null) ? 0 : this.items.size();
        final E[] oldItems = null;
        if (oldCount != 0) {
            this.items.toArray(oldItems);
            this.items.clear();
        }
        final NObject owner = this.getOwner();
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
                        final E item = (owner == null) ? this.fromHandleNative(hItem) : this.fromHandleWithObject(hItem, owner);
                        try {
                            this.items.add(item);
                        }
                        catch (RuntimeException e) {
                            if (owner != null) {
                                item.setOwner(null);
                                item.dispose();
                                throw e;
                            }
                        }
                    }
                }
            }
            catch (RuntimeException e2) {
                this.disposeItems();
                throw e2;
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
    
    protected final E getInternal(final int index) {
        return this.items.get(index);
    }
    
    protected final void addItem(final E item) {
        this.addItem(this.items.size(), item);
    }
    
    protected E addItem(final HNObject hItem) {
        return this.addItem(this.items.size(), hItem);
    }
    
    protected E addItem(final int index, final HNObject hItem) {
        if (hItem == null) {
            throw new NullPointerException("hItem");
        }
        final NObject owner = this.getOwner();
        final E item = (owner == null) ? this.fromHandleNative(hItem) : this.fromHandleWithObject(hItem, owner);
        try {
            this.addItem(index, item);
        }
        finally {
            if (owner != null) {
                item.setOwner(null);
                item.dispose();
            }
        }
        return item;
    }
    
    protected final void addItem(final int index, final E item) {
        if (item == null) {
            throw new NullPointerException("item");
        }
        this.items.add(index, item);
        this.touch();
    }
    
    protected final E removeItemNoDispose(final int index) {
        final E item = this.items.get(index);
        this.items.remove(index);
        this.touch();
        return item;
    }
    
    public final int size() {
        return this.items.size();
    }
    
    protected void onRefreshItem(final E item) {
    }
    
    protected abstract E fromHandleNative(final HNObject p0);
    
    protected abstract E fromHandleWithObject(final HNObject p0, final NObject p1);
    
    protected abstract HNObject getNative(final int p0);
    
    protected abstract int sizeNative();
}
