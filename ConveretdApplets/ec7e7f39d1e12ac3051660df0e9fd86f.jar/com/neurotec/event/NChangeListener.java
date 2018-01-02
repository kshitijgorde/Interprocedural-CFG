// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.event;

import java.util.EventObject;
import java.util.EventListener;

public interface NChangeListener extends EventListener
{
    void changing(final EventObject p0);
    
    void changed(final EventObject p0);
}
