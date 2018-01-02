import java.io.InputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class e extends FilterInputStream
{
    public final byte readByte() throws IOException {
        final int read = super.in.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }
    
    e(final InputStream inputStream) {
        super(inputStream);
    }
    
    public final void readFully(final byte[] array) throws IOException {
        final InputStream in = super.in;
        int n = 0;
        while (true) {
            Label_0043: {
                if (q.a == 0) {
                    break Label_0043;
                }
                final int read = in.read(array, n, array.length - n);
                if (read < 0) {
                    throw new EOFException();
                }
                n += read;
            }
            if (n >= array.length) {
                return;
            }
            continue;
        }
    }
    
    public final short readShort() throws IOException {
        final InputStream in = super.in;
        final int read = in.read();
        final int read2 = in.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)(read + (read2 << 8));
    }
    
    public final int readInt() throws IOException {
        final InputStream in = super.in;
        final int read = in.read();
        final int read2 = in.read();
        final int read3 = in.read();
        final int read4 = in.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
    }
}
