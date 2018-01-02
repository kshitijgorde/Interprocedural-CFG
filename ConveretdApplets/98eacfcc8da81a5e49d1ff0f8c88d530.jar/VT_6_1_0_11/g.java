// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;
import java.io.InputStream;

public abstract class g
{
    public static final byte[] a;
    public static final byte[] b;
    public static final byte[] c;
    public static final byte[] d;
    public static final byte[] e;
    public static final byte[] f;
    public static final byte[] g;
    protected byte[] h;
    protected int i;
    
    protected void a(final InputStream inputStream) {
        final byte[] array = new byte[4];
        inputStream.read(array);
        this.i = a(array);
        inputStream.read(this.h = new byte[this.i]);
        this.a();
    }
    
    protected abstract void a();
    
    public abstract void a(final OutputStream p0);
    
    public final void b(final OutputStream outputStream) {
        this.a(outputStream);
        if (this.h != null) {
            outputStream.write(this.h);
        }
    }
    
    public abstract int a(final byte[] p0, final int p1);
    
    public final int b(final byte[] array, int a) {
        a = this.a(array, 0);
        System.arraycopy(this.h, 0, array, a + 0, this.h.length);
        return a += this.h.length;
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    GENERIC CHUNK\n")).append("  HEADER\n");
        sb.append("Chunk identifier(4): ").append("????").append("\n");
        sb.append("Chunk size (4):      ").append(this.i).append("\n");
        return sb.toString();
    }
    
    public static final boolean a(final byte[] array, final byte[] array2) {
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final int a(byte[] array) {
        return ((array = array)[0] & 0xFF) | (array[1] & 0xFF) << 8 | (array[2] & 0xFF) << 16 | (array[3] & 0xFF) << 24;
    }
    
    public static final short b(byte[] array) {
        return (short)(((array = array)[0] & 0xFF) | (array[1] & 0xFF) << 8);
    }
    
    public static final byte[] a(final int n) {
        final byte[] array;
        (array = new byte[4])[0] = (byte)n;
        array[1] = (byte)(n >>> 8 & 0xFF);
        array[2] = (byte)(n >>> 16 & 0xFF);
        array[3] = (byte)((n & 0xFF000000) >> 24);
        return array;
    }
    
    public static final byte[] a(final short n) {
        final byte[] array;
        (array = new byte[2])[0] = (byte)n;
        array[1] = (byte)(n >>> 8 & 0xFF);
        return array;
    }
    
    static {
        a = new byte[] { 102, 109, 116, 32 };
        b = new byte[] { 102, 97, 99, 116 };
        c = new byte[] { 99, 117, 101, 32 };
        d = new byte[] { 112, 108, 115, 116 };
        e = new byte[] { 105, 110, 115, 116 };
        f = new byte[] { 115, 109, 112, 108 };
        g = new byte[] { 100, 97, 116, 97 };
    }
}
