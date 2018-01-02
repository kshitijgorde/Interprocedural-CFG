// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.IOException;
import java.net.Socket;
import EDU.oswego.cs.dl.util.concurrent.FIFOSemaphore;
import java.io.Externalizable;
import java.rmi.server.RMIClientSocketFactory;

public class QueuedClientSocketFactory implements RMIClientSocketFactory, Externalizable
{
    static final long serialVersionUID = 6566809337830221375L;
    private transient FIFOSemaphore permits;
    private long numPermits;
    
    public QueuedClientSocketFactory() {
    }
    
    public QueuedClientSocketFactory(final long nPermits) {
        this.permits = new FIFOSemaphore(nPermits);
        this.numPermits = nPermits;
    }
    
    public Socket createSocket(final String host, final int port) throws IOException {
        try {
            this.permits.acquire();
            return new Socket(host, port);
        }
        catch (InterruptedException ex) {
            throw new IOException("Failed to acquire FIFOSemaphore for ClientSocketFactory");
        }
        finally {
            this.permits.release();
        }
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof QueuedClientSocketFactory;
    }
    
    public int hashCode() {
        return this.getClass().getName().hashCode();
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeLong(this.numPermits);
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.numPermits = in.readLong();
        this.permits = new FIFOSemaphore(this.numPermits);
    }
}
