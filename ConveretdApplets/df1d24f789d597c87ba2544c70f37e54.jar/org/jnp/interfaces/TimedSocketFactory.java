// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Hashtable;
import javax.net.SocketFactory;

public class TimedSocketFactory extends SocketFactory
{
    public static final String JNP_TIMEOUT = "jnp.timeout";
    public static final String JNP_SO_TIMEOUT = "jnp.sotimeout";
    protected int timeout;
    protected int soTimeout;
    
    public TimedSocketFactory() {
        this.timeout = 0;
        this.soTimeout = 0;
    }
    
    public TimedSocketFactory(final Hashtable env) {
        this.timeout = 0;
        this.soTimeout = 0;
        String value = env.get("jnp.timeout");
        if (value != null) {
            this.timeout = Integer.parseInt(value);
        }
        value = env.get("jnp.sotimeout");
        if (value != null) {
            this.soTimeout = Integer.parseInt(value);
        }
    }
    
    public Socket createSocket(final String host, final int port) throws IOException, UnknownHostException {
        final InetAddress hostAddr = InetAddress.getByName(host);
        return this.createSocket(hostAddr, port, null, 0);
    }
    
    public Socket createSocket(final InetAddress hostAddr, final int port) throws IOException {
        return this.createSocket(hostAddr, port, null, 0);
    }
    
    public Socket createSocket(final String host, final int port, final InetAddress localAddr, final int localPort) throws IOException, UnknownHostException {
        final InetAddress hostAddr = InetAddress.getByName(host);
        return this.createSocket(hostAddr, port, localAddr, localPort);
    }
    
    public Socket createSocket(final InetAddress hostAddr, final int port, final InetAddress localAddr, final int localPort) throws IOException {
        Socket socket = null;
        if (this.timeout <= 0) {
            socket = new Socket(hostAddr, port, localAddr, localPort);
        }
        else {
            socket = this.createSocket(hostAddr, port, localAddr, localPort, this.timeout);
        }
        socket.setSoTimeout(this.soTimeout);
        return socket;
    }
    
    protected Socket createSocket(final InetAddress hostAddr, final int port, final InetAddress localAddr, final int localPort, final int connectTimeout) throws IOException {
        final ConnectThread t = new ConnectThread();
        final Socket socket = t.createSocket(hostAddr, port, localAddr, localPort, connectTimeout);
        return socket;
    }
    
    class ConnectThread extends Thread
    {
        IOException ex;
        InetAddress hostAddr;
        InetAddress localAddr;
        int port;
        int localPort;
        int connectTimeout;
        Socket socket;
        
        ConnectThread() {
            super("JNP ConnectThread");
            super.setDaemon(true);
        }
        
        Socket createSocket(final InetAddress hostAddr, final int port, final InetAddress localAddr, final int localPort, final int connectTimeout) throws IOException {
            this.hostAddr = hostAddr;
            this.port = port;
            this.localAddr = localAddr;
            this.localPort = localPort;
            this.connectTimeout = connectTimeout;
            try {
                synchronized (this) {
                    this.start();
                    this.wait(connectTimeout);
                }
            }
            catch (InterruptedException e) {
                throw new ConnectException("Connect attempt timed out");
            }
            if (this.ex != null) {
                throw this.ex;
            }
            if (this.socket == null) {
                throw new ConnectException("Connect attempt timed out");
            }
            return this.socket;
        }
        
        public void run() {
            try {
                this.socket = new Socket(this.hostAddr, this.port, this.localAddr, this.localPort);
                synchronized (this) {
                    this.notify();
                }
            }
            catch (IOException e) {
                this.ex = e;
            }
        }
    }
}
