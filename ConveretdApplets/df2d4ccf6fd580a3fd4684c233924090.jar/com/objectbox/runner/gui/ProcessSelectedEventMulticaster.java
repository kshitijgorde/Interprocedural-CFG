// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class ProcessSelectedEventMulticaster extends AWTEventMulticaster implements ProcessSelectedListener
{
    protected ProcessSelectedEventMulticaster(final ProcessSelectedListener processSelectedListener, final ProcessSelectedListener processSelectedListener2) {
        super(processSelectedListener, processSelectedListener2);
    }
    
    public static ProcessSelectedListener add(final ProcessSelectedListener processSelectedListener, final ProcessSelectedListener processSelectedListener2) {
        if (processSelectedListener == null) {
            return processSelectedListener2;
        }
        if (processSelectedListener2 == null) {
            return processSelectedListener;
        }
        return new ProcessSelectedEventMulticaster(processSelectedListener, processSelectedListener2);
    }
    
    public void onProcessSelected(final ProcessSelectedEvent processSelectedEvent) {
        ((ProcessSelectedListener)super.a).onProcessSelected(processSelectedEvent);
        ((ProcessSelectedListener)super.b).onProcessSelected(processSelectedEvent);
    }
    
    public static ProcessSelectedListener remove(final ProcessSelectedListener processSelectedListener, final ProcessSelectedListener processSelectedListener2) {
        return (ProcessSelectedListener)AWTEventMulticaster.removeInternal(processSelectedListener, processSelectedListener2);
    }
}
