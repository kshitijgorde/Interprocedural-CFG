// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class LocalEchoRequest implements PluginMessage
{
    protected boolean xecho;
    
    public LocalEchoRequest(final boolean echo) {
        this.xecho = false;
        this.xecho = echo;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof LocalEchoListener) {
            ((LocalEchoListener)pl).setLocalEcho(this.xecho);
        }
        return null;
    }
}
