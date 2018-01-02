// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import anon.infoservice.MixCascade;
import anon.IServiceContainer;

public abstract class AbstractControlChannel extends AbstractChannel
{
    private IServiceContainer m_serviceContainer;
    
    public AbstractControlChannel(final int n, final Multiplexer multiplexer, final IServiceContainer serviceContainer) {
        super(n, multiplexer);
        this.m_serviceContainer = serviceContainer;
        if (this.m_serviceContainer == null) {
            this.m_serviceContainer = new IServiceContainer() {
                public boolean isTrusted(final MixCascade mixCascade) {
                    return true;
                }
                
                public void checkTrust(final MixCascade mixCascade) throws TrustException {
                }
                
                public void keepCurrentService(final boolean b) {
                }
                
                public boolean isServiceAutoSwitched() {
                    return false;
                }
                
                public boolean isReconnectedAutomatically() {
                    return false;
                }
            };
        }
        multiplexer.getChannelTable().registerControlChannel(n, this);
    }
    
    public int sendRawMessage(final byte[] array) {
        try {
            int length = array.length;
            do {
                final MixPacket emptyMixPacket = this.createEmptyMixPacket();
                final int min = Math.min(length, emptyMixPacket.getPayloadData().length);
                emptyMixPacket.setChannelFlags((short)min);
                System.arraycopy(array, array.length - length, emptyMixPacket.getPayloadData(), 0, min);
                this.sendPacket(emptyMixPacket);
                length -= min;
            } while (length > 0 && !Thread.currentThread().isInterrupted());
            return 0;
        }
        catch (IOException ex) {
            return -1;
        }
    }
    
    public void processReceivedPacket(final MixPacket mixPacket) {
        final short channelFlags = mixPacket.getChannelFlags();
        if (channelFlags > mixPacket.getPayloadData().length || channelFlags < 0) {
            LogHolder.log(3, LogType.NET, "AbstractControlChannel: processReceivedPacket(): Invalid packet length.");
        }
        else {
            final byte[] array = new byte[channelFlags];
            System.arraycopy(mixPacket.getPayloadData(), 0, array, 0, channelFlags);
            this.processPacketData(array);
        }
    }
    
    protected final IServiceContainer getServiceContainer() {
        return this.m_serviceContainer;
    }
    
    protected abstract void processPacketData(final byte[] p0);
}
