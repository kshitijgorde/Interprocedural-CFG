// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.util.Properties;

public class PluginConfig extends Properties
{
    public PluginConfig(final Properties props) {
        super(props);
    }
    
    public String getProperty(final String plugin, String id, final String key) {
        if (id == null) {
            id = "";
        }
        else {
            id = "(" + id + ")";
        }
        String result = this.getProperty(plugin + id, key);
        if (result == null) {
            result = this.getProperty(plugin, key);
        }
        return result;
    }
    
    public String getProperty(final String plugin, final String key) {
        return this.getProperty(plugin + "." + key);
    }
}
