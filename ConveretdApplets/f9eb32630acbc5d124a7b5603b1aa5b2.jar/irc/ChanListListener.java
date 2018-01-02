// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public interface ChanListListener
{
    void channelAdded(final ChannelInfo p0, final ChanList p1);
    
    void channelBegin(final ChanList p0);
    
    void channelEnd(final ChanList p0);
}
