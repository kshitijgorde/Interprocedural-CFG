// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.listbox;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class ItemTextChangedEventMulticaster extends AWTEventMulticaster implements ItemTextChangedListener
{
    protected ItemTextChangedEventMulticaster(final ItemTextChangedListener itemTextChangedListener, final ItemTextChangedListener itemTextChangedListener2) {
        super(itemTextChangedListener, itemTextChangedListener2);
    }
    
    public static ItemTextChangedListener add(final ItemTextChangedListener itemTextChangedListener, final ItemTextChangedListener itemTextChangedListener2) {
        if (itemTextChangedListener == null) {
            return itemTextChangedListener2;
        }
        if (itemTextChangedListener2 == null) {
            return itemTextChangedListener;
        }
        return new ItemTextChangedEventMulticaster(itemTextChangedListener, itemTextChangedListener2);
    }
    
    public void onItemTextChanged(final ItemTextChangedEvent itemTextChangedEvent) {
        ((ItemTextChangedListener)super.a).onItemTextChanged(itemTextChangedEvent);
        ((ItemTextChangedListener)super.b).onItemTextChanged(itemTextChangedEvent);
    }
    
    public static ItemTextChangedListener remove(final ItemTextChangedListener itemTextChangedListener, final ItemTextChangedListener itemTextChangedListener2) {
        return (ItemTextChangedListener)AWTEventMulticaster.removeInternal(itemTextChangedListener, itemTextChangedListener2);
    }
}
