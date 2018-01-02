// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.UTFDataFormatException;
import java.io.PushbackInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.DataInput;
import java.io.FilterInputStream;

public final class J extends FilterInputStream implements DataInput
{
    private byte[] b;
    private int A;
    private byte[] d;
    private char[] a;
    
    public J(final InputStream inputStream) {
        super(inputStream);
        this.A = 0;
        this.d = new byte[8];
    }
    
    public final int read() {
        int read = super.in.read();
        if (this.b != null && this.b.length > 0) {
            read ^= (this.b[this.e()] ^ 0x7F);
        }
        return read;
    }
    
    public final void a(final byte[] b) {
        this.b = b;
        this.A = 0;
    }
    
    private int e() {
        return this.A = (this.A + 1) % this.b.length;
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
        return (char)((read << 8) + (read2 << 0));
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
        return (read << 24) + (read2 << 16) + (read3 << 8) + (read4 << 0);
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
        return (read << 8) + (read2 << 0);
    }
    
    public final long readLong() {
        this.readFully(this.d, 0, 8);
        return (this.d[0] << 56) + ((this.d[1] & 0xFF) << 48) + ((this.d[2] & 0xFF) << 40) + ((this.d[3] & 0xFF) << 32) + ((this.d[4] & 0xFF) << 24) + ((this.d[5] & 0xFF) << 16) + ((this.d[6] & 0xFF) << 8) + ((this.d[7] & 0xFF) << 0);
    }
    
    public final short readShort() {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)((read << 8) + (read2 << 0));
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
    
    public final int read(final byte[] array, final int n, final int n2) {
        final int read = super.in.read(array, n, n2);
        if (this.b != null && this.b.length > 0) {
            for (int i = 0; i < array.length; ++i) {
                final int n3 = i;
                array[n3] ^= (byte)(this.b[this.e()] ^ 0x7F);
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
        if (this.b != null && this.b.length > 0) {
            for (int j = 0; j < array.length; ++j) {
                final int n3 = j;
                array[n3] ^= (byte)(this.b[this.e()] ^ 0x7F);
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
    Label_0179:
        while (true) {
            switch (read = this.read()) {
                case -1:
                case 10: {
                    break Label_0179;
                }
                case 13: {
                    final int read2;
                    if ((read2 = this.read()) != 10 && read2 != -1) {
                        if (!(super.in instanceof PushbackInputStream)) {
                            super.in = new PushbackInputStream(super.in);
                        }
                        ((PushbackInputStream)super.in).unread(read2);
                        break Label_0179;
                    }
                    break Label_0179;
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
        return a(this);
    }
    
    private static String a(final DataInput dataInput) {
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
                    sb.append((char)((n & 0xF) << 12 | (b2 & 0x3F) << 6 | (b3 & 0x3F) << 0));
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
