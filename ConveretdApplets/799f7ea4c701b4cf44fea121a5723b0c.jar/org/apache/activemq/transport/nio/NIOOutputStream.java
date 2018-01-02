// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.nio;

import java.io.InterruptedIOException;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.apache.activemq.transport.tcp.TimeStampStream;
import java.io.OutputStream;

public class NIOOutputStream extends OutputStream implements TimeStampStream
{
    private static final int BUFFER_SIZE = 8192;
    private final WritableByteChannel out;
    private final byte[] buffer;
    private final ByteBuffer byteBuffer;
    private int count;
    private boolean closed;
    private volatile long writeTimestamp;
    
    public NIOOutputStream(final WritableByteChannel out) {
        this(out, 8192);
    }
    
    public NIOOutputStream(final WritableByteChannel out, final int size) {
        this.writeTimestamp = -1L;
        this.out = out;
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.buffer = new byte[size];
        this.byteBuffer = ByteBuffer.wrap(this.buffer);
    }
    
    @Override
    public void write(final int b) throws IOException {
        this.checkClosed();
        if (this.availableBufferToWrite() < 1) {
            this.flush();
        }
        this.buffer[this.count++] = (byte)b;
    }
    
    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.checkClosed();
        if (this.availableBufferToWrite() < len) {
            this.flush();
        }
        if (this.buffer.length >= len) {
            System.arraycopy(b, off, this.buffer, this.count, len);
            this.count += len;
        }
        else {
            this.write(ByteBuffer.wrap(b, off, len));
        }
    }
    
    @Override
    public void flush() throws IOException {
        if (this.count > 0 && this.out != null) {
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.count);
            this.write(this.byteBuffer);
            this.count = 0;
        }
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        this.closed = true;
    }
    
    protected void checkClosed() throws IOException {
        if (this.closed) {
            throw new EOFException("Cannot write to the stream any more it has already been closed");
        }
    }
    
    private int availableBufferToWrite() {
        return this.buffer.length - this.count;
    }
    
    protected void write(final ByteBuffer data) throws IOException {
        int remaining = data.remaining();
        int lastRemaining = remaining - 1;
        long delay = 1L;
        try {
            this.writeTimestamp = System.currentTimeMillis();
            while (remaining > 0) {
                Label_0071: {
                    if (remaining == lastRemaining) {
                        try {
                            Thread.sleep(delay);
                            delay *= 2L;
                            if (delay > 1000L) {
                                delay = 1000L;
                            }
                            break Label_0071;
                        }
                        catch (InterruptedException e) {
                            throw new InterruptedIOException();
                        }
                    }
                    delay = 1L;
                }
                lastRemaining = remaining;
                this.out.write(data);
                remaining = data.remaining();
            }
        }
        finally {
            this.writeTimestamp = -1L;
        }
    }
    
    @Override
    public boolean isWriting() {
        return this.writeTimestamp > 0L;
    }
    
    @Override
    public long getWriteTimestamp() {
        return this.writeTimestamp;
    }
}
