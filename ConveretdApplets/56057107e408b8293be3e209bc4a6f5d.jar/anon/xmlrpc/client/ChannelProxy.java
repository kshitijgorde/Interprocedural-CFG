// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.client;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import anon.AnonChannel;

class ChannelProxy implements AnonChannel
{
    private boolean m_bIsClosedByPeer;
    private boolean m_bIsClosed;
    protected int m_id;
    protected int m_type;
    protected AnonServiceImplProxy m_RemoteAnonService;
    private ChannelInputStreamProxy m_inputStream;
    private ChannelOutputStreamProxy m_outputStream;
    
    public ChannelProxy(final int id, final AnonServiceImplProxy remoteAnonService) throws IOException {
        this.m_bIsClosedByPeer = false;
        this.m_bIsClosed = false;
        this.m_bIsClosedByPeer = false;
        this.m_bIsClosed = false;
        this.m_id = id;
        this.m_RemoteAnonService = remoteAnonService;
        this.m_inputStream = new ChannelInputStreamProxy(this);
        this.m_outputStream = new ChannelOutputStreamProxy(this);
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
    
    protected void send(final byte[] array, final int n, final int n2) throws IOException {
        this.m_RemoteAnonService.send(this.m_id, array, n, n2);
    }
    
    protected int recv(final byte[] array, final int n, final int n2) throws IOException {
        return this.m_RemoteAnonService.recv(this.m_id, array, n, n2);
    }
    
    public int getOutputBlockSize() {
        return 829;
    }
    
    public boolean isClosed() {
        return this.m_bIsClosed;
    }
    
    public synchronized void close() {
    }
}
