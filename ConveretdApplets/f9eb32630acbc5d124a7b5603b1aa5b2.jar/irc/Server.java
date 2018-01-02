// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Enumeration;

public interface Server
{
    void say(final String p0, final String p1);
    
    void execute(final String p0);
    
    void sendStatusMessage(final String p0);
    
    String getNick();
    
    String getUserName();
    
    String getServerName();
    
    void connect();
    
    void disconnect();
    
    boolean isConnected();
    
    Enumeration getSources();
    
    void enumerateSourcesAsCreated(final ServerListener p0);
    
    void enumerateSourcesAsRemoved(final ServerListener p0);
    
    void setDefaultSource(final Source p0);
    
    void addServerListener(final ServerListener p0);
    
    void removeServerListener(final ServerListener p0);
    
    void leave();
}
