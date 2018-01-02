// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.interfaces;

import java.net.Socket;
import java.io.IOException;
import irc.com.messages.Message;

public interface ServerProcess
{
    void disconnect();
    
    void enqueueMessage(final Message p0) throws IOException;
    
    Socket getSocket();
}
