// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class StateChangeEventMulticaster extends AWTEventMulticaster implements StateChangeListener
{
    protected StateChangeEventMulticaster(final StateChangeListener stateChangeListener, final StateChangeListener stateChangeListener2) {
        super(stateChangeListener, stateChangeListener2);
    }
    
    public static StateChangeListener add(final StateChangeListener stateChangeListener, final StateChangeListener stateChangeListener2) {
        if (stateChangeListener == null) {
            return stateChangeListener2;
        }
        if (stateChangeListener2 == null) {
            return stateChangeListener;
        }
        return new StateChangeEventMulticaster(stateChangeListener, stateChangeListener2);
    }
    
    public void onChange(final StateChangeEvent stateChangeEvent) {
        ((StateChangeListener)super.a).onChange(stateChangeEvent);
        ((StateChangeListener)super.b).onChange(stateChangeEvent);
    }
    
    public static StateChangeListener remove(final StateChangeListener stateChangeListener, final StateChangeListener stateChangeListener2) {
        return (StateChangeListener)AWTEventMulticaster.removeInternal(stateChangeListener, stateChangeListener2);
    }
}
