// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class TerminalTypeRequest implements PluginMessage
{
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof TerminalTypeListener) {
            final Object ret = ((TerminalTypeListener)pl).getTerminalType();
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }
}
