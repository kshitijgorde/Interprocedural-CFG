// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection.util;

import anon.transport.address.AddressParameter;
import java.io.IOException;
import anon.transport.connection.UnsuportedCommandException;
import anon.transport.connection.ConnectionException;
import anon.transport.connection.IChunkReader;
import anon.util.ObjectQueue;
import anon.transport.address.IAddress;
import anon.transport.connection.IChunkWriter;
import anon.transport.connection.IChunkConnection;

public class QueuedChunkConnection implements IChunkConnection
{
    private final QueuedChunkReader m_reader;
    private final IChunkWriter m_writer;
    private int m_state;
    protected IAddress m_localAddress;
    protected IAddress m_remoteAddress;
    
    public QueuedChunkConnection(final ObjectQueue objectQueue) {
        this(objectQueue, objectQueue);
        this.m_localAddress = new QueuedAddress("loopback");
        this.m_remoteAddress = this.m_localAddress;
    }
    
    public QueuedChunkConnection(final ObjectQueue objectQueue, final ObjectQueue objectQueue2) {
        this.m_reader = new QueuedChunkReader(objectQueue);
        this.m_writer = new QueuedChunkWriter(objectQueue2);
        this.m_state = 1;
        this.m_localAddress = new QueuedAddress("queue");
        this.m_remoteAddress = new QueuedAddress("queue");
    }
    
    public IChunkReader getChunkReader() {
        return this.m_reader;
    }
    
    public IChunkWriter getChunkWriter() {
        return this.m_writer;
    }
    
    public int getCurrentState() {
        return this.m_state;
    }
    
    public IAddress getLocalAddress() {
        return this.m_localAddress;
    }
    
    public IAddress getRemoteAddress() {
        return this.m_remoteAddress;
    }
    
    public int getTimeout() throws ConnectionException {
        return 0;
    }
    
    public void setTimeout(final int n) throws ConnectionException {
        throw new UnsuportedCommandException("Timeout is not changeable");
    }
    
    public void close() throws IOException {
        if (this.m_state != 1) {
            this.m_state = 2;
            this.m_writer.close();
            this.m_reader.tearDown();
        }
    }
    
    private class QueuedAddress implements IAddress
    {
        private String m_identifier;
        
        public QueuedAddress(final String identifier) {
            this.m_identifier = identifier;
        }
        
        public AddressParameter[] getAllParameters() {
            return new AddressParameter[0];
        }
        
        public String getTransportIdentifier() {
            return this.m_identifier;
        }
    }
}
