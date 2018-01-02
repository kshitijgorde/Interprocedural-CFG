// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.UTFDataFormatException;
import java.io.OutputStream;
import java.io.DataOutput;
import java.io.FilterOutputStream;

public final class bN extends FilterOutputStream implements DataOutput
{
    byte[] a;
    int a;
    private byte[] b;
    
    public bN(final OutputStream outputStream) {
        super(outputStream);
        this.a = 0;
        this.b = new byte[8];
    }
    
    public final synchronized void write(int n) {
        if (this.a != null && this.a.length > 0) {
            final int n2 = n;
            final byte[] a = this.a;
            this.a = (this.a + 1) % this.a.length;
            n = (n2 ^ a[this.a]);
        }
        super.out.write(n);
    }
    
    public final void flush() {
        super.out.flush();
    }
    
    public final void writeDouble(final double n) {
        this.writeLong(Double.doubleToLongBits(n));
    }
    
    public final void writeFloat(final float n) {
        this.writeInt(Float.floatToIntBits(n));
    }
    
    public final void writeByte(final int n) {
        this.write(n);
    }
    
    public final void writeChar(final int n) {
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
    }
    
    public final void writeInt(final int n) {
        this.write(n >>> 24);
        this.write(n >>> 16 & 0xFF);
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
    }
    
    public final void writeShort(final int n) {
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
    }
    
    public final void writeLong(final long n) {
        this.b[0] = (byte)(n >>> 56);
        this.b[1] = (byte)(n >>> 48);
        this.b[2] = (byte)(n >>> 40);
        this.b[3] = (byte)(n >>> 32);
        this.b[4] = (byte)(n >>> 24);
        this.b[5] = (byte)(n >>> 16);
        this.b[6] = (byte)(n >>> 8);
        this.b[7] = (byte)n;
        this.write(this.b, 0, 8);
    }
    
    public final void writeBoolean(final boolean b) {
        this.write(b ? 1 : 0);
    }
    
    public final synchronized void write(final byte[] array, final int n, final int n2) {
        if ((n | n2 | array.length - (n2 + n) | n + n2) < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < n2; ++i) {
            this.write(array[n + i]);
        }
    }
    
    public final void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    public final void writeBytes(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.write((byte)s.charAt(i));
        }
    }
    
    public final void writeChars(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            this.write(char1 >>> 8 & '\u00ff');
            this.write(char1 & '\u00ff');
        }
    }
    
    public final void writeUTF(final String s) {
        final int length = s.length();
        int n = 0;
        final char[] array = new char[length];
        int n2 = 0;
        s.getChars(0, length, array, 0);
        for (int i = 0; i < length; ++i) {
            final char c;
            if ((c = array[i]) >= '\u0001' && c <= '\u007f') {
                ++n;
            }
            else if (c > '\u07ff') {
                n += 3;
            }
            else {
                n += 2;
            }
        }
        if (n > 65535) {
            throw new UTFDataFormatException();
        }
        ++n2;
        final byte[] array2;
        (array2 = new byte[n + 2])[0] = (byte)(n >>> 8);
        ++n2;
        array2[1] = (byte)n;
        for (int j = 0; j < length; ++j) {
            final char c2;
            if ((c2 = array[j]) >= '\u0001' && c2 <= '\u007f') {
                array2[n2++] = (byte)c2;
            }
            else if (c2 > '\u07ff') {
                array2[n2++] = (byte)('\u00e0' | (c2 >> 12 & '\u000f'));
                array2[n2++] = (byte)('\u0080' | (c2 >> 6 & '?'));
                array2[n2++] = (byte)('\u0080' | (c2 & '?'));
            }
            else {
                array2[n2++] = (byte)('\u00c0' | (c2 >> 6 & '\u001f'));
                array2[n2++] = (byte)('\u0080' | (c2 & '?'));
            }
        }
        super.write(array2);
    }
}