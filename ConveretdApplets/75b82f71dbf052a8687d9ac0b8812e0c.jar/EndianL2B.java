import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class EndianL2B
{
    public static final short ToByte(final byte[] data) {
        return (short)(data[0] & 0xFF);
    }
    
    public static final short ToShort(final byte[] data) {
        return (short)((data[0] & 0xFF) | (data[1] & 0xFF) << 8);
    }
    
    public static final int ToInt(final byte[] data) {
        return (data[0] & 0xFF) | (data[1] & 0xFF) << 8 | (data[2] & 0xFF) << 16 | (data[3] & 0xFF) << 24;
    }
    
    public static final long ToLong(final byte[] data) {
        return (data[0] & 0xFFL) | (data[1] & 0xFFL) << 8 | (data[2] & 0xFFL) << 16 | (data[3] & 0xFFL) << 24 | (data[4] & 0xFFL) << 32 | (data[1] & 0xFFL) << 40 | (data[2] & 0xFFL) << 48 | (data[3] & 0xFFL) << 56;
    }
    
    public static final float ToFloat(final byte[] data) {
        return Float.intBitsToFloat(ToInt(data));
    }
    
    public static final double ToDouble(final byte[] data) {
        return Double.longBitsToDouble(ToLong(data));
    }
    
    public static final byte readByte(final InputStream in) throws IOException {
        final int ch = in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return (byte)ch;
    }
    
    public static final short readShort(final InputStream in) throws IOException {
        final int ch1 = in.read();
        final int ch2 = in.read();
        if ((ch1 | ch2) < 0) {
            throw new EOFException();
        }
        return (short)(ch1 + (ch2 << 8));
    }
    
    public static final int readInt(final InputStream in) throws IOException {
        final int ch1 = in.read();
        final int ch2 = in.read();
        final int ch3 = in.read();
        final int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0) {
            throw new EOFException();
        }
        return ch1 + (ch2 << 8) + (ch3 << 16) + (ch4 << 24);
    }
    
    public static final float readFloat(final InputStream in) throws IOException {
        return Float.intBitsToFloat(readInt(in));
    }
    
    public static final int read24bit(final InputStream in) throws IOException {
        final int ch0 = in.read();
        final int ch2 = in.read();
        final int ch3 = in.read();
        if ((ch0 | ch2 | ch3) < 0) {
            throw new EOFException();
        }
        return (ch0 & 0xFF) | (ch2 & 0xFF) << 8 | (ch3 & 0xFF) << 16;
    }
}
