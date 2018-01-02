// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.jna.ptr.HNObject;
import java.util.ArrayList;
import com.neurotec.lang.NObject;

public abstract class NObjectFlatReadOnlyCollection<E extends NObject> extends NReadOnlyCollection<E>
{
    private boolean isFromHandle;
    private ArrayList<E> items;
    
    protected NObjectFlatReadOnlyCollection(final NObject owner, final boolean isFromHandle) {
        super(owner);
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
    
    protected final E getInternal(final int index) {
        return this.items.get(index);
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
                if (oldItem2 != null) {}
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
        this.items.add(index, item);
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
    
    protected abstract HNObject getNative(final int p0);
    
    protected abstract E fromHandleNative(final HNObject p0);
    
    protected abstract int sizeNative();
}
