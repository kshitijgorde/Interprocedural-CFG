// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import java.io.IOException;
import de.mud.jta.PluginListener;

public interface TelnetCommandListener extends PluginListener
{
    void sendTelnetCommand(final byte p0) throws IOException;
}
