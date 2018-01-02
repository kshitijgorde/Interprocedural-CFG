// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import anon.transport.address.IAddress;
import java.io.OutputStream;
import java.io.InputStream;

public class ChunkConnectionAdapter implements IStreamConnection
{
    private static final int StreamState_OPEN = 1;
    private static final int StreamState_EOF = 2;
    private static final int StreamState_CLOSE = 3;
    private static final byte DATA_PACKET = 0;
    private static final byte EOF_PACKET = -1;
    private IChunkConnection m_underliningConnection;
    private ChunkInputStream m_inputstream;
    private ChunkOutputStream m_outputstream;
    
    public ChunkConnectionAdapter(final IChunkConnection underliningConnection) {
        this.m_underliningConnection = underliningConnection;
        this.m_inputstream = new ChunkInputStream(this.m_underliningConnection.getChunkReader());
        this.m_outputstream = new ChunkOutputStream(this.m_underliningConnection.getChunkWriter());
    }
    
    public InputStream getInputStream() {
        return this.m_inputstream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_outputstream;
    }
    
    public int getCurrentState() {
        return this.m_underliningConnection.getCurrentState();
    }
    
    public IAddress getLocalAddress() {
        return this.m_underliningConnection.getLocalAddress();
    }
    
    public IAddress getRemoteAddress() {
        return this.m_underliningConnection.getRemoteAddress();
    }
    
    public int getTimeout() throws ConnectionException {
        return this.m_underliningConnection.getTimeout();
    }
    
    public void setTimeout(final int timeout) throws ConnectionException {
        this.m_underliningConnection.setTimeout(timeout);
    }
    
    public void close() throws IOException {
        try {
            this.m_inputstream.close();
        }
        catch (IOException ex) {}
        try {
            this.m_outputstream.close();
        }
        catch (IOException ex2) {}
        this.m_underliningConnection.close();
    }
    
    private static class ChunkInputStream extends InputStream
    {
        private IChunkReader m_reader;
        private byte[] m_buffer;
        private int m_readPos;
        private int m_state;
        
        public ChunkInputStream(final IChunkReader reader) {
            this.m_reader = reader;
            this.m_readPos = 0;
            this.m_buffer = new byte[0];
            this.m_state = 1;
        }
        
        public synchronized int read() throws IOException {
            if (this.m_state == 3) {
                throw new IOException("Stream is allready closed");
            }
            while (this.m_readPos == this.m_buffer.length) {
                if (this.m_state == 2) {
                    this.m_state = 3;
                    return -1;
                }
                this.updateBuffer();
            }
            return this.m_buffer[this.m_readPos++] & 0xFF;
        }
        
        private void updateBuffer() throws IOException {
            byte[] chunk;
            try {
                chunk = this.m_reader.readChunk();
            }
            catch (ConnectionException ex) {
                throw new IOException(ex.getMessage());
            }
            if (chunk == null) {
                throw new IOException("Wrong implementation of IChunkReader.readChunk().Should never return null.");
            }
            if (chunk.length == 0) {
                throw new IOException("Recieved Packet is to small");
            }
            if (chunk[0] == -1) {
                this.m_state = 2;
            }
            System.arraycopy(chunk, 1, this.m_buffer = new byte[chunk.length - 1], 0, this.m_buffer.length);
            this.m_readPos = 0;
        }
        
        public int available() throws IOException {
            try {
                if (this.m_buffer.length - this.m_readPos == 0 && this.m_reader.availableChunks() > 0) {
                    this.updateBuffer();
                }
                return this.m_buffer.length - this.m_readPos;
            }
            catch (ConnectionException ex) {
                throw new IOException(ex.getMessage());
            }
        }
        
        public void close() throws IOException {
            this.m_state = 3;
            this.m_reader.close();
        }
    }
    
    private static class ChunkOutputStream extends OutputStream
    {
        private static final int BUFFER_SIZE = 1000;
        private IChunkWriter m_writer;
        private byte[] m_buffer;
        private int m_writePos;
        private int m_state;
        
        public ChunkOutputStream(final IChunkWriter writer) {
            this.m_writer = writer;
            this.m_buffer = new byte[1000];
            this.m_writePos = 0;
            this.m_state = 1;
        }
        
        public void write(final int n) throws IOException {
            if (this.m_state == 3) {
                throw new IOException("Stream allready closed");
            }
            this.m_buffer[this.m_writePos++] = (byte)(n & 0xFF);
            if (this.m_writePos == this.m_buffer.length) {
                this.flush();
            }
        }
        
        public void flush() throws IOException {
            final byte[] array = new byte[this.m_writePos + 1];
            if (this.m_state == 2) {
                array[0] = -1;
            }
            else {
                array[0] = 0;
            }
            System.arraycopy(this.m_buffer, 0, array, 1, this.m_writePos);
            try {
                this.m_writer.writeChunk(array);
            }
            catch (ConnectionException ex) {
                throw new IOException(ex.getMessage());
            }
            this.m_buffer = new byte[1000];
            this.m_writePos = 0;
            if (this.m_state == 2) {
                this.m_state = 3;
            }
        }
        
        public void close() throws IOException {
            if (this.m_state == 3) {
                throw new IOException("Stream allready closed");
            }
            if (this.m_state == 2) {
                LogHolder.log(4, LogType.TRANSPORT, "Sync Warning. EOF State should be immediately transfert to CLOSE");
            }
            this.m_state = 2;
            this.flush();
            this.m_state = 3;
            this.m_writer.close();
        }
    }
}
