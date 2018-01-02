// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.IOException;
import java.io.InputStream;

public final class RewindableInputStream extends InputStream
{
    private static final int INITIAL_BYTE_COUNT = 4096;
    private InputStream fInputStream;
    private byte[] fData;
    private int fStartOffset;
    private int fEndOffset;
    private int fOffset;
    private int fLength;
    private int fMark;
    
    public RewindableInputStream() {
        this.fData = new byte[4096];
    }
    
    public void setStream(final InputStream fInputStream) {
        this.fInputStream = fInputStream;
        this.fStartOffset = 0;
        this.fEndOffset = -1;
        this.fOffset = 0;
        this.fLength = 0;
        this.fMark = 0;
    }
    
    public void setStartOffset(final int fStartOffset) {
        this.fStartOffset = fStartOffset;
    }
    
    public void rewind() {
        this.fOffset = this.fStartOffset;
    }
    
    public int read() throws IOException {
        if (this.fOffset < this.fLength) {
            return this.fData[this.fOffset++] & 0xFF;
        }
        if (this.fOffset == this.fEndOffset) {
            return -1;
        }
        if (this.fOffset == this.fData.length) {
            final byte[] fData = new byte[this.fOffset << 1];
            System.arraycopy(this.fData, 0, fData, 0, this.fOffset);
            this.fData = fData;
        }
        final int read = this.fInputStream.read(this.fData, this.fOffset, this.fData.length - this.fOffset);
        if (read == -1) {
            this.fEndOffset = this.fOffset;
            return -1;
        }
        this.fLength += read;
        return this.fData[this.fOffset++] & 0xFF;
    }
    
    public int read(final byte[] array, int n, int n2) throws IOException {
        final int n3 = this.fLength - this.fOffset;
        if (n3 == 0) {
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            return this.fInputStream.read(array, n, n2);
        }
        else {
            int n4;
            if (n2 < n3) {
                if (n2 <= 0) {
                    return 0;
                }
                n4 = n2;
            }
            else {
                n4 = n3;
            }
            if (array != null) {
                System.arraycopy(this.fData, this.fOffset, array, n, n4);
            }
            this.fOffset += n4;
            if (n4 == n2 || this.fOffset == this.fEndOffset) {
                return n4;
            }
            n += n4;
            n2 -= n4;
            final int read = this.fInputStream.read(array, n, n2);
            if (read == -1) {
                this.fEndOffset = this.fOffset;
                return n4;
            }
            return n4 + read;
        }
    }
    
    public long skip(long n) throws IOException {
        if (n <= 0L) {
            return 0L;
        }
        final int n2 = this.fLength - this.fOffset;
        if (n2 == 0) {
            if (this.fOffset == this.fEndOffset) {
                return 0L;
            }
            return this.fInputStream.skip(n);
        }
        else {
            if (n <= n2) {
                this.fOffset += (int)n;
                return n;
            }
            this.fOffset += n2;
            if (this.fOffset == this.fEndOffset) {
                return n2;
            }
            n -= n2;
            return this.fInputStream.skip(n) + n2;
        }
    }
    
    public int available() throws IOException {
        final int n = this.fLength - this.fOffset;
        if (n == 0) {
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            return this.fInputStream.available();
        }
        else {
            if (this.fLength == this.fEndOffset) {
                return n;
            }
            return this.fInputStream.available() + n;
        }
    }
    
    public void mark(final int n) {
        this.fMark = this.fOffset;
    }
    
    public void reset() {
        this.fOffset = this.fMark;
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public void close() throws IOException {
        if (this.fInputStream != null) {
            this.fInputStream.close();
            this.fInputStream = null;
        }
    }
}
