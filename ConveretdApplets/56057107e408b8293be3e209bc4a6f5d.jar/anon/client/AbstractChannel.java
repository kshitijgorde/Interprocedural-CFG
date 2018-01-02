// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;

public abstract class AbstractChannel
{
    private int m_channelId;
    protected Multiplexer m_parentMultiplexer;
    private volatile boolean m_channelOpen;
    private Object m_internalSynchronization;
    
    public AbstractChannel(final int channelId, final Multiplexer parentMultiplexer) {
        this.m_channelId = channelId;
        this.m_parentMultiplexer = parentMultiplexer;
        this.m_channelOpen = true;
        this.m_internalSynchronization = new Object();
    }
    
    public MixPacket createEmptyMixPacket() {
        return new MixPacket(this.m_channelId);
    }
    
    public void sendPacket(final MixPacket mixPacket) throws IOException {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_channelOpen) {
                throw new ChannelClosedException("AbstractChannel: sendPacket(): The channel is already closed.");
            }
            this.m_parentMultiplexer.sendPacket(mixPacket);
        }
    }
    
    public boolean isClosed() {
        return !this.m_channelOpen;
    }
    
    public void deleteChannel() {
        synchronized (this.m_internalSynchronization) {
            if (this.m_channelOpen) {
                this.m_parentMultiplexer.getChannelTable().removeChannel(this.m_channelId);
                this.m_channelOpen = false;
            }
        }
    }
    
    public void multiplexerClosed() {
    }
    
    public abstract void processReceivedPacket(final MixPacket p0);
}
