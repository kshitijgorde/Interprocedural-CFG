// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket;

import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.AbstractIoSessionConfig;

public abstract class AbstractSocketSessionConfig extends AbstractIoSessionConfig implements SocketSessionConfig
{
    @Override
    protected final void doSetAll(final IoSessionConfig ioSessionConfig) {
        if (!(ioSessionConfig instanceof SocketSessionConfig)) {
            return;
        }
        if (ioSessionConfig instanceof AbstractSocketSessionConfig) {
            final AbstractSocketSessionConfig abstractSocketSessionConfig;
            if ((abstractSocketSessionConfig = (AbstractSocketSessionConfig)ioSessionConfig).isKeepAliveChanged()) {
                this.setKeepAlive(abstractSocketSessionConfig.isKeepAlive());
            }
            if (abstractSocketSessionConfig.isOobInlineChanged()) {
                this.setOobInline(abstractSocketSessionConfig.isOobInline());
            }
            if (abstractSocketSessionConfig.isReceiveBufferSizeChanged()) {
                this.setReceiveBufferSize(abstractSocketSessionConfig.getReceiveBufferSize());
            }
            if (abstractSocketSessionConfig.isReuseAddressChanged()) {
                this.setReuseAddress(abstractSocketSessionConfig.isReuseAddress());
            }
            if (abstractSocketSessionConfig.isSendBufferSizeChanged()) {
                this.setSendBufferSize(abstractSocketSessionConfig.getSendBufferSize());
            }
            if (abstractSocketSessionConfig.isSoLingerChanged()) {
                this.setSoLinger(abstractSocketSessionConfig.getSoLinger());
            }
            if (abstractSocketSessionConfig.isTcpNoDelayChanged()) {
                this.setTcpNoDelay(abstractSocketSessionConfig.isTcpNoDelay());
            }
            if (abstractSocketSessionConfig.isTrafficClassChanged() && this.getTrafficClass() != abstractSocketSessionConfig.getTrafficClass()) {
                this.setTrafficClass(abstractSocketSessionConfig.getTrafficClass());
            }
            return;
        }
        final SocketSessionConfig socketSessionConfig = (SocketSessionConfig)ioSessionConfig;
        this.setKeepAlive(socketSessionConfig.isKeepAlive());
        this.setOobInline(socketSessionConfig.isOobInline());
        this.setReceiveBufferSize(socketSessionConfig.getReceiveBufferSize());
        this.setReuseAddress(socketSessionConfig.isReuseAddress());
        this.setSendBufferSize(socketSessionConfig.getSendBufferSize());
        this.setSoLinger(socketSessionConfig.getSoLinger());
        this.setTcpNoDelay(socketSessionConfig.isTcpNoDelay());
        if (this.getTrafficClass() != socketSessionConfig.getTrafficClass()) {
            this.setTrafficClass(socketSessionConfig.getTrafficClass());
        }
    }
    
    protected boolean isKeepAliveChanged() {
        return true;
    }
    
    protected boolean isOobInlineChanged() {
        return true;
    }
    
    protected boolean isReceiveBufferSizeChanged() {
        return true;
    }
    
    protected boolean isReuseAddressChanged() {
        return true;
    }
    
    protected boolean isSendBufferSizeChanged() {
        return true;
    }
    
    protected boolean isSoLingerChanged() {
        return true;
    }
    
    protected boolean isTcpNoDelayChanged() {
        return true;
    }
    
    protected boolean isTrafficClassChanged() {
        return true;
    }
}
