// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class SavePropertiesEventMulticaster extends AWTEventMulticaster implements SavePropertiesListener
{
    protected SavePropertiesEventMulticaster(final SavePropertiesListener savePropertiesListener, final SavePropertiesListener savePropertiesListener2) {
        super(savePropertiesListener, savePropertiesListener2);
    }
    
    public static SavePropertiesListener add(final SavePropertiesListener savePropertiesListener, final SavePropertiesListener savePropertiesListener2) {
        if (savePropertiesListener == null) {
            return savePropertiesListener2;
        }
        if (savePropertiesListener2 == null) {
            return savePropertiesListener;
        }
        return new SavePropertiesEventMulticaster(savePropertiesListener, savePropertiesListener2);
    }
    
    public void onSaveProperties(final SavePropertiesEvent savePropertiesEvent) {
        ((SavePropertiesListener)super.a).onSaveProperties(savePropertiesEvent);
        ((SavePropertiesListener)super.b).onSaveProperties(savePropertiesEvent);
    }
    
    public static SavePropertiesListener remove(final SavePropertiesListener savePropertiesListener, final SavePropertiesListener savePropertiesListener2) {
        return (SavePropertiesListener)AWTEventMulticaster.removeInternal(savePropertiesListener, savePropertiesListener2);
    }
}
