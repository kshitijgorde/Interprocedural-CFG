// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.event.ItemListener;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.Rectangle;
import ji.io.h;
import ji.util.d;
import java.awt.Component;
import java.util.Vector;
import java.awt.List;

public class fu extends List implements fm
{
    private String a;
    Vector b;
    
    public fu(final String a) {
        this.b = new Vector();
        this.a = a;
    }
    
    public void releaseResources() {
        if (this.b != null) {
            this.b.removeAllElements();
        }
    }
    
    public int getTotalRowHeight() {
        return 0;
    }
    
    public boolean isSwing() {
        return false;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public int getRows() {
        return super.getItemCount();
    }
    
    public void makeVisible(final int n) {
    }
    
    public boolean isAdjustingValue() {
        return false;
    }
    
    public void beginBulkAdd() {
    }
    
    public void finishBulkAdd() {
    }
    
    public void clear() {
        if (!d.em() || !d.ck(this.a)) {
            if (d.dv()) {
                h.e(null, "jiListImpl 1...");
            }
            super.clear();
            if (d.dv()) {
                h.e(null, "jiListImpl 2...");
            }
        }
    }
    
    public Rectangle getItemBounds(final int n) {
        return null;
    }
    
    public void select(final int n) {
        this.select(n, true);
    }
    
    public void select(final int n, final boolean b) {
        super.select(n);
        if (n > -1 && b) {
            final ItemListener[] itemListeners = this.getItemListeners();
            for (int i = 0; i < itemListeners.length; ++i) {
                if (itemListeners[i] != null) {
                    itemListeners[i].itemStateChanged(new ItemEvent(this, 701, this.getItem(n), 1));
                }
            }
        }
    }
    
    public void deselect(final int n) {
        super.deselect(n);
        if (n > -1) {
            final ItemListener[] itemListeners = this.getItemListeners();
            for (int i = 0; i < itemListeners.length; ++i) {
                if (itemListeners[i] != null) {
                    itemListeners[i].itemStateChanged(new ItemEvent(this, 701, this.getItem(n), 2));
                }
            }
        }
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.b.addElement(itemListener);
        super.addItemListener(itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.b.removeElement(itemListener);
        super.removeItemListener(itemListener);
    }
    
    public ItemListener[] getItemListeners() {
        final ItemListener[] array = new ItemListener[this.b.size()];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = (ItemListener)this.b.elementAt(i);
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        return array;
    }
}
