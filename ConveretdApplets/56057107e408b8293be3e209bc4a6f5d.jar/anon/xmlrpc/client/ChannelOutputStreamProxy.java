// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.client;

import java.io.IOException;
import java.io.OutputStream;

class ChannelOutputStreamProxy extends OutputStream
{
    private ChannelProxy m_channel;
    private boolean m_bIsClosed;
    
    public ChannelOutputStreamProxy(final ChannelProxy channel) {
        this.m_bIsClosed = false;
        this.m_channel = channel;
        this.m_bIsClosed = false;
    }
    
    public void write(final byte[] array) throws IOException {
        this.write(array, 0, array.length);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.m_bIsClosed) {
            throw new IOException("Channel closed by peer");
        }
        this.m_channel.send(array, n, n2);
    }
    
    public void write(final int n) throws IOException {
        if (this.m_bIsClosed) {
            throw new IOException("Channel closed by peer");
        }
        this.m_channel.send(new byte[] { (byte)n }, 0, 1);
    }
}
