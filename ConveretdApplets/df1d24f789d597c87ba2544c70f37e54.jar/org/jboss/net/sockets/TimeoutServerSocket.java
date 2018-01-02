// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.ServerSocket;

public class TimeoutServerSocket extends ServerSocket
{
    public TimeoutServerSocket(final int port) throws IOException {
        this(port, 50);
    }
    
    public TimeoutServerSocket(final int port, final int backlog) throws IOException {
        this(port, backlog, null);
    }
    
    public TimeoutServerSocket(final int port, final int backlog, final InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
    }
    
    public Socket accept() throws IOException {
        final Socket s = super.accept();
        s.setSoTimeout(1000);
        final TimeoutSocket ts = new TimeoutSocket(s);
        return ts;
    }
}
