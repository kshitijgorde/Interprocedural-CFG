// 
// Decompiled by Procyon v0.5.30
// 

package anon.shared;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import anon.AnonChannel;

public abstract class AbstractChannel implements AnonChannel
{
    protected volatile boolean m_bIsClosedByPeer;
    protected volatile boolean m_bIsClosed;
    protected int m_id;
    private ChannelInputStream m_inputStream;
    private ChannelOutputStream m_outputStream;
    
    public AbstractChannel(final int id) {
        this();
        this.m_id = id;
    }
    
    public AbstractChannel() {
        this.m_bIsClosedByPeer = false;
        this.m_bIsClosed = false;
        this.m_bIsClosedByPeer = false;
        this.m_bIsClosed = false;
        this.m_inputStream = new ChannelInputStream(this);
        this.m_outputStream = new ChannelOutputStream(this);
    }
    
    public void finalize() {
        this.close();
    }
    
    public int hashCode() {
        return this.m_id;
    }
    
    public InputStream getInputStream() {
        return this.m_inputStream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_outputStream;
    }
    
    public boolean isClosed() {
        return this.m_bIsClosed;
    }
    
    public synchronized void close() {
        try {
            if (!this.m_bIsClosed) {
                this.m_outputStream.close();
                this.m_inputStream.close();
                if (!this.m_bIsClosedByPeer) {
                    this.close_impl();
                }
            }
        }
        catch (Exception ex) {}
        this.m_bIsClosed = true;
    }
    
    protected abstract void close_impl();
    
    protected void recv(final byte[] array, final int n, final int n2) throws IOException {
        this.m_inputStream.recv(array, n, n2);
    }
    
    protected abstract void send(final byte[] p0, final int p1) throws IOException;
    
    public void closedByPeer() {
        try {
            this.m_inputStream.closedByPeer();
            this.m_outputStream.closedByPeer();
        }
        catch (Exception ex) {}
        this.m_bIsClosedByPeer = true;
    }
    
    public abstract int getOutputBlockSize();
}
