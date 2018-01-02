// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

public class FixedRatioChannelsDescription
{
    private int m_downstreamPackets;
    private long m_channelTimeout;
    private long m_chainTimeout;
    
    public FixedRatioChannelsDescription(final int downstreamPackets, final long channelTimeout, final long chainTimeout) {
        this.m_downstreamPackets = downstreamPackets;
        this.m_channelTimeout = channelTimeout;
        this.m_chainTimeout = chainTimeout;
    }
    
    public int getChannelDownstreamPackets() {
        return this.m_downstreamPackets;
    }
    
    public long getChannelTimeout() {
        return this.m_channelTimeout;
    }
    
    public long getChainTimeout() {
        return this.m_chainTimeout;
    }
}
