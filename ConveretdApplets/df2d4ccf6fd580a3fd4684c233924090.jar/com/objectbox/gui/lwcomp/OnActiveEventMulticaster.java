// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class OnActiveEventMulticaster extends AWTEventMulticaster implements OnActiveListener
{
    protected OnActiveEventMulticaster(final OnActiveListener onActiveListener, final OnActiveListener onActiveListener2) {
        super(onActiveListener, onActiveListener2);
    }
    
    public static OnActiveListener add(final OnActiveListener onActiveListener, final OnActiveListener onActiveListener2) {
        if (onActiveListener == null) {
            return onActiveListener2;
        }
        if (onActiveListener2 == null) {
            return onActiveListener;
        }
        return new OnActiveEventMulticaster(onActiveListener, onActiveListener2);
    }
    
    public void onActive(final OnActiveEvent onActiveEvent) {
        ((OnActiveListener)super.a).onActive(onActiveEvent);
        ((OnActiveListener)super.b).onActive(onActiveEvent);
    }
    
    public static OnActiveListener remove(final OnActiveListener onActiveListener, final OnActiveListener onActiveListener2) {
        return (OnActiveListener)AWTEventMulticaster.removeInternal(onActiveListener, onActiveListener2);
    }
}
