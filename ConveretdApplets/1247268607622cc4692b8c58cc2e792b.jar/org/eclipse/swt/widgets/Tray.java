// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

public class Tray extends Widget
{
    int itemCount;
    TrayItem[] items;
    
    Tray(Display display, final int n) {
        this.items = new TrayItem[4];
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        this.display = display;
        this.reskinWidget();
    }
    
    void createItem(final TrayItem trayItem, final int n) {
        if (n < 0 || n > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final TrayItem[] items = new TrayItem[this.items.length + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        System.arraycopy(this.items, n, this.items, n + 1, this.itemCount++ - n);
        this.items[n] = trayItem;
    }
    
    void destroyItem(final TrayItem trayItem) {
        int n;
        for (n = 0; n < this.itemCount && this.items[n] != trayItem; ++n) {}
        if (n == this.itemCount) {
            return;
        }
        System.arraycopy(this.items, n + 1, this.items, n, --this.itemCount - n);
        this.items[this.itemCount] = null;
    }
    
    public TrayItem getItem(final int n) {
        this.checkWidget();
        if (n < 0 || n >= this.itemCount) {
            this.error(6);
        }
        return this.items[n];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.itemCount;
    }
    
    public TrayItem[] getItems() {
        this.checkWidget();
        final TrayItem[] array = new TrayItem[this.itemCount];
        System.arraycopy(this.items, 0, array, 0, array.length);
        return array;
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TrayItem trayItem = this.items[i];
                if (trayItem != null && !trayItem.isDisposed()) {
                    trayItem.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.display.tray == this) {
            this.display.tray = null;
        }
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TrayItem trayItem = this.items[i];
                if (trayItem != null) {
                    trayItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
}
