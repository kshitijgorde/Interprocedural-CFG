// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public abstract class bm
{
    public static int a(final byte[] array, final int n, final int n2, final long n3, final int n4, final int n5, final int n6, final byte[] array2) {
        a(array, n, "OggS");
        array[n + 4] = 0;
        array[n + 5] = (byte)n2;
        a(array, n + 6, n3);
        a(array, n + 14, n4);
        a(array, n + 18, n5);
        a(array, n + 22, 0);
        array[n + 26] = (byte)n6;
        System.arraycopy(array2, 0, array, n + 27, n6);
        return n6 + 27;
    }
    
    public static void a(final byte[] array, final int n, final int n2) {
        array[n] = (byte)(0xFF & n2);
        array[n + 1] = (byte)(0xFF & n2 >>> 8);
        array[n + 2] = (byte)(0xFF & n2 >>> 16);
        array[n + 3] = (byte)(0xFF & n2 >>> 24);
    }
    
    public static void a(final byte[] array, final int n, final long n2) {
        array[n] = (byte)(0xFFL & n2);
        array[n + 1] = (byte)(0xFFL & n2 >>> 8);
        array[n + 2] = (byte)(0xFFL & n2 >>> 16);
        array[n + 3] = (byte)(0xFFL & n2 >>> 24);
        array[n + 4] = (byte)(0xFFL & n2 >>> 32);
        array[n + 5] = (byte)(0xFFL & n2 >>> 40);
        array[n + 6] = (byte)(0xFFL & n2 >>> 48);
        array[n + 7] = (byte)(0xFFL & n2 >>> 56);
    }
    
    public static void a(final byte[] array, final int n, final String s) {
        final byte[] bytes = s.getBytes();
        System.arraycopy(bytes, 0, array, n, bytes.length);
    }
}
