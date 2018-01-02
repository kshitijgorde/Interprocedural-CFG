// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection.util;

import java.util.Enumeration;
import java.io.IOException;
import anon.transport.connection.ConnectionException;
import java.util.Vector;
import anon.util.ObjectQueue;
import anon.transport.connection.IChunkReader;

public class QueuedChunkReader implements IChunkReader
{
    private final ObjectQueue m_readingQueue;
    private volatile boolean m_isClosed;
    private final Vector m_waitingThreads;
    private int m_timeout;
    private boolean m_isTearDown;
    
    public QueuedChunkReader(final ObjectQueue readingQueue, final int timeout) {
        this.m_isTearDown = false;
        this.m_readingQueue = readingQueue;
        this.m_isClosed = false;
        this.m_waitingThreads = new Vector();
        this.m_timeout = timeout;
    }
    
    public QueuedChunkReader(final ObjectQueue readingQueue) {
        this.m_isTearDown = false;
        this.m_readingQueue = readingQueue;
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
    
    public byte[] readChunk() throws ConnectionException {
        final Thread currentThread = Thread.currentThread();
        try {
            this.m_waitingThreads.addElement(currentThread);
            if (this.m_isClosed) {
                throw new ConnectionException("Reader allready closed");
            }
            if (this.m_timeout <= 0) {
                return (byte[])this.m_readingQueue.take();
            }
            final byte[] array = (byte[])this.m_readingQueue.poll(this.m_timeout);
            if (array != null) {
                return array;
            }
            throw new ConnectionException("Timeout elapsed");
        }
        catch (InterruptedException ex) {
            throw new ConnectionException("Innterupted while reading. Probaly closed Reader.");
        }
        finally {
            this.m_waitingThreads.removeElement(currentThread);
            if (this.m_isTearDown && this.m_readingQueue.isEmpty()) {
                try {
                    this.close();
                }
                catch (IOException ex2) {}
            }
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
    
    public void tearDown() throws IOException {
        this.m_isTearDown = true;
    }
    
    public int availableChunks() throws ConnectionException {
        return this.m_readingQueue.getSize();
    }
}
