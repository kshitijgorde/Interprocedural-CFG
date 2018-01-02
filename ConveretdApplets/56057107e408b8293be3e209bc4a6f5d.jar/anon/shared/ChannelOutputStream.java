// 
// Decompiled by Procyon v0.5.30
// 

package anon.shared;

import java.io.IOException;
import java.io.OutputStream;

final class ChannelOutputStream extends OutputStream
{
    boolean m_bIsClosed;
    AbstractChannel m_channel;
    
    protected ChannelOutputStream(final AbstractChannel channel) {
        this.m_bIsClosed = false;
        this.m_channel = null;
        this.m_channel = channel;
    }
    
    public void write(final int n) throws IOException {
        if (this.m_bIsClosed) {
            throw new IOException("Channel closed by peer");
        }
        this.m_channel.send(new byte[] { (byte)n }, 1);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.m_bIsClosed) {
            throw new IOException("Channel closed by peer");
        }
        this.m_channel.send(array, (short)n2);
    }
    
    public void close() {
        this.m_bIsClosed = true;
    }
    
    void closedByPeer() {
        this.m_bIsClosed = true;
    }
}
