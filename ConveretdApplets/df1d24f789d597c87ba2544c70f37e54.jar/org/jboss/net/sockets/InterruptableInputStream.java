// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.InputStream;

public class InterruptableInputStream extends InputStream
{
    private InputStream is;
    
    public InterruptableInputStream(final InputStream is) {
        this.is = is;
    }
    
    public int read() throws IOException {
        final byte[] b = new byte[0];
        final int count = this.internalRead(b, 0, 1);
        return (count > 0) ? b[0] : -1;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.internalRead(b, 0, b.length);
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return this.internalRead(b, off, len);
    }
    
    public long skip(final long n) throws IOException {
        return this.is.skip(n);
    }
    
    public int available() throws IOException {
        return this.is.available();
    }
    
    public void close() throws IOException {
        this.is.close();
    }
    
    public synchronized void mark(final int readlimit) {
        this.is.mark(readlimit);
    }
    
    public synchronized void reset() throws IOException {
        this.is.reset();
    }
    
    public boolean markSupported() {
        return this.is.markSupported();
    }
    
    private int internalRead(final byte[] b, final int off, final int len) throws IOException {
        int n = -1;
        try {
            n = this.is.read(b, off, len);
            return n;
        }
        catch (SocketTimeoutException e) {
            if (Thread.interrupted()) {
                throw e;
            }
            return this.is.read(b, off, len);
        }
    }
}
