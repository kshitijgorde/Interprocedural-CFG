// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class OnlineStatus implements PluginMessage
{
    protected boolean online;
    
    public OnlineStatus(final boolean online) {
        this.online = online;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof OnlineStatusListener) {
            if (this.online) {
                ((OnlineStatusListener)pl).online();
            }
            else {
                ((OnlineStatusListener)pl).offline();
            }
        }
        return null;
    }
}
