// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.io.IOException;
import java.net.Socket;
import java.io.Serializable;
import java.rmi.server.RMIClientSocketFactory;

public class TimeoutClientSocketFactory implements RMIClientSocketFactory, Serializable
{
    private static final long serialVersionUID = -920483051658660269L;
    
    public Socket createSocket(final String host, final int port) throws IOException {
        final Socket s = new Socket(host, port);
        s.setSoTimeout(1000);
        final TimeoutSocket ts = new TimeoutSocket(s);
        return ts;
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof TimeoutClientSocketFactory;
    }
    
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }
}
