// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.EventObject;

public class SavePropertiesEvent extends EventObject
{
    private String name;
    
    public SavePropertiesEvent(final Object o) {
        super(o);
        this.name = "default";
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
