// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.DataOutput;
import java.io.OutputStream;

public class DataByteArrayOutputStream extends OutputStream implements DataOutput
{
    private static final int DEFAULT_SIZE = 2048;
    protected byte[] buf;
    protected int pos;
    
    public DataByteArrayOutputStream(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        this.buf = new byte[size];
    }
    
    public DataByteArrayOutputStream() {
        this(2048);
    }
    
    public void restart(final int size) {
        this.buf = new byte[size];
        this.pos = 0;
    }
    
    public void restart() {
        this.restart(2048);
    }
    
    public ByteSequence toByteSequence() {
        return new ByteSequence(this.buf, 0, this.pos);
    }
    
    public void write(final int b) throws IOException {
        final int newcount = this.pos + 1;
        this.ensureEnoughBuffer(newcount);
        this.buf[this.pos] = (byte)b;
        this.pos = newcount;
        this.onWrite();
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (len == 0) {
            return;
        }
        final int newcount = this.pos + len;
        this.ensureEnoughBuffer(newcount);
        System.arraycopy(b, off, this.buf, this.pos, len);
        this.pos = newcount;
        this.onWrite();
    }
    
    public byte[] getData() {
        return this.buf;
    }
    
    public void reset() {
        this.pos = 0;
    }
    
    public void position(final int offset) throws IOException {
        this.ensureEnoughBuffer(offset);
        this.pos = offset;
        this.onWrite();
    }
    
    public int size() {
        return this.pos;
    }
    
    public void writeBoolean(final boolean v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 1);
        this.buf[this.pos++] = (byte)(v ? 1 : 0);
        this.onWrite();
    }
    
    public void writeByte(final int v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 1);
        this.buf[this.pos++] = (byte)(v >>> 0);
        this.onWrite();
    }
    
    public void writeShort(final int v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 2);
        this.buf[this.pos++] = (byte)(v >>> 8);
        this.buf[this.pos++] = (byte)(v >>> 0);
        this.onWrite();
    }
    
    public void writeChar(final int v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 2);
        this.buf[this.pos++] = (byte)(v >>> 8);
        this.buf[this.pos++] = (byte)(v >>> 0);
        this.onWrite();
    }
    
    public void writeInt(final int v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 4);
        this.buf[this.pos++] = (byte)(v >>> 24);
        this.buf[this.pos++] = (byte)(v >>> 16);
        this.buf[this.pos++] = (byte)(v >>> 8);
        this.buf[this.pos++] = (byte)(v >>> 0);
        this.onWrite();
    }
    
    public void writeLong(final long v) throws IOException {
        this.ensureEnoughBuffer(this.pos + 8);
        this.buf[this.pos++] = (byte)(v >>> 56);
        this.buf[this.pos++] = (byte)(v >>> 48);
        this.buf[this.pos++] = (byte)(v >>> 40);
        this.buf[this.pos++] = (byte)(v >>> 32);
        this.buf[this.pos++] = (byte)(v >>> 24);
        this.buf[this.pos++] = (byte)(v >>> 16);
        this.buf[this.pos++] = (byte)(v >>> 8);
        this.buf[this.pos++] = (byte)(v >>> 0);
        this.onWrite();
    }
    
    public void writeFloat(final float v) throws IOException {
        this.writeInt(Float.floatToIntBits(v));
    }
    
    public void writeDouble(final double v) throws IOException {
        this.writeLong(Double.doubleToLongBits(v));
    }
    
    public void writeBytes(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.write((byte)s.charAt(i));
        }
    }
    
    public void writeChars(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final int c = s.charAt(i);
            this.write(c >>> 8 & 0xFF);
            this.write(c >>> 0 & 0xFF);
        }
    }
    
    public void writeUTF(final String str) throws IOException {
        final int strlen = str.length();
        int encodedsize = 0;
        for (int i = 0; i < strlen; ++i) {
            final int c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                ++encodedsize;
            }
            else if (c > 2047) {
                encodedsize += 3;
            }
            else {
                encodedsize += 2;
            }
        }
        if (encodedsize > 65535) {
            throw new UTFDataFormatException("encoded string too long: " + encodedsize + " bytes");
        }
        this.ensureEnoughBuffer(this.pos + encodedsize + 2);
        this.writeShort(encodedsize);
        int i;
        int c;
        for (i = 0, i = 0; i < strlen; ++i) {
            c = str.charAt(i);
            if (c < 1) {
                break;
            }
            if (c > 127) {
                break;
            }
            this.buf[this.pos++] = (byte)c;
        }
        while (i < strlen) {
            c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                this.buf[this.pos++] = (byte)c;
            }
            else if (c > 2047) {
                this.buf[this.pos++] = (byte)(0xE0 | (c >> 12 & 0xF));
                this.buf[this.pos++] = (byte)(0x80 | (c >> 6 & 0x3F));
                this.buf[this.pos++] = (byte)(0x80 | (c >> 0 & 0x3F));
            }
            else {
                this.buf[this.pos++] = (byte)(0xC0 | (c >> 6 & 0x1F));
                this.buf[this.pos++] = (byte)(0x80 | (c >> 0 & 0x3F));
            }
            ++i;
        }
        this.onWrite();
    }
    
    private void ensureEnoughBuffer(final int newcount) {
        if (newcount > this.buf.length) {
            final byte[] newbuf = new byte[Math.max(this.buf.length << 1, newcount)];
            System.arraycopy(this.buf, 0, newbuf, 0, this.pos);
            this.buf = newbuf;
        }
    }
    
    protected void onWrite() throws IOException {
    }
    
    public void skip(final int size) throws IOException {
        this.ensureEnoughBuffer(this.pos + size);
        this.pos += size;
        this.onWrite();
    }
    
    public ByteSequence getByteSequence() {
        return new ByteSequence(this.buf, 0, this.pos);
    }
}
