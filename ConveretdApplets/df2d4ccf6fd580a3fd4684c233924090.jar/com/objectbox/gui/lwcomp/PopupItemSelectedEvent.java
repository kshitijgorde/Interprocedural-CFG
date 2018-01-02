// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.Component;
import java.util.EventObject;

public class PopupItemSelectedEvent extends EventObject
{
    protected Component component;
    
    public PopupItemSelectedEvent(final Object o) {
        super(o);
        this.component = null;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public void setComponent(final Component component) {
        this.component = component;
    }
}
