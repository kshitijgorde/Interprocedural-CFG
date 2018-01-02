// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class ContentLengthInputStream extends FilterInputStream
{
    private boolean closed;
    private long contentLength;
    private long pos;
    
    public ContentLengthInputStream(final InputStream in, final int contentLength) {
        super(in);
        this.closed = false;
        this.pos = 0L;
        this.contentLength = contentLength;
    }
    
    public ContentLengthInputStream(final InputStream in, final long contentLength) {
        super(in);
        this.closed = false;
        this.pos = 0L;
        this.contentLength = contentLength;
    }
    
    public void close() throws IOException {
        if (!this.closed) {
            try {
                ChunkedInputStream.exhaustInputStream(this);
            }
            finally {
                this.closed = true;
            }
            this.closed = true;
        }
    }
    
    public int read() throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.pos >= this.contentLength) {
            return -1;
        }
        ++this.pos;
        return super.read();
    }
    
    public int read(final byte[] b, final int off, int len) throws IOException {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        }
        if (this.pos >= this.contentLength) {
            return -1;
        }
        if (this.pos + len > this.contentLength) {
            len = (int)(this.contentLength - this.pos);
        }
        final int count = super.read(b, off, len);
        this.pos += count;
        return count;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
}
