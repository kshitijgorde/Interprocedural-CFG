// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import logging.LogHolder;
import logging.LogType;
import anon.client.crypto.MixCipherChain;

public class SimulatedLimitedDataChannel extends AbstractDataChannel implements Runnable
{
    private static final short FLAG_CHANNEL_DATA = 0;
    private static final short FLAG_CHANNEL_CLOSE = 1;
    private static final short FLAG_CHANNEL_OPEN = 8;
    private Object m_internalSynchronization;
    private boolean m_channelOpened;
    private int m_downstreamPackets;
    private long m_channelTimeout;
    private int m_receivedPackets;
    Thread m_timeoutSupervisionThread;
    Object m_timeoutSynchronization;
    private volatile boolean m_channelClosed;
    
    public SimulatedLimitedDataChannel(final int n, final Multiplexer multiplexer, final AbstractDataChain abstractDataChain, final MixCipherChain mixCipherChain, final int downstreamPackets, final long channelTimeout) {
        super(n, multiplexer, abstractDataChain, mixCipherChain);
        this.m_internalSynchronization = new Object();
        this.m_channelOpened = false;
        this.m_downstreamPackets = downstreamPackets;
        this.m_channelTimeout = channelTimeout;
        this.m_receivedPackets = 0;
        this.m_timeoutSupervisionThread = null;
        this.m_timeoutSynchronization = new Object();
        this.m_channelClosed = false;
    }
    
    public void organizeChannelClose() {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_channelOpened) {
                this.deleteChannel();
                this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(2, null));
            }
        }
    }
    
    public boolean processSendOrder(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_channelOpened) {
                synchronized (this.m_timeoutSynchronization) {
                    (this.m_timeoutSupervisionThread = new Thread(this, "SimulatedLimitedDataChannel: Channel-timeout supervisor thread")).setDaemon(true);
                    this.m_timeoutSupervisionThread.start();
                }
                this.createAndSendMixPacket(dataChainSendOrderStructure, (short)8);
                return this.m_channelOpened = true;
            }
            return false;
        }
    }
    
    public void multiplexerClosed() {
        synchronized (this.m_timeoutSynchronization) {
            if (!this.m_channelClosed) {
                LogHolder.log(3, LogType.NET, "SimulatedLimitedDataChannel: multiplexerClosed(): Multiplexer closed before channel has received all packets.");
                this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(3, null));
                this.m_channelClosed = true;
                this.m_timeoutSynchronization.notify();
            }
        }
    }
    
    protected void handleReceivedPacket(final MixPacket mixPacket) {
        ++this.m_receivedPackets;
        synchronized (this.m_timeoutSynchronization) {
            if (!this.m_channelClosed) {
                if ((mixPacket.getChannelFlags() & 0x1) == 0x1) {
                    if (this.m_receivedPackets < this.m_downstreamPackets) {
                        LogHolder.log(1, LogType.NET, "SimulatedLimitedDataChannel: handleReceivedPacket(): Some packets are missing on channel.");
                        this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(3, null));
                    }
                    this.m_channelClosed = true;
                    this.m_timeoutSynchronization.notify();
                }
                else if (this.m_receivedPackets >= this.m_downstreamPackets) {
                    LogHolder.log(1, LogType.NET, "SimulatedLimitedDataChannel: handleReceivedPacket(): More packets on channel received than allowed.");
                    this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(3, null));
                    this.m_channelClosed = true;
                    this.m_timeoutSynchronization.notify();
                }
                else {
                    this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(1, mixPacket.getPayloadData()));
                }
            }
        }
    }
    
    public void run() {
        synchronized (this.m_timeoutSynchronization) {
            try {
                this.m_timeoutSynchronization.wait(this.m_channelTimeout);
            }
            catch (InterruptedException ex) {}
            if (!this.m_channelClosed) {
                LogHolder.log(1, LogType.NET, "SimulatedLimitedDataChannel: run(): Channel-timeout occured.");
                this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(3, null));
            }
            this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(2, null));
        }
        this.deleteChannel();
    }
}
