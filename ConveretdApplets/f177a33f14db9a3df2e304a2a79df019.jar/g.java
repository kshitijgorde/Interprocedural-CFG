import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class g
{
    public static final short a(final byte[] array) {
        return (short)(array[0] & 0xFF);
    }
    
    public static final short b(final byte[] array) {
        return (short)((array[0] & 0xFF) | (array[1] & 0xFF) << 8);
    }
    
    public static final int c(final byte[] array) {
        return (array[0] & 0xFF) | (array[1] & 0xFF) << 8 | (array[2] & 0xFF) << 16 | (array[3] & 0xFF) << 24;
    }
    
    public static final long d(final byte[] array) {
        return (array[0] & 0xFFL) | (array[1] & 0xFFL) << 8 | (array[2] & 0xFFL) << 16 | (array[3] & 0xFFL) << 24 | (array[4] & 0xFFL) << 32 | (array[1] & 0xFFL) << 40 | (array[2] & 0xFFL) << 48 | (array[3] & 0xFFL) << 56;
    }
    
    public static final float e(final byte[] array) {
        return Float.intBitsToFloat(c(array));
    }
    
    public static final double f(final byte[] array) {
        return Double.longBitsToDouble(d(array));
    }
    
    public static final byte a(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    public static final short b(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)(read + (read2 << 8));
    }
    
    public static final int c(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        final int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
    }
    
    public static final float d(final InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(c(inputStream));
    }
    
    public static final int e(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        if ((read | read2 | read3) < 0) {
            throw new EOFException();
        }
        return (read & 0xFF) | (read2 & 0xFF) << 8 | (read3 & 0xFF) << 16;
    }
}
