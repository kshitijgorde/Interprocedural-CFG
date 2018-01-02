// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream
{
    byte[] buffer;
    int limit;
    int pos;
    int mark;
    
    public ByteArrayInputStream(final byte[] data) {
        this(data, 0, data.length);
    }
    
    public ByteArrayInputStream(final ByteSequence sequence) {
        this(sequence.getData(), sequence.getOffset(), sequence.getLength());
    }
    
    public ByteArrayInputStream(final byte[] data, final int offset, final int size) {
        this.buffer = data;
        this.mark = offset;
        this.pos = offset;
        this.limit = offset + size;
    }
    
    public int read() throws IOException {
        if (this.pos < this.limit) {
            return this.buffer[this.pos++] & 0xFF;
        }
        return -1;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
    
    public int read(final byte[] b, final int off, int len) {
        if (this.pos < this.limit) {
            len = Math.min(len, this.limit - this.pos);
            if (len > 0) {
                System.arraycopy(this.buffer, this.pos, b, off, len);
                this.pos += len;
            }
            return len;
        }
        return -1;
    }
    
    public long skip(long len) throws IOException {
        if (this.pos < this.limit) {
            len = Math.min(len, this.limit - this.pos);
            if (len > 0L) {
                this.pos += (int)len;
            }
            return len;
        }
        return -1L;
    }
    
    public int available() {
        return this.limit - this.pos;
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public void mark(final int markpos) {
        this.mark = this.pos;
    }
    
    public void reset() {
        this.pos = this.mark;
    }
}
