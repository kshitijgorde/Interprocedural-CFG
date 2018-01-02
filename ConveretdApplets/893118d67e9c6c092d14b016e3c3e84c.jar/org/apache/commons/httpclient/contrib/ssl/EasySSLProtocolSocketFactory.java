// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.contrib.ssl;

import java.security.SecureRandom;
import com.sun.net.ssl.KeyManager;
import java.security.KeyStore;
import com.sun.net.ssl.TrustManager;
import com.sun.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

public class EasySSLProtocolSocketFactory implements SecureProtocolSocketFactory
{
    public Socket createSocket(final String host, final int port, final InetAddress clientHost, final int clientPort) throws IOException, UnknownHostException {
        final Socket socket = getEasySSLSocketFactory().createSocket(host, port, clientHost, clientPort);
        return socket;
    }
    
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        return getEasySSLSocketFactory().createSocket(host, port);
    }
    
    public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose) throws IOException, UnknownHostException {
        return getEasySSLSocketFactory().createSocket(socket, host, port, autoClose);
    }
    
    private static SSLSocketFactory getEasySSLSocketFactory() {
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] { new EasyX509TrustManager(null) }, null);
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        return context.getSocketFactory();
    }
}
