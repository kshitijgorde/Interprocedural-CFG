// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;

public class DataChainInputStreamQueueEntry
{
    public static final int TYPE_DATA_AVAILABLE = 1;
    public static final int TYPE_STREAM_END = 2;
    public static final int TYPE_IO_EXCEPTION = 3;
    private int m_type;
    private byte[] m_data;
    private int m_alreadyReadBytes;
    private IOException m_ioException;
    
    public DataChainInputStreamQueueEntry(final int type, final byte[] data) {
        this.m_type = type;
        this.m_data = data;
        this.m_alreadyReadBytes = 0;
        this.m_ioException = null;
    }
    
    public DataChainInputStreamQueueEntry(final IOException ioException) {
        this.m_type = 3;
        this.m_data = null;
        this.m_alreadyReadBytes = 0;
        this.m_ioException = ioException;
    }
    
    public int getType() {
        return this.m_type;
    }
    
    public byte[] getData() {
        return this.m_data;
    }
    
    public int getAlreadyReadBytes() {
        return this.m_alreadyReadBytes;
    }
    
    public void setAlreadyReadBytes(final int alreadyReadBytes) {
        this.m_alreadyReadBytes = alreadyReadBytes;
    }
    
    public IOException getIOException() {
        return this.m_ioException;
    }
}
