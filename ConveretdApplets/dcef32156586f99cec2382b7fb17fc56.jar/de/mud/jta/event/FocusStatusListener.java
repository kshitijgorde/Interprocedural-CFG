// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.Plugin;
import de.mud.jta.PluginListener;

public interface FocusStatusListener extends PluginListener
{
    void pluginGainedFocus(final Plugin p0);
    
    void pluginLostFocus(final Plugin p0);
}
