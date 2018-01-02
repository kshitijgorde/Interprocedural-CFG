// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import logging.LogHolder;
import logging.LogType;
import anon.client.crypto.MixCipherChain;

public class UnlimitedDataChannel extends AbstractDataChannel
{
    private static final short FLAG_CHANNEL_DATA = 0;
    private static final short FLAG_CHANNEL_CLOSE = 1;
    private static final short FLAG_CHANNEL_OPEN = 8;
    private Object m_internalSynchronization;
    private boolean m_channelOpened;
    
    public UnlimitedDataChannel(final int n, final Multiplexer multiplexer, final AbstractDataChain abstractDataChain, final MixCipherChain mixCipherChain) {
        super(n, multiplexer, abstractDataChain, mixCipherChain);
        this.m_internalSynchronization = new Object();
        this.m_channelOpened = false;
    }
    
    public void organizeChannelClose() {
        synchronized (this.m_internalSynchronization) {
            if (this.m_channelOpened) {
                final DataChainSendOrderStructure dataChainSendOrderStructure = new DataChainSendOrderStructure(null);
                this.createAndSendMixPacket(dataChainSendOrderStructure, (short)1);
                synchronized (dataChainSendOrderStructure.getSynchronizationObject()) {
                    if (!dataChainSendOrderStructure.isProcessingDone()) {
                        try {
                            dataChainSendOrderStructure.getSynchronizationObject().wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            this.deleteChannel();
            this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(2, null));
        }
    }
    
    public boolean processSendOrder(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        synchronized (this.m_internalSynchronization) {
            if (!this.m_channelOpened) {
                this.createAndSendMixPacket(dataChainSendOrderStructure, (short)8);
                this.m_channelOpened = true;
            }
            else {
                this.createAndSendMixPacket(dataChainSendOrderStructure, (short)0);
            }
        }
        return true;
    }
    
    public void multiplexerClosed() {
        LogHolder.log(3, LogType.NET, "UnlimitedDataChannel: multiplexerClosed(): Multiplexer closed while channel was still active.");
        this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(3, null));
        this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(2, null));
    }
    
    protected void handleReceivedPacket(final MixPacket mixPacket) {
        if ((mixPacket.getChannelFlags() & 0x1) == 0x1) {
            this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(2, mixPacket.getPayloadData()));
            this.deleteChannel();
        }
        else {
            this.getChannelMessageQueue().addChannelMessage(new InternalChannelMessage(1, mixPacket.getPayloadData()));
        }
    }
}
