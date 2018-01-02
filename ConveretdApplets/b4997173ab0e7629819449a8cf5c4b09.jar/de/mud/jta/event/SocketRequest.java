// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class SocketRequest implements PluginMessage
{
    String host;
    int port;
    
    public SocketRequest() {
        this.host = null;
    }
    
    public SocketRequest(final String host, final int port) {
        this.host = host;
        this.port = port;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof SocketListener) {
            try {
                if (this.host != null) {
                    ((SocketListener)pl).connect(this.host, this.port);
                }
                else {
                    ((SocketListener)pl).disconnect();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
