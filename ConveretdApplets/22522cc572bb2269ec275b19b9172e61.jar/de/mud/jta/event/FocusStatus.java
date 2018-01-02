// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import java.awt.event.FocusEvent;
import de.mud.jta.Plugin;
import de.mud.jta.PluginMessage;

public class FocusStatus implements PluginMessage
{
    protected Plugin plugin;
    protected FocusEvent event;
    
    public FocusStatus(final Plugin plugin, final FocusEvent event) {
        this.plugin = plugin;
        this.event = event;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof FocusStatusListener) {
            switch (this.event.getID()) {
                case 1004: {
                    ((FocusStatusListener)pl).pluginGainedFocus(this.plugin);
                    break;
                }
                case 1005: {
                    ((FocusStatusListener)pl).pluginLostFocus(this.plugin);
                    break;
                }
            }
        }
        return null;
    }
}
