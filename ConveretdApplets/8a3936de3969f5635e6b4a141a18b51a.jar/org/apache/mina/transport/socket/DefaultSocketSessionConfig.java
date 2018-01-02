// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket;

import org.apache.mina.core.service.IoService;

public final class DefaultSocketSessionConfig extends AbstractSocketSessionConfig
{
    private static boolean DEFAULT_REUSE_ADDRESS;
    private static int DEFAULT_TRAFFIC_CLASS;
    private static boolean DEFAULT_KEEP_ALIVE;
    private static boolean DEFAULT_OOB_INLINE;
    private static int DEFAULT_SO_LINGER;
    private static boolean DEFAULT_TCP_NO_DELAY;
    private boolean defaultReuseAddress;
    private boolean reuseAddress;
    private int receiveBufferSize;
    private int sendBufferSize;
    private int trafficClass;
    private boolean keepAlive;
    private boolean oobInline;
    private int soLinger;
    private boolean tcpNoDelay;
    
    public DefaultSocketSessionConfig() {
        this.receiveBufferSize = -1;
        this.sendBufferSize = -1;
        this.trafficClass = 0;
        this.keepAlive = false;
        this.oobInline = false;
        this.soLinger = DefaultSocketSessionConfig.DEFAULT_SO_LINGER;
        this.tcpNoDelay = false;
    }
    
    public final void init(final IoService ioService) {
        if (ioService instanceof SocketAcceptor) {
            this.defaultReuseAddress = true;
        }
        else {
            this.defaultReuseAddress = false;
        }
        this.reuseAddress = this.defaultReuseAddress;
    }
    
    @Override
    public final boolean isReuseAddress() {
        return this.reuseAddress;
    }
    
    @Override
    public final void setReuseAddress(final boolean reuseAddress) {
        this.reuseAddress = reuseAddress;
    }
    
    @Override
    public final int getReceiveBufferSize() {
        return this.receiveBufferSize;
    }
    
    @Override
    public final void setReceiveBufferSize(final int receiveBufferSize) {
        this.receiveBufferSize = receiveBufferSize;
    }
    
    @Override
    public final int getSendBufferSize() {
        return this.sendBufferSize;
    }
    
    @Override
    public final void setSendBufferSize(final int sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
    }
    
    @Override
    public final int getTrafficClass() {
        return this.trafficClass;
    }
    
    @Override
    public final void setTrafficClass(final int trafficClass) {
        this.trafficClass = trafficClass;
    }
    
    @Override
    public final boolean isKeepAlive() {
        return this.keepAlive;
    }
    
    @Override
    public final void setKeepAlive(final boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
    
    @Override
    public final boolean isOobInline() {
        return this.oobInline;
    }
    
    @Override
    public final void setOobInline(final boolean oobInline) {
        this.oobInline = oobInline;
    }
    
    @Override
    public final int getSoLinger() {
        return this.soLinger;
    }
    
    @Override
    public final void setSoLinger(final int soLinger) {
        this.soLinger = soLinger;
    }
    
    @Override
    public final boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }
    
    @Override
    public final void setTcpNoDelay(final boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }
    
    @Override
    protected final boolean isKeepAliveChanged() {
        return this.keepAlive;
    }
    
    @Override
    protected final boolean isOobInlineChanged() {
        return this.oobInline;
    }
    
    @Override
    protected final boolean isReceiveBufferSizeChanged() {
        return this.receiveBufferSize != -1;
    }
    
    @Override
    protected final boolean isReuseAddressChanged() {
        return this.reuseAddress != this.defaultReuseAddress;
    }
    
    @Override
    protected final boolean isSendBufferSizeChanged() {
        return this.sendBufferSize != -1;
    }
    
    @Override
    protected final boolean isSoLingerChanged() {
        return this.soLinger != DefaultSocketSessionConfig.DEFAULT_SO_LINGER;
    }
    
    @Override
    protected final boolean isTcpNoDelayChanged() {
        return this.tcpNoDelay;
    }
    
    @Override
    protected final boolean isTrafficClassChanged() {
        return this.trafficClass != 0;
    }
    
    static {
        DefaultSocketSessionConfig.DEFAULT_REUSE_ADDRESS = false;
        DefaultSocketSessionConfig.DEFAULT_TRAFFIC_CLASS = 0;
        DefaultSocketSessionConfig.DEFAULT_KEEP_ALIVE = false;
        DefaultSocketSessionConfig.DEFAULT_OOB_INLINE = false;
        DefaultSocketSessionConfig.DEFAULT_SO_LINGER = -1;
        DefaultSocketSessionConfig.DEFAULT_TCP_NO_DELAY = false;
    }
}
