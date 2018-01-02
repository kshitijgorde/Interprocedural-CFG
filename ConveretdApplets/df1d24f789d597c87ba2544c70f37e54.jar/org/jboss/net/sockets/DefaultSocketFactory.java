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
import javax.net.ServerSocketFactory;

public class DefaultSocketFactory extends ServerSocketFactory implements RMIServerSocketFactory, Serializable
{
    static final long serialVersionUID = -7626239955727142958L;
    private transient InetAddress bindAddress;
    private int backlog;
    
    public DefaultSocketFactory() {
        this(null, 200);
    }
    
    public DefaultSocketFactory(final InetAddress bindAddress) {
        this(bindAddress, 200);
    }
    
    public DefaultSocketFactory(final int backlog) {
        this(null, backlog);
    }
    
    public DefaultSocketFactory(final InetAddress bindAddress, final int backlog) {
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
    
    public ServerSocket createServerSocket(final int port) throws IOException {
        return this.createServerSocket(port, this.backlog, this.bindAddress);
    }
    
    public ServerSocket createServerSocket(final int port, final int backlog) throws IOException {
        return this.createServerSocket(port, backlog, null);
    }
    
    public ServerSocket createServerSocket(final int port, final int backlog, final InetAddress inetAddress) throws IOException {
        final ServerSocket activeSocket = new ServerSocket(port, backlog, this.bindAddress);
        return activeSocket;
    }
    
    public boolean equals(final Object obj) {
        boolean equals = obj instanceof DefaultSocketFactory;
        if (equals && this.bindAddress != null) {
            final DefaultSocketFactory dsf = (DefaultSocketFactory)obj;
            final InetAddress dsfa = dsf.bindAddress;
            equals = (dsfa != null && this.bindAddress.equals(dsfa));
        }
        return equals;
    }
    
    public int hashCode() {
        int hashCode = this.getClass().getName().hashCode();
        if (this.bindAddress != null) {
            hashCode += this.bindAddress.toString().hashCode();
        }
        return hashCode;
    }
    
    public String toString() {
        final StringBuffer tmp = new StringBuffer(super.toString());
        tmp.append('[');
        tmp.append("bindAddress=");
        tmp.append(this.bindAddress);
        tmp.append(']');
        return tmp.toString();
    }
}
