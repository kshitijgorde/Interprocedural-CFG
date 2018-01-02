// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UCSReader extends Reader
{
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final short UCS2LE = 1;
    public static final short UCS2BE = 2;
    public static final short UCS4LE = 4;
    public static final short UCS4BE = 8;
    protected InputStream fInputStream;
    protected byte[] fBuffer;
    protected short fEncoding;
    
    public UCSReader(final InputStream inputStream, final short encoding) {
        this(inputStream, 8192, encoding);
    }
    
    public UCSReader(final InputStream inputStream, final int size, final short encoding) {
        this.fInputStream = inputStream;
        this.fBuffer = new byte[size];
        this.fEncoding = encoding;
    }
    
    public int read() throws IOException {
        final int b0 = this.fInputStream.read() & 0xFF;
        if (b0 == 255) {
            return -1;
        }
        final int b2 = this.fInputStream.read() & 0xFF;
        if (b2 == 255) {
            return -1;
        }
        if (this.fEncoding >= 4) {
            final int b3 = this.fInputStream.read() & 0xFF;
            if (b3 == 255) {
                return -1;
            }
            final int b4 = this.fInputStream.read() & 0xFF;
            if (b4 == 255) {
                return -1;
            }
            System.err.println("b0 is " + (b0 & 0xFF) + " b1 " + (b2 & 0xFF) + " b2 " + (b3 & 0xFF) + " b3 " + (b4 & 0xFF));
            if (this.fEncoding == 8) {
                return (b0 << 24) + (b2 << 16) + (b3 << 8) + b4;
            }
            return (b4 << 24) + (b3 << 16) + (b2 << 8) + b0;
        }
        else {
            if (this.fEncoding == 2) {
                return (b0 << 8) + b2;
            }
            return (b2 << 8) + b0;
        }
    }
    
    public int read(final char[] ch, final int offset, final int length) throws IOException {
        int byteLength = length << ((this.fEncoding >= 4) ? 2 : 1);
        if (byteLength > this.fBuffer.length) {
            byteLength = this.fBuffer.length;
        }
        int count = this.fInputStream.read(this.fBuffer, 0, byteLength);
        if (count == -1) {
            return -1;
        }
        if (this.fEncoding >= 4) {
            final int numToRead = 4 - (count & 0x3) & 0x3;
            for (int i = 0; i < numToRead; ++i) {
                final int charRead = this.fInputStream.read();
                if (charRead == -1) {
                    for (int j = i; j < numToRead; ++j) {
                        this.fBuffer[count + j] = 0;
                    }
                    break;
                }
                this.fBuffer[count + i] = (byte)charRead;
            }
            count += numToRead;
        }
        else {
            final int numToRead = count & 0x1;
            if (numToRead != 0) {
                ++count;
                final int charRead2 = this.fInputStream.read();
                if (charRead2 == -1) {
                    this.fBuffer[count] = 0;
                }
                else {
                    this.fBuffer[count] = (byte)charRead2;
                }
            }
        }
        final int numChars = count >> ((this.fEncoding >= 4) ? 2 : 1);
        int curPos = 0;
        for (int k = 0; k < numChars; ++k) {
            final int b0 = this.fBuffer[curPos++] & 0xFF;
            final int b2 = this.fBuffer[curPos++] & 0xFF;
            if (this.fEncoding >= 4) {
                final int b3 = this.fBuffer[curPos++] & 0xFF;
                final int b4 = this.fBuffer[curPos++] & 0xFF;
                if (this.fEncoding == 8) {
                    ch[offset + k] = (char)((b0 << 24) + (b2 << 16) + (b3 << 8) + b4);
                }
                else {
                    ch[offset + k] = (char)((b4 << 24) + (b3 << 16) + (b2 << 8) + b0);
                }
            }
            else if (this.fEncoding == 2) {
                ch[offset + k] = (char)((b0 << 8) + b2);
            }
            else {
                ch[offset + k] = (char)((b2 << 8) + b0);
            }
        }
        return numChars;
    }
    
    public long skip(final long n) throws IOException {
        final int charWidth = (this.fEncoding >= 4) ? 2 : 1;
        final long bytesSkipped = this.fInputStream.skip(n << charWidth);
        if ((bytesSkipped & (charWidth | 0x1)) == 0x0L) {
            return bytesSkipped >> charWidth;
        }
        return (bytesSkipped >> charWidth) + 1L;
    }
    
    public boolean ready() throws IOException {
        return false;
    }
    
    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }
    
    public void mark(final int readAheadLimit) throws IOException {
        this.fInputStream.mark(readAheadLimit);
    }
    
    public void reset() throws IOException {
        this.fInputStream.reset();
    }
    
    public void close() throws IOException {
        this.fInputStream.close();
    }
}
