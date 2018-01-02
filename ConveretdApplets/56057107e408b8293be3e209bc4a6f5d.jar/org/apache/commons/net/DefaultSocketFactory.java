// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;

public class DefaultSocketFactory implements SocketFactory
{
    public Socket createSocket(final String host, final int port) throws UnknownHostException, IOException {
        return new Socket(host, port);
    }
    
    public Socket createSocket(final InetAddress address, final int port) throws IOException {
        return new Socket(address, port);
    }
    
    public Socket createSocket(final String host, final int port, final InetAddress localAddr, final int localPort) throws UnknownHostException, IOException {
        return new Socket(host, port, localAddr, localPort);
    }
    
    public Socket createSocket(final InetAddress address, final int port, final InetAddress localAddr, final int localPort) throws IOException {
        return new Socket(address, port, localAddr, localPort);
    }
    
    public ServerSocket createServerSocket(final int port) throws IOException {
        return new ServerSocket(port);
    }
    
    public ServerSocket createServerSocket(final int port, final int backlog) throws IOException {
        return new ServerSocket(port, backlog);
    }
    
    public ServerSocket createServerSocket(final int port, final int backlog, final InetAddress bindAddr) throws IOException {
        return new ServerSocket(port, backlog, bindAddr);
    }
}
