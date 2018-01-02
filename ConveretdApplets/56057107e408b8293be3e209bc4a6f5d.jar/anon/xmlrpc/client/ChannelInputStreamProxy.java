// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.client;

import java.io.IOException;
import java.io.InputStream;

class ChannelInputStreamProxy extends InputStream
{
    ChannelProxy m_channel;
    
    public ChannelInputStreamProxy(final ChannelProxy channel) {
        this.m_channel = channel;
    }
    
    public int read() throws IOException {
        final byte[] array = { 0 };
        if (this.m_channel.recv(array, 0, 1) < 0) {
            return -1;
        }
        return array[0] & 0xFF;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.m_channel.recv(array, n, n2);
    }
}
