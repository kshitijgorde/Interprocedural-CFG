// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;

public interface SocketFactory
{
    Socket createSocket(final String p0, final int p1) throws UnknownHostException, IOException;
    
    Socket createSocket(final InetAddress p0, final int p1) throws IOException;
    
    Socket createSocket(final String p0, final int p1, final InetAddress p2, final int p3) throws UnknownHostException, IOException;
    
    Socket createSocket(final InetAddress p0, final int p1, final InetAddress p2, final int p3) throws IOException;
    
    ServerSocket createServerSocket(final int p0) throws IOException;
    
    ServerSocket createServerSocket(final int p0, final int p1) throws IOException;
    
    ServerSocket createServerSocket(final int p0, final int p1, final InetAddress p2) throws IOException;
}
