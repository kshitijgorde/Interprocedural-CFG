// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection.util;

import java.io.IOException;
import java.util.Enumeration;
import anon.transport.connection.ConnectionException;
import java.util.Vector;
import anon.util.ObjectQueue;
import anon.transport.connection.IChunkWriter;

public class QueuedChunkWriter implements IChunkWriter
{
    private final ObjectQueue m_writingQueue;
    private volatile boolean m_isClosed;
    private final Vector m_waitingThreads;
    private int m_timeout;
    
    public QueuedChunkWriter(final ObjectQueue writingQueue, final int timeout) {
        this.m_writingQueue = writingQueue;
        this.m_isClosed = false;
        this.m_waitingThreads = new Vector();
        this.m_timeout = timeout;
    }
    
    public QueuedChunkWriter(final ObjectQueue writingQueue) {
        this.m_writingQueue = writingQueue;
        this.m_isClosed = false;
        this.m_waitingThreads = new Vector();
        this.m_timeout = 0;
    }
    
    public int getTimeout() {
        return this.m_timeout;
    }
    
    public void setTimeout(final int timeout) {
        this.m_timeout = timeout;
    }
    
    public void writeChunk(final byte[] array) throws ConnectionException {
        final Thread currentThread = Thread.currentThread();
        try {
            this.m_waitingThreads.addElement(currentThread);
            if (this.m_isClosed) {
                throw new ConnectionException("Reader allready closed");
            }
            this.m_writingQueue.push(array);
        }
        catch (Exception ex) {
            throw new ConnectionException("Innterupted while reading. Probaly closed Reader.");
        }
        finally {
            this.m_waitingThreads.removeElement(currentThread);
        }
    }
    
    public void close() throws IOException {
        synchronized (this) {
            if (this.m_isClosed) {
                return;
            }
            this.m_isClosed = true;
        }
        final Enumeration<Thread> elements = this.m_waitingThreads.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().interrupt();
        }
    }
}
