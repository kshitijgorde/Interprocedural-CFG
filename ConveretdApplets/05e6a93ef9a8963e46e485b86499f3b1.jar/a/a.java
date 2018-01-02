// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.UTFDataFormatException;
import java.io.OutputStream;
import java.io.DataOutput;
import java.io.BufferedOutputStream;

public final class a extends BufferedOutputStream implements DataOutput
{
    byte[] q;
    int q;
    private int w;
    private byte[] w;
    
    public a(final OutputStream outputStream) {
        super(outputStream);
        this.q = G.q;
        this.q = 0;
        this.w = new byte[8];
    }
    
    public final synchronized void write(int n) {
        if (this.q != null && this.q.length > 0) {
            final int n2 = n;
            final byte[] q = this.q;
            this.q = (this.q + 1) % this.q.length;
            n = (n2 ^ q[this.q]);
        }
        this.out.write(n);
        this.w(1);
    }
    
    public final void flush() {
        this.out.flush();
    }
    
    public final void writeDouble(final double n) {
        this.writeLong(Double.doubleToLongBits(n));
    }
    
    public final void writeFloat(final float n) {
        this.writeInt(Float.floatToIntBits(n));
    }
    
    private void w(int w) {
        if ((w += this.w) < 0) {
            w = Integer.MAX_VALUE;
        }
        this.w = w;
    }
    
    public final void writeByte(final int n) {
        this.write(n);
        this.w(1);
    }
    
    public final void writeChar(final int n) {
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
        this.w(2);
    }
    
    public final void writeInt(final int n) {
        this.write(n >>> 24);
        this.write(n >>> 16 & 0xFF);
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
        this.w(4);
    }
    
    public final void q(final int n) {
        this.out.write(n >>> 24);
        this.out.write(n >>> 16 & 0xFF);
        this.out.write(n >>> 8 & 0xFF);
        this.out.write(n & 0xFF);
        this.w(4);
    }
    
    public final void writeShort(final int n) {
        this.write(n >>> 8 & 0xFF);
        this.write(n & 0xFF);
        this.w(2);
    }
    
    public final void writeLong(final long n) {
        this.w[0] = (byte)(n >>> 56);
        this.w[1] = (byte)(n >>> 48);
        this.w[2] = (byte)(n >>> 40);
        this.w[3] = (byte)(n >>> 32);
        this.w[4] = (byte)(n >>> 24);
        this.w[5] = (byte)(n >>> 16);
        this.w[6] = (byte)(n >>> 8);
        this.w[7] = (byte)n;
        this.write(this.w, 0, 8);
        this.w(8);
    }
    
    public final void writeBoolean(final boolean b) {
        this.write(b ? 1 : 0);
        this.w(1);
    }
    
    public final synchronized void write(final byte[] array, final int n, final int n2) {
        if ((n | n2 | array.length - (n2 + n) | n + n2) < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < n2; ++i) {
            this.write(array[n + i]);
        }
        this.w(n2);
    }
    
    public final void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    public final void writeBytes(final String s) {
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            this.write((byte)s.charAt(i));
        }
        this.w(length);
    }
    
    public final void writeChars(final String s) {
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            this.write(char1 >>> 8 & '\u00ff');
            this.write(char1 & '\u00ff');
        }
        this.w(length << 1);
    }
    
    public final void writeUTF(String s) {
        final int length = (s = s).length();
        int n = 0;
        final char[] array = new char[length];
        int n2 = 0;
        s.getChars(0, length, array, 0);
        for (int i = 0; i < length; ++i) {
            final char c;
            if ((c = array[i]) > '\0' && c <= '\u007f') {
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
        final byte[] array3;
        final byte[] array2 = array3 = new byte[n + 2];
        final int n3 = 0;
        ++n2;
        array2[n3] = (byte)(n >>> 8);
        final byte[] array4 = array3;
        final int n4 = 1;
        ++n2;
        array4[n4] = (byte)n;
        for (int j = 0; j < length; ++j) {
            final char c2;
            if ((c2 = array[j]) > '\0' && c2 <= '\u007f') {
                array3[n2++] = (byte)c2;
            }
            else if (c2 > '\u07ff') {
                array3[n2++] = (byte)('\u00e0' | (c2 >> 12 & '\u000f'));
                array3[n2++] = (byte)('\u0080' | (c2 >> 6 & '?'));
                array3[n2++] = (byte)('\u0080' | (c2 & '?'));
            }
            else {
                array3[n2++] = (byte)('\u00c0' | (c2 >> 6 & '\u001f'));
                array3[n2++] = (byte)('\u0080' | (c2 & '?'));
            }
        }
        super.write(array3);
    }
}
