// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class CellselectedEventMulticaster extends AWTEventMulticaster implements CellselectedListener
{
    protected CellselectedEventMulticaster(final CellselectedListener cellselectedListener, final CellselectedListener cellselectedListener2) {
        super(cellselectedListener, cellselectedListener2);
    }
    
    public static CellselectedListener add(final CellselectedListener cellselectedListener, final CellselectedListener cellselectedListener2) {
        if (cellselectedListener == null) {
            return cellselectedListener2;
        }
        if (cellselectedListener2 == null) {
            return cellselectedListener;
        }
        return new CellselectedEventMulticaster(cellselectedListener, cellselectedListener2);
    }
    
    public void cellselected(final CellselectedEvent cellselectedEvent) {
        ((CellselectedListener)super.a).cellselected(cellselectedEvent);
        ((CellselectedListener)super.b).cellselected(cellselectedEvent);
    }
    
    public static CellselectedListener remove(final CellselectedListener cellselectedListener, final CellselectedListener cellselectedListener2) {
        return (CellselectedListener)AWTEventMulticaster.removeInternal(cellselectedListener, cellselectedListener2);
    }
}
