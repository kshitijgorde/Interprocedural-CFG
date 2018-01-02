// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class e
{
    public static void a(int n, int n2, final float[] array) {
        final int[] array2 = new int[4];
        array2[0] = (n2 & 0x7) * 5;
        n2 >>= 3;
        array2[1] = (n2 & 0x7) * 5 + 1;
        n2 >>= 3;
        array2[2] = (n2 & 0x7) * 5 + 2;
        n2 >>= 3;
        final int n3 = n2 & 0x1;
        n2 >>= 1;
        array2[3] = (n2 & 0x7) * 5 + 3 + n3;
        for (int i = 0; i < 40; ++i) {
            array[i] = 0.0f;
        }
        for (int j = 0; j < 4; ++j) {
            final int n4 = n & 0x1;
            n >>= 1;
            if (n4 != 0) {
                array[array2[j]] = 1.0f;
            }
            else {
                array[array2[j]] = -1.0f;
            }
        }
    }
}
