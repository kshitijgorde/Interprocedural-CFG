// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.interfaces;

import irc.com.messages.Message;

public interface ClientProcess
{
    void disconnect();
    
    void notifyDisconnect();
    
    void processMessage(final Message p0);
}
