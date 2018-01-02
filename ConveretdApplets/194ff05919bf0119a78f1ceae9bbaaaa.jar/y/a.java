// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInputStream;

public final class a
{
    public static short a;
    
    public static int a(final byte[] array, int n) {
        int n2 = 0;
        for (int i = 0; i < 2; ++i, ++n) {
            n2 = (n2 << 8) + (array[n] & 0xFF);
        }
        return n2;
    }
    
    public static int a(final DataInputStream dataInputStream) {
        int byte1;
        if ((byte1 = dataInputStream.readByte()) < 0) {
            byte1 += 256;
        }
        return byte1;
    }
    
    public static int b(final byte[] array, int n) {
        int n2 = 0;
        for (int i = 0; i < 4; ++i, ++n) {
            n2 = (n2 << 8) + (array[n] & 0xFF);
        }
        return n2;
    }
    
    public static void a(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(n >> 8);
        array[n2 + 1] = (byte)n;
    }
    
    public static void a(final int n, final byte[] array) {
        array[3] = (byte)(n >> 24);
        array[4] = (byte)(n >> 16);
        array[5] = (byte)(n >> 8);
        array[6] = (byte)n;
    }
    
    static {
        y.a.a = -32768;
        Long.toString(Long.MIN_VALUE).length();
    }
}
