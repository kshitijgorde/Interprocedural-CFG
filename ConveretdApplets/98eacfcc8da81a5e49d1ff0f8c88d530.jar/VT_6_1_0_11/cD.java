// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cD
{
    private static int[] a;
    
    public static int a(int n, final byte[] array, int i, int n2) {
        for (n2 += i; i < n2; ++i) {
            n = (n << 8 ^ cD.a[n >>> 24 ^ (array[i] & 0xFF)]);
        }
        return n;
    }
    
    static {
        cD.a = new int[256];
        for (int i = 0; i < cD.a.length; ++i) {
            int n = i << 24;
            for (int j = 0; j < 8; ++j) {
                if ((n & Integer.MIN_VALUE) != 0x0) {
                    n = (n << 1 ^ 0x4C11DB7);
                }
                else {
                    n <<= 1;
                }
            }
            cD.a[i] = n;
        }
    }
}
