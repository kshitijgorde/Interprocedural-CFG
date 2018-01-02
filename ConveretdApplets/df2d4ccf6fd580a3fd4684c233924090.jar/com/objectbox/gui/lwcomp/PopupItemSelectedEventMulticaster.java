// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class PopupItemSelectedEventMulticaster extends AWTEventMulticaster implements PopupItemSelectedListener
{
    protected PopupItemSelectedEventMulticaster(final PopupItemSelectedListener popupItemSelectedListener, final PopupItemSelectedListener popupItemSelectedListener2) {
        super(popupItemSelectedListener, popupItemSelectedListener2);
    }
    
    public static PopupItemSelectedListener add(final PopupItemSelectedListener popupItemSelectedListener, final PopupItemSelectedListener popupItemSelectedListener2) {
        if (popupItemSelectedListener == null) {
            return popupItemSelectedListener2;
        }
        if (popupItemSelectedListener2 == null) {
            return popupItemSelectedListener;
        }
        return new PopupItemSelectedEventMulticaster(popupItemSelectedListener, popupItemSelectedListener2);
    }
    
    public void handlePopupSelection(final PopupItemSelectedEvent popupItemSelectedEvent) {
        ((PopupItemSelectedListener)super.a).handlePopupSelection(popupItemSelectedEvent);
        ((PopupItemSelectedListener)super.b).handlePopupSelection(popupItemSelectedEvent);
    }
    
    public static PopupItemSelectedListener remove(final PopupItemSelectedListener popupItemSelectedListener, final PopupItemSelectedListener popupItemSelectedListener2) {
        return (PopupItemSelectedListener)AWTEventMulticaster.removeInternal(popupItemSelectedListener, popupItemSelectedListener2);
    }
}
