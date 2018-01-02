// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.UTFDataFormatException;
import java.io.PushbackInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.DataInput;
import java.io.BufferedInputStream;

public final class bA extends BufferedInputStream implements DataInput
{
    byte[] q;
    int q;
    private byte[] w;
    private char[] q;
    
    public bA(final InputStream inputStream) {
        super(inputStream);
        this.q = F.q;
        this.q = 0;
        this.w = new byte[8];
    }
    
    public final int read() {
        int read = this.in.read();
        if (this.q != null && this.q.length > 0) {
            read ^= this.q[this.w()];
        }
        return read;
    }
    
    private int w() {
        return this.q = (this.q + 1) % this.q.length;
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
    
    public final int q() {
        final int read = this.in.read();
        final int read2 = this.in.read();
        final int read3 = this.in.read();
        final int read4 = this.in.read();
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
        this.readFully(this.w, 0, 8);
        return (this.w[0] << 56) + ((this.w[1] & 0xFF) << 48) + ((this.w[2] & 0xFF) << 40) + ((this.w[3] & 0xFF) << 32) + ((this.w[4] & 0xFF) << 24) + ((this.w[5] & 0xFF) << 16) + ((this.w[6] & 0xFF) << 8) + (this.w[7] & 0xFF);
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
        for (n2 = 0; n2 < n && (n3 = (int)this.in.skip(n - n2)) > 0; n2 += n3) {}
        return n2;
    }
    
    public final int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    public final void readFully(final byte[] array) {
        this.readFully(array, 0, array.length);
    }
    
    public final int read(final byte[] array, int read, int i) {
        read = this.in.read(array, read, i);
        if (this.q != null && this.q.length > 0) {
            int n;
            for (i = 0; i < array.length; ++i) {
                n = i;
                array[n] ^= this.q[this.w()];
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
            if ((read = this.in.read(array, n + i, n2 - i)) < 0) {
                throw new EOFException();
            }
        }
        if (this.q != null && this.q.length > 0) {
            for (int j = 0; j < array.length; ++j) {
                final int n3 = j;
                array[n3] ^= this.q[this.w()];
            }
        }
    }
    
    public final String readLine() {
        char[] q;
        if ((q = this.q) == null) {
            final char[] q2 = new char[128];
            this.q = q2;
            q = q2;
        }
        int length = q.length;
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
                        if (!(this.in instanceof PushbackInputStream)) {
                            this.in = new PushbackInputStream(this.in);
                        }
                        ((PushbackInputStream)this.in).unread(read2);
                        break Label_0176;
                    }
                    break Label_0176;
                }
                default: {
                    if (--length < 0) {
                        length = (q = new char[n + 128]).length - n - 1;
                        System.arraycopy(this.q, 0, q, 0, n);
                        this.q = q;
                    }
                    q[n++] = (char)read;
                    continue;
                }
            }
        }
        if (read == -1 && n == 0) {
            return null;
        }
        return String.copyValueOf(q, 0, n);
    }
    
    public final String readUTF() {
        return readUTF(this);
    }
    
    public static final String readUTF(final DataInput dataInput) {
        final int unsignedShort = dataInput.readUnsignedShort();
        final StringBuffer sb = new StringBuffer(unsignedShort);
        final byte[] array = new byte[unsignedShort];
        int i = 0;
        dataInput.readFully(array, 0, unsignedShort);
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
