// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

public final class 10
{
    public static int a;
    public static short b;
    public static String c;
    static byte[] d;
    static int e;
    
    public static int ga(final byte[] array, int n) {
        int n2 = 0;
        for (int i = 0; i < 2; ++i, ++n) {
            n2 = (n2 << 8) + (array[n] & 0xFF);
        }
        return n2;
    }
    
    public static int ia(final byte[] array, int n) {
        int n2 = 0;
        for (int i = 0; i < 4; ++i, ++n) {
            n2 = (n2 << 8) + (array[n] & 0xFF);
        }
        return n2;
    }
    
    public static void ja(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(n >> 8);
        array[n2 + 1] = (byte)n;
    }
    
    public static void ka(final int n, final byte[] array, final int n2) {
        array[n2] = (byte)(n >> 24);
        array[n2 + 1] = (byte)(n >> 16);
        array[n2 + 2] = (byte)(n >> 8);
        array[n2 + 3] = (byte)n;
    }
    
    static {
        10.a = 65536;
        10.b = -32768;
        10.c = "      ";
        10.d = new byte[Long.toString(Long.MIN_VALUE).length()];
        10.e = 95;
    }
}
