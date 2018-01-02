// 
// Decompiled by Procyon v0.5.30
// 

public class y
{
    private static int a(final byte[] array, final int n) {
        return (array[n + 3] & 0xFF) | (array[n + 2] << 8 & 0xFF00) | (array[n + 1] << 16 & 0xFF0000) | (array[n + 0] << 24 & 0xFF000000);
    }
    
    private static void a(final int n, final byte[] array, final int n2) {
        array[n2 + 0] = (byte)(n >> 24 & 0xFF);
        array[n2 + 1] = (byte)(n >> 16 & 0xFF);
        array[n2 + 2] = (byte)(n >> 8 & 0xFF);
        array[n2 + 3] = (byte)(n >> 0 & 0xFF);
    }
    
    public static void a(final byte[] array, final int[] array2, final int n, final int n2, final int n3) {
        int a = a(array, n);
        int a2 = a(array, n + 4);
        for (int n4 = 930658718, i = n4 * n3; i != 0; i -= n4, a -= ((a2 << 4 ^ a2 >> 5) + a2 ^ i + array2[i & 0x3])) {
            a2 -= ((a << 4 ^ a >> 5) + a ^ i + array2[i >> 11 & 0x3]);
        }
        a(a, array, n);
        a(a2, array, n + 4);
    }
}
