// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;
import java.io.InputStream;

final class LEDataInputStream extends InputStream
{
    int position;
    InputStream in;
    protected byte[] buf;
    protected int pos;
    
    public LEDataInputStream(final InputStream inputStream) {
        this(inputStream, 512);
    }
    
    public LEDataInputStream(final InputStream in, final int pos) {
        this.in = in;
        if (pos > 0) {
            this.buf = new byte[pos];
            this.pos = pos;
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public void close() throws IOException {
        this.buf = null;
        if (this.in != null) {
            this.in.close();
            this.in = null;
        }
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public int available() throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        return this.buf.length - this.pos + this.in.available();
    }
    
    public int read() throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        if (this.pos < this.buf.length) {
            ++this.position;
            return this.buf[this.pos++] & 0xFF;
        }
        final int read = this.in.read();
        if (read != -1) {
            ++this.position;
        }
        return read;
    }
    
    public int read(final byte[] array, int n, final int n2) throws IOException {
        int n3;
        int data;
        for (n3 = 0; n3 != n2 && (data = this.readData(array, n, n2 - n3)) != -1; n += data, n3 += data) {}
        this.position += n3;
        if (n3 == 0 && n3 != n2) {
            return -1;
        }
        return n3;
    }
    
    private int readData(final byte[] array, final int n, final int n2) throws IOException {
        if (this.buf == null) {
            throw new IOException();
        }
        if (n < 0 || n > array.length || n2 < 0 || n2 > array.length - n) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n3 = 0;
        int n4 = n;
        final int n5 = this.buf.length - this.pos;
        if (n5 > 0) {
            n3 = ((n5 >= n2) ? n2 : n5);
            System.arraycopy(this.buf, this.pos, array, n4, n3);
            n4 += n3;
            this.pos += n3;
        }
        if (n3 == n2) {
            return n2;
        }
        final int read = this.in.read(array, n4, n2 - n3);
        if (read > 0) {
            return read + n3;
        }
        if (n3 == 0) {
            return read;
        }
        return n3;
    }
    
    public int readInt() throws IOException {
        final byte[] array = new byte[4];
        this.read(array);
        return (array[3] & 0xFF) << 24 | (array[2] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[0] & 0xFF);
    }
    
    public short readShort() throws IOException {
        final byte[] array = new byte[2];
        this.read(array);
        return (short)((array[1] & 0xFF) << 8 | (array[0] & 0xFF));
    }
    
    public void unread(final byte[] array) throws IOException {
        final int length = array.length;
        if (length > this.pos) {
            throw new IOException();
        }
        this.position -= length;
        this.pos -= length;
        System.arraycopy(array, 0, this.buf, this.pos, length);
    }
}
