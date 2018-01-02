// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface ServerListener
{
    void serverConnected(final Server p0);
    
    void serverDisconnected(final Server p0);
    
    void serverLeft(final Server p0);
    
    String[] cannotUseRequestedNicknames(final Server p0);
    
    void sourceCreated(final Source p0, final Server p1, final Boolean p2);
    
    void sourceRemoved(final Source p0, final Server p1);
    
    Object specialServerRequest(final String p0, final Server p1, final Object[] p2);
}
