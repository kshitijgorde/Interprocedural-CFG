// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.OutputStream;

public class ByteArrayOutputStream extends OutputStream
{
    byte[] buffer;
    int size;
    
    public ByteArrayOutputStream() {
        this(1028);
    }
    
    public ByteArrayOutputStream(final int capacity) {
        this.buffer = new byte[capacity];
    }
    
    public void write(final int b) {
        final int newsize = this.size + 1;
        this.checkCapacity(newsize);
        this.buffer[this.size] = (byte)b;
        this.size = newsize;
    }
    
    public void write(final byte[] b, final int off, final int len) {
        final int newsize = this.size + len;
        this.checkCapacity(newsize);
        System.arraycopy(b, off, this.buffer, this.size, len);
        this.size = newsize;
    }
    
    private void checkCapacity(final int minimumCapacity) {
        if (minimumCapacity > this.buffer.length) {
            final byte[] b = new byte[Math.max(this.buffer.length << 1, minimumCapacity)];
            System.arraycopy(this.buffer, 0, b, 0, this.size);
            this.buffer = b;
        }
    }
    
    public void reset() {
        this.size = 0;
    }
    
    public ByteSequence toByteSequence() {
        return new ByteSequence(this.buffer, 0, this.size);
    }
    
    public byte[] toByteArray() {
        final byte[] rc = new byte[this.size];
        System.arraycopy(this.buffer, 0, rc, 0, this.size);
        return rc;
    }
    
    public int size() {
        return this.size;
    }
}
