// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.event;

import java.io.IOException;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginMessage;

public class TelnetCommandRequest implements PluginMessage
{
    byte cmd;
    
    public TelnetCommandRequest(final byte command) {
        this.cmd = command;
    }
    
    public Object firePluginMessage(final PluginListener pl) {
        if (pl instanceof TelnetCommandListener) {
            try {
                ((TelnetCommandListener)pl).sendTelnetCommand(this.cmd);
            }
            catch (IOException io) {
                System.err.println("io exception caught:" + io);
                io.printStackTrace();
            }
        }
        return null;
    }
}
