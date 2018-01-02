// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import java.io.IOException;
import java.net.UnknownHostException;
import de.mud.jta.PluginListener;

public interface SocketListener extends PluginListener
{
    void connect(final String p0, final int p1) throws UnknownHostException, IOException;
    
    void disconnect() throws IOException;
}
