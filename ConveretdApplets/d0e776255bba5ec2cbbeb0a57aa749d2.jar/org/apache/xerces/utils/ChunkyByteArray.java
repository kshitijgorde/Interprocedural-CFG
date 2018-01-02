// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import java.io.IOException;
import java.io.InputStream;

public final class ChunkyByteArray extends InputStream
{
    private static final int CHUNK_SHIFT = 14;
    private static final int CHUNK_SIZE = 16384;
    private static final int CHUNK_MASK = 16383;
    private static final int INITIAL_CHUNK_COUNT = 64;
    private InputStream fInputStream;
    private byte[][] fData;
    private int fLength;
    private int fOffset;
    
    public ChunkyByteArray(final InputStream fInputStream) throws IOException {
        this.fInputStream = null;
        this.fData = new byte[64][];
        this.fLength = 0;
        this.fOffset = 0;
        this.fInputStream = fInputStream;
        this.fill();
    }
    
    public int read() throws IOException {
        if (this.fData == null) {
            return (this.fInputStream == null) ? -1 : this.fInputStream.read();
        }
        final byte b = this.fData[0][this.fOffset];
        if (++this.fOffset == this.fLength) {
            this.fData = null;
            if (this.fLength < 16384) {
                this.fInputStream = null;
            }
        }
        return b;
    }
    
    public int read(final byte[] array, int i, int n) throws IOException {
        final int n2 = this.fLength - this.fOffset;
        if (n2 == 0) {
            return (this.fInputStream == null) ? -1 : this.fInputStream.read(array, i, n);
        }
        if (n <= 0) {
            return 0;
        }
        final byte[] array2 = this.fData[0];
        if (n >= n2) {
            n = n2;
            if (this.fLength < 16384) {
                this.fInputStream = null;
            }
        }
        if (array == null) {
            this.fOffset += n;
            return n;
        }
        do {
            array[i++] = array2[this.fOffset++];
        } while (i < i + n);
        return n;
    }
    
    public void rewind() {
        this.fOffset = 0;
    }
    
    public byte byteAt(final int n) throws IOException {
        final int n2 = n >> 14;
        final int n3 = n & 0x3FFF;
        try {
            return this.fData[n2][n3];
        }
        catch (NullPointerException ex) {}
        catch (ArrayIndexOutOfBoundsException ex2) {
            final byte[][] fData = new byte[this.fData.length * 2][];
            System.arraycopy(this.fData, 0, fData, 0, this.fData.length);
            this.fData = fData;
        }
        if (n3 == 0) {
            this.fill();
            return this.fData[n2][n3];
        }
        return 0;
    }
    
    public boolean atEOF(final int n) {
        return n > this.fLength;
    }
    
    public void close() throws IOException {
        if (this.fInputStream != null) {
            this.fInputStream.close();
            this.fInputStream = null;
        }
    }
    
    private void fill() throws IOException {
        final int n = this.fLength >> 14;
        final byte[] array = new byte[16384];
        this.fData[n] = array;
        int n2 = 0;
        int i = 16384;
        do {
            final int read = this.fInputStream.read(array, n2, i);
            if (read == -1) {
                array[n2] = -1;
                this.fInputStream.close();
                this.fInputStream = null;
                break;
            }
            if (read <= 0) {
                continue;
            }
            this.fLength += read;
            n2 += read;
            i -= read;
        } while (i > 0);
    }
}
