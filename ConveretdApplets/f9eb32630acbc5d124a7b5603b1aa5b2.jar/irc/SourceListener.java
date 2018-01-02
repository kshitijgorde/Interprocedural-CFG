// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface SourceListener
{
    void messageReceived(final String p0, final String p1, final Source p2);
    
    void reportReceived(final String p0, final Source p1);
    
    void noticeReceived(final String p0, final String p1, final Source p2);
    
    void action(final String p0, final String p1, final Source p2);
    
    void clear(final Source p0);
}
