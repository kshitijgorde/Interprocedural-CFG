// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface ServerProtocolListener
{
    void replyReceived(final String p0, final String p1, final String[] p2);
    
    void messageReceived(final String p0, final String p1, final String[] p2);
    
    void connected(final String p0);
    
    void connectionFailed(final String p0, final String p1);
    
    void disconnected(final String p0);
}
