// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface ChannelListener extends SourceListener
{
    void nickSet(final String[] p0, final String[] p1, final Channel p2);
    
    void nickJoin(final String p0, final String p1, final Channel p2);
    
    void nickQuit(final String p0, final String p1, final Channel p2);
    
    void nickPart(final String p0, final String p1, final Channel p2);
    
    void nickKick(final String p0, final String p1, final String p2, final Channel p3);
    
    void topicChanged(final String p0, final String p1, final Channel p2);
    
    void modeApply(final String p0, final String p1, final Channel p2);
    
    void nickModeApply(final String p0, final String p1, final String p2, final Channel p3);
    
    void nickChanged(final String p0, final String p1, final Channel p2);
    
    void nickWhoisUpdated(final String p0, final String p1, final Channel p2);
}
