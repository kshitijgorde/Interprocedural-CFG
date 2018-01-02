// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.TooMuchDataForPacketException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import anon.AnonChannel;

public class TypeFilterDataChain implements AnonChannel
{
    private AnonChannel m_originChannel;
    private Object m_internalSynchronization;
    private boolean m_firstPacket;
    private OutputStream m_typeFilterOutputStream;
    
    public TypeFilterDataChain(final AnonChannel originChannel, final int n) {
        this.m_originChannel = originChannel;
        this.m_firstPacket = true;
        this.m_internalSynchronization = new Object();
        this.m_typeFilterOutputStream = new TypeFilterOutputStreamImplementation(originChannel.getOutputStream(), n);
    }
    
    public InputStream getInputStream() {
        return this.m_originChannel.getInputStream();
    }
    
    public OutputStream getOutputStream() {
        return this.m_typeFilterOutputStream;
    }
    
    public int getOutputBlockSize() {
        int outputBlockSize = this.m_originChannel.getOutputBlockSize();
        synchronized (this.m_internalSynchronization) {
            if (this.m_firstPacket && outputBlockSize > 0) {
                --outputBlockSize;
            }
        }
        return outputBlockSize;
    }
    
    public void close() {
        this.m_originChannel.close();
    }
    
    public boolean isClosed() {
        return this.m_originChannel.isClosed();
    }
    
    private class TypeFilterOutputStreamImplementation extends OutputStream
    {
        private int m_dataChainType;
        private OutputStream m_originOutputStream;
        
        public TypeFilterOutputStreamImplementation(final OutputStream originOutputStream, final int dataChainType) {
            this.m_originOutputStream = originOutputStream;
            this.m_dataChainType = dataChainType;
        }
        
        public void write(final int n) throws IOException {
            this.write(new byte[] { (byte)n });
        }
        
        public void write(final byte[] array, int n, int length) throws IOException {
            synchronized (TypeFilterDataChain.this.m_internalSynchronization) {
                byte[] array2 = array;
                if (TypeFilterDataChain.this.m_firstPacket) {
                    array2 = new byte[length + 1];
                    array2[0] = (byte)this.m_dataChainType;
                    System.arraycopy(array, n, array2, 1, length);
                    n = 0;
                    length = array2.length;
                }
                try {
                    this.m_originOutputStream.write(array2, n, length);
                    TypeFilterDataChain.this.m_firstPacket = false;
                }
                catch (TooMuchDataForPacketException ex) {
                    if (TypeFilterDataChain.this.m_firstPacket) {
                        if (ex.getBytesSent() > 0) {
                            TypeFilterDataChain.this.m_firstPacket = false;
                        }
                        throw new TooMuchDataForPacketException(Math.max(0, ex.getBytesSent() - 1));
                    }
                    throw ex;
                }
            }
        }
        
        public void flush() throws IOException {
            this.m_originOutputStream.flush();
        }
        
        public void close() throws IOException {
            this.m_originOutputStream.close();
        }
    }
}
