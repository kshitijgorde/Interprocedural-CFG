// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import java.net.SocketException;
import java.io.IOException;
import anon.infoservice.ImmutableProxyInterface;
import anon.crypto.tinytls.TinyTLS;

public class FirstOnionRouterConnectionThread implements Runnable
{
    private TinyTLS m_tls;
    private Thread m_thread;
    private String m_name;
    private int m_port;
    private long m_timeout;
    private Exception m_exception;
    private Object m_oNotifySync;
    private ImmutableProxyInterface m_proxyInterface;
    
    public FirstOnionRouterConnectionThread(final String name, final int port, final long timeout, final ImmutableProxyInterface proxyInterface) {
        this.m_thread = null;
        this.m_oNotifySync = new Object();
        this.m_name = name;
        this.m_port = port;
        this.m_timeout = timeout;
        this.m_exception = null;
        this.m_proxyInterface = proxyInterface;
    }
    
    public TinyTLS getConnection() throws IOException {
        (this.m_thread = new Thread(this, "FirstOnionRouterConnectionThread")).setDaemon(true);
        this.m_thread.start();
        synchronized (this.m_oNotifySync) {
            try {
                this.m_oNotifySync.wait(this.m_timeout);
            }
            catch (InterruptedException ex) {}
        }
        if (this.m_exception != null) {
            throw new IOException(this.m_exception.getMessage());
        }
        if (this.m_tls == null) {
            throw new SocketException("Connection timed out");
        }
        return this.m_tls;
    }
    
    public void run() {
        TinyTLS tls = null;
        try {
            tls = new TinyTLS(this.m_name, this.m_port, this.m_proxyInterface);
        }
        catch (Exception exception) {
            this.m_exception = exception;
        }
        this.m_tls = tls;
        synchronized (this.m_oNotifySync) {
            this.m_oNotifySync.notify();
        }
    }
}
