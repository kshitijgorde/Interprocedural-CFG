// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.Serializable;
import java.rmi.server.RMIServerSocketFactory;

public class TimeoutServerSocketFactory implements RMIServerSocketFactory, Serializable
{
    static final long serialVersionUID = 7006964274840965634L;
    protected transient InetAddress bindAddress;
    protected int backlog;
    
    public TimeoutServerSocketFactory() {
        this(null, 200);
    }
    
    public TimeoutServerSocketFactory(final InetAddress bindAddress) {
        this(bindAddress, 200);
    }
    
    public TimeoutServerSocketFactory(final int backlog) {
        this(null, backlog);
    }
    
    public TimeoutServerSocketFactory(final InetAddress bindAddress, final int backlog) {
        this.backlog = 200;
        this.bindAddress = bindAddress;
        this.backlog = backlog;
    }
    
    public String getBindAddress() {
        String address = null;
        if (this.bindAddress != null) {
            address = this.bindAddress.getHostAddress();
        }
        return address;
    }
    
    public void setBindAddress(final String host) throws UnknownHostException {
        this.bindAddress = InetAddress.getByName(host);
    }
    
    public void setBindAddress(final InetAddress bindAddress) {
        this.bindAddress = bindAddress;
    }
    
    public ServerSocket createServerSocket(final int port) throws IOException {
        final ServerSocket activeSocket = new TimeoutServerSocket(port, this.backlog, this.bindAddress);
        return activeSocket;
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof TimeoutServerSocketFactory;
    }
    
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }
}
