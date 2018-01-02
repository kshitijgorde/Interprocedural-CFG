// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.jna.ptr.HNObject;
import java.util.ArrayList;
import com.neurotec.lang.NObject;

public abstract class NObjectReadOnlyCollection<E extends NObject> extends NReadOnlyCollection<E>
{
    private ArrayList<E> items;
    
    protected NObjectReadOnlyCollection(final NObject owner) {
        this(owner, true);
    }
    
    protected NObjectReadOnlyCollection(final NObject owner, final boolean refresh) {
        super(owner);
        if (refresh) {
            this.refreshItems();
        }
    }
    
    private void disposeItems(final int index, final int count) {
        for (int i = 0; i < count; ++i) {
            final E item = this.items.get(index + i);
            item.setOwner(null);
            item.dispose();
        }
        this.items.subList(index, index + count).clear();
    }
    
    private void disposeItem(final int index) {
        this.disposeItems(index, 1);
    }
    
    protected final E getInternal(final int index) {
        return this.items.get(index);
    }
    
    protected void onInsertItem(final int index, final E item) {
        this.items.add(index, item);
    }
    
    protected void onRemoveItemRange(final int index, final int count) {
        this.disposeItems(index, count);
    }
    
    protected void onRefreshItem(final E item) {
    }
    
    protected final void refreshItems() {
        final int oldCount = (this.items == null) ? 0 : this.items.size();
        final E[] oldItems = (E[])new NObject[10];
        if (oldCount != 0) {
            this.items.toArray(oldItems);
            this.items.clear();
        }
        try {
            final int count = this.sizeNative();
            if (this.items == null) {
                this.items = new ArrayList<E>(count);
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
                        try {
                            item.setOwner(this.getOwner());
                            this.items.add(item);
                        }
                        catch (RuntimeException e) {
                            item.setOwner(null);
                            item.dispose();
                            throw e;
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
    
    protected final E addItem(final HNObject hItem) {
        return this.addItem(this.items.size(), hItem);
    }
    
    protected final void addItem(final E item) {
        this.addItem(this.items.size(), item);
    }
    
    protected final E addItem(final int index, final HNObject hItem) {
        if (hItem == null) {
            throw new NullPointerException("item");
        }
        final E item = this.fromHandleNative(hItem);
        try {
            item.setOwner(this.getOwner());
            this.addItem(index, item);
        }
        catch (RuntimeException e) {
            item.setOwner(null);
            item.dispose();
            throw e;
        }
        return item;
    }
    
    protected final void addItem(final int index, final E item) {
        if (item == null) {
            throw new NullPointerException("item");
        }
        this.onInsertItem(index, item);
        this.touch();
    }
    
    protected final E removeItemNoDispose(final int index) {
        final E item = this.items.get(index);
        this.items.remove(index);
        this.touch();
        return item;
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
    
    public int indexOf(final HNObject handle) {
        for (int c = this.items.size(), i = 0; i < c; ++i) {
            if (this.items.get(i).getHandle().equals(handle)) {
                return i;
            }
        }
        return -1;
    }
    
    public E get(final HNObject handle) {
        final int index = this.indexOf(handle);
        return (E)((index == -1) ? null : ((E)this.items.get(index)));
    }
    
    public Object getSyncRoot() {
        return this;
    }
    
    public final int size() {
        return this.items.size();
    }
    
    protected abstract HNObject getNative(final int p0);
    
    protected abstract E fromHandleNative(final HNObject p0);
    
    protected abstract int sizeNative();
}
