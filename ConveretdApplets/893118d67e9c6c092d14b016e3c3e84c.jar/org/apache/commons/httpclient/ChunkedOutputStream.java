// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.OutputStream;

public class ChunkedOutputStream extends OutputStream
{
    private static final byte[] CRLF;
    private static final byte[] ENDCHUNK;
    private static final byte[] ZERO;
    private static final byte[] ONE;
    private boolean closed;
    private OutputStream stream;
    
    static {
        CRLF = new byte[] { 13, 10 };
        ENDCHUNK = ChunkedOutputStream.CRLF;
        ZERO = new byte[] { 48 };
        ONE = new byte[] { 49 };
    }
    
    public ChunkedOutputStream(final OutputStream stream) {
        this.closed = false;
        this.stream = null;
        if (stream == null) {
            throw new IllegalArgumentException("Stream parameter may not be null");
        }
        this.stream = stream;
    }
    
    public void print(String s) throws IOException {
        if (s == null) {
            s = "null";
        }
        this.write(HttpConstants.getBytes(s));
    }
    
    public void println() throws IOException {
        this.print("\r\n");
    }
    
    public void println(final String s) throws IOException {
        this.print(s);
        this.println();
    }
    
    public void write(final int b) throws IOException, IllegalStateException {
        if (this.closed) {
            throw new IllegalStateException("Output stream already closed");
        }
        this.stream.write(ChunkedOutputStream.ONE, 0, ChunkedOutputStream.ONE.length);
        this.stream.write(ChunkedOutputStream.CRLF, 0, ChunkedOutputStream.CRLF.length);
        this.stream.write(b);
        this.stream.write(ChunkedOutputStream.ENDCHUNK, 0, ChunkedOutputStream.ENDCHUNK.length);
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("Output stream already closed");
        }
        final byte[] chunkHeader = HttpConstants.getBytes(String.valueOf(Integer.toHexString(len)) + "\r\n");
        this.stream.write(chunkHeader, 0, chunkHeader.length);
        this.stream.write(b, off, len);
        this.stream.write(ChunkedOutputStream.ENDCHUNK, 0, ChunkedOutputStream.ENDCHUNK.length);
    }
    
    public void writeClosingChunk() throws IOException {
        if (!this.closed) {
            try {
                this.stream.write(ChunkedOutputStream.ZERO, 0, ChunkedOutputStream.ZERO.length);
                this.stream.write(ChunkedOutputStream.CRLF, 0, ChunkedOutputStream.CRLF.length);
                this.stream.write(ChunkedOutputStream.ENDCHUNK, 0, ChunkedOutputStream.ENDCHUNK.length);
            }
            catch (IOException e) {
                throw e;
            }
            finally {
                this.closed = true;
            }
            this.closed = true;
        }
    }
    
    public void flush() throws IOException {
        this.stream.flush();
    }
    
    public void close() throws IOException {
        this.writeClosingChunk();
        super.close();
    }
}
