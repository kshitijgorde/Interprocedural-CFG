// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.loader;

import java.util.EventListener;

public interface StateChangeListener extends EventListener
{
    void onChange(final StateChangeEvent p0);
}
