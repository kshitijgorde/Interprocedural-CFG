// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.DataInput;
import java.io.InputStream;

public abstract class X extends InputStream implements DataInput
{
    protected long B;
    private byte[] A;
    
    public X() {
        this.B = -1L;
        this.A = new byte[4];
    }
    
    public static X A(final InputStream inputStream, final boolean b) {
        X x;
        if (b) {
            try {
                x = new u(inputStream);
            }
            catch (Exception ex) {
                x = new d(inputStream);
            }
        }
        else {
            x = new n(inputStream);
        }
        return x;
    }
    
    public abstract int read() throws IOException;
    
    public abstract int read(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public synchronized void mark(final int n) {
        try {
            this.B = this.G();
        }
        catch (IOException ex) {
            this.B = -1L;
        }
    }
    
    public synchronized void reset() throws IOException {
        if (this.B != -1L) {
            this.A(this.B);
        }
    }
    
    public boolean markSupported() {
        return this.B();
    }
    
    public boolean B() {
        return false;
    }
    
    public abstract long G() throws IOException;
    
    public abstract void A(final long p0) throws IOException;
    
    public final void readFully(final byte[] array) throws IOException {
        this.readFully(array, 0, array.length);
    }
    
    public final void readFully(final byte[] array, final int n, final int n2) throws IOException {
        int i = 0;
        do {
            final int read = this.read(array, n + i, n2 - i);
            if (read < 0) {
                throw new EOFException();
            }
            i += read;
        } while (i < n2);
    }
    
    public int skipBytes(final int n) throws IOException {
        if (n <= 0) {
            return 0;
        }
        return (int)this.skip(n);
    }
    
    public final boolean readBoolean() throws IOException {
        final int read = this.read();
        if (read < 0) {
            throw new EOFException();
        }
        return read != 0;
    }
    
    public final byte readByte() throws IOException {
        final int read = this.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    public final int readUnsignedByte() throws IOException {
        final int read = this.read();
        if (read < 0) {
            throw new EOFException();
        }
        return read;
    }
    
    public final short readShort() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)((read << 8) + (read2 << 0));
    }
    
    public final short I() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)((read2 << 8) + (read << 0));
    }
    
    public final int readUnsignedShort() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (read << 8) + (read2 << 0);
    }
    
    public final int C() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (read2 << 8) + (read << 0);
    }
    
    public final char readChar() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (char)((read << 8) + (read2 << 0));
    }
    
    public final char D() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (char)((read2 << 8) + (read << 0));
    }
    
    public final int readInt() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        final int read3 = this.read();
        final int read4 = this.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return (read << 24) + (read2 << 16) + (read3 << 8) + (read4 << 0);
    }
    
    public final int E() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        final int read3 = this.read();
        final int read4 = this.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return (read4 << 24) + (read3 << 16) + (read2 << 8) + (read << 0);
    }
    
    public final long A() throws IOException {
        final long n = this.read();
        final long n2 = this.read();
        final long n3 = this.read();
        final long n4 = this.read();
        if ((n | n2 | n3 | n4) < 0L) {
            throw new EOFException();
        }
        return (n << 24) + (n2 << 16) + (n3 << 8) + (n4 << 0);
    }
    
    public final long F() throws IOException {
        this.readFully(this.A);
        return ((this.A[3] & 0xFF) << 24) + ((this.A[2] & 0xFF) << 16) + ((this.A[1] & 0xFF) << 8) + ((this.A[0] & 0xFF) << 0);
    }
    
    public final long readLong() throws IOException {
        return (this.readInt() << 32) + (this.readInt() & 0xFFFFFFFFL);
    }
    
    public final long H() throws IOException {
        return (this.E() << 32) + (this.E() & 0xFFFFFFFFL);
    }
    
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }
    
    public final float J() throws IOException {
        return Float.intBitsToFloat(this.E());
    }
    
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readLong());
    }
    
    public final double K() throws IOException {
        return Double.longBitsToDouble(this.H());
    }
    
    public final String readLine() throws IOException {
        final StringBuffer sb = new StringBuffer();
        int read = -1;
        int i = 0;
        while (i == 0) {
            switch (read = this.read()) {
                case -1:
                case 10: {
                    i = 1;
                    continue;
                }
                case 13: {
                    i = 1;
                    final long g = this.G();
                    if (this.read() != 10) {
                        this.A(g);
                        continue;
                    }
                    continue;
                }
                default: {
                    sb.append((char)read);
                    continue;
                }
            }
        }
        if (read == -1 && sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
    
    public final String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.close();
    }
}
