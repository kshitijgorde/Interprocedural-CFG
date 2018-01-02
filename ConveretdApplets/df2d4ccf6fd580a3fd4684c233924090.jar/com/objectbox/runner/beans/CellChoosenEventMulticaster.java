// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class CellChoosenEventMulticaster extends AWTEventMulticaster implements CellChoosenListener
{
    protected CellChoosenEventMulticaster(final CellChoosenListener cellChoosenListener, final CellChoosenListener cellChoosenListener2) {
        super(cellChoosenListener, cellChoosenListener2);
    }
    
    public static CellChoosenListener add(final CellChoosenListener cellChoosenListener, final CellChoosenListener cellChoosenListener2) {
        if (cellChoosenListener == null) {
            return cellChoosenListener2;
        }
        if (cellChoosenListener2 == null) {
            return cellChoosenListener;
        }
        return new CellChoosenEventMulticaster(cellChoosenListener, cellChoosenListener2);
    }
    
    public void cellChoosen(final CellChoosenEvent cellChoosenEvent) {
        ((CellChoosenListener)super.a).cellChoosen(cellChoosenEvent);
        ((CellChoosenListener)super.b).cellChoosen(cellChoosenEvent);
    }
    
    public static CellChoosenListener remove(final CellChoosenListener cellChoosenListener, final CellChoosenListener cellChoosenListener2) {
        return (CellChoosenListener)AWTEventMulticaster.removeInternal(cellChoosenListener, cellChoosenListener2);
    }
}
