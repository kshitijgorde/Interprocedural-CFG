// 
// Decompiled by Procyon v0.5.30
// 

package anon.shared;

import java.io.IOException;
import java.io.InputStream;

final class ChannelInputStream extends InputStream
{
    private IOQueue m_Queue;
    private boolean m_bIsClosed;
    
    ChannelInputStream(final AbstractChannel abstractChannel) {
        this.m_Queue = null;
        this.m_bIsClosed = false;
        this.m_Queue = new IOQueue();
        this.m_bIsClosed = false;
    }
    
    protected void recv(final byte[] array, final int n, final int n2) throws IOException {
        try {
            this.m_Queue.write(array, n, n2);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new IOException(ex2.getMessage());
        }
    }
    
    public synchronized int available() throws IOException {
        return this.m_Queue.available();
    }
    
    public int read() throws IOException {
        return this.m_Queue.read();
    }
    
    public int read(final byte[] array) throws IOException {
        return this.m_Queue.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.m_Queue.read(array, n, n2);
    }
    
    protected void closedByPeer() {
        try {
            this.m_Queue.closeWrite();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void close() throws IOException {
        if (!this.m_bIsClosed) {
            try {
                this.m_Queue.closeWrite();
            }
            catch (Exception ex) {}
            this.m_Queue.closeRead();
            this.m_Queue = null;
            this.m_bIsClosed = true;
        }
    }
}
