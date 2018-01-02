// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.PluginMessage;

public class ConfigurationRequest implements PluginMessage
{
    PluginConfig config;
    
    public ConfigurationRequest(final PluginConfig config) {
        this.config = config;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof ConfigurationListener) {
            ((ConfigurationListener)pl).setConfiguration(this.config);
        }
        return null;
    }
}
