// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.event;

import com.neurotec.plugins.NPlugin;
import java.util.EventObject;

public class NPluginEvent extends EventObject
{
    private NPlugin plugin;
    
    public NPluginEvent(final Object source, final NPlugin plugin) {
        super(source);
        this.plugin = plugin;
    }
    
    public final NPlugin getPlugin() {
        return this.plugin;
    }
}
