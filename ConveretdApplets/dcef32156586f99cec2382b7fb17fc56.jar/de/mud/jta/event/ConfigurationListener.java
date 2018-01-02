// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginConfig;
import de.mud.jta.PluginListener;

public interface ConfigurationListener extends PluginListener
{
    void setConfiguration(final PluginConfig p0);
}
