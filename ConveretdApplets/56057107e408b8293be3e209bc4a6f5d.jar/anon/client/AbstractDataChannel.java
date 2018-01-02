// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;
import logging.LogHolder;
import logging.LogType;
import anon.client.crypto.MixCipherChain;

public abstract class AbstractDataChannel extends AbstractChannel
{
    private static final short FLAG_CHANNEL_DUMMY = 16;
    private MixCipherChain m_mixCipherChain;
    private AbstractDataChain m_parentDataChain;
    private InternalChannelMessageQueue m_channelMessageQueue;
    
    public AbstractDataChannel(final int n, final Multiplexer multiplexer, final AbstractDataChain parentDataChain, final MixCipherChain mixCipherChain) {
        super(n, multiplexer);
        this.m_parentDataChain = parentDataChain;
        this.m_mixCipherChain = mixCipherChain;
        this.m_channelMessageQueue = new InternalChannelMessageQueue();
    }
    
    public InternalChannelMessageQueue getChannelMessageQueue() {
        return this.m_channelMessageQueue;
    }
    
    public void processReceivedPacket(final MixPacket mixPacket) {
        this.m_mixCipherChain.decryptPacket(mixPacket.getPayloadData());
        if ((mixPacket.getChannelFlags() & 0x10) == 0x10) {
            LogHolder.log(6, LogType.NET, "AbstractDataChannel: processReceivedPacket(): Catched an unexpected dummy-paket on channel '" + Integer.toString(mixPacket.getChannelId()) + "'.");
        }
        else {
            this.handleReceivedPacket(mixPacket);
        }
    }
    
    public int getNextPacketRecommandedOutputBlocksize() {
        int n = 0;
        synchronized (this.m_mixCipherChain) {
            n = MixPacket.getPayloadSize() - this.m_mixCipherChain.getNextPacketEncryptionOverhead();
        }
        return n;
    }
    
    protected void createAndSendMixPacket(final DataChainSendOrderStructure dataChainSendOrderStructure, final short channelFlags) {
        final MixPacket emptyMixPacket = this.createEmptyMixPacket();
        emptyMixPacket.setChannelFlags(channelFlags);
        synchronized (this.m_mixCipherChain) {
            dataChainSendOrderStructure.setChannelCell(new byte[emptyMixPacket.getPayloadData().length - this.m_mixCipherChain.getNextPacketEncryptionOverhead()]);
            System.arraycopy(emptyMixPacket.getPayloadData(), emptyMixPacket.getPayloadData().length - dataChainSendOrderStructure.getChannelCell().length, dataChainSendOrderStructure.getChannelCell(), 0, dataChainSendOrderStructure.getChannelCell().length);
            if (dataChainSendOrderStructure.getOrderData() != null) {
                this.m_parentDataChain.createPacketPayload(dataChainSendOrderStructure);
            }
            System.arraycopy(this.m_mixCipherChain.encryptPacket(dataChainSendOrderStructure.getChannelCell(), emptyMixPacket.getPayloadData().length, emptyMixPacket.getSendCallbackHandlers()), 0, emptyMixPacket.getPayloadData(), 0, emptyMixPacket.getPayloadData().length);
            try {
                this.sendPacket(emptyMixPacket);
            }
            catch (IOException thrownException) {
                dataChainSendOrderStructure.setThrownException(thrownException);
            }
        }
        dataChainSendOrderStructure.processingDone();
    }
    
    public abstract boolean processSendOrder(final DataChainSendOrderStructure p0);
    
    public abstract void organizeChannelClose() throws IOException;
    
    protected abstract void handleReceivedPacket(final MixPacket p0);
}
