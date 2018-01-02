// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.protocol;

import java.net.UnknownHostException;
import java.io.IOException;
import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;
import java.net.InetAddress;

public class SSLProtocolSocketFactory implements SecureProtocolSocketFactory
{
    public Socket createSocket(final String host, final int port, final InetAddress clientHost, final int clientPort) throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(host, port, clientHost, clientPort);
    }
    
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(host, port);
    }
    
    public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException, UnknownHostException {
        return ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(socket, host, port, autoClose);
    }
}
