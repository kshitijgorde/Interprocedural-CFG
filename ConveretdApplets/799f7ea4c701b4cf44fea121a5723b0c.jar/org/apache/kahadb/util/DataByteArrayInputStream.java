// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.DataInput;
import java.io.InputStream;

public final class DataByteArrayInputStream extends InputStream implements DataInput
{
    private byte[] buf;
    private int pos;
    private int offset;
    private int length;
    
    public DataByteArrayInputStream(final byte[] buf) {
        this.buf = buf;
        this.pos = 0;
        this.offset = 0;
        this.length = buf.length;
    }
    
    public DataByteArrayInputStream(final ByteSequence sequence) {
        this.buf = sequence.getData();
        this.offset = sequence.getOffset();
        this.pos = this.offset;
        this.length = sequence.length;
    }
    
    public DataByteArrayInputStream() {
        this(new byte[0]);
    }
    
    public int size() {
        return this.pos - this.offset;
    }
    
    public byte[] getRawData() {
        return this.buf;
    }
    
    public void restart(final byte[] newBuff) {
        this.buf = newBuff;
        this.pos = 0;
        this.length = newBuff.length;
    }
    
    public void restart() {
        this.pos = 0;
        this.length = this.buf.length;
    }
    
    public void restart(final ByteSequence sequence) {
        this.buf = sequence.getData();
        this.pos = sequence.getOffset();
        this.length = sequence.getLength();
    }
    
    public void restart(final int size) {
        if (this.buf == null || this.buf.length < size) {
            this.buf = new byte[size];
        }
        this.restart(this.buf);
        this.length = size;
    }
    
    public int read() {
        return (this.pos < this.length) ? (this.buf[this.pos++] & 0xFF) : -1;
    }
    
    public int read(final byte[] b, final int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if (this.pos >= this.length) {
            return -1;
        }
        if (this.pos + len > this.length) {
            len = this.length - this.pos;
        }
        if (len <= 0) {
            return 0;
        }
        System.arraycopy(this.buf, this.pos, b, off, len);
        this.pos += len;
        return len;
    }
    
    public int available() {
        return this.length - this.pos;
    }
    
    public void readFully(final byte[] b) {
        this.read(b, 0, b.length);
    }
    
    public void readFully(final byte[] b, final int off, final int len) {
        this.read(b, off, len);
    }
    
    public int skipBytes(int n) {
        if (this.pos + n > this.length) {
            n = this.length - this.pos;
        }
        if (n < 0) {
            return 0;
        }
        this.pos += n;
        return n;
    }
    
    public boolean readBoolean() {
        return this.read() != 0;
    }
    
    public byte readByte() {
        return (byte)this.read();
    }
    
    public int readUnsignedByte() {
        return this.read();
    }
    
    public short readShort() {
        final int ch1 = this.read();
        final int ch2 = this.read();
        return (short)((ch1 << 8) + (ch2 << 0));
    }
    
    public int readUnsignedShort() {
        final int ch1 = this.read();
        final int ch2 = this.read();
        return (ch1 << 8) + (ch2 << 0);
    }
    
    public char readChar() {
        final int ch1 = this.read();
        final int ch2 = this.read();
        return (char)((ch1 << 8) + (ch2 << 0));
    }
    
    public int readInt() {
        final int ch1 = this.read();
        final int ch2 = this.read();
        final int ch3 = this.read();
        final int ch4 = this.read();
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }
    
    public long readLong() {
        final long rc = (this.buf[this.pos++] << 56) + ((this.buf[this.pos++] & 0xFF) << 48) + ((this.buf[this.pos++] & 0xFF) << 40) + ((this.buf[this.pos++] & 0xFF) << 32);
        return rc + ((this.buf[this.pos++] & 0xFF) << 24) + ((this.buf[this.pos++] & 0xFF) << 16) + ((this.buf[this.pos++] & 0xFF) << 8) + ((this.buf[this.pos++] & 0xFF) << 0);
    }
    
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }
    
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readLong());
    }
    
    public String readLine() {
        final int start = this.pos;
        while (this.pos < this.length) {
            int c = this.read();
            if (c == 10) {
                break;
            }
            if (c != 13) {
                continue;
            }
            c = this.read();
            if (c != 10 && c != -1) {
                --this.pos;
                break;
            }
            break;
        }
        return new String(this.buf, start, this.pos);
    }
    
    public String readUTF() throws IOException {
        final int length = this.readUnsignedShort();
        final char[] characters = new char[length];
        int count = 0;
        final int total = this.pos + length;
        while (this.pos < total) {
            final int c = this.buf[this.pos] & 0xFF;
            if (c > 127) {
                break;
            }
            ++this.pos;
            characters[count++] = (char)c;
        }
        while (this.pos < total) {
            final int c = this.buf[this.pos] & 0xFF;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    ++this.pos;
                    characters[count++] = (char)c;
                    continue;
                }
                case 12:
                case 13: {
                    this.pos += 2;
                    if (this.pos > length) {
                        throw new UTFDataFormatException("bad string");
                    }
                    final int c2 = this.buf[this.pos - 1];
                    if ((c2 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException("bad string");
                    }
                    characters[count++] = (char)((c & 0x1F) << 6 | (c2 & 0x3F));
                    continue;
                }
                case 14: {
                    this.pos += 3;
                    if (this.pos > length) {
                        throw new UTFDataFormatException("bad string");
                    }
                    final int c2 = this.buf[this.pos - 2];
                    final int c3 = this.buf[this.pos - 1];
                    if ((c2 & 0xC0) != 0x80 || (c3 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException("bad string");
                    }
                    characters[count++] = (char)((c & 0xF) << 12 | (c2 & 0x3F) << 6 | (c3 & 0x3F) << 0);
                    continue;
                }
                default: {
                    throw new UTFDataFormatException("bad string");
                }
            }
        }
        return new String(characters, 0, count);
    }
    
    public int getPos() {
        return this.pos;
    }
    
    public void setPos(final int pos) {
        this.pos = pos;
    }
    
    public int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
}
