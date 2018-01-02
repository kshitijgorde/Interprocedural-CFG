// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

public class DataChainChannelListEntry
{
    private AbstractDataChannel m_channel;
    private int m_processedDownstreamPackets;
    private Object m_internalSynchronization;
    private int m_processedUpstreamPackets;
    
    public DataChainChannelListEntry(final AbstractDataChannel channel) {
        this.m_channel = channel;
        this.m_processedDownstreamPackets = 0;
        this.m_processedUpstreamPackets = 0;
        this.m_internalSynchronization = new Object();
    }
    
    public AbstractDataChannel getChannel() {
        return this.m_channel;
    }
    
    public int getProcessedDownstreamPackets() {
        synchronized (this.m_internalSynchronization) {
            return this.m_processedDownstreamPackets;
        }
    }
    
    public void incProcessedDownstreamPackets() {
        synchronized (this.m_internalSynchronization) {
            ++this.m_processedDownstreamPackets;
        }
    }
    
    public int getProcessedUpstreamPackets() {
        synchronized (this.m_internalSynchronization) {
            return this.m_processedUpstreamPackets;
        }
    }
    
    public void incProcessedUpstreamPackets() {
        synchronized (this.m_internalSynchronization) {
            ++this.m_processedUpstreamPackets;
        }
    }
}
