// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.protocol;

import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

public class DefaultProtocolSocketFactory implements ProtocolSocketFactory
{
    public Socket createSocket(final String host, final int port, final InetAddress clientHost, final int clientPort) throws IOException, UnknownHostException {
        return new Socket(host, port, clientHost, clientPort);
    }
    
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        return new Socket(host, port);
    }
}
