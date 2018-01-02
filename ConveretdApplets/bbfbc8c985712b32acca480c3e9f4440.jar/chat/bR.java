// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.UTFDataFormatException;
import java.io.PushbackInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.DataInput;
import java.io.FilterInputStream;

public final class bR extends FilterInputStream implements DataInput
{
    byte[] a;
    int a;
    private byte[] b;
    private char[] a;
    
    public bR(final InputStream inputStream) {
        super(inputStream);
        this.a = 0;
        this.b = new byte[8];
    }
    
    public final int read() {
        int read = super.in.read();
        if (this.a != null && this.a.length > 0) {
            read ^= this.a[this.a()];
        }
        return read;
    }
    
    private int a() {
        return this.a = (this.a + 1) % this.a.length;
    }
    
    public final byte readByte() {
        final int read;
        if ((read = this.read()) < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    public final char readChar() {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (char)((read << 8) + read2);
    }
    
    public final double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }
    
    public final float readFloat() {
        return Float.intBitsToFloat(this.readInt());
    }
    
    public final int readInt() {
        final int read = this.read();
        final int read2 = this.read();
        final int read3 = this.read();
        final int read4 = this.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
    }
    
    public final int readUnsignedByte() {
        final int read;
        if ((read = this.read()) < 0) {
            throw new EOFException();
        }
        return read;
    }
    
    public final int readUnsignedShort() {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (read << 8) + read2;
    }
    
    public final long readLong() {
        this.readFully(this.b, 0, 8);
        return (this.b[0] << 56) + ((this.b[1] & 0xFF) << 48) + ((this.b[2] & 0xFF) << 40) + ((this.b[3] & 0xFF) << 32) + ((this.b[4] & 0xFF) << 24) + ((this.b[5] & 0xFF) << 16) + ((this.b[6] & 0xFF) << 8) + (this.b[7] & 0xFF);
    }
    
    public final short readShort() {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)((read << 8) + read2);
    }
    
    public final boolean readBoolean() {
        final int read;
        if ((read = this.read()) < 0) {
            throw new EOFException();
        }
        return read != 0;
    }
    
    public final int skipBytes(final int n) {
        int n2;
        int n3;
        for (n2 = 0; n2 < n && (n3 = (int)super.in.skip(n - n2)) > 0; n2 += n3) {}
        return n2;
    }
    
    public final int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    public final void readFully(final byte[] array) {
        this.readFully(array, 0, array.length);
    }
    
    public final int read(final byte[] array, int read, int i) {
        read = super.in.read(array, read, i);
        if (this.a != null && this.a.length > 0) {
            int n;
            for (i = 0; i < array.length; ++i) {
                n = i;
                array[n] ^= this.a[this.a()];
            }
        }
        return read;
    }
    
    public final void readFully(final byte[] array, final int n, final int n2) {
        if (n2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int read;
        for (int i = 0; i < n2; i += read) {
            if ((read = super.in.read(array, n + i, n2 - i)) < 0) {
                throw new EOFException();
            }
        }
        if (this.a != null && this.a.length > 0) {
            for (int j = 0; j < array.length; ++j) {
                final int n3 = j;
                array[n3] ^= this.a[this.a()];
            }
        }
    }
    
    public final String readLine() {
        char[] a;
        if ((a = this.a) == null) {
            final char[] a2 = new char[128];
            this.a = a2;
            a = a2;
        }
        int length = a.length;
        int n = 0;
        int read = 0;
    Label_0176:
        while (true) {
            switch (read = this.read()) {
                case -1:
                case 10: {
                    break Label_0176;
                }
                case 13: {
                    final int read2;
                    if ((read2 = this.read()) != 10 && read2 != -1) {
                        if (!(super.in instanceof PushbackInputStream)) {
                            super.in = new PushbackInputStream(super.in);
                        }
                        ((PushbackInputStream)super.in).unread(read2);
                        break Label_0176;
                    }
                    break Label_0176;
                }
                default: {
                    if (--length < 0) {
                        length = (a = new char[n + 128]).length - n - 1;
                        System.arraycopy(this.a, 0, a, 0, n);
                        this.a = a;
                    }
                    a[n++] = (char)read;
                    continue;
                }
            }
        }
        if (read == -1 && n == 0) {
            return null;
        }
        return String.copyValueOf(a, 0, n);
    }
    
    public final String readUTF() {
        final int unsignedShort = (this = this).readUnsignedShort();
        final StringBuffer sb = new StringBuffer(unsignedShort);
        final byte[] array = new byte[unsignedShort];
        int i = 0;
        super.readFully(array, 0, unsignedShort);
        while (i < unsignedShort) {
            final int n;
            switch ((n = (array[i] & 0xFF)) >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    ++i;
                    sb.append((char)n);
                    continue;
                }
                case 12:
                case 13: {
                    i += 2;
                    if (i > unsignedShort) {
                        throw new UTFDataFormatException();
                    }
                    final byte b;
                    if (((b = array[i - 1]) & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    sb.append((char)((n & 0x1F) << 6 | (b & 0x3F)));
                    continue;
                }
                case 14: {
                    i += 3;
                    if (i > unsignedShort) {
                        throw new UTFDataFormatException();
                    }
                    final byte b2 = array[i - 2];
                    final byte b3 = array[i - 1];
                    if ((b2 & 0xC0) != 0x80 || (b3 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    sb.append((char)((n & 0xF) << 12 | (b2 & 0x3F) << 6 | (b3 & 0x3F)));
                    continue;
                }
                default: {
                    throw new UTFDataFormatException();
                }
            }
        }
        return new String(sb);
    }
}
