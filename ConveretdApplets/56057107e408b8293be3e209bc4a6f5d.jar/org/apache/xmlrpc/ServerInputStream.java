// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;

class ServerInputStream extends InputStream
{
    private long available;
    private long markedAvailable;
    private BufferedInputStream in;
    
    public ServerInputStream(final BufferedInputStream in, final int n) {
        this.available = -1L;
        this.in = in;
        this.available = n;
    }
    
    public int read() throws IOException {
        if (this.available > 0L) {
            --this.available;
            return this.in.read();
        }
        if (this.available == -1L) {
            return this.in.read();
        }
        return -1;
    }
    
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, int n2) throws IOException {
        if (this.available > 0L) {
            if (n2 > this.available) {
                n2 = (int)this.available;
            }
            final int read = this.in.read(array, n, n2);
            if (read != -1) {
                this.available -= read;
            }
            else {
                this.available = -1L;
            }
            return read;
        }
        if (this.available == -1L) {
            return this.in.read(array, n, n2);
        }
        return -1;
    }
    
    public long skip(final long n) throws IOException {
        final long skip = this.in.skip(n);
        if (this.available > 0L) {
            this.available -= skip;
        }
        return skip;
    }
    
    public void mark(final int n) {
        this.in.mark(n);
        this.markedAvailable = this.available;
    }
    
    public void reset() throws IOException {
        this.in.reset();
        this.available = this.markedAvailable;
    }
    
    public boolean markSupported() {
        return true;
    }
}
