// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.util.EventObject;

public class OnActiveEvent extends EventObject
{
    boolean exited;
    
    public OnActiveEvent(final Object o) {
        super(o);
        this.exited = false;
    }
    
    public OnActiveEvent(final Object o, final boolean exited) {
        super(o);
        this.exited = false;
        this.exited = exited;
    }
}
