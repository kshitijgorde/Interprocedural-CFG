// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket.nio;

import java.net.SocketException;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.transport.socket.AbstractSocketSessionConfig;
import org.apache.mina.core.service.DefaultTransportMetadata;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.buffer.IoBuffer;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.core.session.IoSessionConfig;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import java.nio.channels.SocketChannel;
import java.net.Socket;
import org.apache.mina.core.service.TransportMetadata;

final class NioSocketSession extends NioSession
{
    static final TransportMetadata METADATA;
    
    private Socket getSocket() {
        return ((SocketChannel)this.channel).socket();
    }
    
    public NioSocketSession(final IoService ioService, final IoProcessor<NioSession> ioProcessor, final SocketChannel socketChannel) {
        super(ioProcessor, ioService, socketChannel);
        (this.config = new SessionConfigImpl()).setAll(ioService.getSessionConfig());
    }
    
    @Override
    public final TransportMetadata getTransportMetadata() {
        return NioSocketSession.METADATA;
    }
    
    static {
        METADATA = new DefaultTransportMetadata("nio", "socket", false, true, InetSocketAddress.class, SocketSessionConfig.class, (Class<?>[])new Class[] { IoBuffer.class, FileRegion.class });
    }
    
    final class SessionConfigImpl extends AbstractSocketSessionConfig
    {
        private SessionConfigImpl(final byte b) {
        }
        
        @Override
        public final boolean isKeepAlive() {
            try {
                return NioSocketSession.this.getSocket().getKeepAlive();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setKeepAlive(final boolean keepAlive) {
            try {
                NioSocketSession.this.getSocket().setKeepAlive(keepAlive);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final boolean isOobInline() {
            try {
                return NioSocketSession.this.getSocket().getOOBInline();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setOobInline(final boolean oobInline) {
            try {
                NioSocketSession.this.getSocket().setOOBInline(oobInline);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final boolean isReuseAddress() {
            try {
                return NioSocketSession.this.getSocket().getReuseAddress();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setReuseAddress(final boolean reuseAddress) {
            try {
                NioSocketSession.this.getSocket().setReuseAddress(reuseAddress);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final int getSoLinger() {
            try {
                return NioSocketSession.this.getSocket().getSoLinger();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setSoLinger(final int n) {
            try {
                if (n >= 0) {
                    NioSocketSession.this.getSocket().setSoLinger(true, n);
                    return;
                }
                NioSocketSession.this.getSocket().setSoLinger(false, 0);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final boolean isTcpNoDelay() {
            if (!NioSocketSession.this.isConnected()) {
                return false;
            }
            try {
                return NioSocketSession.this.getSocket().getTcpNoDelay();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setTcpNoDelay(final boolean tcpNoDelay) {
            try {
                NioSocketSession.this.getSocket().setTcpNoDelay(tcpNoDelay);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final int getTrafficClass() {
            try {
                return NioSocketSession.this.getSocket().getTrafficClass();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setTrafficClass(final int trafficClass) {
            try {
                NioSocketSession.this.getSocket().setTrafficClass(trafficClass);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final int getSendBufferSize() {
            try {
                return NioSocketSession.this.getSocket().getSendBufferSize();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setSendBufferSize(final int sendBufferSize) {
            try {
                NioSocketSession.this.getSocket().setSendBufferSize(sendBufferSize);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final int getReceiveBufferSize() {
            try {
                return NioSocketSession.this.getSocket().getReceiveBufferSize();
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
        
        @Override
        public final void setReceiveBufferSize(final int receiveBufferSize) {
            try {
                NioSocketSession.this.getSocket().setReceiveBufferSize(receiveBufferSize);
            }
            catch (SocketException ex) {
                throw new RuntimeIoException(ex);
            }
        }
    }
}
