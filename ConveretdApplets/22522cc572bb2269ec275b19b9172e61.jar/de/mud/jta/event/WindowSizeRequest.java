// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class WindowSizeRequest implements PluginMessage
{
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof WindowSizeListener) {
            final Object ret = ((WindowSizeListener)pl).getWindowSize();
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }
}
